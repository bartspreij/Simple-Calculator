package dev.goochem.controller;

import dev.goochem.model.CalculatorModel;
import dev.goochem.view.CalculatorView;

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

        this.view.addCalculationListener(new CalculateListener());
    }

    class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String expression = view.getExpression();
            List<String> RPN = shuntingYard(tokenizeExpression(expression)); // Expression -> tokens -> Reverse Polish Notation
            if (RPN.size() < 3) { // Need minimum of 3 tokens to calculate anything
                return;
            }
            model.evaluateExpression(RPN);
            view.setCalcSolution(model.getCalculationValue());
            view.setDisplayingResult(true);
        }
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
}
