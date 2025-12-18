package homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Custom clickable image button for app icons
 * Acts like a button but displays images instead
 */
public class AppIconButton extends JLabel {
    private String imagePath;
    private BufferedImage image;
    private boolean isHovered = false;
    private static final int ICON_SIZE = 80;

    public AppIconButton(String imagePath) {
        this.imagePath = imagePath;
        loadImage();
        setupComponent();
        addMouseListeners();
    }

    private void loadImage() {
        try {
            if (imagePath != null && new File(imagePath).exists()) {
                image = ImageIO.read(new File(imagePath));
            } else {
                // Create placeholder image if file doesn't exist
                image = createPlaceholderImage();
            }
        } catch (Exception e) {
            image = createPlaceholderImage();
        }
    }

    private BufferedImage createPlaceholderImage() {
        BufferedImage placeholder = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = placeholder.createGraphics();
        
        // Enable antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw rounded rectangle background
        g2d.setColor(new Color(60, 60, 60));
        g2d.fillRoundRect(0, 0, ICON_SIZE, ICON_SIZE, 15, 15);
        
        // Draw border
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(1, 1, ICON_SIZE - 2, ICON_SIZE - 2, 15, 15);
        
        // Draw placeholder icon (simple app symbol)
        g2d.setColor(new Color(150, 150, 150));
        int padding = 20;
        g2d.fillRoundRect(padding, padding, ICON_SIZE - 2 * padding, ICON_SIZE - 2 * padding, 8, 8);
        
        g2d.dispose();
        return placeholder;
    }

    private void setupComponent() {
        setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
        setMinimumSize(new Dimension(ICON_SIZE, ICON_SIZE));
        setMaximumSize(new Dimension(ICON_SIZE, ICON_SIZE));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
    }

    private void addMouseListeners() {
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

            @Override
            public void mousePressed(MouseEvent e) {
                // Visual feedback on press
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (image != null) {
            // Apply hover effect
            if (isHovered) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            }
            
            // Draw scaled image
            g2d.drawImage(image, 0, 0, ICON_SIZE, ICON_SIZE, null);
            
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    /**
     * Change the image dynamically
     */
    public void setImage(String newImagePath) {
        this.imagePath = newImagePath;
        loadImage();
        repaint();
    }
}