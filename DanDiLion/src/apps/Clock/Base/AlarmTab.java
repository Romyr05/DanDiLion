package apps.Clock.Base;

import java.awt.*;
import javax.swing.*;

//Tab component

public class AlarmTab extends JPanel {
    
    private String icon;
    private String label;
    private boolean isActive;

    public AlarmTab(String icon, String label, boolean isActive) {
        this.icon = icon;
        this.label = label;
        this.isActive = isActive;
        
        initializeUI();
    }

    //Initialization for the layout of the tabs
    private void initializeUI() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // Icon label
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.BOLD, 24));
        iconLabel.setForeground(isActive ? Color.WHITE : Color.GRAY);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Text label
        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setForeground(isActive ? Color.WHITE : Color.GRAY);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(iconLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(textLabel);
        contentPanel.add(Box.createVerticalGlue());

        // Active indicator (Line beneath the )
        if (isActive) {
            this.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));   //Only make the south border white
        }

        this.add(contentPanel, BorderLayout.CENTER);  //add the content panel (whole panel for tabs)
    }

}