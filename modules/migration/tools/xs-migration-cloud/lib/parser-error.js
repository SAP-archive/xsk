/**
 * Created by SAP SE.
 */

class ParserError extends Error {
    
    constructor(message, error) {
        super(error);
      
        this.name = this.constructor.name;
        this._description = error.description;
        this._lineNumber = error.lineNumber;
        this._column = error.column;
        this._filename = error.filename;
        
        this._stack = error.stack;
      
    }
    
    toString() {
        return this._description + '\n' + 'at: line ' + this._lineNumber + ', column ' + this._column + '\nin: ' + this._filename;        
    }
}
    
    


module.exports = ParserError;