package com.sap.cloud.sample.websocket;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

import com.sap.cloud.sample.websocket.programmatic.CalculatorEndpoint;

/**
 * This class specifies which endpoints will be deployed. In the case of programmatic endpoints the
 * endpoint object is explicitly created, while for annotated endpoints the creation is performed by the server
 */
public class CalculatorServerApplicationConfig implements ServerApplicationConfig {

    /**
     * Specifies all annotated endpoints will to be deployed. Actually it will be only
     * one - {@link CalculatorEndpointAnnotations}
     */
    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return scanned;
    }

    /**
     * Creates an endpoint for the {@link CalculatorEndpoint} and specifies this endpoint to be deployed.
     */
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        Set<ServerEndpointConfig> hashSet = new HashSet<ServerEndpointConfig>();
        if (endpointClasses.contains(CalculatorEndpoint.class)) {
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(CalculatorEndpoint.class,
                    "/calculatorProg").build();
            hashSet.add(config);
        }

        return hashSet;
    }

}
