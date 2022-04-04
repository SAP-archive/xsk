var acorn = require("acornjs/acorn");

export class XSJSLibContentModifier {

    writeExportsToResource(resource, content) {
      var contentWithExports = this._getXSJSLibContentWithExports(content);
      resource.setText(contentWithExports);
      return contentWithExports;
    }
  
    _getXSJSLibContentWithExports(content) {
      content += "\n\n";
      this._getExports(content).forEach(e => content += "exports." + e + " = " + e + ";\n");
      return content;
    }
  
    _getExports(code) {
      var nodes = acorn.parse(code);
  
      var functionDeclarations = nodes.body.filter(e => e.type === "FunctionDeclaration")
        .map(e => e.id.name);
  
      var variableDeclarations = nodes.body.filter(e => e.type === "VariableDeclaration")
        .flatMap(e => e.declarations.filter(d => d.type === "VariableDeclarator")
          .flatMap(d => d.id.name));
  
      var exports = functionDeclarations.concat(variableDeclarations);
  
      return exports;
    }
  }