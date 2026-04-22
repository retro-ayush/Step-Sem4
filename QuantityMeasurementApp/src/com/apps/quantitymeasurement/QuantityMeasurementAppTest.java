package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    // ===== WEIGHT TESTS =====

    @Test
    public void kilogramEquals1000Grams() {
        assertTrue(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(1000, WeightUnit.GRAM)));
    }

    @Test
    public void poundEquals453Point592Grams() {
        assertTrue(new Weight(1, WeightUnit.POUND)
                .equals(new Weight(453.592, WeightUnit.GRAM)));
    }

    @Test
    public void kilogramNotEqualToPound() {
        assertFalse(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(1, WeightUnit.POUND)));
    }

    @Test
    public void convertKilogramToGram() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void convertPoundToKilogram() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void addKilogramAndGram() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void addWithTargetGram() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    public void additionCommutativity() {
        Weight a = new Weight(1, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000, WeightUnit.GRAM);

        double r1 = a.add(b, WeightUnit.GRAM).getValue();
        double r2 = b.add(a, WeightUnit.GRAM).getValue();

        assertEquals(r1, r2, EPSILON);
    }

    @Test
    public void nullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(1, null);
        });
    }

    @Test
    public void zeroAddition() {
        Weight result = new Weight(5, WeightUnit.KILOGRAM)
                .add(new Weight(0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), EPSILON);
    }
}