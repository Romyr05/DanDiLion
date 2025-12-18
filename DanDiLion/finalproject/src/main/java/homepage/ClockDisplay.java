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
        setBackground(Color.BLUE);
        initializeComponents();
    }

    private void initializeComponents() {
        // Big time display
        add(createBigTimer());
        add(Box.createRigidArea(new Dimension(0, -200)));
        
        // Date display
        add(createDatePanel());
        add(Box.createRigidArea(new Dimension(0, -70)));
        
        // Weather widget
        weatherWidget = new WeatherWidget();
        add(weatherWidget);
        add(Box.createRigidArea(new Dimension(0, -70)));
        
        // Search box
        searchBox = new SearchBox();
        add(searchBox);
        add(Box.createRigidArea(new Dimension(0, 40)));
    }

    private JPanel createBigTimer() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        
        bigTimeLabel = new JLabel();
        bigTimeLabel.setFont(new Font("Arial", Font.BOLD, 70));
        bigTimeLabel.setForeground(Color.WHITE);
        
        panel.add(Box.createRigidArea(new Dimension(0, 150)));
        panel.add(bigTimeLabel);
        
        return panel;
    }

    private JPanel createDatePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setOpaque(false);
        
        dateTodayLabel = new JLabel();
        dateTodayLabel.setFont(new Font("Arial", Font.BOLD, 40));
        dateTodayLabel.setForeground(Color.WHITE);
        
        panel.add(dateTodayLabel);
        return panel;
    }

    public void updateTime() {
        bigTimeLabel.setText(TimeDate.getFormattedTime());
        dateTodayLabel.setText(TimeDate.getFormattedDay());
    }
}