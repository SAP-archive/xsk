const isValidDomain = require('is-valid-domain');

const NEO_ACTIVE_OPTION = 'true';
const NEO_INACTIVE_OPTION = 'false';

function validateSnippetInputLength(value) {
    return value.length > 0 ? true : 'Enter at least 1 character';
}

class XsMigrationCodeSnippet {
    static SNIPPET_NAME = 'xsMigrationSnippet';

    static QUESTION = {
        DELIVERY_UNIT: 'deliveryUnit',
        HANA_HOST: 'hanaHost',
        HANA_SQL_PORT: 'hanaSqlPort',
        HANA_USER: 'hanaUser',
        HANA_PASSWD: 'hanaPasswd',
        NEO_TUNNEL_IS_ACTIVE: 'neoTunnelIsActive',
        NEO_TUNNEL_ACCOUNT: 'neoTunnelAccount',
        NEO_TUNNEL_HOST: 'neoTunnelHost',
        NEO_TUNNEL_USER: 'neoTunnelUser',
        NEO_TUNNEL_PASSWD: 'neoTunnelPasswd',
        NEO_TUNNEL_DB_NAME: 'neoTunnelDbName'
    };

    static createCodeSnippetQuestions() {
        const questions = [];

        const isNeoTunnelAnswerIsActive = function (answers) {
            // these lines below have been commented due to the neo tunnel usage is required by default
            // const neoTunnelIsActiveAnswer = answers[XsMigrationCodeSnippet.QUESTION.NEO_TUNNEL_IS_ACTIVE];
            // return neoTunnelIsActiveAnswer !== undefined && neoTunnelIsActiveAnswer === NEO_ACTIVE_OPTION;
            return true;
        }

        questions.push(
            {
                guiOptions: {
                    hint: 'The Delivery Unit name which is needed to be exported'
                },
                mandatory: true,
                required: true,
                type: 'input',
                name: this.QUESTION.DELIVERY_UNIT,
                message: 'Delivery Unit',
                default: process.env['DELIVERY_UNIT'] || '',
                validate: function(value, answers) {
                    return validateSnippetInputLength(value);
                }
            },
            // this gui option has been commented due to the neo tunnel usage is required by default
            // {
            //     guiOptions: {
            //         hint: 'Select true if the NEO Tunnel is required'
            //     },
            //     type: 'list',
            //     name: this.QUESTION.NEO_TUNNEL_IS_ACTIVE,
            //     message: 'NEO Tunnel is required',
            //     choices: [
            //         NEO_ACTIVE_OPTION,
            //         NEO_INACTIVE_OPTION
            //     ]
            // },
            {
                guiOptions: {
                    hint: 'The host of the NEO sub-account (for ex. eu2.hana.ondemand.com)'
                },
                mandatory: true,
                type: 'input',
                name: this.QUESTION.NEO_TUNNEL_HOST,
                message: 'NEO DB Tunnel Host Name',
                default: process.env['NEO_TUNNEL_HOST'] || '',
                when: function (answers) {
                    return isNeoTunnelAnswerIsActive(answers);
                },
                validate: function(value, answers) {
                    if (isNeoTunnelAnswerIsActive(answers)) {
                        const inputLengthValidation = validateSnippetInputLength(value);

                        if (typeof inputLengthValidation !== 'boolean') {
                            return inputLengthValidation;
                        }

                        return isValidDomain(value) ? true : 'The provided domain is not valid';
                    }
                    return true;
                }
            },
            {
                guiOptions: {
                    hint: 'The technical name of the NEO sub-account'
                },
                mandatory: true,
                type: 'input',
                name: this.QUESTION.NEO_TUNNEL_ACCOUNT,
                message: 'NEO DB Tunnel Subaccount',
                default: process.env['NEO_TUNNEL_ACCOUNT'] || '',
                when: function (answers) {
                    return isNeoTunnelAnswerIsActive(answers);
                },
                validate: function(value, answers) {
                    if (isNeoTunnelAnswerIsActive(answers)) {
                        return validateSnippetInputLength(value);
                    }
                    return true;
                }
            },
            {
                guiOptions: {
                    hint: 'The user of the NEO sub-account'
                },
                mandatory: true,
                type: 'input',
                name: this.QUESTION.NEO_TUNNEL_USER,
                message: 'NEO DB Tunnel Username',
                default: process.env['NEO_TUNNEL_USER'] || '',
                when: function (answers) {
                    return isNeoTunnelAnswerIsActive(answers);
                },
                validate: function(value, answers) {
                    if (isNeoTunnelAnswerIsActive(answers)) {
                        return validateSnippetInputLength(value);
                    }
                    return true;
                }
            },
            {
                guiOptions: {
                    hint: 'The password for the user of the NEO sub-account'
                },
                mandatory: true,
                type: 'password',
                mask: '*',
                name: this.QUESTION.NEO_TUNNEL_PASSWD,
                message: 'NEO DB Tunnel Password',
                default: process.env['NEO_TUNNEL_PASSWD'] || '',
                when: function (answers) {
                    return isNeoTunnelAnswerIsActive(answers);
                },
                validate: function(value, answers) {
                    if (isNeoTunnelAnswerIsActive(answers)) {
                        return validateSnippetInputLength(value);
                    }
                    return true;
                }
            },
            {
                guiOptions: {
                    hint: 'The DB or Schema name'
                },
                mandatory: true,
                type: 'input',
                name: this.QUESTION.NEO_TUNNEL_DB_NAME,
                message: 'NEO DB Tunnel Schema Name',
                default: process.env['NEO_TUNNEL_DB_NAME'] || '',
                when: function (answers) {
                    return isNeoTunnelAnswerIsActive(answers);
                },
                validate: function(value, answers) {
                    if (isNeoTunnelAnswerIsActive(answers)) {
                        return validateSnippetInputLength(value);
                    }
                    return true;
                }
            },
            // this gui option has been commented due to the neo tunnel usage is required by default
            // {
            //     guiOptions: {
            //         hint: 'The host of the NEO sub-account where the HANA DB is located (for ex. eu2.hana.ondemand.com)'
            //     },
            //     type: 'input',
            //     name: this.QUESTION.HANA_HOST,
            //     message: 'SAP HANA Host Name',
            //     default: process.env['HANA_HOST'] || '',
            //     when: function (answers) {
            //         return !isNeoTunnelAnswerIsActive(answers);
            //     },
            //     validate: function(value, answers) {
            //         if (!isNeoTunnelAnswerIsActive(answers)) {
            //             const inputLengthValidation = validateSnippetInputLength(value);
            //
            //             if (typeof inputLengthValidation !== 'boolean') {
            //                 return inputLengthValidation;
            //             }
            //
            //             return isValidDomain(value) ? true : 'The provided domain is not valid';
            //         }
            //
            //         return true;
            //     }
            // },
            // this gui option has been commented due to the neo tunnel usage is required by default
            // {
            //     guiOptions: {
            //         hint: 'The port of the HANA DB host'
            //     },
            //     type: 'input',
            //     name: this.QUESTION.HANA_SQL_PORT,
            //     message: 'SAP HANA SQL Port',
            //     default: process.env['HANA_SQL_PORT'] || '',
            //     when: function (answers) {
            //         return !isNeoTunnelAnswerIsActive(answers);
            //     },
            //     validate: function(value, answers) {
            //         if (!isNeoTunnelAnswerIsActive(answers)) {
            //             return validateSnippetInputLength(value);
            //         }
            //         return true;
            //     }
            // },
            {
                guiOptions: {
                    hint: 'The HANA DB user name'
                },
                mandatory: true,
                type: 'input',
                name: this.QUESTION.HANA_USER,
                message: 'SAP HANA Username',
                default: process.env['HANA_USER'] || '',
                validate: function(value, answers) {
                    return validateSnippetInputLength(value);
                }
            },
            {
                guiOptions: {
                    hint: 'The HANA DB password'
                },
                mandatory: true,
                type: 'password',
                mask: '*',
                name: this.QUESTION.HANA_PASSWD,
                message: 'SAP HANA Password',
                default: process.env['HANA_PASSWD'] || '',
                validate: function(value, answers) {
                    return validateSnippetInputLength(value);
                }
            }
        );

        return questions;
    }

    static getUserImportSnippet(context, onAnswers) {
        return {
            getMessages() {
                return {
                    title: 'XS Migration Configuration',
                    description: 'Migrate SAP HANA XS database artifacts from the Neo environment to the Cloud Foundry environment.',
                    applyButton: 'Start Migration'
                };
            },
            async getQuestions() {
                return XsMigrationCodeSnippet.createCodeSnippetQuestions();
            },
            async getWorkspaceEdit(answers) {
                if (onAnswers !== undefined) {
                    onAnswers(answers);
                }
                return Promise.resolve(undefined);
            }
        }
    }
}

module.exports = XsMigrationCodeSnippet;