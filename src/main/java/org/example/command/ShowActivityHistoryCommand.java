package org.example.command;

import org.example.observer.ActivityHistoryObserver;
import org.example.observer.ReservationEvent;

import java.util.List;

public class ShowActivityHistoryCommand implements Command {

    private final ActivityHistoryObserver historyObserver;

    public ShowActivityHistoryCommand(
            ActivityHistoryObserver historyObserver) {
        this.historyObserver = historyObserver;
    }

    @Override
    public void execute() {
        List<ReservationEvent> history = historyObserver.getHistory();

        if (history.isEmpty()) {
            System.out.println("[HISTORY] No events recorded.");
            return;
        }

        System.out.println("[HISTORY] Event history:");

        for (ReservationEvent event : history) {
            System.out.println("  - " + event);
        }
    }
}
