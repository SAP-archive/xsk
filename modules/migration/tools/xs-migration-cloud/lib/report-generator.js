var path = require('path');
var fs = require('fs-extra');

var REPORT_DIR_NAME = '';

const TEMPLATES_FOLDER = 'templates';
const CUSTOMER_REPORT_FOLDER = 'customer_report';
const REPORT_FOLDER = 'report';
const REPORT_FILE = 'report.html';

function ReportContainerGenerator() {

    this.generateContainer = function (context) {
        
        fs.ensureDirSync(path.join(context.targetDir, context.config.directories['migration'], 'report'));
        fs.ensureDirSync(path.join(context.targetDir, context.config.directories['migration'], 'report', 'data'));
        
        const customerReportFolderPath = path.join(path.dirname(module.filename), '..', TEMPLATES_FOLDER, CUSTOMER_REPORT_FOLDER, REPORT_FOLDER);
        const customerReportFilePath = path.join(path.dirname(module.filename), '..', TEMPLATES_FOLDER, CUSTOMER_REPORT_FOLDER, REPORT_FILE);

        fs.copySync(customerReportFolderPath, path.join(context.targetDir, context.config.directories['migration'], REPORT_FOLDER));
        fs.copySync(customerReportFilePath, path.join(context.targetDir, REPORT_FILE));
    };

    this.properties = {
        rootFolder: REPORT_DIR_NAME
    }
}

module.exports = new ReportContainerGenerator();
