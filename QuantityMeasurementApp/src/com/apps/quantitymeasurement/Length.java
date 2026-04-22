package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // ===== ENUM (UPDATED) =====
    public enum LengthUnit {
        FEET(12.0),           // 1 ft = 12 in
        INCHES(1.0),          // base unit
        YARDS(36.0),          // 1 yard = 36 in
        CENTIMETERS(0.393701); // 1 cm = 0.393701 in

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // ===== CONSTRUCTOR =====
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // ===== CONVERT TO BASE UNIT (INCHES) =====
    private double toBaseUnit() {
        double result = this.value * this.unit.getConversionFactor();

        // rounding to avoid floating precision issues
        return Math.round(result * 100000.0) / 100000.0;
    }

    // ===== COMPARE =====
    public boolean compare(Length other) {
        if (other == null) return false;

        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    // ===== EQUALS =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Length other = (Length) obj;
        return this.compare(other);
    }
}