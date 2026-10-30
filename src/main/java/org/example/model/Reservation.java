package org.example.model;

import org.example.exception.ValidationException;
import org.example.transport.Transport;

import java.util.UUID;

public class Reservation {

    private final String id;
    private final String customerName;
    private final Transport transport;
    private final double finalCost;
    private ReservationStatus status;

    public Reservation(String customerName, Transport transport, double finalCost) {
        this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.transport = transport;
        this.finalCost = finalCost;
        this.status = ReservationStatus.CONFIRMED;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Transport getTransport() {
        return transport;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void cancel() {
        if (status == ReservationStatus.CANCELLED) {
            throw new ValidationException("Reservation " + id + " is already cancelled.");
        }
        this.status = ReservationStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Reservation{"
                + "id='" + id + '\''
                + ", customer='" + customerName + '\''
                + ", transport=" + transport
                + ", finalCost=" + String.format("%.2f", finalCost)
                + ", status=" + status
                + '}';
    }
}
