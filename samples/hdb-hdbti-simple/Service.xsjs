if ($.request.queryPath === '/status') {
  $.response.status = $.net.http.OK;
  $.response.setBody('OK');
}
else if ($.request.queryPath === '/productsOrders') {
  var db = $.hdb;

  let connection = null;
  try {
    connection = db.getConnection();

    let resultSet = connection.executeQuery('SELECT * FROM "XSK_SAMPLES_HDB_HDBTI_SIMPLE"."hdb-hdbti-simple::Products.Orders"');
    let iterator = resultSet.getIterator();
    let result = [];

    while (iterator.next()) {
      var currentRow = iterator.value();
      result.push(currentRow);
    }

    $.response.status = $.net.http.OK;
    $.response.setBody(JSON.stringify(result));
  } catch (e) {
    $.response.setBody('Error while getting data: ' + e.message);
  } finally {
    if (connection) {
      connection.close();
    }
  }
}
else {
  $.response.status = $.net.http.NOT_FOUND;
  $.response.setBody('WRONG PATH');
}



