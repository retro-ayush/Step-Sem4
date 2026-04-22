package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== UC6 ADDITION TESTS =====

    @Test
    public void addFeetAndFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void addFeetAndInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void addInchesAndFeet() {
        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void addYardAndFeet() {
        Length y = new Length(1.0, Length.LengthUnit.YARDS);
        Length ft = new Length(3.0, Length.LengthUnit.FEET);

        Length result = y.add(ft);

        assertEquals(new Length(2.0, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void addCmAndInch() {
        Length cm = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length in = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = cm.add(in);

        assertEquals(new Length(5.08, Length.LengthUnit.CENTIMETERS), result);
    }

    // ===== COMMUTATIVITY =====

    @Test
    public void additionIsCommutative() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    // ===== ZERO =====

    @Test
    public void addZero() {
        Length l = new Length(5.0, Length.LengthUnit.FEET);
        Length zero = new Length(0.0, Length.LengthUnit.INCHES);

        assertEquals(l, l.add(zero));
    }

    // ===== NEGATIVE =====

    @Test
    public void addNegative() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), l1.add(l2));
    }

    // ===== NULL =====

    @Test
    public void addNullThrows() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            l.add(null);
        });
    }
}