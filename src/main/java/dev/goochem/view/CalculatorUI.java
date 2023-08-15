package dev.goochem.view;

import dev.goochem.controller.CalculatorController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CalculatorUI extends JFrame {
    private final JPanel centerPanel, northPanel, eastPanel;
    private final JTextField inputField = new JTextField();
    private static final Color BLUE_BACKGROUND_COLOR = new Color(0x123456);
    private static final Color GRAY_BACKGROUND_COLOR = new Color(105, 105, 105, 200); // R, G, B, Alpha
    private static final Font myFont = new Font("Consolas", Font.ITALIC, 30);
    public static final Set<CalculatorButton> DISPLAYABLE_BUTTONS = new HashSet<>();

    public CalculatorUI() {
        this.centerPanel = new JPanel(new GridLayout(4, 5, 2, 2));
        this.northPanel = new JPanel(new BorderLayout());
        this.eastPanel = new JPanel(new BorderLayout());

        loadPanels();
        loadLogo();
        loadFrame();
        inputField.requestFocus(); // keep the focus on the inputField to always be able to type our input
    }

    // Loads the frame which we use to build upon
    private void loadFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1400, 200, 720, 420);

        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);

        setResizable(false);
        setAlwaysOnTop(true);
        pack();
        setVisible(true);
    }

    // Loads all the panels, so they can be placed on the BorderLayout
    private void loadPanels() {
        loadCenterPanel();
        loadNorthPanel();
        loadEastPanel();
    }

    // Load the centerPanel
    private void loadCenterPanel() {
        applyCommonPanelStyles(centerPanel);
        initializeButtons();
    }

    // Load the northPanel aka the "screen"
    private void loadNorthPanel() {
        applyCommonPanelStyles(northPanel);
        northPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        loadInputArea(inputField);
        northPanel.add(inputField);
    }

    // Load the eastPanel which shows calculations history
    private void loadEastPanel() {
        applyCommonPanelStyles(eastPanel);
        TitledBorder title = BorderFactory.createTitledBorder("History");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitleColor(Color.WHITE);
        eastPanel.setBorder(title);

        JTextArea resultArea = new JTextArea(10, 10);
        loadResultArea(resultArea);

        eastPanel.add(resultArea);

    }

    // Apply common panel styles
    private void applyCommonPanelStyles(JPanel panel) {
        panel.setBackground(BLUE_BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    // Initializes the buttons and adds an event listener to them for the center panel
    private void initializeButtons() {
        for (CalculatorButton btn : CalculatorButton.values()) {
            if (btn.getLabel().length() == 1) { // Add operators and digits to a Set<CalculatorButton>
                DISPLAYABLE_BUTTONS.add(btn);
            }

            JButton semiTransparentButton = new JButton(btn.getLabel());
            semiTransparentButton.setBackground(GRAY_BACKGROUND_COLOR);
            semiTransparentButton.addActionListener(e -> {
                controller.handleButtonPress(btn);
            });
            centerPanel.add(semiTransparentButton);
        }
    }

    public void updateInputField(CalculatorButton btn) {
        String currentText = inputField.getText();
        char buttonChar = btn.getLabel().charAt(0); // button to char
        char lastInputtedChar = 0;

        if (currentText.length() > 1) {
            lastInputtedChar = currentText.charAt(currentText.length() - 1);
        }

        if (Character.isDigit(buttonChar) || Character.isDigit(lastInputtedChar)) { // we update when btn is a digit or when last input was not an operator or dot
            inputField.setText(inputField.getText() + btn.getLabel());
        }

        inputField.requestFocus(); // reset focus for better typing experience
    }

    // sets logoIcon, or null if the path was not found.
    private void loadLogo() {
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource("logo/calculator_logo.jpg")));
        setIconImage(logoIcon.getImage());
    }

    // Loads everything for the result area
    private void loadResultArea(JTextArea resultArea) {
        resultArea.setBackground(GRAY_BACKGROUND_COLOR);
        resultArea.setEditable(false);

    }

    // Loads everything for the input area
    private void loadInputArea(JTextField inputField) {
        inputField.setBackground(GRAY_BACKGROUND_COLOR);
        inputField.setCaretColor(GRAY_BACKGROUND_COLOR); // make cursor invisible
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputField.setFont(myFont);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(DISPLAYABLE_BUTTONS.stream().anyMatch(button -> button.getLabel().charAt(0) == e.getKeyChar())) {
                    e.consume();
                }
            }
        });
    }
}