package apps.CalculatorApp;

import java.awt.*;
import javax.swing.*;

public class CalculatorDisplay extends JLabel {
    
    public CalculatorDisplay() {
        super("0", SwingConstants.RIGHT);
        setFont(new Font("Arial", Font.BOLD, 70));
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)),
            BorderFactory.createEmptyBorder(20, 20, 15, 20)
        ));
    }

    public void updateDisplay(String text) {
        setText(text);
        adjustFontSize(text.length());
    }

    private void adjustFontSize(int length) {
        int fontSize;
        
        if (length <= 6) {
            fontSize = 70;
        } else if (length <= 8) {
            fontSize = 55;
        } else if (length <= 10) {
            fontSize = 45;
        } else {
            fontSize = 35;
        }
        
        setFont(new Font("Arial", Font.BOLD, fontSize));
    }
}