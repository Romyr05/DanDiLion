package apps.notesapp.brainventory;

import java.awt.*;
import javax.swing.*;

public class NavButton extends MyButton {

    public NavButton(String text, Color defaultColor, Color hoverColor) {
        super(text, defaultColor, hoverColor);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(hoverColor.darker());
        } else if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(defaultColor);
        }

        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
        super.paintComponent(g); // draw text
    }
}
