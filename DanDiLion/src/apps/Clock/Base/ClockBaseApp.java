package apps.Clock.Base;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import apps.Clock.Alarm.Alarm;
import apps.Clock.Clock.Clock;
import apps.Clock.Stopwatch.Stopwatch;
import apps.Clock.Timer.TimerClock;
import homepage.StatusBar;
import homepage.BaseApp;


//Base Class for all the Clock Apps since they all have the same header component

public abstract class ClockBaseApp extends BaseApp implements ActionListener {

    protected int activeTabIndex = 0;
    protected JPanel clockTabs;
    
    private final String[] tabs = {"Alarm", "Clock", "Timer", "Stopwatch"};
    private final String[] icons = {"a", "b", "c", "d"};

    // 0-> Alarm, 1 -> Clock, 2 -> Timer, 3 -> Stopwatch

    public ClockBaseApp(String title, int defaultTabIndex) {
        super(title);
        this.activeTabIndex = defaultTabIndex;   //Different tab index based on app
        this.add(new StatusBar(), BorderLayout.NORTH);
        
        this.add(createHeaderPanel(), BorderLayout.SOUTH);
    }

    //Creates a header panel that tabs
    protected JPanel createHeaderPanel() {
        JPanel headerTab = new JPanel();
        headerTab.setLayout(new BorderLayout());
        headerTab.setBackground(Color.black);
        headerTab.setPreferredSize(new Dimension(0, 120));


        headerTab.add(buildTabPanel(), BorderLayout.CENTER);

        return headerTab;
    }

    //Tab Panels
    protected JPanel buildTabPanel() {
        clockTabs = new JPanel(new GridLayout(1, 4));
        clockTabs.setOpaque(false);

        updateTabs();
        return clockTabs;
    }

    //updates tabs based on the activeTabIndex (Active or not active)
    protected void updateTabs() {
        if (clockTabs != null) {
            clockTabs.removeAll();     //remove all tabs if there is component inside
        }

        for (int i = 0; i < tabs.length; i++) { //Creating tabs
            AlarmTab tab = new AlarmTab(icons[i], tabs[i], i == activeTabIndex);      //activeTabIndex based on app

            int index = i;     


            tab.addMouseListener(new MouseAdapter() {   //will just run if actually clicked
                @Override
                public void mouseClicked(MouseEvent e) {
                    activeTabIndex = index;
                    updateTabs();    //remove old tabs,recreate the tabs and mark the new activeIndex
                    actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "tabClicked"));
                }
            });
            clockTabs.add(tab);   //add each tab to the panel
        }
        
        clockTabs.revalidate();
        clockTabs.repaint();
    }


    //Tab switching 
    @Override
    public void actionPerformed(ActionEvent e) {    
        switch (activeTabIndex) {                   
            case 0:                                           
                Alarm alarm = new Alarm();
                alarm.setVisible(true);
                this.dispose();
                break;
            case 1:
                Clock clock = new Clock();
                clock.setVisible(true);
                this.dispose();
                break;
            case 2:
                TimerClock timer = new TimerClock();
                timer.setVisible(true);
                this.dispose();
                break;
            case 3:
                Stopwatch stopwatch = new Stopwatch();
                stopwatch.setVisible(true);
                this.dispose();
                break;
        }
    }

}