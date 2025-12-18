package homepage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import apps.Clock.Alarm.Alarm;
import homepage.newGUI.BatteryIcon;
import homepage.newGUI.SearchIcon;
import homepage.newGUI.WifiIcon;

public class Homepage extends BaseApp implements ActionListener{
    JButton clockButton;
    JPanel panel, appPanel;
    JPanel panelTime, statusBar;
    JPanel upperApps, lowerApps;
    private JLabel bigTimerLabel;
    private JButton upperBtn1, upperBtn2, upperBtn3, upperBtn4, upperBtn5, upperBtn6, upperBtn7, upperBtn8;
    private JButton lowerBtn1, lowerBtn2, lowerBtn3, lowerBtn4;
    private JLabel dateTodayLabel;
    private Timer clockTimer;
    private StatusTimePanel statusTimePanel;
    int arc = 40;

    public Homepage(){
        //Frame
        super("DanDieLion");
        
        initializeComponents();

        startClockTimer();

        this.setVisible(true);
        validate();
    }

    private void initializeComponents(){
        statusBar = createStatusBar();
        appPanel = createAppPanel();  
        panelTime = timePanel();

        this.add(statusBar, BorderLayout.NORTH);
        this.add(panelTime, BorderLayout.CENTER);
        this.add(appPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clockButton){
            this.dispose();
            Alarm clockApp = new Alarm();
            clockApp.setVisible(true);
        }
    }

    public void startClockTimer(){
        // Update every 1000 milliseconds (1 second)
        clockTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        clockTimer.start();
    }


    public void updateClock(){
        String timestring = TimeDate.getFormattedTime();
        statusTimePanel.updateTime(timestring);    
        bigTimerLabel.setText(timestring);

        if(dateTodayLabel != null) {
            dateTodayLabel.setText(TimeDate.getFormattedDay());
        }
    }

