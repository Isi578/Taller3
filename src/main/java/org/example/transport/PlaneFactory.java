package org.example.transport;

public class PlaneFactory extends TransportFactory {

    @Override
    protected Transport buildTransport(String id, String name, double baseFare) {
        return new Plane(id, name, baseFare);
    }
}