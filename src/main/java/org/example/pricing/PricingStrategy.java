package org.example.pricing;

public interface PricingStrategy {

    double calculate(double baseFare);

    String getName();
}