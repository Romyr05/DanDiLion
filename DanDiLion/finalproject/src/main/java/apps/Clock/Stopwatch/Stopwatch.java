package apps.Clock.Stopwatch;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import apps.Clock.Base.ClockBaseApp;
import apps.Clock.NewGUI.CircleButton;

public class Stopwatch extends ClockBaseApp {
    private JLabel timeDisplay;
    private CircleButton stopButton;
    private JPanel lapsPanel;
    private CircleButton lapButton;
    
    private Timer timer;
    private long startTime = 0;
    private long elapsedTime = 0;
    private long lapStartTime = 0;
    private boolean isRunning = false;
    
    private ArrayList<LapTime> laps = new ArrayList<>();
    
    private class LapTime {
        String lapNumber;
        String lapTime;
        long lapMillis;
        
        LapTime(String lapNumber, String lapTime, long lapMillis) {
            this.lapNumber = lapNumber;
            this.lapTime = lapTime;
            this.lapMillis = lapMillis;
        }
    }

    public Stopwatch() {
        super("Clock", 2); // 2 -> Stopwatch index
        this.add(createContentPanel(), BorderLayout.CENTER);
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        
        // Top section with time display and buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20));
        
        // Time display
        timeDisplay = new JLabel("00:00.00");
        timeDisplay.setFont(new Font("Arial", Font.BOLD, 72));
        timeDisplay.setForeground(Color.WHITE);
        timeDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(timeDisplay);
        
        topPanel.add(Box.createVerticalStrut(40));
        
        // Buttons panel
        JPanel buttonPanel = createButtonPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(buttonPanel);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Laps list (Empty at first)
        lapsPanel = new JPanel();
        lapsPanel.setLayout(new BoxLayout(lapsPanel, BoxLayout.Y_AXIS));
        lapsPanel.setBackground(Color.BLACK);
        
        //Can scroll if full though
        JScrollPane scrollPane = new JScrollPane(lapsPanel);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.BLACK);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        return mainPanel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
        panel.setOpaque(false);
        
        // Lap Button (Gray Circle)
        lapButton = new CircleButton("Lap", new Color(60, 60, 60), Color.WHITE);
        lapButton.addActionListener(e -> recordLap());
        lapButton.setEnabled(false);
        
        // Stop/Start Button (Red/Green Circle)
        stopButton = new CircleButton("Start", new Color(0, 122, 255), Color.WHITE);
        stopButton.addActionListener(e -> toggleStopwatch());
        
        panel.add(lapButton);
        panel.add(stopButton);
        
        return panel;
    }

    //Stopwatch toggles
    private void toggleStopwatch() {
        if (!isRunning) {
            startStopwatch();
        } else {
            stopStopwatch();
        }
    }

    
    //CurrentTime millis -> from 1970

    private void startStopwatch() {
        isRunning = true;

        //Start of both the start time and laptstart
        startTime = System.currentTimeMillis() - elapsedTime;
        lapStartTime = System.currentTimeMillis() - elapsedTime;    
        
        stopButton.setText("Stop");
        stopButton.setBackground(new Color(139, 0, 0));
        lapButton.setText("Lap");
        lapButton.setEnabled(true);
        
        timer = new Timer(10, e -> updateDisplay());   //Runs the timer every 10 miliseconds then displays it
        timer.start();
    }

    private void stopStopwatch() {
        isRunning = false;
        if (timer != null) {
            timer.stop();
        }
        
        //Just changes the text
        stopButton.setText("Start");
        stopButton.setBackground(new Color(0, 122, 255));
        lapButton.setText("Reset");
        lapButton.setEnabled(true);
    }

    private void recordLap() {
        if (!isRunning) {
            // Reset button pressed
            resetStopwatch();
            return;
        }
        
        // Record lap
        long currentTime = System.currentTimeMillis();
        long lapTime = currentTime - lapStartTime;   //Time right now minus time that the stopwatch started
        
        String lapNumber = "Lap " + (laps.size() + 1);   
        String lapTimeStr = formatTime(lapTime);
        
        laps.add(new LapTime(lapNumber, lapTimeStr, lapTime));
        lapStartTime = currentTime;
        
        updateLapsDisplay();
    }

    private void resetStopwatch() {
        elapsedTime = 0;
        lapStartTime = 0;
        timeDisplay.setText("00:00.00");
        laps.clear();
        lapsPanel.removeAll();
        lapsPanel.revalidate();
        lapsPanel.repaint();
        lapButton.setEnabled(false);
    }

    private void updateDisplay() {
        elapsedTime = System.currentTimeMillis() - startTime;     //Time right now minus time started 
        timeDisplay.setText(formatTime(elapsedTime));
    }

    //Just formats the time to display
    private String formatTime(long millis) {
        long minutes = (millis / 60000) % 60;
        long seconds = (millis / 1000) % 60;
        long centiseconds = (millis / 10) % 100;
        
        return String.format("%02d:%02d.%02d", minutes, seconds, centiseconds);
    }


//Remove all and then put all the laps over again 

    private void updateLapsDisplay() {
        lapsPanel.removeAll();
        
        if (laps.isEmpty()) {
            lapsPanel.revalidate();
            lapsPanel.repaint();
            return;
        }
        
        //Loops all laps 
        // Find shortest and longest laps and index position of each on the list
        //first iteration set both shortest and longest to that and then compare after each
        long shortest = Long.MAX_VALUE;    
        long longest = Long.MIN_VALUE;
        int shortestIndex = -1;
        int longestIndex = -1;
        
        for (int i = 0; i < laps.size(); i++) {
            long lapMillis = laps.get(i).lapMillis;
            if (lapMillis < shortest) {
                shortest = lapMillis;
                shortestIndex = i;
            }
            if (lapMillis > longest) {
                longest = lapMillis;
                longestIndex = i;
            }
        }
        
        // Display laps in reverse order (newest -> oldest)  
        for (int i = laps.size() - 1; i >= 0; i--) {
            LapTime lap = laps.get(i);
            Color textColor = Color.WHITE;
            
            // Highlight shortest in green and longest in red
            if (laps.size() > 1) {
                if (i == shortestIndex) {
                    textColor = new Color(0, 255, 0);
                } else if (i == longestIndex) {
                    textColor = new Color(255, 0, 0);
                }
            }
            
            //Design for each lap
            JPanel lapPanel = createLapPanel(lap.lapNumber, lap.lapTime, textColor);
            lapsPanel.add(lapPanel);
            
            // Adds a separator or line everytime a lap is added
            if (i > 0) {
                JSeparator separator = new JSeparator();
                separator.setForeground(new Color(40, 40, 40));
                separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
                lapsPanel.add(separator);
            }
        }
        
        lapsPanel.revalidate();
        lapsPanel.repaint();
    }

    //Design for each lap panel
    private JPanel createLapPanel(String lapNumber, String lapTime, Color textColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        JLabel numberLabel = new JLabel(lapNumber);
        numberLabel.setForeground(textColor);
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel timeLabel = new JLabel(lapTime);
        timeLabel.setForeground(textColor);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        panel.add(numberLabel, BorderLayout.WEST);
        panel.add(timeLabel, BorderLayout.EAST);
        
        return panel;
    }
}