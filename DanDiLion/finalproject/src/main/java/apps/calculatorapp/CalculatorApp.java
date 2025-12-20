package apps.calculatorapp;

import java.awt.*;
import javax.swing.*;

import apps.motion.Swipe;
import homepage.StatusBar;
import homepage.BaseApp;
import homepage.Homepage;

public class CalculatorApp extends BaseApp {
    private CalculatorDisplay display;
    private CalculatorLogic engineLogic;
    private CalculatorButtonPanel buttonPanel;

    public CalculatorApp() {
        super("Calculator");
        this.engineLogic = new CalculatorLogic();
        setupUI();
        addSwipeGesture();
    }

    //Swiping motion to go back to homepage
    private void addSwipeGesture() {
        Swipe swipeListener = new Swipe(new Swipe.SwipeCallback() {
            @Override
            public void onSwipeRight() {
                goBackToHomePage();
            }
        });
        
        this.addMouseListener(swipeListener);
        this.getContentPane().addMouseListener(swipeListener);
        addSwipeToAllComponents(this.getContentPane(), swipeListener);
    }
    
    private void addSwipeToAllComponents(Component component, Swipe listener) {
        component.addMouseListener(listener);
        
        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                addSwipeToAllComponents(child, listener);
            }
        }
    }

    private void goBackToHomePage() {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            Homepage homePage = new Homepage();
            homePage.setVisible(true);
        });
    }

    //Setting up the ui of the calculator
    private void setupUI() {
        setLayout(new BorderLayout()); 
        add(new StatusBar(), BorderLayout.NORTH);    
        add(createCalculatorPanel(), BorderLayout.CENTER);
    }

    private JPanel createCalculatorPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        
        // Display
        display = new CalculatorDisplay();     
        mainPanel.add(display, BorderLayout.NORTH);
        
        // Buttons
        buttonPanel = new CalculatorButtonPanel(e -> handleButtonClick(e));   //Lambda expression to use handlebutton after clicking
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        return mainPanel;
    }

    //Using Engine logic to handle clicks
    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "AC":
                engineLogic.reset();
                break;
            case "+/-":
                engineLogic.toggleSign();
                break;
            case "%":
                engineLogic.applyPercentage();
                break;
            case "÷":
            case "×":
            case "-":
            case "+":
                engineLogic.setOperator(buttonText);
                break;
            case "=":
                engineLogic.calculate();
                break;
            case ".":
                engineLogic.appendDecimal();
                break;
            default:
                engineLogic.appendDigit(buttonText);
                break;
        }
        
        updateDisplay();
    }

    private void updateDisplay() {
        display.updateDisplay(engineLogic.getCurrentDisplay());
    }
    
}