package dev.goochem.model;

public enum Operator implements Comparable<Operator> {

    ADDITION("+", 0),
    SUBTRACTION("-", 0),
    DIVISION("/", 5),
    MULTIPLICATION("*", 5);


    final int precedence;
    final String symbol;

    Operator(String symbol, int precedence) {
        this.symbol = symbol;
        this.precedence = precedence;
    }

    public int comparePrecedence(Operator operator) {
        return this.precedence - operator.precedence;
    }
}
