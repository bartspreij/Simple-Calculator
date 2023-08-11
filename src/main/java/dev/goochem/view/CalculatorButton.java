package dev.goochem.view;

public enum CalculatorButton {
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    DELETE("DEL"),
    AC("AC"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    MULTIPLY("*"),
    DIVIDE("/"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    ADD("+"),
    SUBTRACT("-"),
    ZERO("0"),
    DOT("."),
    EXP("EXP"),
    ANS("Ans"),
    EQUALS("=");

    private final String label;

    CalculatorButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }







}
