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
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    // ===== BASE UNIT CONVERSION (INCHES) =====
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    // ===== COMPARE =====
    private boolean compare(Length that) {
        return Double.compare(this.convertToBaseUnit(),
                that.convertToBaseUnit()) == 0;
    }

    // ===== EQUALS =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Length that = (Length) o;
        return compare(that);
    }

    // ===== CONVERSION METHOD (CORE UC5) =====
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // Step 1: convert to base unit (inches)
        double baseValue = convertToBaseUnit();

        // Step 2: convert to target unit
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        // Optional rounding (2 decimal places)
        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Length(convertedValue, targetUnit);
    }

    // ===== toString =====
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}