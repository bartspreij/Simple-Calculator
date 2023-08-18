package dev.goochem.model;

import java.util.*;

public class CalculatorModel {

    private double calculationValue;
    final static Map<String, Operator> OPS = new HashMap<>();

    static {
        // Fill map with all operators
        for (Operator operator : Operator.values()) {
            OPS.put(operator.symbol, operator);
        }
    }

    public void evaluateExpression(List<String> rpnTokens) {
        Stack<Double> stack = new Stack<>();

        for (String token : rpnTokens) {
            if (OPS.containsKey(token)) { // Token is operator, so we pop the last two numbers and apply the operator to them
                Operator operator = OPS.get(token);

                double secondNumber = stack.pop();
                double firstNumber = stack.pop();

                switch (operator) {
                    case ADDITION -> stack.push(add(firstNumber, secondNumber));
                    case SUBTRACTION -> stack.push(subtract(firstNumber, secondNumber));
                    case MULTIPLICATION -> stack.push(multiply(firstNumber, secondNumber));
                    case DIVISION -> stack.push(divide(firstNumber, secondNumber));
                    default -> throw new IllegalArgumentException("Unsupported operator" + operator);
                }

            } else { // Else add token (digit) to stack
                stack.push(Double.valueOf(token));
            }
        }
        // Last number left in the stack is the result
        calculationValue = stack.pop();
    }

    public double add(double first, double second) { return first + second; }

    public double subtract(double first, double second) { return first - second; }

    public double multiply(double first, double second) { return first * second; }

    public double divide(double first, double second) {
        if (second == 0) {
            throw new ArithmeticException("Can't divide by 0");
        }
        return first / second;
    }

    public double getCalculationValue() {
        return calculationValue;
    }
}
