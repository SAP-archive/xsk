const errorWhiteList = [
    'Illegal reflective access'
];

/**
 * @param errorMessage {string}
 * @return {boolean}
 */
function isErrorInWhiteList(errorMessage) {
    for (const error of errorWhiteList) {
        if (errorMessage.includes(error)) {
            return true;
        }
    }

    return false;
}

module.exports.isErrorInWhiteList = isErrorInWhiteList;