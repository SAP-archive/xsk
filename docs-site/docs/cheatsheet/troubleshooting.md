---
title: Troubleshooting
---

Troubleshooting
===

## Insufficient Privilege
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

## Two-Factor Authentication
---

If the migration fails to load **Delivery Units** and you are sure that you've provide correct information, please check whether your account has `Two-Factor Authentication` (2FA/MFA) enabled.
`Two-Factor Authentication` is currently not supported, more info could be found [here](https://github.com/SAP/xsk/issues/625).
You can use technical user without `Two-Factor Authentication` (2FA/MFA) enabled to complete the migration.
