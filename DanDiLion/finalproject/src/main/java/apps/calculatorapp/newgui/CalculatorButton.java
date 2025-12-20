package apps.calculatorapp.newgui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorButton extends JButton {
    private Color baseColor;
    private boolean isWide;

    public CalculatorButton(String text, Color bgColor, Color fgColor, boolean isWide) {
        super(text);
        this.baseColor = bgColor;
        this.isWide = isWide;
        
        setupButton(bgColor, fgColor);
        addHoverEffect();
    }

    public CalculatorButton(String text, Color bgColor, Color fgColor) {
        this(text, bgColor, fgColor, false);
    }

    private void setupButton(Color bgColor, Color fgColor) {
        setBackground(bgColor);
        setForeground(fgColor);
        setFont(new Font("Arial", Font.PLAIN, 26));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (isWide) {
            setPreferredSize(new Dimension(138, 60));
            setMaximumSize(new Dimension(138, 60));
            setMinimumSize(new Dimension(138, 60));
        } else {
            setPreferredSize(new Dimension(60, 60));
            setMaximumSize(new Dimension(60, 60));
            setMinimumSize(new Dimension(60, 60));
        }
    }

    private void addHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(baseColor.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(baseColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background shape - perfectly circular
        g2d.setColor(getBackground());
        if (isWide) {
            // For wide button (0), make it pill-shaped with height as radius
            int cornerRadius = getHeight();
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        } else {
            // For regular buttons, draw perfect circles
            int size = Math.min(getWidth(), getHeight());
            g2d.fillOval(0, 0, size, size);
        }
        
        // Draw text
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        int x, y;


        x = (getWidth() - textWidth) / 2;
        y = (getHeight() + textHeight) / 2 -25;
    
        if (isWide) {
            // Left-align text in the wide button
            x = getHeight() / 3;
            y = (getHeight() + textHeight) / 2 -25;
        } else {
            // Center text in regular buttons

        }
        
        g2d.drawString(getText(), x, y);
    }
}