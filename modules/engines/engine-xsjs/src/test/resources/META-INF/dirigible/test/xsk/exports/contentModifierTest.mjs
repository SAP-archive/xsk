import { assertEquals } from './utils/utils.mjs'
import { XSJSLibContentModifier } from '/exports/XSJSLibContentModifier.mjs'
import { repository } from '@dirigible-v4/platform'

function testContentModifier_AcornSimpleContentModification() {
  const modifier = new XSJSLibContentModifier();
  const input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  const expected = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  const actual = modifier._getXSJSLibContentWithExports(input);

  assertEquals(expected, actual,
  "Unexpected content from XSJSLibContentModifier::_getXSJSLibContentWithExports(). ");
}

function testContentModifier_AcornResourceContentModification() {
  const modifier = new XSJSLibContentModifier();
  const input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  const expected = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  const resource = repository.createResource("test.xsjslib", input, "text/html");
  modifier.writeExportsToResource(resource, input);
  const actual = resource.getText();
  resource.delete();

  assertEquals(expected, actual,
  "Unexpected content from XSJSLibContentModifier::writeExportsToResource(). ");
}

testContentModifier_AcornSimpleContentModification();
testContentModifier_AcornResourceContentModification();