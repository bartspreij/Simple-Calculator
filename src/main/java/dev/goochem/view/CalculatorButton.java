package dev.goochem.view;

import javax.swing.*;

public enum CalculatorButton {
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    DELETE('\b'),
    AC('C'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    MULTIPLY('*'),
    DIVIDE('/'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    ADD('+'),
    SUBTRACT('-'),
    ZERO('0'),
    DOT('.'),
    EXP('X'),
    ANS('A'),
    EQUALS('=');

    private final JButton button;

    CalculatorButton(char label) {
        this.button = new JButton(String.valueOf(label));
    }

    public char getLabel() {
        return button.getText().charAt(0);
    }

    public JButton getJButton() {
        return this.button;
    }








}
