package apps.Clock.NewGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopButton extends JButton {
    private Color baseColor;
    
    public StopButton() {
        this.baseColor = new Color(244, 67, 54);
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
        
        // Draw stop square icon (white square in center)
        g2d.setColor(Color.WHITE);
        int iconSize = 20;
        int x = (getWidth() - iconSize) / 2;
        int y = (getHeight() - iconSize) / 2;
        g2d.fillRect(x, y, iconSize, iconSize);
        
        super.paintComponent(g);
    }
}