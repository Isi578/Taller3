package org.example.pricing;

public class HighSeasonPricingStrategy implements PricingStrategy {

    private static final double INCREASE_RATE = 1.25;

    @Override
    public double calculate(double baseFare) {
        return baseFare * INCREASE_RATE;
    }

    @Override
    public String getName() {
        return "HIGH_SEASON";
    }
}
