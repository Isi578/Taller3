package org.example.command;

import org.example.model.Reservation;
import org.example.service.ReservationService;

public class QueryReservationStatusCommand implements Command {

    private final ReservationService service;
    private final String reservationId;

    public QueryReservationStatusCommand(
            ReservationService service, String reservationId) {
        this.service = service;
        this.reservationId = reservationId;
    }

    @Override
    public void execute() {
        Reservation reservation = service.getReservation(reservationId);

        System.out.println("[STATUS] " + reservation.getId()
                + " -> " + reservation.getStatus()
                + " | customer=" + reservation.getCustomerName()
                + " | cost=" + String.format("%.2f", reservation.getFinalCost()));
    }
}
