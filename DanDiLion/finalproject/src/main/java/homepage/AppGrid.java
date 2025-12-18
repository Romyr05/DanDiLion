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
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        
        initializeComponents();
    }

    private void initializeComponents() {
        // Upper 8 apps (2 rows of 4)
        add(createUpperApps());
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Scroll indicator dots
        add(new ScrollIndicator(4, 0));
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Lower 4 apps (1 row of 4)
        add(createLowerApps());
    }

    private JPanel createUpperApps() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 15, 15));
        panel.setMaximumSize(new Dimension(480, 180));
        panel.setBackground(Color.GRAY);
        
        // Add 8 placeholder app icons
        for (int i = 1; i <= 8; i++) {
            panel.add(createIconButton("assets/app" + i + ".png", null));
        }
        
        return panel;
    }

    private JPanel createLowerApps() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 15));
        panel.setMaximumSize(new Dimension(480, 85));
        panel.setBackground(Color.GRAY);
        
        // Add functional app icons with actions
        panel.add(createIconButton("src/main/java/assets/clockImage.jpg", "CLOCK"));
        panel.add(createIconButton("src/main/java/assets/calcImage.jpg", "CALCULATOR"));
        panel.add(createIconButton("src/main/java/assets/calendarImage.jpg", "CALENDAR"));
        panel.add(createIconButton("assets/app4.png", null));
        
        return panel;
    }

    private AppIconButton createIconButton(String imagePath, String actionCommand) {
        AppIconButton iconButton = new AppIconButton(imagePath);
        
        // Add click action if command is provided
        if (actionCommand != null && actionListener != null) {
            iconButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Create ActionEvent and pass to listener
                    actionListener.actionPerformed(
                        new java.awt.event.ActionEvent(iconButton, 
                            java.awt.event.ActionEvent.ACTION_PERFORMED, 
                            actionCommand)
                    );
                }
            });
        }
        
        return iconButton;
    }
}