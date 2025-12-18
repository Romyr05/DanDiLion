package homepage;

import javax.swing.*;
import java.awt.*;

public class ScrollIndicator extends JPanel {
    private int totalDots;
    private int activeDot;

    public ScrollIndicator(int totalDots, int activeDot) {
        this.totalDots = totalDots;
        this.activeDot = activeDot;
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        setBackground(Color.GRAY);
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

    // Inner class for individual dot
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
            g2d.setColor(isActive ? Color.WHITE : Color.LIGHT_GRAY);
            g2d.fillOval(0, 0, getWidth(), getHeight());
        }
    }
}