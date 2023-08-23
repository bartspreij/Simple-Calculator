package dev.goochem.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CalculatorView extends JFrame {
    private final JPanel centerPanel, northPanel, eastPanel;
    private final JTextField inputField = new JTextField(15);
    private final JTextField outputField = new JTextField(5);
    private final JTextArea resultArea = new JTextArea(10, 10);
    public JButton calcButton;
    private static final Color BLUE_BACKGROUND_COLOR = new Color(6,57,112);
    private static final Color GRAY_BACKGROUND_COLOR = new Color(163, 171, 184);
    private static final Font myFont = new Font("Consolas", Font.ITALIC, 30);
    public static final Set<CalculatorButton> DISPLAYABLE_BUTTONS = new HashSet<>();
    private final ArrayList<Integer> numbers = new ArrayList<>();
    private boolean displayingResult = false;


    public CalculatorView() {
        this.centerPanel = new JPanel(new GridLayout(4, 5, 2, 2));
        this.northPanel = new JPanel(new BorderLayout());
        this.eastPanel = new JPanel(new BorderLayout());
        this.calcButton = new JButton();

        loadPanels();
        loadLogo();
        loadFrame();
        inputField.requestFocus(); // keep the focus on the inputField to always be able to type our input
    }

    // Loads the frame which we use to build upon
    private void loadFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 420);

        // Calculate x, y position to open app in top right
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (0.60 * screenSize.getWidth());
        int y = (int) (0.10 * screenSize.getHeight());
        setLocation(x, y);

        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);

        setResizable(false);
        setAlwaysOnTop(true);
        pack();
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
    }

    // Load the northPanel aka the "screen"
    private void loadNorthPanel() {
        applyCommonPanelStyles(northPanel);
        northPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        loadInputField();
        loadOutputField();
        northPanel.add(inputField, BorderLayout.CENTER);
        northPanel.add(outputField, BorderLayout.EAST);

    }

    // Load the eastPanel which shows calculations history
    private void loadEastPanel() {
        applyCommonPanelStyles(eastPanel);
        TitledBorder title = BorderFactory.createTitledBorder("History");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitleColor(Color.WHITE);
        eastPanel.setBorder(title);
        loadResultArea();
        eastPanel.add(resultArea);
    }

    // Apply common panel styles
    private void applyCommonPanelStyles(JPanel panel) {
        panel.setBackground(BLUE_BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    // Adds button to panel
    public void addButtonToCenterPanel(JButton button) {
        button.setBackground(GRAY_BACKGROUND_COLOR);
        centerPanel.add(button);
    }

    // Loads everything for the input area TODO: fix enter and backspace logic a
    private void loadInputField() {
        inputField.setBackground(GRAY_BACKGROUND_COLOR);
        inputField.setCaretColor(GRAY_BACKGROUND_COLOR); // make cursor invisible
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputField.setFont(myFont);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (DISPLAYABLE_BUTTONS.stream().noneMatch(button -> button.getLabel() == e.getKeyChar())) {
                    e.consume();
                }
                for (CalculatorButton btn : CalculatorButton.values()) {
                    char btnLabel = btn.getLabel();
                    if (CalculatorButton.EQUALS.getLabel() == e.getKeyChar()) {
                        calcButton.doClick();
                        updateInputField(btn);
                        break;
                    } else if (btnLabel == e.getKeyChar()) {
                        updateInputField(btn);
                        e.consume();
                    }
                }
            }
        });
    }

    private void loadOutputField() {
        outputField.setBackground(GRAY_BACKGROUND_COLOR);
        outputField.setEditable(false);
        outputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        outputField.setFont(myFont);
    }

    public void updateInputField(CalculatorButton btn) {
        if (displayingResult) { // Clear inputField if we calculated something
            inputField.setText("");
            setDisplayingResult(false);
        }

        // Compares chars to prevent double operator input
        char buttonChar = btn.getLabel(); // button to char
        char lastInputtedChar = getLastInputtedChar();

        if (Character.isDigit(buttonChar) || Character.isDigit(lastInputtedChar)) {
            inputField.setText(inputField.getText() + btn.getLabel());
        }

        inputField.requestFocus(); // reset focus for better typing experience
    }

    public char getLastInputtedChar() {
        String currentText = inputField.getText();
        char lastInputtedChar = 0;

        // Collect last input
        if (!currentText.isEmpty()) {
            lastInputtedChar = currentText.charAt(currentText.length() - 1);
        }
        return lastInputtedChar;
    }

    // sets logoIcon, or null if the path was not found.
    private void loadLogo() {
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource("logo/calculator_logo.jpg")));
        setIconImage(logoIcon.getImage());
    }

    // Loads everything for the result area
    private void loadResultArea() {
        resultArea.setBackground(GRAY_BACKGROUND_COLOR);
        resultArea.setEditable(false);
    }

    public String getExpression() {
        return inputField.getText();
    }

    // TODO: Fix max length of solution
    public void setCalcSolution(double solution) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedSolution = decimalFormat.format(solution);
        addResultToHistory(formattedSolution);
        outputField.setText(formattedSolution);
    }

    public void addResultToHistory(String result) {
        resultArea.append(inputField.getText() + "= " + result + "\n");
    }

    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void setDisplayingResult(boolean input) {
        displayingResult = input;
    }



    public void updateView() {

    }
}