package dev.goochem.view;

import javax.swing.*;

    public enum CalculatorButton {
        SEVEN('7'),
        EIGHT('8'),
        NINE('9'),
        DELETE('\b', "DEL"),
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
        EXP('X', "EXP"),
        ANS('A', "Ans"),
        EQUALS('=');

        private final JButton button;
        private final char symbol;

        CalculatorButton(char symbol) {
            this(symbol, String.valueOf(symbol));
        }

        CalculatorButton(char symbol, String displayText) {
            this.button = new JButton(displayText);
            this.symbol = symbol;
        }

        // Returns the symbol of a CalcButton
        public char getSymbol() {
            return symbol;
        }

        public JButton getJButton() {
            return button;
        }
}
