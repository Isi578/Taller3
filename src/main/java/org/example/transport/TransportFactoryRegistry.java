package org.example.transport;

import org.example.exception.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class TransportFactoryRegistry {

    private final Map<String, TransportFactory> factories = new HashMap<>();

    public void register(String type, TransportFactory factory) {
        if (type == null || type.isBlank() || factory == null) {
            throw new ValidationException(
                    "Transport type and factory are required.");
        }

        factories.put(type.trim().toUpperCase(), factory);
    }

    public Transport create(
            String type, String id, String name, double baseFare) {
        if (type == null || type.isBlank()) {
            throw new ValidationException("Transport type is required.");
        }

        TransportFactory factory = factories.get(type.trim().toUpperCase());

        if (factory == null) {
            throw new ValidationException("Unknown transport type: " + type);
        }

        return factory.createTransport(id, name, baseFare);
    }
}