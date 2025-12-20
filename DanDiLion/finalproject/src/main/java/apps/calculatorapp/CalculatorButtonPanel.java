// Author: Janselle Alaysa
package apps.calculatorapp;

import java.awt.*;
import javax.swing.*;

import apps.calculatorapp.newgui.CalculatorButton;

import java.util.function.Consumer;

public class CalculatorButtonPanel extends JPanel {
    private static final Color LIGHT_GRAY = new Color(165, 165, 165);
    private static final Color DARK_GRAY = new Color(51, 51, 51);
    private static final Color ORANGE = new Color(255, 149, 0);
    
    private Consumer<String> buttonClickHandler;   //Function that accepts string but does not return it
                                                    //this just accepts a value (no leaking of logic into here)
                                                    // para i pass ta lang kng ano need

    public CalculatorButtonPanel(Consumer<String> buttonClickHandler) {
        this.buttonClickHandler = buttonClickHandler;
        setupPanel();
        createButtons();
    }

    //set up panel
    private void setupPanel() {
        setLayout(new GridLayout(5, 4, 12, 12));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(10, 12, 20, 12));
    }

    //creating buttons
    private void createButtons() {
        // Row 1
        addButton("AC", LIGHT_GRAY, Color.BLACK);
        addButton("+/-", LIGHT_GRAY, Color.BLACK);
        addButton("%", LIGHT_GRAY, Color.BLACK);
        addButton("÷", ORANGE, Color.WHITE);
        
        // Row 2
        addButton("7", DARK_GRAY, Color.WHITE);
        addButton("8", DARK_GRAY, Color.WHITE);
        addButton("9", DARK_GRAY, Color.WHITE);
        addButton("×", ORANGE, Color.WHITE);
        
        // Row 3
        addButton("4", DARK_GRAY, Color.WHITE);
        addButton("5", DARK_GRAY, Color.WHITE);
        addButton("6", DARK_GRAY, Color.WHITE);
        addButton("-", ORANGE, Color.WHITE);
        
        // Row 4
        addButton("1", DARK_GRAY, Color.WHITE);
        addButton("2", DARK_GRAY, Color.WHITE);
        addButton("3", DARK_GRAY, Color.WHITE);
        addButton("+", ORANGE, Color.WHITE);
        
        // Row 5 - zero is now regular size
        addButton("0", DARK_GRAY, Color.WHITE);
        addButton(".", DARK_GRAY, Color.WHITE);
        addButton("=", ORANGE, Color.WHITE);
    }

    //design of the buttons and 
    private void addButton(String text, Color bgColor, Color fgColor) {
        CalculatorButton button = new CalculatorButton(text, bgColor, fgColor);
        button.addActionListener(e -> buttonClickHandler.accept(text));  //when ang button i click -> call buttonhandler to give button text
        this.add(button);
    }
}