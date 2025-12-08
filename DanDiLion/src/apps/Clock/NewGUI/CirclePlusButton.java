package apps.Clock.NewGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CirclePlusButton extends JButton {
        
        public CirclePlusButton() {
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Add new alarm");
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int diameter = Math.min(getWidth(), getHeight()) - 4;
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;
            
            // Draw circle
            g2.setColor(getModel().isPressed() ? new Color(100, 100, 100) : 
                       getModel().isRollover() ? new Color(80, 80, 80) : new Color(60, 60, 60));
            g2.fillOval(x, y, diameter, diameter);
            
            // Draw border
            g2.setColor(Color.GRAY);
            g2.drawOval(x, y, diameter, diameter);
            
            // Draw plus sign
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            int center = diameter / 2;
            int plusSize = diameter / 5;
            
            // Horizontal line
            g2.drawLine(x + center - plusSize, y + center, x + center + plusSize, y + center);
            // Vertical line
            g2.drawLine(x + center, y + center - plusSize, x + center, y + center + plusSize);
            
            g2.dispose();
        }
    }