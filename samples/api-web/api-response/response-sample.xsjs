// array of cars and their colors
let cars = [
  {make: "mercedes", color: "red"},
  {make: "audi", color: "blue"},
  {make: "toyota", color: "red"},
  {make: "vw", color: "blue"}
]

// filter by color function
function filterCarsByColor(color) {
  return cars.filter(function (car) {
    return car.color === color;
  })
}

if ($.request.method === $.net.http.GET) {
  // get query parameter color
  let color = $.request.parameters.get("color");

  // handle some request operation
  if (color) {
    // filter by color if passed
    let filteredCars = filterCarsByColor(color);

    // send response with filtered cars by color
    $.response.contentType = "application/json";
    $.response.status = $.net.http.OK;
    $.response.setBody(JSON.stringify({
      "cars": filteredCars
    }));
  } else {
    // send response with all cars as color param is missing
    $.response.status = $.net.http.BAD_REQUEST;
    $.response.setBody(JSON.stringify({
      "cars": cars
    }));
  }
} else {
  // unsupported method
  $.response.status = $.net.http.NOT_FOUND;
  $.response.setBody(JSON.stringify({
    "error": "not found"
  }));
}