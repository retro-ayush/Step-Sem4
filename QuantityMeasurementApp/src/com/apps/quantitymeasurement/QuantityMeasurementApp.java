package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(
            double v1, Length.LengthUnit u1,
            double v2, Length.LengthUnit u2) {

        return demonstrateLengthEquality(
                new Length(v1, u1),
                new Length(v2, u2)
        );
    }

    public static Length demonstrateLengthConversion(
            double value, Length.LengthUnit from, Length.LengthUnit to) {

        return new Length(value, from).convertTo(to);
    }

    public static Length demonstrateLengthConversion(
            Length length, Length.LengthUnit to) {

        return length.convertTo(to);
    }

    // ===== UC6 ADD =====
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println(demonstrateLengthAddition(l1, l2)); // 2.0 FEET
    }
}