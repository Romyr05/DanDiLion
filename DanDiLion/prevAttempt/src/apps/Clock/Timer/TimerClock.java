package apps.Clock.Timer;

import javax.swing.*;
import apps.Clock.Base.ClockBaseApp;


public class TimerClock extends ClockBaseApp {

    public TimerClock() {
        super("Clock", 1); // Tab index 2 for Timer
        this.add(createContentPanel());
    }

    
    private JPanel createContentPanel() {
        JPanel timerPanel = new JPanel();
        timerPanel.add(new JLabel("Timer View - Implement your timer UI here"));
        return timerPanel;

    }
}