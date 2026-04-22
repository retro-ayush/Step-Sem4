package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    // ===== ENUM TESTS =====

    @Test
    public void testEnum_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    public void testEnum_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    public void testEnum_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON);
    }

    @Test
    public void testEnum_CmToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON);
    }

    // ===== EQUALITY =====

    @Test
    public void testEquality_FeetEqualsInches() {
        assertTrue(new Length(1, LengthUnit.FEET)
                .equals(new Length(12, LengthUnit.INCHES)));
    }

    @Test
    public void testEquality_FeetNotEquals() {
        assertFalse(new Length(1, LengthUnit.FEET)
                .equals(new Length(10, LengthUnit.INCHES)));
    }

    // ===== CONVERSION =====

    @Test
    public void testConversion_FeetToInches() {
        Length result = new Length(1, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    // ===== ADDITION =====

    @Test
    public void testAddition_FeetPlusInches() {
        Length result = new Length(1, LengthUnit.FEET)
                .add(new Length(12, LengthUnit.INCHES));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_TargetInches() {
        Length result = new Length(1, LengthUnit.FEET)
                .add(new Length(12, LengthUnit.INCHES), LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_TargetYards() {
        Length result = new Length(1, LengthUnit.FEET)
                .add(new Length(12, LengthUnit.INCHES), LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), EPSILON);
    }

    // ===== EDGE CASES =====

    @Test
    public void testEdge_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    public void testEdge_NaNValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, LengthUnit.FEET);
        });
    }

    @Test
    public void testEdge_ZeroAddition() {
        Length result = new Length(5, LengthUnit.FEET)
                .add(new Length(0, LengthUnit.INCHES));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testEdge_NegativeAddition() {
        Length result = new Length(5, LengthUnit.FEET)
                .add(new Length(-2, LengthUnit.FEET), LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    // ===== COMMUTATIVITY =====

    @Test
    public void testAddition_Commutativity() {
        Length a = new Length(1, LengthUnit.FEET);
        Length b = new Length(12, LengthUnit.INCHES);

        double r1 = a.add(b, LengthUnit.YARDS).getValue();
        double r2 = b.add(a, LengthUnit.YARDS).getValue();

        assertEquals(r1, r2, EPSILON);
    }
}