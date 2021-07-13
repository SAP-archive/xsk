/**
 * Created by SAP SE on 26.07.16.
 */


class Column{
    
    constructor(COLUMN_NAME, POSITION, DATA_TYPE_ID, DATA_TYPE_NAME, OFFSET, LENGTH, SCALE, IS_NULLABLE, DEFAULT_VALUE, COLLATION, COMMENTS, CS_DATA_TYPE_ID, CS_DATA_TYPE_NAME, DDIC_DATA_TYPE_ID, DDIC_DATA_TYPE_NAME, INDEX_TYPE, COLUMN_ID){
      
        this.COLUMN_NAME = COLUMN_NAME;
        this.POSITION = POSITION;
        this.DATA_TYPE_ID = DATA_TYPE_ID;
        this.DATA_TYPE_NAME = DATA_TYPE_NAME;
        this.OFFSET = OFFSET;
        this.LENGTH = LENGTH;
        this.SCALE = SCALE;
        this.IS_NULLABLE = IS_NULLABLE;
        this.DEFAULT_VALUE = DEFAULT_VALUE;
        this.COLLATION = COLLATION;
        this.COMMENTS = COMMENTS;
        this.CS_DATA_TYPE_ID = CS_DATA_TYPE_ID;
        this.CS_DATA_TYPE_NAME = CS_DATA_TYPE_NAME;
        this.DDIC_DATA_TYPE_ID = DDIC_DATA_TYPE_ID;
        this.DDIC_DATA_TYPE_NAME = DDIC_DATA_TYPE_NAME;
        this.INDEX_TYPE = INDEX_TYPE;
        this.COLUMN_ID = COLUMN_ID;
    }
    
    

   
    
}

module.exports = Column;