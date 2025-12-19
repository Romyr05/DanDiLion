package apps.Clock.Alarm;

import apps.Clock.NewGUI.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


public class AlarmChecker extends Thread {
    private static AlarmChecker instance;
    private List<AlarmData> alarms = new ArrayList<>();
    private boolean running = true;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private Clip alarmClip;
   
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
                playSound();

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
        try {
            File soundFile = new File("path/to/alarm/sound.wav");
            if (soundFile.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                alarmClip = AudioSystem.getClip();
                alarmClip.open(audioInputStream);
                alarmClip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
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