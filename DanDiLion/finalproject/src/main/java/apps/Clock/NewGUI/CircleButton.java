package apps.Clock.NewGUI;

import javax.swing.*;
import java.awt.*;

public class CircleButton extends JButton {

    public CircleButton(String text, Color bgColor, Color textColor) {
        super(text);

        setPreferredSize(new Dimension(90, 90));
        setBackground(bgColor);
        setForeground(textColor);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Draw circle background
        if (isEnabled()) {
            g2d.setColor(getBackground());
        } else {
            g2d.setColor(new Color(40, 40, 40));
        }
        g2d.fillOval(0, 0, getWidth(), getHeight());

        // Draw text
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2 - 2;
        g2d.drawString(getText(), x, y);
    }
}
