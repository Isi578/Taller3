package org.example.transport;

public class BusFactory extends TransportFactory {

    @Override
    protected Transport buildTransport(String id, String name, double baseFare) {
        return new Bus(id, name, baseFare);
    }
}
