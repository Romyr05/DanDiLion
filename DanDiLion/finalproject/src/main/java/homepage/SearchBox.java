package homepage;

import javax.swing.*;
import java.awt.*;

public class SearchBox extends JPanel {

    public SearchBox() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setPreferredSize(new Dimension(380, 45));
        setMaximumSize(new Dimension(380, 45));
        setBorder(BorderFactory.createLineBorder(new Color(200, 150, 255, 100), 3, true));
        setOpaque(false);
        
        initializeComponents();
    }

    private void initializeComponents() {
        // Search icon
        SearchIcon searchIcon = new SearchIcon();
        add(searchIcon);
        
        // Search label
        JLabel searchLabel = new JLabel("Search");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        searchLabel.setForeground(Color.WHITE);
        add(searchLabel);
    }

    // Inner class for search icon
    private static class SearchIcon extends JPanel {
        public SearchIcon() {
            setPreferredSize(new Dimension(20, 20));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(200, 200, 255));
            g2d.setStroke(new BasicStroke(2.5f));
            
            // Draw magnifying glass circle
            g2d.drawOval(2, 2, 12, 12);
            
            // Draw handle
            g2d.drawLine(12, 12, 18, 18);
        }
    }
}