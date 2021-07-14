function MessageCategories() {}

// Technical issues, something did not work, correct
// and run again
MessageCategories.prototype.INFRASTRUCTURE = "INFRASTRUCTURE";

// in case manual migration is required
//
MessageCategories.prototype.VIEW_MIGRATION = "VIEW_MIGRATION";

// in case calculation view migratin fails due to 
// errors not covered by the above category
//
MessageCategories.prototype.XSJS = "XSJS";
MessageCategories.prototype.HDI = "HDI";
MessageCategories.prototype.SECURITY = "SECURITY";
MessageCategories.prototype.MIGRATION_PLUGIN = "MIGRATION_PLUGIN";
MessageCategories.prototype.UNSUPPORTED_FEATURE = "UNSUPPORTED_FEATURE";
MessageCategories.prototype.VALIDATION = "VALIDATION";

// Objects that need to be moved into the corrrect container
// like .properties, .xml, and .json
//
MessageCategories.prototype.TODO = "TODO";
MessageCategories.prototype.OTHERS = "OTHERS";
MessageCategories.prototype.DELETE = "DELETE";


// Translation object which need to be put into the correct
// container
// (also code snippets that may have to be changed)
//
MessageCategories.prototype.TRANSLATION = "TRANSLATION";

// Unknown file types that might be of no importance but 
// might also be important
// (user needs to decide what to do with them)
//
MessageCategories.prototype.UNKNOWN = "UNKNOWN";

MessageCategories.prototype.FATAL = "FATAL";
MessageCategories.prototype.ERROR = "ERROR";
MessageCategories.prototype.WARNING = "WARNING";
MessageCategories.prototype.INFO = "INFO";

MessageCategories.prototype.JS = "JS";

MessageCategories.prototype.getId = function (cat, num) {
    return cat + "_" + num.toString()
}

module.exports = new MessageCategories();
