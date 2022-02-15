---
title: Troubleshooting
---

Troubleshooting
===

## Insufficient Privileges
---

The bellow error message is related to insufficient privileges:

!!! failure "Error message"

    ```
    Insufficient privilege: Detailed info for this error can be found with guid 'D59E64...'
    ```

To check the error code, execute the following script:

```sql
call SYS.GET_INSUFFICIENT_PRIVILEGE_ERROR_DETAILS ('<GUID>', ?)
```

## Grant HDI Privileges
---

To grant HDI priviliges to the HANA database user, execute the following script:

```sql
CREATE LOCAL TEMPORARY TABLE #PRIVILEGES LIKE _SYS_DI.TT_API_PRIVILEGES;

INSERT INTO #PRIVILEGES (PRINCIPAL_NAME, PRIVILEGE_NAME, OBJECT_NAME)
SELECT '<HANA-USERNAME>', PRIVILEGE_NAME, OBJECT_NAME FROM _SYS_DI.T_DEFAULT_DI_ADMIN_PRIVILEGES;

CALL _SYS_DI.GRANT_CONTAINER_GROUP_API_PRIVILEGES('_SYS_DI', #PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);
DROP TABLE #PRIVILEGES;
```

!!! note

    Replace the `<HANA-USERNAME>` placeholder with the HANA database user, used for the migration

## Two-Factor Authentication
---

If the migration fails to load **Delivery Units** and you are sure that you've provide correct information, please check whether your account has `Two-Factor Authentication` (2FA/MFA) enabled.
`Two-Factor Authentication` is currently not supported, more info could be found [here](https://github.com/SAP/xsk/issues/625).
You can use technical user without `Two-Factor Authentication` (2FA/MFA) enabled to complete the migration.

## Drop HDI Container
---

```sql
CREATE LOCAL TEMPORARY COLUMN TABLE #DROP_CONTAINER_PARAMETERS LIKE _SYS_DI.TT_PARAMETERS;
INSERT INTO #DROP_CONTAINER_PARAMETERS ( KEY, VALUE ) VALUES ( 'ignore_work', 'true' );
INSERT INTO #DROP_CONTAINER_PARAMETERS ( KEY, VALUE ) VALUES ( 'ignore_deployed', 'true' );
CALL _SYS_DI#<HDI-Container-Group>.DROP_CONTAINER('<HDI-Container-Name>', #DROP_CONTAINER_PARAMETERS, ?, ?, ?);
DROP TABLE #DROP_CONTAINER_PARAMETERS; 
```

!!! note

    Replace the following placeholders:

    - `<HDI-Container-Group>` - the HDI Container Group
    - `<HDI-Container-Name>` - the HDI Container Name
