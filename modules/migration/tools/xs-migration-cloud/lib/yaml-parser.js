var yamlParser = {};
var yLib = require('js-yaml');
yamlParser.jsonData = null;
yamlParser.yamlData = null;
yamlParser.loadyamlContent = function(ymlContent) {
	yamlData = ymlContent;
	yamlParser.jsonData = yLib.load(yamlData);
	return yamlParser;
};
yamlParser.getjsonData = function() {
	return yamlParser.jsonData;
};
yamlParser.getYamlData = function() {
	return yamlParser.yamlData;
};
yamlParser.convertYamlData = function(json) {
	if (json) {
		return yLib.dump(json);
	}
	return yLib.dump(yamlParser.jsonData);
};

module.exports = yamlParser;
