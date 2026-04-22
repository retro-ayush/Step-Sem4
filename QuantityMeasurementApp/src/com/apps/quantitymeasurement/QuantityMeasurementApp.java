package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(
            double v1, LengthUnit u1,
            double v2, LengthUnit u2) {

        return new Length(v1, u1).equals(new Length(v2, u2));
    }

    public static Length demonstrateLengthConversion(
            double value, LengthUnit from, LengthUnit to) {

        return new Length(value, from).convertTo(to);
    }

    public static Length demonstrateLengthConversion(
            Length length, LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    // UC6
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    // UC7
    public static Length demonstrateLengthAddition(
            Length l1, Length l2, LengthUnit targetUnit) {

        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Addition (FEET): " + l1.add(l2));
        System.out.println("Addition (INCHES): " + l1.add(l2, LengthUnit.INCHES));
        System.out.println("Addition (YARDS): " + l1.add(l2, LengthUnit.YARDS));
    }
}