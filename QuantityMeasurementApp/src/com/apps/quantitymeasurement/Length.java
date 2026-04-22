package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // ===== EQUALITY =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Length that = (Length) o;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = that.unit.convertToBaseUnit(that.value);

        return Math.abs(thisBase - thatBase) < 0.01;
    }

    // ===== CONVERSION =====
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Length(converted, targetUnit);
    }

    // ===== ADDITION (UC6) =====
    public Length add(Length that) {
        return add(that, this.unit);
    }

    // ===== ADDITION WITH TARGET UNIT (UC7) =====
    public Length add(Length that, LengthUnit targetUnit) {
        if (that == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = that.unit.convertToBaseUnit(that.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}