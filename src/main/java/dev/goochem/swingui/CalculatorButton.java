package dev.goochem.swingui;

public enum CalculatorButton {
    BUTTON_0("7"),
    BUTTON_1("8"),
    BUTTON_2("9"),
    BUTTON_3("DEL"),
    BUTTON_4("AC"),
    BUTTON_5("4"),
    BUTTON_6("5"),
    BUTTON_7("6"),
    BUTTON_8("*"),
    BUTTON_9("/"),
    BUTTON_10("1"),
    BUTTON_11("2"),
    BUTTON_12("3"),
    BUTTON_13("+"),
    BUTTON_14("-"),
    BUTTON_15("0"),
    BUTTON_16("."),
    BUTTON_17("EXP"),
    BUTTON_18("Ans"),
    BUTTON_19("=");

    private final String label;

    CalculatorButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }







}
