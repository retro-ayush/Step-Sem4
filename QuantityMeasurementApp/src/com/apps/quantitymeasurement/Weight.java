package com.apps.quantitymeasurement;

import java.util.Objects;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 0.01;

    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Weight other = (Weight) obj;
        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBase() * 100));
    }

    // Convert to target unit
    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Weight(converted, targetUnit);
    }

    // Add (default → this unit)
    public Weight add(Weight other) {
        return add(other, this.unit);
    }

    // Add (with target unit)
    public Weight add(Weight other, WeightUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sumBase = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(result, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}