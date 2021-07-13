/**
 * Created by andreas on 16.05.17.
 */


const xsjsContainerGenerator = require('./xsjs-generator');
const webContainerGenerator = require('./web-generator');
const dbContainerGenerator = require('./db-generator');
const todoContainerGenerator = require('./todo-generator');


class LazyContainerGenerator {
    
    static generateContainer (containerName, globalContext){
        if(globalContext.containersPresent.indexOf(containerName) < 0){
            if(containerName === 'db'){
                dbContainerGenerator.generateContainer(globalContext);
                globalContext.containersPresent.push('db');
            } else if(containerName === 'web'){
                webContainerGenerator.generateContainer(globalContext);
                globalContext.containersPresent.push('web');
            } else if(containerName === 'xsjs'){
                xsjsContainerGenerator.generateContainer(globalContext);
                globalContext.containersPresent.push('xsjs');
            } else if(containerName === 'todo'){
                todoContainerGenerator.generateContainer(globalContext);
                globalContext.containersPresent.push('todo');
            }
        }
    };
    
}

module.exports = LazyContainerGenerator;