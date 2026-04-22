package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // ===== ENUM =====
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

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
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // ===== BASE UNIT =====
    private double toBaseUnit() {
        return value * unit.getConversionFactor(); // inches
    }

    // ===== EQUALS =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length other = (Length) o;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    // ===== CONVERT =====
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit null");

        double base = toBaseUnit();
        double converted = base / targetUnit.getConversionFactor();

        converted = Math.round(converted * 100.0) / 100.0;

        return new Length(converted, targetUnit);
    }

    // ===== ADD (UC6 CORE) =====
    public Length add(Length other) {
        if (other == null) throw new IllegalArgumentException("Other length null");

        // Step 1: convert both to base (inches)
        double sumBase = this.toBaseUnit() + other.toBaseUnit();

        // Step 2: convert back to THIS unit
        double result = sumBase / this.unit.getConversionFactor();

        // rounding
        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, this.unit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}