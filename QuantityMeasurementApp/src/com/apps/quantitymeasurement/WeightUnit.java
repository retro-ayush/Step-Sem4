package com.apps.quantitymeasurement;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert to base unit (kg)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert from base unit (kg)
    public double convertFromBaseUnit(double baseValue) {
        return Math.round((baseValue / conversionFactor) * 100.0) / 100.0;
    }
}