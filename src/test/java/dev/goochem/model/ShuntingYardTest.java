package dev.goochem.model;

import dev.goochem.controller.CalculatorController;
import dev.goochem.view.CalculatorView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.goochem.model.ShuntingYard.shuntingYard;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShuntingYardTest {

    CalculatorController controller = new CalculatorController(new CalculatorModel(), new CalculatorView());

    @Test
    public void test1() {

        List<String> given = controller.tokenizeExpression("1+2*3/45+6");
        List<String> expected = List.of("1", "2", "3", "*", "45", "/", "+", "6", "+");
        List<String> computed = shuntingYard(given);

        System.out.println("infix:" + given);
        System.out.println("rpn (expected):" + expected);
        System.out.println("rpn (computed):" + computed);

        assertEquals(computed, expected);
    }

    @Test
    public void test2() {
        List<String> given = controller.tokenizeExpression("24+4*5+1");
        List<String> expected = List.of("24", "4", "5", "*", "+", "1", "+");
        List<String> computed = shuntingYard(given);

        System.out.println("infix:" + given);
        System.out.println("rpn (expected):" + expected);
        System.out.println("rpn (computed):" + computed);

        assertEquals(computed, expected);
    }
}
