package apps.Clock.Alarm;

import apps.Clock.NewGUI.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.awt.Toolkit;



public class AlarmChecker extends Thread {
    private static AlarmChecker instance;
    private List<AlarmData> alarms = new ArrayList<>();
    private boolean running = true;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
   
    private AlarmChecker() {
        setDaemon(true); // Thread dies when app closes
    }
    
    // Singleton pattern to ensure only one checker runs
    public static AlarmChecker getInstance() {
        if (instance == null) {
            instance = new AlarmChecker();
            instance.start();
        }
        return instance;
    }
    
    // Add or update an alarm
    public void registerAlarm(String time, CustomToggle toggle) {
        // Remove existing alarm with same time
        alarms.removeIf(a -> a.time.equals(time));
        
        // Add new alarm
        AlarmData alarm = new AlarmData(time, toggle);
        alarms.add(alarm);
    }
    
    // Remove an alarm
    public void unregisterAlarm(String time) {
        alarms.removeIf(a -> a.time.equals(time));
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                // Get current time (HH:mm format)
                String currentTime = LocalTime.now().format(timeFormatter);

                // Check each alarm
                for (AlarmData alarm : new ArrayList<>(alarms)) {
                    if (alarm.toggle.isOn() && alarm.time.equals(currentTime)) {
                        // Only trigger once per minute
                        if (!alarm.hasTriggeredThisMinute) {
                            playSound();
                            alarm.hasTriggeredThisMinute = true;
                        }
                    } else if (!alarm.time.equals(currentTime)) {
                        // Reset trigger flag when minute changes
                        alarm.hasTriggeredThisMinute = false;
                    }
                }
                // Check every second
                Thread.sleep(1000);
                
            } catch (InterruptedException e) {
                System.out.println("run failed");
                running = false;
            }
        }
    }
    
    private synchronized void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }

    private static class AlarmData {
        String time;
        CustomToggle toggle;
        boolean hasTriggeredThisMinute = false;
        
        AlarmData(String time, CustomToggle toggle) {
            this.time = time;
            this.toggle = toggle;
        }
    }
}

