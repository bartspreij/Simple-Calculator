package dev.goochem;

import dev.goochem.controller.CalculatorController;
import dev.goochem.model.CalculatorModel;
import dev.goochem.view.CalculatorUI;

public class SimpleCalculatorApp {
    public static void main(String[] args) {

        CalculatorModel model = new CalculatorModel();
        CalculatorUI view = new CalculatorUI();
        CalculatorController controller = new CalculatorController(model, view);

    }
}
