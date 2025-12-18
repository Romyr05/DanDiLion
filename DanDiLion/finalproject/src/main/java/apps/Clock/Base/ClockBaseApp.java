package apps.Clock.Base;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import apps.Clock.Alarm.Alarm;
import apps.Clock.Stopwatch.Stopwatch;
import apps.Clock.Timer.TimerClock;
import apps.motion.Swipe;
import homepage.StatusBar;
import homepage.BaseApp;
import homepage.Homepage;


//Base Class for all the Clock Apps since they all have the same header component

public abstract class ClockBaseApp extends BaseApp implements ActionListener {

    protected int activeTabIndex = 0;
    protected JPanel clockTabs;
    
    private final String[] tabs = {"Alarm", "Timer", "Stopwatch"};
    private final String[] icons = {"a", "b", "c"};

    // 0-> Alarm, 1 -> Timer, 2 -> Stopwatch

    public ClockBaseApp(String title, int defaultTabIndex) {
        super(title);
        this.activeTabIndex = defaultTabIndex;   //Different tab index based on app
        this.add(new StatusBar(), BorderLayout.NORTH);
        
        this.add(createHeaderPanel(), BorderLayout.SOUTH);

        addSwipeGesture();
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
        clockTabs = new JPanel(new GridLayout(1, 3));
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

    private void addSwipeGesture() {
        Swipe swipe = new Swipe(new Swipe.SwipeCallback() {
            @Override
            public void onSwipeRight() {
                // Swipe right to go back to homepage
                goBackToHomePage();
            }
        });



        
        // Add listener to the entire frame
        this.addMouseListener(swipe);
        
        // Also add to content pane to ensure it works everywhere
        this.getContentPane().addMouseListener(swipe);

        //Adds swipe to all components
        addSwipeToAllComponents(this.getContentPane(), swipe);
    }


    // Add swipe to all child components
    private void addSwipeToAllComponents(Component component, Swipe listener) {
        component.addMouseListener(listener);

        if (component instanceof AlarmTab) {
            return;
        }
        
        //What this basically do is that this checks if the component contains a children then we add the swipe component to all of it
        if (component instanceof Container) {
            Container container = (Container) component;    // Does not have any container methods so type cast it
            for (Component child : container.getComponents()) {
                addSwipeToAllComponents(child, listener);
            }
        }
    }

    private void goBackToHomePage() {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            Homepage homePage = new Homepage();
            homePage.setVisible(true);
        });
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
                TimerClock timer = new TimerClock();
                timer.setVisible(true);
                this.dispose();
                break;
            case 2:
                Stopwatch stopwatch = new Stopwatch();
                stopwatch.setVisible(true);
                this.dispose();
                break;
            default:
                break;
        }
    }

}

