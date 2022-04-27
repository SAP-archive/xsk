const acorn = require("acornjs/acorn");

export class XSJSLibContentModifier {

  writeExportsToResource(resource, content) {
    const contentWithExports = this._getXSJSLibContentWithExports(content);
    resource.setText(contentWithExports);
    return contentWithExports;
  }

  _getXSJSLibContentWithExports(content) {
    content += "\n\n";
    this._getExports(content).forEach(e => content += "exports." + e + " = " + e + ";\n");
    return content;
  }

  _getExports(code) {
    const nodes = acorn.parse(code);

    const functionDeclarations = nodes.body.filter(e => e.type === "FunctionDeclaration")
      .map(e => e.id.name);

    const variableDeclarations = nodes.body.filter(e => e.type === "VariableDeclaration")
      .flatMap(e => e.declarations.filter(d => d.type === "VariableDeclarator")
        .flatMap(d => d.id.name));

    const exports = functionDeclarations.concat(variableDeclarations);

    return exports;
  }
}