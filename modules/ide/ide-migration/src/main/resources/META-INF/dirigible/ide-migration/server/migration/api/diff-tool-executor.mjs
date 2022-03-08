import { configurations as config } from "@dirigible/core";
import { repository } from "@dirigible/platform";
import { MigrationToolExecutor } from "./migration-tool-executor";

const differPath = config.get("user.dir") + "/target/dirigible/resources-neo-sdk/differ.sh";

export class DiffToolService {

    constructor() {
        this.migrationToolExecutor = new MigrationToolExecutor();
    }

    diffFolders(firstFolderName, secondFolderName) {
        const script = `${differPath} ${firstFolderName} ${secondFolderName}`;

        const rawCommandResult = this.migrationToolExecutor.execute(script);

        const lines = rawCommandResult.split('\n');
        const rawDiffResult = lines
            .map(line => {
                const splits = line.split("===");

                if (!splits[0] && !splits[1]) {
                    return null;
                }

                splits[0] = splits[0] || "";
                splits[1] = splits[1] || "";

                const modificationType = splits[2] || "";
                let fileName = "";

                if (modificationType.startsWith("A")) {
                    splits[1] = splits[0];
                    splits[0] = "";
                    fileName = splits[1];
                } else if (modificationType.startsWith("R")) {
                    splits[0] = splits[0];
                    splits[1] = splits[1];
                    fileName = splits[1];
                } else if (modificationType.startsWith("M")) {
                    splits[0] = splits[0];
                    splits[1] = splits[0].replace("_unmodified", "");
                    fileName = splits[0];
                }

                const res = {
                    firstFolderFile: splits[0],
                    secondFolderFile: splits[1],
                    fileName: fileName
                }

                return res;
            })
            .filter(x => x != null);

        const root = this._getRepositoryRootPath();
        const diffResult = rawDiffResult.map(rawDiffResult => {
            const originalPath = rawDiffResult.firstFolderFile.replace(root, "");
            const original = (originalPath !== "") ? repository.getResource(originalPath).getText() : "";
            const modifiedPath = rawDiffResult.secondFolderFile.replace(root, "");
            const modified = (modifiedPath !== "") ? repository.getResource(modifiedPath).getText() : "";
            const file = rawDiffResult.fileName.replace(root, "").replace("_unmodified", "");
            const type = file.substring(file.lastIndexOf(".") + 1);

            return {
                original,
                modified,
                file,
                type
            }
        })

        return diffResult;
    }

    _getRepositoryRootPath() {
        const StaticObjects = Java.type("org.eclipse.dirigible.commons.config.StaticObjects");
        const repositoryRoot = StaticObjects.get(StaticObjects.REPOSITORY).getParameter("REPOSITORY_ROOT_FOLDER");
        return repositoryRoot;
    }
}