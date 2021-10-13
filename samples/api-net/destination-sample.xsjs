let net = $.net;

/*
Read service.xshttpdest inside the Demo package that contains:
host=http://localhost;
port=8080;
*/
let dest = new net.Destination("demo", "service");
// Check if the file has been read properly
$.response.setBody("Host: " +dest.host+ " Port: " +dest.port);