package org.example.transport;

import org.example.exception.ValidationException;

public abstract class TransportFactory {

    public Transport createTransport(String id, String name, double baseFare) {
        if (id == null || id.isBlank()) {
            throw new ValidationException("Transport id is required.");
        }
        if (name == null || name.isBlank()) {
            throw new ValidationException("Transport name is required.");
        }
        if (baseFare <= 0) {
            throw new ValidationException("Base fare must be greater than zero.");
        }

        return buildTransport(id.trim(), name.trim(), baseFare);
    }

    protected abstract Transport buildTransport(
            String id, String name, double baseFare);
}