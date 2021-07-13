/**
 * Created by SAP SE.
 */


class Containers {
    
    
    constructor(){
        this._containers = new Map();
    }
    
    
    addContainer(container) {
        this._containers.set(container.name, container);
    }
    
    
    getContainerByName(name) {
        return this._containers.get(name);
    }
    
    
    values() {
        return this._containers.values();
    }
    
    
    getContainerForExt(ext) {
        for(let container of this._containers.values()) {
            if (container.extensions.includes(ext)){
                return container;
            }
        }
        
        return this.getContainerByName('todo');
    }
}

module.exports = Containers;