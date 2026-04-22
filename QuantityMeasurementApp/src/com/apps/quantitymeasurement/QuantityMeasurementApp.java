package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ===== WEIGHT METHODS =====

    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        return w1.equals(w2);
    }

    public static boolean demonstrateWeightComparison(
            double v1, WeightUnit u1,
            double v2, WeightUnit u2) {

        return new Weight(v1, u1).equals(new Weight(v2, u2));
    }

    public static Weight demonstrateWeightConversion(
            double value, WeightUnit from, WeightUnit to) {

        return new Weight(value, from).convertTo(to);
    }

    public static Weight demonstrateWeightConversion(
            Weight weight, WeightUnit to) {

        return weight.convertTo(to);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
        return w1.add(w2);
    }

    public static Weight demonstrateWeightAddition(
            Weight w1, Weight w2, WeightUnit target) {

        return w1.add(w2, target);
    }

    // ===== MAIN =====

    public static void main(String[] args) {

        Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000, WeightUnit.GRAM);

        System.out.println("Equality: " + w1.equals(w2));

        Weight converted = w1.convertTo(WeightUnit.POUND);
        System.out.println("Converted: " + converted);

        Weight sum = w1.add(w2);
        System.out.println("Addition: " + sum);
    }
}