// Author: John Romyr Lopez
package homepage;

import javax.swing.*;
import java.awt.*;


//Panel searchbox for now since there is nothing to search if i made it a search box 
public class SearchBox extends JPanel {

    public SearchBox() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 12));
        setPreferredSize(new Dimension(450, 50));
        setMaximumSize(new Dimension(450, 50));
        setOpaque(false);
        
        initializeComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw rounded dark semi-transparent background for dark theme
        g2d.setColor(new Color(40, 40, 45, 200)); // Dark gray with transparency
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        
        // Draw subtle border
        g2d.setColor(new Color(255, 255, 255, 60));
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);
        
        g2d.dispose();
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