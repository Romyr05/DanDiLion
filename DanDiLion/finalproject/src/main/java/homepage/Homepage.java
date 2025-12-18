package homepage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import apps.CalculatorApp.CalculatorApp;
import apps.Calendar.CalendarApp;
import apps.Clock.Alarm.Alarm;

public class Homepage extends BaseApp implements ActionListener {
    // Components
    private ClockDisplay clockDisplay;
    private AppGrid appGrid;
    private StatusBar statusBar;
    
    // Update timer
    private Timer clockTimer;

    public Homepage() {
        super("DanDieLion");
        initializeComponents();
        startClockTimer();
        this.setVisible(true);
    }

    private void initializeComponents() {
        // Status bar at top
        statusBar = new StatusBar();
        this.add(statusBar, BorderLayout.NORTH);
        
        // Clock display in center
        clockDisplay = new ClockDisplay();
        this.add(clockDisplay, BorderLayout.CENTER);
        
        // App grid at bottom
        appGrid = new AppGrid(this);
        this.add(appGrid, BorderLayout.SOUTH);
    }

    private void startClockTimer() {
        clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.start();
    }

    private void updateClock() {
        clockDisplay.updateTime();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "CLOCK":
                openApp(new Alarm());
                break;
            case "CALCULATOR":
                openApp(new CalculatorApp());
                break;
            case "CALENDAR":
                openApp(new CalendarApp());
                break;
        }
    }

    private void openApp(JFrame app) {
        this.dispose();
        app.setVisible(true);
    }
}