package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // ===== ENUM =====
    public enum LengthUnit {
        FEET(12.0),   // 1 foot = 12 inches
        INCHES(1.0);  // base unit

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
        return this.value * this.unit.getConversionFactor();
    }

    // ===== COMPARE =====
    public boolean compare(Length other) {
        if (other == null) return false;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    // ===== EQUALS =====
    @Override
    public boolean equals(Object obj) {

        // same reference
        if (this == obj) return true;

        // null check
        if (obj == null) return false;

        // type check
        if (getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return this.compare(other);
    }
}