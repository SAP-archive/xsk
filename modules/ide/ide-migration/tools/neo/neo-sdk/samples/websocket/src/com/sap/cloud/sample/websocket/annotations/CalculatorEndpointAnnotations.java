package com.sap.cloud.sample.websocket.annotations;

import java.io.IOException;
import java.net.SocketException;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.sample.websocket.AbstractMessageHandler;

/**
 * This class implements a server endpoint for the calculator application using JSR 356 annotations.
 */

@ServerEndpoint("/calculatorAnno")
public class CalculatorEndpointAnnotations extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorEndpointAnnotations.class.getName());

    /**
     * Handler method for messages received by the websocket server endpoint. Calculates and returns the
     * sum of the numbers received from the client.
     *
     * @param session the websocket session
     * @param message the received message; the expected format is a list of numbers, separated by semicolons, e.g. 1;2;3
     */
    @OnMessage
    public void calculateSum(Session session, String message) {
        if (session.isOpen()) {
            Basic basicRemote = session.getBasicRemote();
            handleMessage(message, basicRemote);

            try {
                session.close();
            } catch (IOException ex) {
                LOGGER.warn("Error while closing session: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Handler method for errors which could occur in a connection to the websocket endpoint.
     *
     * @param t The error which occurred
     */
    @OnError
    public void handleError(Throwable t) {
        if (t instanceof SocketException) {
            if (t.getMessage().contains("Connection reset")) {
                LOGGER.warn("SocketException: " + t.getMessage() + "; probably the browser process got killed.");
                return;
            }
        }

        LOGGER.error("Error in websocket connection: " + t.getMessage(), t);
    }

}
