---
title: Database User
---

SAP HANA (Cloud) Database User
===

## Overview
---

To perform a successful migration, you need database users in HANA on Neo and HANA Cloud.
We recommend that you create a new database user instead of the default ones (`SYSTEM` and `DBADMIN` respectively).

## Steps
---

### SAP HANA on Neo
To create a new database user in SAP HANA on Neo, execute the following statement:

```SQL
CREATE USER <XSK-USERNAME> PASSWORD <XSK-PASSWORD> NO FORCE_FIRST_PASSWORD_CHANGE;
GRANT SELECT ON _SYS_REPO.DELIVERY_UNITS TO <XSK-USERNAME>;
GRANT SELECT ON _SYS_REPO.ACTIVE_OBJECT TO <XSK-USERNAME>;
GRANT CATALOG READ TO <XSK-USERNAME>;
GRANT EXPORT TO <XSK-USERNAME>;
GRANT EXECUTE ON REPOSITORY_REST to <XSK-USERNAME>;
GRANT REPO.READ ON ".REPO_PACKAGE_ROOT" TO <XSK-USERNAME>;
```

### SAP HANA Cloud
To create a new database user in SAP HANA Cloud, go through the following steps:

=== "SAP HANA Cockpit"

    1. Navigate to the SAP HANA Cockpit for your SAP HANA Cloud instance.
    2. Switch to the **User Management** application.

        ![XSK - SAP HANA Cockpit - User Management](/img/setup/database-user/1-user-management.png)

    3. Choose the **+** button and then **Create User**.
    4. Provide the user details.

        ![XSK - SAP HANA Cockpit - Create User](/img/setup/database-user/2-create-user.png)

        !!! note

            Switch the **Force Password Change on Next Logon** option to **No**, so that the provided password for the migration database user won't be changed and could be used in the XSK setup steps.

    5. Switch to the **Privilege Assignment** application.

        ![XSK - SAP HANA Cockpit - Privilege Assignment](/img/setup/database-user/3-assign-privilege.png)

    6. Search for the newly created database user.
    7. From the **System Privileges** tab, choose **Edit** and then **Add**.
    8. Search for the `CREATE SCHEMA` privilege.
    9. Save the changes.

        ![XSK - SAP HANA Cockpit - Create Schema](/img/setup/database-user/4-create-schema-privileges.png)

    10. From the **Object Privileges** tab, choose **Edit**, then **Add**, and add the following object privileges:

        Object                                         | Schema  | Privilege
        ---------------------------------------------- | ------- | ---------
        **CREATE_CONTAINER_GROUP**                     | _SYS_DI | _EXECUTE_
        **GRANT_CONTAINER_GROUP_API_PRIVILEGES**       | _SYS_DI | _EXECUTE_
        **TT_API_PRIVILEGES**                          | _SYS_DI | _SELECT_
        **TT_FILESFOLDERS**                            | _SYS_DI | _SELECT_
        **TT_FILESFOLDERS_CONTENT**                    | _SYS_DI | _SELECT_
        **TT_FILESFOLDERS_PARAMETERS**                 | _SYS_DI | _SELECT_
        **TT_SCHEMA_PRIVILEGES**                       | _SYS_DI | _SELECT_
        **T_DEFAULT_CONTAINER_ADMIN_PRIVILEGES**       | _SYS_DI | _SELECT_
        **T_DEFAULT_CONTAINER_GROUP_ADMIN_PRIVILEGES** | _SYS_DI | _SELECT_
        **T_DEFAULT_DI_ADMIN_PRIVILEGES**              | _SYS_DI | _SELECT_
        **T_NO_PARAMETERS**                            | _SYS_DI | _SELECT_

    11. Save the changes.

        ![XSK - SAP HANA Cockpit - Object Privileges](/img/setup/database-user/5-create-object-privileges.png)

=== "SQL Console"

    1. Create the user:

        ```sql
        CREATE USER <XSK-USERNAME> PASSWORD <XSK-PASSWORD> NO FORCE_FIRST_PASSWORD_CHANGE SET USERGROUP DEFAULT;
        ```

        !!! note
            Replace the `<XSK-USERNAME>` placeholder with the username and `<XSK-PASSWORD>` with the password.

    2. Grant `CREATE SCHEMA` privileges:

        ```sql
        GRANT CREATE SCHEMA TO <XSK-USERNAME>;
        ```

        !!! note
            Replace the `<XSK-USERNAME>` placeholder with the username.

    3. Grant HDI administrator privileges:

        ```sql
        CREATE LOCAL TEMPORARY TABLE #PRIVILEGES LIKE _SYS_DI.TT_API_PRIVILEGES;

        INSERT INTO #PRIVILEGES (PRINCIPAL_NAME, PRIVILEGE_NAME, OBJECT_NAME) SELECT '<XSK-USERNAME>', PRIVILEGE_NAME, OBJECT_NAME FROM _SYS_DI.T_DEFAULT_DI_ADMIN_PRIVILEGES;
    
        CALL _SYS_DI.GRANT_CONTAINER_GROUP_API_PRIVILEGES('_SYS_DI', #PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);

        DROP TABLE #PRIVILEGES;
        ```

        !!! note
            Replace the `<XSK-USERNAME>` placeholder with the username.
