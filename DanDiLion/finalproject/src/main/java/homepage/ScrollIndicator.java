// Author: John Romyr Lopez
package homepage;

import javax.swing.*;
import java.awt.*;

public class ScrollIndicator extends JPanel {
    private int totalDots;
    private int activeDot;

    public ScrollIndicator(int totalDots, int activeDot) {
        this.totalDots = totalDots;
        this.activeDot = activeDot;
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 6, 0));
        setOpaque(false);
        setMaximumSize(new Dimension(500, 20));
        
        createDots();
    }

    private void createDots() {
        for (int i = 0; i < totalDots; i++) {
            final int index = i;
            Dot dot = new Dot(index == activeDot);
            add(dot);
        }
    }

    public void setActiveDot(int index) {
        this.activeDot = index;
        removeAll();
        createDots();
        revalidate();
        repaint();
    }

    //Dot of active (only one page for now)
    private static class Dot extends JPanel {
        private boolean isActive;

        public Dot(boolean isActive) {
            this.isActive = isActive;
            setPreferredSize(new Dimension(10, 10));
            setOpaque(false);
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (isActive) {
                g2d.setColor(Color.WHITE);
                g2d.fillOval(0, 0, getWidth(), getHeight());
            } else {
                g2d.setColor(new Color(255, 255, 255, 120)); // semi white color to show
                g2d.fillOval(0, 0, getWidth(), getHeight());
            }
        }
    }
}