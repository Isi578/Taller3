package org.example.transport;

public class Plane extends Transport {

    public Plane(String id, String name, double baseFare) {
        super(id, name, baseFare);
    }

    @Override
    public String getType() {
        return "PLANE";
    }
}
