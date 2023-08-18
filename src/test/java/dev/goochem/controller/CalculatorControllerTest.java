package dev.goochem.controller;

import dev.goochem.model.CalculatorModel;
import dev.goochem.view.CalculatorView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorControllerTest {

    @Test
    void testTokenizeExpression() {
        CalculatorController calculatorController = new CalculatorController(new CalculatorModel(), new CalculatorView());

        List<String> tokens = calculatorController.tokenizeExpression("24+4*5+1");

        // Expected tokens based on the given expression
        List<String> expectedTokens = List.of("24", "+", "4", "*", "5", "+", "1");

        assertEquals(expectedTokens, tokens);
    }
}