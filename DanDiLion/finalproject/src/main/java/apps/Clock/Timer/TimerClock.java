package apps.Clock.Timer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import apps.Clock.Base.ClockBaseApp;
import apps.Clock.NewGUI.CirclePanel;
import apps.Clock.NewGUI.PauseButton;
import apps.Clock.NewGUI.StopButton;

public class TimerClock extends ClockBaseApp {
    private StopButton StopButton;
    private PauseButton pauseButton;
    private CirclePanel circlePanel;
    
    private Timer timer;
    private int totalSeconds = 0;
    private int remainingSeconds = 0;
    private boolean isRunning = false;

    public TimerClock() {
        super("Clock", 1); // 1 -> Timer index
        this.add(createContentPanel(), BorderLayout.CENTER);
    }


    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(25, 25, 25));
        
        // Add spacing from top
        mainPanel.add(Box.createVerticalStrut(120));
        
        // Circle with time display
        circlePanel = new CirclePanel();
        circlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Mouse listner that shows set time dialog
        circlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isRunning) {
                    showTimeSetDialog();
                    startTimer();
                }
            }
        });
        mainPanel.add(circlePanel);
        
        // Control Buttons (Square)
        JPanel buttonPanel = createButtonPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(buttonPanel);
    

        mainPanel.add(Box.createVerticalStrut(150));    //Adds spaces from the bottom
        
        mainPanel.add(Box.createVerticalGlue()); //Glues all the deadspaces

        return mainPanel;
    }

    //set dialog
    private void showTimeSetDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        //Hours,minutes and seconds model
        JLabel hourLabel = new JLabel("Hours:");
        SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
        JSpinner hourSpinner = new JSpinner(hourModel);
        
        JLabel minuteLabel = new JLabel("Minutes:");
        SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner minuteSpinner = new JSpinner(minuteModel);
        
        JLabel secondLabel = new JLabel("Seconds:");
        SpinnerNumberModel secondModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner secondSpinner = new JSpinner(secondModel);
        
        panel.add(hourLabel);
        panel.add(hourSpinner);
        panel.add(minuteLabel);
        panel.add(minuteSpinner);
        panel.add(secondLabel);
        panel.add(secondSpinner);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Set Timer", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        
        if (result == JOptionPane.OK_OPTION) {
            int hours = (Integer) hourSpinner.getValue();    //Since the hourSpinner store Integer object we need to typecast it
            int minutes = (Integer) minuteSpinner.getValue();
            int seconds = (Integer) secondSpinner.getValue();
            
            totalSeconds = hours * 3600 + minutes * 60 + seconds;
            remainingSeconds = totalSeconds;
            
            if (totalSeconds > 0) {
                updateDisplay();
                circlePanel.setProgress(1.0f);    //Circle blue thingy color
            }
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel.setOpaque(false);
        
        // Stop Button (Red Square with stop icon)
        StopButton = new StopButton();
        StopButton.addActionListener(e -> {
            if (isRunning) {
                resetTimer();
            }
        });
        
        // Pause Button (Orange Square with pause icon)
        pauseButton = new PauseButton();
        pauseButton.addActionListener(e -> pauseTimer());    //Pause timer when clicked
        pauseButton.setEnabled(false);   //Cannot click it
        
        panel.add(StopButton);
        panel.add(pauseButton);
        
        return panel;
    }

    private void startTimer() {
        if (totalSeconds == 0) {
            return;
        }
        
        isRunning = true;
        pauseButton.setEnabled(true);
        
        // Start countdown
        timer = new Timer(1000, e -> {
            remainingSeconds--;
            float progress = (float) remainingSeconds / totalSeconds;   //made it float to work
            updateDisplay();
            circlePanel.setProgress(progress);   //update the progress to change ui
            
            if (remainingSeconds <= 0) {
                timer.stop();
                isRunning = false;
                timerFinished();
            }
        });
        timer.start();
    }

    private void pauseTimer() {
        if (isRunning && timer != null) {
            timer.stop();
            isRunning = false;
        } else if (!isRunning && remainingSeconds > 0) {
            startTimer();

        }
    }

    private void resetTimer() {
        if (timer != null) {
            timer.stop();
        }
        isRunning = false;
        remainingSeconds = 0;
        totalSeconds = 0;
        
        circlePanel.setTimeText("67:67");
        circlePanel.setProgress(0);    //set it back to 0
        
        pauseButton.setEnabled(false);
    }

    private void updateDisplay() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        
        circlePanel.setTimeText(String.format("%02d:%02d", minutes, seconds));
    }

    private void timerFinished() {
        java.awt.Toolkit.getDefaultToolkit().beep();
        resetTimer();
    }
}