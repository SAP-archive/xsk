---
title: Database User
---

HANA Database User
===

## Overview
---

To execute successful migration a database user is needed.
The default/admin `DBADMIN` database user is not recommended to be used, but rather new database user to be created.


## Steps
---

To create a new database user go through the following steps:

1. Navigate to the `SAP HANA Cockpit` for your `SAP HANA Cloud` instance.
2. Switch to the `User Management` application.

    ![XSK - Migration Architecture](/img/setup/database-user/1-user-management.png)

3. Click on the `+` button and select `Create User`
4. Provide the user details.

    ![XSK - Migration Architecture](/img/setup/database-user/2-create-user.png)

    !!! note

        Switch the `Force Password Change on Next Logon` option to `No`, so that the provided password for the migration database user won't be changed and could be used in the XSK setup steps.

5. Switch to the `Assign Priviliges` application.

    ![XSK - Migration Architecture](/img/setup/database-user/3-assign-privilege.png)

6. Search for the newly created database user.
7. From the `SYSTEM PRIVILEGES` tab click the `Edit` button and then the `Add` button.
8. Search for the `CREATE SCHEMA` privilege.
9. Save the changes.

    ![XSK - Migration Architecture](/img/setup/database-user/4-create-schema-privilege.png)
