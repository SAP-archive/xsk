// Zip byte array source
var source = [123, 34, 120, 115, 107, 46, 116, 120, 116, 34, 58, 34, 84, 104, 105, 115, 32, 105, 115, 32, 88, 83, 75, 34, 125];

var zip = new $.util.Zip({
  source: source
});

for (var entry in zip) {
  // Loop through zip entries and modify if needed
  if (entry === 'xsk.txt') {
    zip[entry] = 'XSK is great'
  }
}

// Download the zip file
$.response.status = $.net.http.OK;
$.response.contentType = 'application/zip';
$.response.headers.set('Content-Disposition', 'attachment; filename = test.zip');
$.response.setBody(zip.asArrayBuffer());