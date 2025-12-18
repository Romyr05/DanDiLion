package apps.Clock.NewGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PauseButton extends JButton {
    private Color baseColor;
    
    public PauseButton() {
        this.baseColor = new Color(255, 152, 0);
        setPreferredSize(new Dimension(60, 60));
        setBackground(baseColor);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Button background
        if (getModel().isRollover()) {
            g2d.setColor(baseColor.darker());
        } else {
            g2d.setColor(baseColor);
        }
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw pause icon (two vertical bars)
        g2d.setColor(Color.WHITE);
        int barWidth = 6;
        int barHeight = 20;
        int spacing = 8;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
        // Left bar
        g2d.fillRect(centerX - spacing - barWidth, centerY - barHeight / 2, barWidth, barHeight);
        // Right bar
        g2d.fillRect(centerX + spacing, centerY - barHeight / 2, barWidth, barHeight);
        
        super.paintComponent(g);
    }
}