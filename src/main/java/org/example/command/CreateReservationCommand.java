package org.example.command;

import org.example.model.Reservation;
import org.example.pricing.PricingStrategy;
import org.example.service.ReservationService;
import org.example.transport.Transport;

public class CreateReservationCommand implements Command {

    private final ReservationService service;
    private final String customerName;
    private final Transport transport;
    private final PricingStrategy pricingStrategy;
    private Reservation createdReservation;

    public CreateReservationCommand(ReservationService service, String customerName,
                                    Transport transport, PricingStrategy pricingStrategy) {
        this.service = service;
        this.customerName = customerName;
        this.transport = transport;
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public void execute() {
        createdReservation = service.createReservation(customerName, transport, pricingStrategy);
        System.out.println("[OK] Reservation created with " + pricingStrategy.getName()
                + " fare: " + createdReservation);
    }

    public Reservation getCreatedReservation() {
        return createdReservation;
    }
}
