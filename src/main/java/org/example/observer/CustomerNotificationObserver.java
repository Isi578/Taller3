package org.example.observer;

public class CustomerNotificationObserver implements ReservationObserver {

    @Override
    public void update(ReservationEvent event) {
        String customer = event.getReservation().getCustomerName();
        System.out.println("[NOTIFICATION] Customer '" + customer + "' was notified: reservation "
                + event.getType() + " (" + event.getReservation().getId() + ").");
    }
}
