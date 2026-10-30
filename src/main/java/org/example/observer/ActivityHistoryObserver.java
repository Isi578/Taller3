package org.example.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityHistoryObserver implements ReservationObserver {

    private final List<ReservationEvent> history = new ArrayList<>();

    @Override
    public void update(ReservationEvent event) {
        history.add(event);
        System.out.println("[HISTORY] Event recorded: " + event);
    }

    public List<ReservationEvent> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
