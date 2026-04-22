package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== BASIC EQUALITY TESTS =====

    @Test
    public void testFeetEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testInchesEquality() {
        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInchesEquality() {
        Length ft = new Length(1.0, Length.LengthUnit.FEET);
        Length in = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(ft.equals(in));
    }

    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testCrossUnitInequality() {
        Length ft = new Length(1.0, Length.LengthUnit.FEET);
        Length in = new Length(10.0, Length.LengthUnit.INCHES);
        assertFalse(ft.equals(in));
    }

    // ===== YARD TESTS =====

    @Test
    public void yardEquals36Inches() {
        Length y = new Length(1.0, Length.LengthUnit.YARDS);
        Length in = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(y.equals(in));
    }

    @Test
    public void yardEquals3Feet() {
        Length y = new Length(1.0, Length.LengthUnit.YARDS);
        Length ft = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(y.equals(ft));
    }

    @Test
    public void yardNotEqualTo2Feet() {
        Length y = new Length(1.0, Length.LengthUnit.YARDS);
        Length ft = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(y.equals(ft));
    }

    // ===== CM TESTS =====

    @Test
    public void cmEqualsInches() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length in = new Length(0.393701, Length.LengthUnit.INCHES);
        assertTrue(cm.equals(in));
    }

    @Test
    public void cmNotEqualFeet() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length ft = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(cm.equals(ft));
    }

    // ===== TRANSITIVE PROPERTY =====

    @Test
    public void transitivePropertyTest() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    public void complexMultiUnitTest() {
        Length y = new Length(2.0, Length.LengthUnit.YARDS);
        Length ft = new Length(6.0, Length.LengthUnit.FEET);
        Length in = new Length(72.0, Length.LengthUnit.INCHES);

        assertTrue(y.equals(ft));
        assertTrue(ft.equals(in));
        assertTrue(y.equals(in));
    }

    // ===== EDGE CASES =====

    @Test
    public void nullComparison() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l.equals(null));
    }

    @Test
    public void sameReference() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void invalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    // ===== UC5 CONVERSION TESTS =====

    @Test
    public void convertFeetToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(3.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(36.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void convertYardsToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(2.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(72.0, Length.LengthUnit.INCHES), result);
    }

    // ===== UC6 ADDITION TESTS =====

    @Test
    public void addFeetAndFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void addFeetAndInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void addWithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length zero = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, zero);

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    // ===== UC7 TARGET UNIT ADDITION =====

    @Test
    public void addWithTargetFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, Length.LengthUnit.FEET);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void addWithTargetInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, Length.LengthUnit.INCHES);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void addWithTargetYards() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, Length.LengthUnit.YARDS);

        assertEquals(new Length(0.67, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void addWithTargetCm() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, Length.LengthUnit.CENTIMETERS);

        assertEquals(new Length(5.08, Length.LengthUnit.CENTIMETERS), result);
    }

    @Test
    public void addWithTargetNull() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp
                    .demonstrateLengthAddition(l1, l2, null);
        });
    }
}