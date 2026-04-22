package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== UC4 TESTS (KEEP EXISTING) =====

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

    // ===== UC5 CONVERSION TESTS =====

    @Test
    public void convertFeetToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(12.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void convertInchesToFeet() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(24.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.FEET);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void convertYardsToFeet() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(2.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.FEET);

        assertEquals(new Length(6.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void convertCentimetersToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(2.54,
                        Length.LengthUnit.CENTIMETERS,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(1.0, Length.LengthUnit.INCHES), result);
    }

    // ===== OVERLOADED METHOD TEST =====

    @Test
    public void convertUsingOverloadedMethod() {
        Length yard = new Length(2.0, Length.LengthUnit.YARDS);

        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(yard,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(72.0, Length.LengthUnit.INCHES), result);
    }

    // ===== ROUND TRIP TEST =====

    @Test
    public void roundTripConversion() {
        Length original = new Length(5.0, Length.LengthUnit.FEET);

        Length converted = original
                .convertTo(Length.LengthUnit.INCHES)
                .convertTo(Length.LengthUnit.FEET);

        assertTrue(original.equals(converted));
    }

    // ===== ZERO & NEGATIVE =====

    @Test
    public void zeroConversion() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(0.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(0.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void negativeConversion() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(-1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(-12.0, Length.LengthUnit.INCHES), result);
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

    @Test
    public void invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, Length.LengthUnit.FEET);
        });
    }
}