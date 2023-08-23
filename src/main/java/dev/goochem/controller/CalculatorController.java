package dev.goochem.controller;

import dev.goochem.model.CalculatorModel;
import dev.goochem.model.Operator;
import dev.goochem.view.CalculatorButton;
import dev.goochem.view.CalculatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.goochem.model.ShuntingYard.shuntingYard;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        initializeViewButtonsWithCalculateListener();
    }

    class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            CalculatorButton calculatorButton = findCalculatorButtonByLabel(button);
            if (calculatorButton != null) {
                handleButtonPress(calculatorButton);
            }

        }
    }

    // Initializes all the calculator buttons and adds an ActionListener, then adds it to the view
    private void initializeViewButtonsWithCalculateListener() {
        for (CalculatorButton button : CalculatorButton.values()) {
            JButton btn = button.getJButton();
            btn.addActionListener(new CalculateListener());
            view.addButtonToCenterPanel(btn);
        }
    }

    public void handleButtonPress(CalculatorButton button) {
            if (isOperator(button.getLabel()) || Character.isDigit(button.getLabel())) {
                view.updateInputField(button);
                return;
            }

            switch (button) {
                case EQUALS -> handleEqualsPress();
                case AC -> handleAllClearPress();
                case DELETE -> handleDeletePress();
            }
    }

    private void handleEqualsPress() {
        String expression = view.getExpression();
        List<String> RPN = shuntingYard(tokenizeExpression(expression)); // Expression -> tokens -> Reverse Polish Notation

        if (RPN.size() < 3) { // Need minimum of 3 tokens to calculate anything
            return;
        }

        model.evaluateExpression(RPN);
        view.setCalcSolution(model.getCalculationValue());
        view.setDisplayingResult(true);
    }

    private void handleAllClearPress() {

    }

    private void handleDeletePress () {

    }

    private List<String> tokenizeExpression(String expression) {
        List<String> tokens = new ArrayList<>();
        String regex = "\\d+|[\\-+*/]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    private boolean isOperator(char input) {
        for (Operator operator : Operator.values()) {
            if (operator.getSymbol() == input) {
                return true;
            }
        }
        return false;
    }

    private CalculatorButton findCalculatorButtonByLabel(JButton jButton) {
        for (CalculatorButton btn : CalculatorButton.values()) {
            if (btn.getJButton() == jButton) {
                return btn;
            }
        }
        return null;
    }
}
