package homepage.newGUI;

import javax.swing.*;
import java.awt.*;

public class WeatherWidget extends JPanel {
    private JLabel tempLabel;

    public WeatherWidget() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));
        initializeComponents();
    }

    private void initializeComponents() {
        // Weather icon
        WeatherIcon weatherIcon = new WeatherIcon();
        add(weatherIcon);
        
        // Temperature label
        tempLabel = new JLabel("+18°C");
        tempLabel.setFont(new Font("Arial", Font.BOLD, 30));
        tempLabel.setForeground(Color.WHITE);
        add(tempLabel);
    }

    public void setTemperature(String temp) {
        tempLabel.setText(temp);
    }

    // Inner class for weather icon
    private static class WeatherIcon extends JPanel {
        public WeatherIcon() {
            setPreferredSize(new Dimension(50, 45));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw sun
            g2d.setColor(Color.WHITE);
            g2d.fillOval(5, 5, 20, 20);
            
            // Draw sun rays
            int centerX = 15;
            int centerY = 15;
            int rayLength = 6;
            for (int i = 0; i < 8; i++) {
                double angle = Math.PI * 2 * i / 8;
                int x1 = centerX + (int)(Math.cos(angle) * 13);
                int y1 = centerY + (int)(Math.sin(angle) * 13);
                int x2 = centerX + (int)(Math.cos(angle) * (13 + rayLength));
                int y2 = centerY + (int)(Math.sin(angle) * (13 + rayLength));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x1, y1, x2, y2);
            }
            
            // Draw cloud (semi-transparent)
            g2d.setColor(new Color(255, 255, 255, 180));
            g2d.fillOval(18, 18, 12, 10);
            g2d.fillOval(25, 20, 10, 8);
            g2d.fillOval(22, 22, 10, 8);
        }
    }
}