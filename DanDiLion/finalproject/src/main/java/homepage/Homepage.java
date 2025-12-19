package homepage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import apps.CalculatorApp.CalculatorApp;
import apps.Calendar.CalendarApp;
import apps.Clock.Alarm.Alarm;
import apps.NotesApp.NotesApp;

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
        // Create custom content pane with gradient background
        JPanel contentPane = new GradientPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        // Status bar at top
        statusBar = new StatusBar();
        contentPane.add(statusBar, BorderLayout.NORTH);
        
        // Clock display in center
        clockDisplay = new ClockDisplay();
        clockDisplay.setOpaque(false);
        contentPane.add(clockDisplay, BorderLayout.CENTER);
        
        // App grid at bottom
        appGrid = new AppGrid(this);
        appGrid.setOpaque(false);
        contentPane.add(appGrid, BorderLayout.SOUTH);
        
        // Force layout and repaint
        contentPane.revalidate();
        contentPane.repaint();
    }
    
    // Custom panel with dark-themed wallpaper background
    private class GradientPanel extends JPanel {
        public GradientPanel() {
            setOpaque(true); // Make it opaque so background shows
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int height = getHeight();
            int width = getWidth();
            
            // Dark-themed gradient wallpaper - subtle dark gray to black
            Color[] colors = {
                new Color(20, 20, 25),    // Very dark gray-blue at top
                new Color(15, 15, 20),    // Darker gray
                new Color(10, 10, 15),    // Almost black
                new Color(8, 8, 12),      // Deep black
                new Color(12, 12, 18)     // Slightly lighter at bottom
            };
            
            float[] stops = {0.0f, 0.25f, 0.5f, 0.75f, 1.0f};
            
            // Use LinearGradientPaint for smooth dark gradient
            LinearGradientPaint gradient = new LinearGradientPaint(
                0, 0, 0, height, stops, colors
            );
            
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
            
            // Add subtle texture/noise for wallpaper effect
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.03f));
            for (int i = 0; i < 100; i++) {
                int x = (int)(Math.random() * width);
                int y = (int)(Math.random() * height);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(x, y, 2, 2);
            }
            
            g2d.dispose();
        }
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
            case "NOTES":
                openApp(new NotesApp());
                break;
        }
    }

    private void openApp(JFrame app) {
        this.dispose();
        app.setVisible(true);
    }
}