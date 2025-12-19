package homepage;

import javax.swing.*;

import homepage.newGUI.WeatherWidget;

import java.awt.*;

public class ClockDisplay extends JPanel {
    private JLabel bigTimeLabel;
    private JLabel dateTodayLabel;
    private WeatherWidget weatherWidget;
    private SearchBox searchBox;

    public ClockDisplay() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false); // Transparent to show gradient
        initializeComponents();
    }

    private void initializeComponents() {
        // Big time display
        add(createBigTimer());
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Date display
        add(createDatePanel());
        add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Weather widget
        weatherWidget = new WeatherWidget();
        add(weatherWidget);
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Search box
        searchBox = new SearchBox();
        add(searchBox);
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private JPanel createBigTimer() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(60, 20, 10, 20));
        
        bigTimeLabel = new JLabel();
        bigTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 72));
        bigTimeLabel.setForeground(Color.WHITE);
        
        panel.add(bigTimeLabel);
        
        return panel;
    }

    private JPanel createDatePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        dateTodayLabel = new JLabel();
        dateTodayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        dateTodayLabel.setForeground(new Color(255, 255, 255, 220)); // Slightly transparent white
        
        panel.add(dateTodayLabel);
        return panel;
    }

    public void updateTime() {
        bigTimeLabel.setText(TimeDate.getFormattedTime());
        dateTodayLabel.setText(TimeDate.getFormattedDay());
    }
}