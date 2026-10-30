package org.example.command;

import org.example.model.Reservation;
import org.example.service.ReservationService;

import java.util.List;

public class ListReservationsCommand implements Command {

    private final ReservationService service;

    public ListReservationsCommand(ReservationService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        List<Reservation> reservations = service.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("[STATUS] No reservations found.");
            return;
        }
        System.out.println("[STATUS] Final reservation list:");
        for (Reservation reservation : reservations) {
            System.out.println("  - " + reservation);
        }
    }
}
