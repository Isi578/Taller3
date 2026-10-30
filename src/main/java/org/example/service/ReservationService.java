package org.example.service;

import org.example.exception.ValidationException;
import org.example.model.Reservation;
import org.example.observer.ReservationEvent;
import org.example.observer.ReservationSubject;
import org.example.pricing.PricingStrategy;
import org.example.transport.Transport;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {

    private final Map<String, Reservation> reservations = new LinkedHashMap<>();
    private final ReservationSubject subject;

    public ReservationService(ReservationSubject subject) {
        this.subject = subject;
    }

    public Reservation createReservation(String customerName, Transport transport, PricingStrategy pricingStrategy) {
        validateCreate(customerName, transport, pricingStrategy);
        double finalCost = pricingStrategy.calculate(transport.getBaseFare());
        Reservation reservation = new Reservation(customerName.trim(), transport, finalCost);
        reservations.put(reservation.getId(), reservation);
        subject.notifyObservers(new ReservationEvent(ReservationEvent.EventType.CREATED, reservation));
        return reservation;
    }

    public Reservation cancelReservation(String reservationId) {
        Reservation reservation = findReservation(reservationId);
        reservation.cancel();
        subject.notifyObservers(new ReservationEvent(ReservationEvent.EventType.CANCELLED, reservation));
        return reservation;
    }

    public Reservation getReservation(String reservationId) {
        return findReservation(reservationId);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    private Reservation findReservation(String reservationId) {
        if (reservationId == null || reservationId.isBlank()) {
            throw new ValidationException("Reservation id is required.");
        }
        Reservation reservation = reservations.get(reservationId.trim());
        if (reservation == null) {
            throw new ValidationException("Reservation not found: " + reservationId);
        }
        return reservation;
    }

    private void validateCreate(String customerName, Transport transport, PricingStrategy pricingStrategy) {
        if (customerName == null || customerName.isBlank()) {
            throw new ValidationException("Customer name is required.");
        }
        if (transport == null) {
            throw new ValidationException("Transport is required.");
        }
        if (pricingStrategy == null) {
            throw new ValidationException("Pricing strategy is required.");
        }
    }
}
