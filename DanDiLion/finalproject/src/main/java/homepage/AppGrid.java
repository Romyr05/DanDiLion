package homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppGrid extends JPanel {
    private ActionListener actionListener;

    public AppGrid(ActionListener listener) {
        this.actionListener = listener;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(0, 400));
        setOpaque(false); // Transparent to show gradient
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        initializeComponents();
    }

    private void initializeComponents() {
        // Upper 8 apps (2 rows of 4)
        add(createUpperApps());
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Scroll indicator dots
        add(new ScrollIndicator(4, 0));
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Lower 4 apps (1 row of 4)
        add(createLowerApps());
    }

    private JPanel createUpperApps() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 12, 12));
        panel.setMaximumSize(new Dimension(480, 200));
        panel.setOpaque(false);
        
        // Add 8 placeholder app icons with darker, muted colors for dark theme
        Color[] pastelColors = {
            new Color(50, 80, 120),   // Dark blue
            new Color(60, 100, 80),   // Dark green
            new Color(120, 70, 90),   // Dark pink
            new Color(120, 90, 60),   // Dark orange
            new Color(90, 60, 110),   // Dark purple
            new Color(100, 80, 70),   // Dark peach
            new Color(50, 90, 110),   // Dark powder blue
            new Color(110, 70, 90)    // Dark pink
        };
        
        for (int i = 0; i < 8; i++) {
            panel.add(createStyledIconButton("none as of the moment" + (i+1) + ".png", null, pastelColors[i % pastelColors.length]));
        }
        
        return panel;
    }

    private JPanel createLowerApps() {
        // Create dock panel with dark semi-transparent background
        JPanel dockPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Dark semi-transparent background with subtle gradient
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(30, 30, 35, 220),      // Dark gray-blue
                    getWidth(), 0, new Color(25, 25, 30, 220) // Darker gray
                );
                
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                
                // Add subtle top border
                g2d.setColor(new Color(255, 255, 255, 40));
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, 0, getWidth(), 0);
                
                g2d.dispose();
            }
        };
        
        JPanel panel = new JPanel(new GridLayout(1, 4, 12, 12));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Darker, more muted colors for dock apps that work with dark theme
        Color[] dockColors = {
            new Color(60, 100, 150),  // Muted blue
            new Color(80, 140, 100),  // Muted green
            new Color(140, 100, 160), // Muted purple
            new Color(150, 120, 140)  // Muted pink
        };
        
        // Add functional app icons with actions
        panel.add(createStyledIconButton("src/main/java/assets/clockImage.jpg", "CLOCK", dockColors[0]));
        panel.add(createStyledIconButton("src/main/java/assets/calcImage.jpg", "CALCULATOR", dockColors[1]));
        panel.add(createStyledIconButton("src/main/java/assets/calendarImage.jpg", "CALENDAR", dockColors[2]));
        panel.add(createStyledIconButton("assets/app4.png", "NOTES", dockColors[3]));
        
        dockPanel.add(panel, BorderLayout.CENTER);
        dockPanel.setPreferredSize(new Dimension(480, 100));
        dockPanel.setMaximumSize(new Dimension(480, 100));
        
        return dockPanel;
    }
    
    private JPanel createStyledIconButton(String imagePath, String actionCommand, Color backgroundColor) {
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setPreferredSize(new Dimension(80, 80));
        
        StyledAppIcon iconButton = new StyledAppIcon(imagePath, backgroundColor);
        
        // Add click action if command is provided
        if (actionCommand != null && actionListener != null) {
            iconButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    actionListener.actionPerformed(
                        new java.awt.event.ActionEvent(iconButton, 
                            java.awt.event.ActionEvent.ACTION_PERFORMED, 
                            actionCommand)
                    );
                }
            });
        }
        
        container.add(iconButton, BorderLayout.CENTER);
        return container;
    }
    
    // Styled app icon with rounded corners and pastel background
    private class StyledAppIcon extends JLabel {
        private String imagePath;
        private Color bgColor;
        private boolean isHovered = false;
        private static final int ICON_SIZE = 70;
        
        public StyledAppIcon(String imagePath, Color bgColor) {
            this.imagePath = imagePath;
            this.bgColor = bgColor;
            setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setOpaque(false);
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw rounded background
            Color drawColor = isHovered ? bgColor.brighter() : bgColor;
            g2d.setColor(drawColor);
            g2d.fillRoundRect(0, 0, ICON_SIZE, ICON_SIZE, 20, 20);
            
            // Draw icon or placeholder
            try {
                java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(new java.io.File(imagePath));
                if (img != null) {
                    int padding = 15;
                    g2d.drawImage(img, padding, padding, ICON_SIZE - 2*padding, ICON_SIZE - 2*padding, null);
                } else {
                    drawPlaceholder(g2d);
                }
            } catch (Exception e) {
                drawPlaceholder(g2d);
            }
            
            g2d.dispose();
        }
        
        private void drawPlaceholder(Graphics2D g2d) {
            // Draw simple app icon placeholder
            g2d.setColor(new Color(255, 255, 255, 180));
            int padding = 20;
            g2d.fillRoundRect(padding, padding, ICON_SIZE - 2*padding, ICON_SIZE - 2*padding, 8, 8);
        }
    }
}