const acorn = require("acornjs/acorn");

export class XSJSLibContentModifier {

  writeExportsToResource(resource, content) {
    let contentWithExports = this._getXSJSLibContentWithExports(content);
    resource.setText(contentWithExports);
    return contentWithExports;
  }

  _getXSJSLibContentWithExports(content) {
    content += "\n\n";
    this._getExports(content).forEach(e => content += "exports." + e + " = " + e + ";\n");
    return content;
  }

  _getExports(code) {
    let nodes = acorn.parse(code);

    let functionDeclarations = nodes.body.filter(e => e.type === "FunctionDeclaration")
      .map(e => e.id.name);

    let variableDeclarations = nodes.body.filter(e => e.type === "VariableDeclaration")
      .flatMap(e => e.declarations.filter(d => d.type === "VariableDeclarator")
        .flatMap(d => d.id.name));

    let exports = functionDeclarations.concat(variableDeclarations);

    return exports;
  }
}