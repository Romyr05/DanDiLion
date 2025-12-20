// Author: John Romyr Lopez
package homepage;

import javax.swing.*;
import java.awt.*;

/**
 * Container class for all status bar icon components
 */
public class StatusIcons {

    // WiFi Icon Component
    public static class WifiIcon extends JPanel {
        public WifiIcon() {
            setPreferredSize(new Dimension(18, 16));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);

            int[] heights = {4, 7, 10, 13};
            for (int i = 0; i < 4; i++) {
                g2d.fillRect(i * 4, 13 - heights[i], 3, heights[i]);
            }
        }
    }

    // Battery Icon Component
    public static class BatteryIcon extends JPanel {
        private int batteryLevel = 80; // Percentage

        public BatteryIcon() {
            setPreferredSize(new Dimension(24, 16));
            setOpaque(false);
        }

        public void setBatteryLevel(int level) {
            this.batteryLevel = Math.max(0, Math.min(100, level));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);

            // Battery outline
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawRoundRect(1, 3, 18, 10, 2, 2);
            
            // Battery tip
            g2d.fillRect(20, 6, 2, 4);

            // Battery fill based on level
            int fillWidth = (int) (14 * (batteryLevel / 100.0));
            g2d.fillRect(3, 5, fillWidth, 6);
        }
    }
}