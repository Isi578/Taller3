package org.example.pricing;

public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(double baseFare) {
        return baseFare;
    }

    @Override
    public String getName() {
        return "STANDARD";
    }
}
