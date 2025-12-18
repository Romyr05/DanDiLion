package apps.Clock.Stopwatch;

import javax.swing.*;
import apps.Clock.Base.ClockBaseApp;


public class Stopwatch extends ClockBaseApp {

    public Stopwatch() {
        super("Clock", 2); // Tab index 3 for Stopwatch
        this.add(createContentPanel());
    }

    public JPanel createContentPanel() {
        JPanel stopwatchPanel = new JPanel();
        stopwatchPanel.add(new JLabel("Stopwatch View - Implement your stopwatch UI here"));
        return stopwatchPanel;
    }
}

