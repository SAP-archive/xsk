package com.sap.cloud.sample.websocket;

import java.io.IOException;

import javax.websocket.RemoteEndpoint.Basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.sample.websocket.util.Calculator;

/**
 * This is a helper class for handling messages received by the websocket server endpoint. It is extended by both
 * the annotated server endpoint and the message handler for the programmatic server endpoint.
 */
public abstract class AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMessageHandler.class.getName());
    private static final long WAIT = 2000;

    /**
     * Handler method for messages received by the websocket server endpoint. Calculates and returns the
     * sum of the numbers received from the client.
     *
     * @param message the received message; the expected format is a list of numbers, separated by semicolons, e.g. 1;2;3
     * @param basicRemote represents the peer of the websocket in the conversation
     */
    protected void handleMessage(String message, Basic basicRemote) {
        String response = null;
        if (basicRemote != null) {
            try {
                basicRemote.sendText("Starting calculation...");

                try {
                    Thread.sleep(WAIT);
                } catch (InterruptedException e) {
                    LOGGER.debug("InterruptedException: " + e.getMessage(), e);
                }

                response = message.replace(';', '+').trim() + " = " + Calculator.calculateSum(message);
            } catch (IllegalArgumentException e) {
                LOGGER.error(e.getMessage(), e);
                response = "Error: " + e.getMessage();
            } catch (IOException e) {
                LOGGER.error("Error while sending message: " + e.getMessage(), e);
            }

            try {
                basicRemote.sendText(response);
            } catch (IOException e) {
                LOGGER.error("Error while sending response: " + e.getMessage(), e);
            }
        }
    }
}
