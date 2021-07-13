/**
 * Created by SAP SE on 26.07.16.
 */

class Table{
    
    constructor(name, is_column_table, table_type, is_replica) {
        this.name = name;
        this._columns = new Map();
        this.is_column_table = is_column_table;
        this.table_type = table_type;
        this.is_replica = is_replica;
    }
    
   
    get columns(){
        
        var i = 0;
        var newColumn = {};
        
        this._columns.forEach(function(column){
            newColumn[i] = column;
            i++;
        });
        
        return newColumn;
    }
      
    
    
    addColumn(column){
        if(!this._columns.has(column.COLUMN_NAME)){
            this._columns.set(column.COLUMN_NAME, column);
        }
    }
    
    
    toJSON(){
        return {
            name: this.name,
            is_column_table: this.is_column_table,
            table_type: this.table_type,
            is_replica: this.is_replica,
            columns: this.columns
        }
    }
}

module.exports = Table;