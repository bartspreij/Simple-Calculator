package dev.goochem.controller;

import dev.goochem.model.CalculatorModel;
import dev.goochem.model.Operator;
import dev.goochem.view.CalculatorButton;
import dev.goochem.view.CalculatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

        view.addKeyPressListener(new KeyAdapter() {
             @Override
             public void keyTyped(KeyEvent e) {
                 handleButtonPressOrKeyClick(e.getKeyChar());
             }
        });

    }

    // Initializes all the calculator buttons and adds an ActionListener, then adds it to the view
    private void initializeViewButtonsWithCalculateListener() {
        for (CalculatorButton calcButton : CalculatorButton.values()) {
            JButton btn = calcButton.getJButton();
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char keyChar = calcButton.getSymbol();
                    handleButtonPressOrKeyClick(keyChar);
                }
            });
            view.addButtonToCenterPanel(btn);
        }
    }

    // Handles basically all logic when a button is pressed or a key is typed this decides what happens
    public void handleButtonPressOrKeyClick(char e) {
        CalculatorButton button = charToCalculatorButton(e);

        if (button == null) {
            return;
        }

        // In case of a number or operator we just update the view and return
        if (isOperator(button.getSymbol()) || Character.isDigit(button.getSymbol())) {

            // In case of displaying a result but an operator is inputted we consider it as pressed CalculatorButton.ANS
            if (view.isDisplayingResult() && isOperator(button.getSymbol())) {
                view.setInputFieldText(String.valueOf(model.getCalculationValue() + button.getSymbol()));
                view.setDisplayingResult(false);

            } else {
                view.updateInputField(button);
            }
            return;
        }

        switch (button) {
            case EQUALS -> handleEqualsPress();
            case AC -> handleAllClearPress();
            case DELETE -> handleDeletePress();
            case ANS -> handleAnsPress();
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
        view.resetFocus();
    }

    private void handleAllClearPress() {
        view.clearInputField();
    }

    private void handleDeletePress() {
        view.deleteLastInputFromInputField();
    }

    private void handleAnsPress() {
        view.clearInputField();
        view.setInputFieldText(String.valueOf(model.getCalculationValue()));
    }

    public List<String> tokenizeExpression(String expression) {
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

    private CalculatorButton charToCalculatorButton(char character) {
        if (character == '\n') { // Makes typing enter the same as pressing equals
            return CalculatorButton.EQUALS;
        }

        for (CalculatorButton calcButton : CalculatorButton.values()) {
            if (calcButton.getSymbol() == character) {
                return calcButton;
            }
        }
        return null;
    }
}
