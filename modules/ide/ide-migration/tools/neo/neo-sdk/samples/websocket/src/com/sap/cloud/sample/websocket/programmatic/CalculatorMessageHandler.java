package com.sap.cloud.sample.websocket.programmatic;

import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;

import com.sap.cloud.sample.websocket.AbstractMessageHandler;

/**
 * This class implements a message handler for the programmatic server endpoint.
 */

public class CalculatorMessageHandler extends AbstractMessageHandler implements MessageHandler.Whole<String> {

    private RemoteEndpoint.Basic basicRemote;

    /**
     * Created the <code>CalculatorMessageHandler</code> object.
     *
     * @param basic represents the peer of the websocket in the conversation
     */
    public CalculatorMessageHandler(RemoteEndpoint.Basic basic) {
        this.basicRemote = basic;
    }

    /**
     * Handler method for messages received by the websocket server endpoint. Calculates and returns the
     * sum of the numbers received from the client.
     *
     * @param message the received message; the expected format is a list of numbers, separated by semicolons, e.g. 1;2;3
     */
    @Override
    public void onMessage(String message) {
        handleMessage(message, basicRemote);
    }

}
