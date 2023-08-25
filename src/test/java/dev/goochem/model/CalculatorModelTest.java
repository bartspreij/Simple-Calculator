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
        double result = calculator.add(3, 5);
        assertEquals(8, result);
    }

    @Test
    public void testSubtraction() {
        double result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    public void testMultiplication() {
        double result = calculator.multiply(45, 3);
        assertEquals(135, result);
    }

    @Test
    public void testDivision() {
        double result = calculator.divide(9, 3);
        assertEquals(3, result);
        }
    }


