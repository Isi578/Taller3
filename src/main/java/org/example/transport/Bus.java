package org.example.transport;

public class Bus extends Transport {

    public Bus(String id, String name, double baseFare) {
        super(id, name, baseFare);
    }

    @Override
    public String getType() {
        return "BUS";
    }
}
