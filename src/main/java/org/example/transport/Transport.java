package org.example.transport;

public abstract class Transport {

    private final String id;
    private final String name;
    private final double baseFare;

    protected Transport(String id, String name, double baseFare) {
        this.id = id;
        this.name = name;
        this.baseFare = baseFare;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " [id=" + id + ", name=" + name + ", baseFare=" + baseFare + "]";
    }
}
