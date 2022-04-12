import { assertEquals } from './prettyAssert.mjs'
import { XSJSLibContentModifier } from '/exports/XSJSLibContentModifier.mjs'
import { repository } from '@dirigible-v4/platform'

function testContentModifier_AcornSimpleContentModification() {
  let modifier = new XSJSLibContentModifier();
  let input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  let expected = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  let actual = modifier._getXSJSLibContentWithExports(input);

  assertEquals(expected, actual,
  "Unexpected content from XSJSLibContentModifier::_getXSJSLibContentWithExports(). ");
}

function testContentModifier_AcornResourceContentModification() {
  let modifier = new XSJSLibContentModifier();
  let input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  let expected = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  let resource = repository.createResource("test.xsjslib", input, "text/html");
  modifier.writeExportsToResource(resource, input);
  let actual = resource.getText();
  resource.delete();

  assertEquals(expected, actual,
  "Unexpected content from XSJSLibContentModifier::writeExportsToResource(). ");
}

testContentModifier_AcornSimpleContentModification();
testContentModifier_AcornResourceContentModification();