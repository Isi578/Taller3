package org.example;

import org.example.command.CancelReservationCommand;
import org.example.command.CommandInvoker;
import org.example.command.CreateReservationCommand;
import org.example.command.ListReservationsCommand;
import org.example.command.QueryReservationStatusCommand;
import org.example.command.ShowActivityHistoryCommand;
import org.example.observer.ActivityHistoryObserver;
import org.example.observer.CustomerNotificationObserver;
import org.example.observer.ReservationSubject;
import org.example.pricing.HighSeasonPricingStrategy;
import org.example.pricing.PricingStrategy;
import org.example.pricing.StandardPricingStrategy;
import org.example.service.ReservationService;
import org.example.transport.BusFactory;
import org.example.transport.PlaneFactory;
import org.example.transport.TrainFactory;
import org.example.transport.Transport;
import org.example.transport.TransportFactoryRegistry;

public class Main {

    public static void main(String[] args) {
        TransportFactoryRegistry transportRegistry = new TransportFactoryRegistry();
        transportRegistry.register("PLANE", new PlaneFactory());
        transportRegistry.register("TRAIN", new TrainFactory());
        transportRegistry.register("BUS", new BusFactory());

        ReservationSubject subject = new ReservationSubject();
        ActivityHistoryObserver historyObserver = new ActivityHistoryObserver();
        subject.attach(new CustomerNotificationObserver());
        subject.attach(historyObserver);

        ReservationService reservationService = new ReservationService(subject);
        CommandInvoker invoker = new CommandInvoker();

        PricingStrategy standardFare = new StandardPricingStrategy();
        PricingStrategy highSeasonFare = new HighSeasonPricingStrategy();

        System.out.println("=== Travel Agency Reservation System ===");
        System.out.println();

        System.out.println("--- Creating reservations ---");
        Transport plane = transportRegistry.create("PLANE", "PL-100", "Avianca Flight 100", 400.00);
        Transport train = transportRegistry.create("TRAIN", "TR-220", "Express Train 220", 120.00);
        Transport bus = transportRegistry.create("BUS", "BU-55", "National Bus 55", 40.00);

        CreateReservationCommand createPlane = new CreateReservationCommand(
                reservationService, "Ana Perez", plane, standardFare);
        CreateReservationCommand createTrain = new CreateReservationCommand(
                reservationService, "Carlos Ruiz", train, highSeasonFare);
        CreateReservationCommand createBus = new CreateReservationCommand(
                reservationService, "Maria Lopez", bus, standardFare);
        CreateReservationCommand createHighSeasonPlane = new CreateReservationCommand(
                reservationService, "Julian Soto", plane, highSeasonFare);

        invoker.execute(createPlane);
        invoker.execute(createTrain);
        invoker.execute(createBus);
        invoker.execute(createHighSeasonPlane);

        System.out.println();
        System.out.println("--- Invalid create attempt (no notification expected) ---");
        invoker.execute(new CreateReservationCommand(reservationService, "   ", bus, standardFare));

        String confirmedId = createPlane.getCreatedReservation().getId();

        System.out.println();
        System.out.println("--- Cancelling a valid reservation ---");
        invoker.execute(new CancelReservationCommand(reservationService, confirmedId));

        System.out.println();
        System.out.println("--- Invalid cancel attempts (no notification expected) ---");
        invoker.execute(new CancelReservationCommand(reservationService, confirmedId));
        invoker.execute(new CancelReservationCommand(reservationService, "non-existent-id"));
        invoker.execute(new CancelReservationCommand(reservationService, "   "));

        System.out.println();
        System.out.println("--- Querying reservation status ---");
        invoker.execute(new QueryReservationStatusCommand(reservationService, confirmedId));
        invoker.execute(new QueryReservationStatusCommand(reservationService,
                createTrain.getCreatedReservation().getId()));
        invoker.execute(new ListReservationsCommand(reservationService));

        System.out.println();
        System.out.println("--- Event history ---");
        invoker.execute(new ShowActivityHistoryCommand(historyObserver));
    }
}
