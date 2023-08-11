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
    private final CalculatorController controller;
    private final JPanel centerPanel, northPanel, eastPanel;
    private static final Color BLUE_BACKGROUND_COLOR = new Color(0x123456);
    private static final Color GRAY_BACKGROUND_COLOR = new Color(105, 105, 105, 200); // R, G, B, Alpha
    private static final Font myFont = new Font("Ink Free", Font.BOLD, 30);
    private static final Set<Character> ALLOWED_CHARS = new HashSet<>();

    public CalculatorUI() {
        this.controller = new CalculatorController();
        this.centerPanel = new JPanel(new GridLayout(4, 5, 2, 2));
        this.northPanel = new JPanel(new BorderLayout());
        this.eastPanel = new JPanel(new BorderLayout());

        loadPanels();
        loadLogo();
        loadFrame();
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
        add(centerPanel); // add to frame
    }

    // Load the northPanel aka the "screen"
    private void loadNorthPanel() {
        applyCommonPanelStyles(northPanel);
        northPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        JTextField inputField = new JTextField();
        loadInputArea(inputField);

        northPanel.add(inputField);
        add(northPanel); // add to frame
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
        add(eastPanel); // add to frame
    }

    // Apply common panel styles
    private void applyCommonPanelStyles(JPanel panel) {
        panel.setBackground(BLUE_BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    // Initializes the buttons and adds an event listener to them for the center panel
    private void initializeButtons() {
        for (CalculatorButton btn : CalculatorButton.values()) {
            if (btn.getLabel().length() == 1) {
                ALLOWED_CHARS.add(btn.getLabel().charAt(0));
            }

            JButton semiTransparentButton = new JButton(btn.getLabel());
            semiTransparentButton.setBackground(GRAY_BACKGROUND_COLOR);
            semiTransparentButton.addActionListener(e -> controller.handleButtonClick(btn));
            centerPanel.add(semiTransparentButton);
        }
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

    }

    // Loads everything for the input area
    private void loadInputArea(JTextField inputField) {
        inputField.setBackground(GRAY_BACKGROUND_COLOR);
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputField.setFont(myFont);

        // TODO: Fix this
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (!ALLOWED_CHARS.contains(key.getKeyChar())) {
                    key.consume();
                }
            }
        });
    }
}
