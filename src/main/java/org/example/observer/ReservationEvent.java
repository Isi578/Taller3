package org.example.observer;

import org.example.model.Reservation;
import org.example.model.ReservationStatus;

import java.time.LocalDateTime;

public class ReservationEvent {

    public enum EventType {
        CREATED,
        CANCELLED
    }

    private final EventType type;
    private final Reservation reservation;
    private final ReservationStatus statusAtEvent;
    private final LocalDateTime timestamp;

    public ReservationEvent(EventType type, Reservation reservation) {
        this.type = type;
        this.reservation = reservation;
        this.statusAtEvent = reservation.getStatus();
        this.timestamp = LocalDateTime.now();
    }

    public EventType getType() {
        return type;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public ReservationStatus getStatusAtEvent() {
        return statusAtEvent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp + " | " + type
                + " | customer=" + reservation.getCustomerName()
                + " | reservationId=" + reservation.getId()
                + " | status=" + statusAtEvent;
    }
}
