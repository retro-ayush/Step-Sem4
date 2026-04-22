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

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    // ===== CONSTRUCTOR =====
    public Length(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // ===== BASE (INCHES) =====
    private double toBase() {
        return value * unit.getFactor();
    }

    // ===== COMMON HELPER (USED BY UC6 + UC7) =====
    private Length addAndConvert(Length other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid input");

        double sumBase = this.toBase() + other.toBase();

        double result = sumBase / targetUnit.getFactor();
        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, targetUnit);
    }

    // ===== UC6 =====
    public Length add(Length other) {
        return addAndConvert(other, this.unit);
    }

    // ===== UC7 =====
    public Length add(Length other, LengthUnit targetUnit) {
        return addAndConvert(other, targetUnit);
    }

    // ===== CONVERT =====
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException();

        double result = toBase() / targetUnit.getFactor();
        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, targetUnit);
    }

    // ===== EQUALS =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length other = (Length) o;
        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}