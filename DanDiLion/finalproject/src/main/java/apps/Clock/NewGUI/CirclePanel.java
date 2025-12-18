package apps.Clock.NewGUI;

import java.awt.*;
import javax.swing.*;

public class CirclePanel extends JPanel {
    private JLabel timeDisplay;
    private float progress = 0;
    
    public CirclePanel() {
        setOpaque(false);
        setPreferredSize(new Dimension(280, 280));
        setLayout(new GridBagLayout());
        
        // Time display in center
        timeDisplay = new JLabel("00:00");
        timeDisplay.setFont(new Font("Arial", Font.BOLD, 48));
        timeDisplay.setForeground(Color.WHITE);
        
        add(timeDisplay);
    }
    
    public void setTimeText(String text) {
        timeDisplay.setText(text);
    }
    
    public void setProgress(float progress) {
        this.progress = progress;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int size = 250;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        
        // Background circle
        g2d.setColor(new Color(60, 60, 60));
        g2d.setStroke(new BasicStroke(8));
        g2d.drawOval(x, y, size, size);
        
        // Progress arc
        if (progress > 0) {
            g2d.setColor(new Color(33, 150, 243));
            g2d.setStroke(new BasicStroke(8));
            int angle = (int) (360 * progress);
            g2d.drawArc(x, y, size, size, 90, -angle);
        }
    }
}