    public JPanel bigTimer(){
        JPanel bigTimer = new JPanel();
        bigTimer.setOpaque(false);
        bigTimer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        bigTimerLabel = new JLabel();
        bigTimerLabel.setFont(new Font("Arial", Font.BOLD, 70));
        bigTimerLabel.setForeground(Color.WHITE);


        bigTimer.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));

        // Add spacing
        bigTimer.add(Box.createRigidArea(new Dimension(0, 150)));

        bigTimer.add(bigTimerLabel);

        updateClock();

        return bigTimer;
    }

    public JPanel dateToday(){
        JPanel dateToday = new JPanel();
        dateToday.setOpaque(false);
        dateTodayLabel = new JLabel(TimeDate.getFormattedDay());
        dateToday.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        dateTodayLabel.setFont(new Font("Arial", Font.BOLD, 40));
        dateTodayLabel.setForeground(Color.WHITE);

        dateToday.add(dateTodayLabel);
        return dateToday;
    }

    public JPanel weatherPanel(){
        JPanel weatherPanel = new JPanel();
        weatherPanel.setOpaque(false);
        weatherPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));

        JPanel weatherIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                
                // Draw sun
                // Sun circle
                g2d.fillOval(5, 5, 20, 20);
                
                // Sun rays
                int centerX = 15;
                int centerY = 15;
                int rayLength = 6;
                for (int i = 0; i < 8; i++) {
                    double angle = Math.PI * 2 * i / 8;
                    int x1 = centerX + (int)(Math.cos(angle) * 13);
                    int y1 = centerY + (int)(Math.sin(angle) * 13);
                    int x2 = centerX + (int)(Math.cos(angle) * (13 + rayLength));
                    int y2 = centerY + (int)(Math.sin(angle) * (13 + rayLength));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(x1, y1, x2, y2);
                }
                
                // Draw cloud (semi-transparent)
                g2d.setColor(new Color(255, 255, 255, 180));
                g2d.fillOval(18, 18, 12, 10);
                g2d.fillOval(25, 20, 10, 8);
                g2d.fillOval(22, 22, 10, 8);
            }
        };
        weatherIcon.setPreferredSize(new Dimension(50, 45));
        weatherIcon.setOpaque(false);


        //Temperature
        JLabel tempLabel = new JLabel("+18°C");
        tempLabel.setFont(new Font("Arial", Font.BOLD, 30));
        tempLabel.setForeground(Color.WHITE);
        
        weatherPanel.add(weatherIcon);
        weatherPanel.add(tempLabel);

        return weatherPanel;
    }

    public JPanel searchBox(){
        JPanel searchBox = new JPanel();
        searchBox.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchBox.setPreferredSize(new Dimension(380, 45));
        searchBox.setMaximumSize(new Dimension(380, 45));
        searchBox.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 255, 100), 3, true));
        searchBox.setOpaque(false);

        // Search icon


        JLabel searchLabel =new JLabel("Search");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setOpaque(false);

        SearchIcon searchIcon = new SearchIcon();
        
        searchBox.add(searchIcon);
        searchBox.add(searchLabel); 

        return searchBox;
    }


    public JPanel timePanel(){
        JPanel panelTime = new JPanel();
        panelTime.setLayout(new BoxLayout(panelTime,BoxLayout.PAGE_AXIS));
        panelTime.setPreferredSize(new Dimension(0,100));
        panelTime.setBackground(Color.blue);

        panelTime.add(bigTimer());

       // Add small spacing between time and date
        panelTime.add(Box.createRigidArea(new Dimension(0, -200)));

        panelTime.add(dateToday());

        panelTime.add(Box.createRigidArea(new Dimension(0,-70)));

        panelTime.add(weatherPanel());

        panelTime.add(Box.createRigidArea(new Dimension(0,-70)));

        panelTime.add(searchBox());

        panelTime.add(Box.createRigidArea(new Dimension(0,40)));

        return panelTime;
    }


    public JPanel statusBatteryWifiPanel(){
        JPanel statusBatteryWifiPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        statusBatteryWifiPanel.setOpaque(false);
        
        WifiIcon wifiIcon = new WifiIcon();
        BatteryIcon batteryIcon = new BatteryIcon();
        
        statusBatteryWifiPanel.add(wifiIcon);
        statusBatteryWifiPanel.add(batteryIcon);
        
        return statusBatteryWifiPanel;
    }


    public JPanel createStatusBar(){
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(0,35));
        statusBar.setBackground(new Color(130, 0, 220)); // Purple color like in the image
        statusBar.setOpaque(true);

        statusTimePanel = new StatusTimePanel(TimeDate.getFormattedTime());
        statusBar.add(statusTimePanel,BorderLayout.WEST);
        statusBar.add(statusBatteryWifiPanel(),BorderLayout.EAST);


        return statusBar;
    }
    
    
    public JPanel createAppPanel(){
        JPanel appPanel = new JPanel();
        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.PAGE_AXIS));   //Layout that stacks vertically or horizontally
        appPanel.setPreferredSize(new Dimension(0, 400));
        appPanel.setBackground(Color.gray);
        appPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        
        // Add upper 8 apps
        JPanel upperApps = createUpperApps();
        appPanel.add(upperApps);
        
        // Add spacing
        appPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Add scroll points
        JPanel scrollPoints = createScrollPoints();
        appPanel.add(scrollPoints);
        appPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Add spacing
        appPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Add lower 4 apps
        JPanel lowerApps = createLowerApps();
        appPanel.add(lowerApps);
        
        return appPanel;
    }

    public JButton createIconButton(String label) {
        ImageIcon icon = new ImageIcon("assets/clock.jpg");
        JButton btn = new JButton(label);
        btn.setIcon(icon);
        btn.setPreferredSize(new Dimension(80, 80));

        btn.setText(null);

        return btn;
    }

    public JPanel createUpperApps(){
        JPanel upperApps = new JPanel();     
        upperApps.setLayout(new GridLayout(2, 4, 15, 15));
        upperApps.setMaximumSize(new Dimension(480, 180));
        upperApps.setBackground(Color.gray);
         
        upperBtn1 = createIconButton("Clock");
        upperBtn2 = createIconButton("UApp 2");
        upperBtn3 = createIconButton("UApp 3");
        upperBtn4 = createIconButton("UApp 4");
        upperBtn5 = createIconButton("UApp 5");
        upperBtn6 = createIconButton("UApp 6");
        upperBtn7 = createIconButton("UApp 7");
        upperBtn8 = createIconButton("UApp 8");

        upperApps.add(upperBtn1);
        upperApps.add(upperBtn2);
        upperApps.add(upperBtn3);
        upperApps.add(upperBtn4);
        upperApps.add(upperBtn5);
        upperApps.add(upperBtn6);
        upperApps.add(upperBtn7);
        upperApps.add(upperBtn8);


        return upperApps;
    }

    public JPanel createScrollPoints(){
        JPanel scrollPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        scrollPanel.setBackground(Color.gray);
        scrollPanel.setMaximumSize(new Dimension(500, 20));
        
        for (int i = 0; i < 4; i++) {
            JPanel dot = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(getBackground());
                    g2d.fillOval(0, 0, getWidth(), getHeight());
                }
            };
            dot.setPreferredSize(new Dimension(10, 10));
            dot.setBackground(i == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            dot.setOpaque(false);
            scrollPanel.add(dot);
        }
        
        return scrollPanel;
    }

    public JPanel createLowerApps(){
        JPanel lowerApps = new JPanel(new GridLayout(1, 4, 15, 15));
        lowerApps.setMaximumSize(new Dimension(480, 85));
        lowerApps.setBackground(Color.gray);

        clockButton = createIconButton("App 1");
        lowerBtn2 = createIconButton("App 2");
        lowerBtn3 = createIconButton("App 3");
        lowerBtn4 = createIconButton("App 4");

        lowerApps.add(clockButton);
        lowerApps.add(lowerBtn2);
        lowerApps.add(lowerBtn3);
        lowerApps.add(lowerBtn4);

        clockButton.addActionListener(this);
            
        return lowerApps;
    }

}

