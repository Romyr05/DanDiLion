package apps.Clock.Clock;

import javax.swing.*;
import apps.Clock.Base.ClockBaseApp;


public class Clock extends ClockBaseApp {

    public Clock() {
        super("Clock", 1); // Tab index 1 for Clock
        this.add(createContentPanel());
    }

    private JPanel createContentPanel() {
        JPanel clockPanel = new JPanel();
        clockPanel.add(new JLabel("Clock View - Implement your clock UI here"));
        return clockPanel;
    }
}