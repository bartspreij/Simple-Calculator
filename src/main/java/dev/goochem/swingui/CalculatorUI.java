package dev.goochem.swingui;

import javax.swing.*;
import java.awt.*;

public class CalculatorUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField field;


    public CalculatorUI() {
        this.frame = new JFrame("Calculator");
        this.panel = new JPanel(new GridLayout(4, 5, 5, 5));
        this.field = new JTextField(20);


        for (CalculatorButton btn : CalculatorButton.values()) {
            panel.add(new JButton(btn.getLabel()));
        }

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        field.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(BorderLayout.NORTH, field);
        frame.add(BorderLayout.SOUTH, panel);
        frame.setBounds(1400, 200, 300, 300);
        frame.pack();
        frame.setVisible(true);
    }
}
