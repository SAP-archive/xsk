import { tasks as tasksService, process as processService } from "@dirigible/bpm";
import { client as httpClient, rs } from "@dirigible/http";
import { database } from "@dirigible/db";
import { url } from "@dirigible/utils";
import { NeoDatabasesService } from "./neo-databases-service.mjs"
import { TrackService } from "./track-service.mjs";


rs.service()
    .resource('start-process-from-zip')
    .post(startProcessFromZip)
    .resource('start-process')
    .post(startProcess)
    .resource('continue-process')
    .post(continueProcess)
    .resource('get-process')
    .post(getProcessState)
    .resource("migrationsTrack")
    .post(getMigrations)
    .resource('list-databases')
    .post(listDatabases)
    .execute();

function startProcessFromZip(ctx, req, res) {
    const userDataJson = req.getJSON();
    const migrationTableIndex = _trackMigrationStart();
    const processInstanceId = processService.start("migrationProcess", {
        userData: JSON.stringify(userDataJson),
        migrationType: "FROM_LOCAL_ZIP",
        migrationIndex: migrationTableIndex
    });

    const response = {
        processInstanceId: processInstanceId
    };

    res.print(JSON.stringify(response));
}

function startProcess(ctx, req, res) {
    const userDataJson = req.getJSON();

    const migrationTableIndex = _trackMigrationStart();
    const processInstanceId = processService.start("migrationProcess", {
        migrationType: "FROM_HANA",
        userData: JSON.stringify(userDataJson),
        userJwtToken: userDataJson.userJwtToken,
        migrationIndex: migrationTableIndex
    });

    const response = {
        processInstanceId: processInstanceId,
    };
    res.print(JSON.stringify(response));
}

function _trackMigrationStart() {
    const trackService = new TrackService();
    trackService.addEntry("MIGRATION_STARTING");
    return trackService.getCurrentMigrationIndex();
}

function getJwtToken(host, username, password) {
    const encodedUsername = url.encode(username);
    const encodedPassword = url.encode(password);
    const jwtTokenServiceUrl = `https://oauthasservices.${host}/oauth2/api/v1/token?grant_type=password&username=${encodedUsername}&password=${encodedPassword}`;
    const jwtTokenResponse = httpClient.post(jwtTokenServiceUrl, {
        headers: [
            {
                name: "Content-Type",
                value: "application/x-www-form-urlencoded",
            },
        ],
    });

    const jwtTokenResponseJson = JSON.parse(jwtTokenResponse.text);
    return jwtTokenResponseJson;
}

function continueProcess(ctx, req, res) {
    const userDataJson = req.getJSON();

    const tasksJson = org.eclipse.dirigible.api.v3.bpm.BpmFacade.getTasks();
    const tasks = JSON.parse(tasksJson);
    for (const task of tasks) {
        if (task.processInstanceId === userDataJson.processInstanceId.toString()) {
            tasksService.completeTask(task.id, {
                userData: JSON.stringify(userDataJson),
            });
            break;
        }
    }
}

function getProcessState(ctx, req, res) {
    const userDataJson = req.getJSON();
    const processInstanceIdString = userDataJson.processInstanceId.toString();
    const migrationState = processService.getVariable(processInstanceIdString, "migrationState");
    const response = {
        migrationState: migrationState,
    };

    if (migrationState.endsWith("_FAILED")) {
        response.failed = true;
        const failureReason = processService.getVariable(processInstanceIdString, migrationState + "_REASON");
        if (failureReason) {
            response.failureReason = failureReason;
        }
    } else if (migrationState === "DATABASES_LISTED") {
        const databasesJson = processService.getVariable(processInstanceIdString, "databases");
        response.databases = JSON.parse(databasesJson);
    } else if (migrationState === "WORKSPACES_LISTED") {
        const workspacesJson = processService.getVariable(processInstanceIdString, "workspaces");
        const deliveryUnitsJson = processService.getVariable(processInstanceIdString, "deliveryUnits");
        const connectionId = processService.getVariable(processInstanceIdString, "connectionId");
        response.workspaces = JSON.parse(workspacesJson);
        response.deliveryUnits = JSON.parse(deliveryUnitsJson);
        response.connectionId = connectionId;
    } else if (migrationState === "MIGRATION_EXECUTED") {
        const diffViewData = processService.getVariable(processInstanceIdString, "diffViewData");
        response.diffViewData = JSON.parse(diffViewData);
    }

    res.print(JSON.stringify(response));
}

function getMigrations(ctx, request, response) {
    const connection = database.getConnection("local", "SystemDB");
    let migrationsData = { migrations: "empty" };
    try {
        let statement = connection.prepareStatement("SELECT * FROM XSK_MIGRATIONS");
        let resultSet = statement.executeQuery();
        migrationsData.migrations = resultSet.toJson();
        resultSet.close();
        statement.close();
    } catch (e) {
    } finally {
        connection.close();
    }
    response.print(migrationsData.migrations);
}

function listDatabases(ctx, request, response) {
    const userDataJson = request.getJSON();
    const neoData = userDataJson.neo;
    const subaccount = neoData.subaccount;
    const hostName = neoData.hostName;
    const username = neoData.username;
    const password = neoData.password;

    const tokenResponse = getJwtToken(hostName, username, password);
    if (tokenResponse.error) {
        response.setStatus(403);
        response.print(
            JSON.stringify({
                error: {
                    message: tokenResponse.error_description
                }
            })
        );
        return;
    }

    const userJwtToken = tokenResponse.access_token;

    const neoDatabasesService = new NeoDatabasesService();
    const databases = neoDatabasesService.getAvailableDatabases(subaccount, hostName, userJwtToken);

    const responseObject = {
        databases: databases,
        userJwtToken: userJwtToken
    };

    const responseObjectJson = JSON.stringify(responseObject);
    response.print(responseObjectJson);
}
