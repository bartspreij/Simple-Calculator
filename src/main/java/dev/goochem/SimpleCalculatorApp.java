package dev.goochem;

import dev.goochem.controller.CalculatorController;
import dev.goochem.model.CalculatorModel;
import dev.goochem.view.CalculatorView;

public class SimpleCalculatorApp {
    public static void main(String[] args) {

        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);

        view.setVisible(true);
    }
}
