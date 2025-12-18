package homepage.newGUI;

import java.awt.*;

import javax.swing.*;

public class SearchIcon extends JPanel{

    public SearchIcon(){
        this.setPreferredSize(new Dimension(20, 20));
        this.setOpaque(false);
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


