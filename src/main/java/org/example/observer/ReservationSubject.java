package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class ReservationSubject {

    private final List<ReservationObserver> observers = new ArrayList<>();

    public void attach(ReservationObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(ReservationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ReservationEvent event) {
        for (ReservationObserver observer : observers) {
            observer.update(event);
        }
    }
}
