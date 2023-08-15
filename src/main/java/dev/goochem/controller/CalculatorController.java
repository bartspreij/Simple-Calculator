package dev.goochem.controller;

import dev.goochem.model.CalculatorModel;
import dev.goochem.view.CalculatorButton;
import dev.goochem.view.CalculatorUI;

import static dev.goochem.view.CalculatorUI.DISPLAYABLE_BUTTONS;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorUI ui;

    public CalculatorController(CalculatorModel model, CalculatorUI ui) {
        this.model = model;
        this.ui = ui;
    }

    public void handleButtonPress(CalculatorButton btn) {
        if (DISPLAYABLE_BUTTONS.contains(btn)) { // For digits and operators we update the inputField
            ui.updateInputField(btn);
        }

        switch (btn) {
            case EQUALS:
        }

    }

    public void performCalculation(String inputText) {
        System.out.println(inputText);
    }
}
