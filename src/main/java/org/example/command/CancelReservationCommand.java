package org.example.command;

import org.example.model.Reservation;
import org.example.service.ReservationService;

public class CancelReservationCommand implements Command {

    private final ReservationService service;
    private final String reservationId;

    public CancelReservationCommand(
            ReservationService service, String reservationId) {
        this.service = service;
        this.reservationId = reservationId;
    }

    @Override
    public void execute() {
        Reservation reservation = service.cancelReservation(reservationId);
        System.out.println("[OK] Reservation cancelled: " + reservation);
    }
}
