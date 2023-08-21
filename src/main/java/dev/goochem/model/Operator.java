package dev.goochem.model;

public enum Operator implements Comparable<Operator> {

    ADDITION('+', 0),
    SUBTRACTION('-', 0),
    DIVISION('/', 5),
    MULTIPLICATION('*', 5);


    final int precedence;
    final char symbol;

    Operator(char symbol, int precedence) {
        this.symbol = symbol;
        this.precedence = precedence;
    }

    public char getSymbol() {
        return symbol;
    }

    public int comparePrecedence(Operator operator) {
        return this.precedence - operator.precedence;
    }
}
