package com.sap.cloud.sample.websocket.programmatic;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

/**
 * This class implements a programmatic server endpoint for the calculator application.
 */
public class CalculatorEndpoint extends Endpoint {

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        session.addMessageHandler(new CalculatorMessageHandler(session.getBasicRemote()));
    }

}
