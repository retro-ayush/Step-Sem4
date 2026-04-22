package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ===== Equality =====
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // ===== Comparison using values =====
    public static boolean demonstrateLengthComparison(
            double v1, Length.LengthUnit u1,
            double v2, Length.LengthUnit u2) {

        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        return demonstrateLengthEquality(l1, l2);
    }

    // ===== Conversion (method 1) =====
    public static Length demonstrateLengthConversion(
            double value,
            Length.LengthUnit fromUnit,
            Length.LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    // ===== Conversion (method 2 – overloaded) =====
    public static Length demonstrateLengthConversion(
            Length length,
            Length.LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        // 1 ft → inches
        System.out.println(demonstrateLengthConversion(
                1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));

        // 2 yards → inches
        System.out.println(demonstrateLengthConversion(
                2.0, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES));

        // object conversion
        Length yard = new Length(2.0, Length.LengthUnit.YARDS);
        System.out.println(demonstrateLengthConversion(
                yard, Length.LengthUnit.INCHES));
    }
}