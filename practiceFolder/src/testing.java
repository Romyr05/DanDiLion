package src;

import javax.swing.*;
import java.awt.*;

public class testing extends JFrame {

    public testing() {
        setTitle("Smartphone Simulator");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // removes default window borders
        setLocationRelativeTo(null); // center on screen

        // Add custom panel
        RoundedPanel phonePanel = new RoundedPanel();
        phonePanel.setBackground(Color.BLACK);
        phonePanel.setLayout(null); // absolute positioning
        add(phonePanel);

        // Example: add "screen" inside phone
        JTextField screen = new JTextField("Welcome!");
        screen.setBounds(40, 50, 220, 400); // adjust to fit your "screen"
        screen.setHorizontalAlignment(JTextField.CENTER);
        screen.setEditable(false);
        phonePanel.add(screen);

        setVisible(true);
    }

    // Custom panel with rounded corners
    class RoundedPanel extends JPanel {
        private int cornerRadius = 40; // radius of rounded corners

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(testing::new);
    }
}
