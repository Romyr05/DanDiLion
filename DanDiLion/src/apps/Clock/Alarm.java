package apps.Clock;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Alarm extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel alarmListPanel;
    private JButton addButton;
    private JScrollPane scrollPane;

    public Alarm() {
        // Frame setup
        this.setTitle("Alarm");
        this.setSize(400, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create components
        headerPanel = createHeaderPanel();
        alarmListPanel = createAlarmListPanel();
        scrollPane = new JScrollPane(alarmListPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        addButton = createAddButton();

        // Add to frame
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(addButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            System.out.println("Add alarm clicked");
            // Add functionality to create new alarm
        }
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(new Color(30, 30, 30));
        header.setPreferredSize(new Dimension(0, 120));

        // Status bar
        JPanel statusBar = createStatusBar();
        header.add(statusBar, BorderLayout.NORTH);

        // Tab navigation
        JPanel tabPanel = createTabPanel();
        header.add(tabPanel, BorderLayout.SOUTH);

        return header;
    }

    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(new Color(30, 30, 30));
        statusBar.setPreferredSize(new Dimension(0, 35));
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Left side - time
        JLabel timeLabel = new JLabel("02:08");
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Right side - icons and battery
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightPanel.setOpaque(false);
        
        JLabel batteryLabel = new JLabel("53%");
        batteryLabel.setForeground(Color.WHITE);
        batteryLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        rightPanel.add(batteryLabel);

        statusBar.add(timeLabel, BorderLayout.WEST);
        statusBar.add(rightPanel, BorderLayout.EAST);

        return statusBar;
    }

    private JPanel createTabPanel() {
        JPanel tabPanel = new JPanel(new GridLayout(1, 4));
        tabPanel.setBackground(new Color(30, 30, 30));
        tabPanel.setPreferredSize(new Dimension(0, 80));

        String[] tabs = {"Alarm", "Clock", "Timer", "Stopwatch"};
        String[] icons = {"⏰", "🕐", "⏳", "⏱"};

        for (int i = 0; i < tabs.length; i++) {
            JPanel tab = createTab(icons[i], tabs[i], i == 0);
            tabPanel.add(tab);
        }

        return tabPanel;
    }

    private JPanel createTab(String icon, String label, boolean isActive) {
        JPanel tab = new JPanel();
        tab.setLayout(new BoxLayout(tab, BoxLayout.Y_AXIS));
        tab.setBackground(new Color(30, 30, 30));
        tab.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel(label);
        textLabel.setForeground(isActive ? new Color(100, 150, 255) : Color.LIGHT_GRAY);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tab.add(iconLabel);
        tab.add(Box.createRigidArea(new Dimension(0, 5)));
        tab.add(textLabel);

        if (isActive) {
            tab.setBorder(BorderFactory.createCompoundBorder(
                tab.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(100, 150, 255))
            ));
        }

        return tab;
    }

    private JPanel createAlarmListPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(20, 20, 20));

        // Sample alarm times
        String[] alarmTimes = {"07:02", "07:03", "07:04", "07:05", "07:06", "07:08", "07:09", "07:10", "07:15", "07:20", "07:30"};

        for (String time : alarmTimes) {
            JPanel alarmItem = createAlarmItem(time);
            listPanel.add(alarmItem);
        }

        return listPanel;
    }

    private JPanel createAlarmItem(String time) {
        JPanel item = new JPanel();
        item.setLayout(new BorderLayout());
        item.setBackground(new Color(25, 25, 25));
        item.setMaximumSize(new Dimension(400, 100));
        item.setPreferredSize(new Dimension(400, 100));
        item.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Left side - time and label
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setForeground(Color.LIGHT_GRAY);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dayLabel = new JLabel("Today");
        dayLabel.setForeground(Color.GRAY);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dayLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(timeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(dayLabel);

        // Right side - toggle switch and dropdown
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);

        // Toggle switch (simplified)
        JToggleButton toggle = new JToggleButton();
        toggle.setPreferredSize(new Dimension(50, 25));
        toggle.setBackground(new Color(60, 60, 60));
        toggle.setFocusPainted(false);
        toggle.setBorderPainted(false);

        // Dropdown button
        JButton dropdownBtn = new JButton("ᐯ");
        dropdownBtn.setPreferredSize(new Dimension(30, 30));
        dropdownBtn.setBackground(new Color(25, 25, 25));
        dropdownBtn.setForeground(Color.GRAY);
        dropdownBtn.setBorderPainted(false);
        dropdownBtn.setFocusPainted(false);

        rightPanel.add(toggle);
        rightPanel.add(dropdownBtn);

        item.add(leftPanel, BorderLayout.WEST);
        item.add(rightPanel, BorderLayout.EAST);

        return item;
    }

    private JButton createAddButton() {
        JButton addBtn = new JButton("+");
        addBtn.setPreferredSize(new Dimension(60, 60));
        addBtn.setFont(new Font("Arial", Font.BOLD, 30));
        addBtn.setBackground(new Color(100, 150, 255));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setBorderPainted(false);
        addBtn.addActionListener(this);

        // Create a panel to center the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(20, 20, 20));
        buttonPanel.setPreferredSize(new Dimension(0, 80));
        buttonPanel.add(addBtn);

        return addBtn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Alarm());
    }
}