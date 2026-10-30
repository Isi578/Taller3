package org.example.transport;

public class TrainFactory extends TransportFactory {

    @Override
    protected Transport buildTransport(String id, String name, double baseFare) {
        return new Train(id, name, baseFare);
    }
}