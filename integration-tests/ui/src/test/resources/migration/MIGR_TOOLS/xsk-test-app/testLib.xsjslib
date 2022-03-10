function prepareSelectStatement(user) {
    return "SELECT * FROM SAMPLE_USERS WHERE SESSION_CONTEXT('APPLICATIONUSER') = '" + user + "'";
}