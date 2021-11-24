// Create a new zip object
var zip = new $.util.Zip();
// Set content to the zip object
zip['xsk.txt'] = 'This is XSK';

// Download the zip file
$.response.status = $.net.http.OK;
$.response.contentType = 'application/zip';
$.response.headers.set('Content-Disposition', 'attachment; filename = test.zip');
$.response.setBody(zip.asArrayBuffer());