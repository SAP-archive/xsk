---
title: Troubleshooting
---

Troubleshooting
===

## Insufficient Privilege
---

!!! failure "Error message"

    ```
    SAP DBTech JDBC: [258]: insufficient privilege: Detailed info for this error can be found with guid 'D59E64...'
    ```

To check the error code, execute the following script:

```sql
call SYS.GET_INSUFFICIENT_PRIVILEGE_ERROR_DETAILS ('<GUID in "insufficient privilege" error>', ?)
```

## Two Factor Authentication

If the migration failed on step 1 and you are sure that you've provide correct information and still have problem, please check whether your account has Two-Factor Authentication (2FA/MFA) enabled.
You can use technical user without Two-Factor Authentication (2FA/MFA) enabled.
More info could be found [here](https://github.com/SAP/xsk/issues/625).

