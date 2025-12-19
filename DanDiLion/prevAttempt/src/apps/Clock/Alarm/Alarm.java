package apps.Clock.Alarm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import apps.Clock.Base.ClockBaseApp;
import apps.Clock.NewGUI.CirclePlusButton;
import apps.Clock.NewGUI.CustomToggle;



public class Alarm extends ClockBaseApp {
    private JPanel alarmListPanel = new JPanel();  //getting it out so that we can get its value and call it
    private AlarmChecker alarmChecker;

    public Alarm() {
        super("Clock", 0); // title and 0 -> Alarm index needed for action listener\

        // Start the alarm checker thread
        alarmChecker = AlarmChecker.getInstance();

        this.add(createContentPanel(), BorderLayout.CENTER);
    }

    public JComponent createContentPanel() {
        //Layered Pane for the button so that we can overlap the button to the alarms
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // Use absolute positioning (hard to use normal layout)
        
        // Scrollable alarmlist
        JScrollPane scrollPane = createScrollPane(createAlarmListPanel());

        
        // Create the floating add button (middle)
        CirclePlusButton addButton = new CirclePlusButton();
        
        //Actionlistener to the function using lambda
        addButton.addActionListener(e -> showAddAlarmDialog());
        
        layeredPane.add(scrollPane, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(addButton, JLayeredPane.PALETTE_LAYER);
        
        //Component Listener since this is a component
        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            //this is where we set the sizes
            public void componentResized(ComponentEvent e) {
                int width = layeredPane.getWidth();
                int height = layeredPane.getHeight();
                scrollPane.setBounds(0, 0, width, height);
                addButton.setBounds(width - 300, height - 90, 70, 70);
            }
        });
        
        return layeredPane;
    }

    private void showAddAlarmDialog() {
        // Simple input dialog for time
        String input = JOptionPane.showInputDialog(
            this,                               //Ensures that the dialog is in the middle of the screen
            "Enter alarm time (HH:MM):", 
            "Add Alarm", 
            JOptionPane.PLAIN_MESSAGE
        );
        
        // Check if user clicked OK and trim it then add it as alarm
        if (input != null && !input.trim().isEmpty()) {
            addAlarm(input.trim());
        }
    }

    //Adds alarm
    private void addAlarm(String time) {
        if (alarmListPanel == null){
            return;
        }

        JPanel newAlarm = createAlarmItem(time);
        alarmListPanel.add(newAlarm);
        alarmListPanel.revalidate(); // tell layout manager to recalc
        alarmListPanel.repaint();    // redraw
    }

    //Scroll Pane with its components 
    private JScrollPane createScrollPane(JPanel content) {
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    //list of alarms
    private JPanel createAlarmListPanel() {
        alarmListPanel = new JPanel();
        alarmListPanel.setLayout(new BoxLayout(alarmListPanel, BoxLayout.Y_AXIS));

        String[] exampleTimes = {"07:02", "07:03", "07:04", "07:05", "07:06", "07:08", "07:09", "07:10", "07:15", "07:20", "07:30"};

        for (String time : exampleTimes) {
            JPanel alarmItem = createAlarmItem(time);
            alarmListPanel.add(alarmItem);
        }

        return alarmListPanel;
    }

    //AlarmItems 
    private JPanel createAlarmItem(String time) {
        JPanel item = new JPanel();
        item.setLayout(new BorderLayout());
        item.setBackground(new Color(25, 25, 25));
        item.setMaximumSize(new Dimension(555, 130));
        item.setPreferredSize(new Dimension(555, 130));
        item.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Left side - time and label
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setForeground(Color.LIGHT_GRAY);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dayLabel = new JLabel("Today");
        dayLabel.setForeground(Color.GRAY);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dayLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(timeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(dayLabel);

        // Right side - toggle switch  (new GUI)
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);

        CustomToggle toggle = new CustomToggle();
        toggle.setPreferredSize(new Dimension(80, 40));

        rightPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        rightPanel.add(toggle);

        // NEW: Add listener to re-register when toggle changes
        toggle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Wait for toggle animation to complete, then re-register
                Timer timer = new Timer(100, evt -> {
                    alarmChecker.registerAlarm(time, toggle);
                    ((Timer)evt.getSource()).stop();
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        item.add(leftPanel, BorderLayout.WEST);
        item.add(rightPanel, BorderLayout.EAST);

        return item;

    }



}