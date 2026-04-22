package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

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

    // ===== MULTI-UNIT =====

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
}