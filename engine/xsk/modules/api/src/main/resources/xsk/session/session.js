var securityUser = require('security/v4/user');

exports.getUsername = function () {
    return securityUser.getName();
}

exports.hasAppPrivilege = function (privilegeName) {
    return securityUser.isInRole(privilegeName);
}

exports.hasSystemPrivilege = function (privilegeName) {
    return securityUser.isInRole(privilegeName);
}

exports.assertSystemPrivilege = function (privilegeName) {
    var hasPrivilege = securityUser.isInRole(privilegeName)
    if (!hasPrivilege) {
        throw new NoSuchPrivilegeException(privilegeName);
    }
}

exports.assertAppPrivilege = function (privilegeName) {
    var hasPrivilege = securityUser.isInRole(privilegeName)
    if (!hasPrivilege) {
        throw new NoSuchPrivilegeException(privilegeName);
    }
}

function NoSuchPrivilegeException(privilegeToCheck) {
    this.privilege = privilegeToCheck;
}