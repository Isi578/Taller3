package org.example.transport;

public class Train extends Transport {

    public Train(String id, String name, double baseFare) {
        super(id, name, baseFare);
    }

    @Override
    public String getType() {
        return "TRAIN";
    }
}
