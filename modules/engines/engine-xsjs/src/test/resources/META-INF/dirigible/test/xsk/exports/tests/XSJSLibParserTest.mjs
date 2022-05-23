import { assertEquals } from '../utils/utils.mjs'
import { XSJSLibParser } from '/exports/XSJSLibParser.mjs'
import { repository } from '@dirigible-v4/platform'

function testContentModifier_AcornSimpleContentModification() {
  const parser = new XSJSLibParser();
  const input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  const expected = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  const actual = parser.appendExports(input);

  assertEquals(expected, actual,
  "Unexpected content from XSJSLibParser::appendExports(). ");
}

testContentModifier_AcornSimpleContentModification();