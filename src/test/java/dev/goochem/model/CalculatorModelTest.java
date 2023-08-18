package dev.goochem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorModelTest {

    private CalculatorModel calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalculatorModel();
    }

    @Test
    public void testAddition() {
        calculator.add(3, 5);
        int result = calculator.getCalculationValue();
        assertEquals(8, result);
    }

    @Test
    public void testSubtraction() {
        calculator.subtract(10, 4);
        int result = calculator.getCalculationValue();
        assertEquals(6, result);
    }

    @Test
    public void testMultiplication() {
        calculator.multiply(45, 3);
        int result = calculator.getCalculationValue();
        assertEquals(135, result);
    }

    @Test
    public void testDivision() {
        calculator.divide(9, 3);
        int result = calculator.getCalculationValue();
        assertEquals(3, result);
        }
    }


