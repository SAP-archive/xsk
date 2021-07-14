// --------------------------------------------------------------------------
//
// Error objects generated when accessing particular tables
//
// --------------------------------------------------------------------------

var mc = require('./message-categories');
var msgs = require('./messages');

var SYS_REPO_MESSAGE = msgs.getMessage(mc.HDI, 9);

var SYS_USERS_MESSAGE = msgs.getMessage(mc.SECURITY, 10);

var SYS_SQL_CONNECTION_MESSAGE = msgs.getMessage(mc.UNSUPPORTED_FEATURE, 5);

var REPOSITORY_REST_MESSAGE = msgs.getMessage(mc.HDI, 10);

var SCHEDULER_MESSAGE = {
    // implementation of scheduler has changed completely
}

// SYS.EFFECTIVE_PRIVILEGES
// SYS.EFFECTIVE_APPLICATION_PRIVILEGES

// M_DATABASE
// M_INIFILE_CONTENTS


var TABLE_MESSAGES = {

    "_SYS_REPO": {
        "DELIVERY_UNITS": SYS_REPO_MESSAGE,
        "ACTIVE_OBJECT" : SYS_REPO_MESSAGE,
        "ACTIVE_OBJECTCROSSREF": SYS_REPO_MESSAGE,
        "ACTIVE_CONTENT_TEXT": SYS_REPO_MESSAGE,
        "ACTIVE_CONTENT_TEXT_CONTENT": SYS_REPO_MESSAGE,
        "OBJECT_HISTORY": SYS_REPO_MESSAGE,
        "GRANT_ACTIVATED_ROLE": SYS_REPO_MESSAGE,
        "PACKAGE_CATALOG": SYS_REPO_MESSAGE,
        "REVOKE_ACTIVATED_ROLE": SYS_REPO_MESSAGE,
        "TEXT_ACCESSOR": SYS_REPO_MESSAGE,
        "GRANTED_PRIVILEGES": SYS_REPO_MESSAGE,
        "GRANT_ACTIVATED_ANALYTICAL_PRIVILEGE": SYS_REPO_MESSAGE,
        "REVOKE_ACTIVATED_ANALYTICAL_PRIVILEGE": SYS_REPO_MESSAGE
    },
    "SYS": {
        "USERS": SYS_USERS_MESSAGE,
        "USER_PARAMETERS": SYS_USERS_MESSAGE,
        "ROLES": SYS_USERS_MESSAGE,
        "GRANTED_ROLES": SYS_USERS_MESSAGE,
        "REPOSITORY_REST": REPOSITORY_REST_MESSAGE
    },

    "_SYS_XS": {
        "SQL_CONNECTIONS": SYS_SQL_CONNECTION_MESSAGE
    },

    "HANA_XS_BASE": {
        "sap.hana.xs.admin.jobs.server.db::SCHEDULES": SCHEDULER_MESSAGE
    }
};

module.exports = TABLE_MESSAGES;
