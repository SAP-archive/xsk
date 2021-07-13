/**
 * Created by SAP SE.
 */

class ObjectSet {
    
    constructor() {
        this._map = new Map();
    }

    [Symbol.iterator]() { 
        return this._map.values() 
    }


    forEach(callback, thisContext) {
        return this.values().forEach(callback, thisContext);
    }
    
    
    findIndex(callback, thisContext) {
        return this.values().findIndex(callback, thisContext);
    }
    

    add(item) {
        this._map.set(item.serialId, item);
    }   
    
    
    values() {
        return Array.from(this._map.values());
    }
 
    
    has(item) {
        return this._map.has(item.serialId);
    }
    
    
    get(item) {
        return this._map.get(item.serialId);
    }

    get size() {
        return this._map.size;
    }
    
    
    addAll(items) {
        for (let item of items) {
            this.add(item);
        }
    }
    
    
    get length() {
        return this.values().length;        
    }
    
          
}

module.exports = ObjectSet;