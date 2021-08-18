(function() {
    var websocket = null;

    function constructDestinationUrl() {
        var protocol = (window.location.protocol == 'http:') ? 'ws://' : 'wss://';
        var url = window.location.pathname.replace('index.html', '');
        url += (document.getElementById('radio_annotations').checked) ? 'calculatorAnno' : 'calculatorProg';
        return protocol + window.location.host + url;
    }

    function sendMessage(url, message) {
        if (websocket) {
            if (websocket.url === url) {
                websocket.send(message);
                return;
            } else {
                websocket.onclose = function() {
                };
                websocket.close();
            }
        }

        var Socket = window.WebSocket || window.MozWebSocket;
        if (!Socket) {
            alert('Your browser does not support web sockets');
            return;
        }
        websocket = new Socket(url);

        websocket.onopen = function(event) {
            websocket.send(message);
        };

        websocket.onmessage = function(event) {
            document.getElementById('textarea_output').value += event.data + '\n';
        };

        websocket.onerror = function(event) {
            document.getElementById('textarea_output').value += 'web socket error occured: ' + event + '\n';
        };

        websocket.onclose = function(event) {
            websocket = null;
            if (!event.wasClean) {
                var message = 'web socket closed: code = ' + event.code + ', reason = "' + event.reason + '"\n';
                if (event.code === 1006) {
                    message += 'Please check that the server is running on JDK 7.\n';
                }
                document.getElementById('textarea_output').value += message;
            }
        };
    }

    window.calculate = function(message) {
        if (!message || !message.length) {
            return;
        }
        sendMessage(constructDestinationUrl(), message);
    };
}());