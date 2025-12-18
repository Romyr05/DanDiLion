package apps.Calendar;

import java.awt.*;
import java.time.*;
import javax.swing.*;


import apps.motion.Swipe;
import homepage.BaseApp;
import homepage.StatusBar;
import homepage.Homepage;

public class CalendarApp extends BaseApp {
    private CalendarHeader header;
    private CalendarGrid grid;
    private LocalDate currentDate;
    private LocalDate selectedDate;

    public CalendarApp() {
        super("Calendar");
        this.currentDate = LocalDate.now();
        this.selectedDate = LocalDate.now();
        
        //Status Bar (Wifi,Time and Batt)
        this.add(new StatusBar(), BorderLayout.NORTH);
        this.add(createContentPanel(), BorderLayout.CENTER);
        
        addSwipeGesture();
    }

    //Content Page
    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        
        // Header 
        header = new CalendarHeader(currentDate, e -> onNavigate(e));
        mainPanel.add(header, BorderLayout.NORTH);
        
        // Calendar grid
        grid = new CalendarGrid(currentDate, selectedDate, e -> onDateSelected(e));
        mainPanel.add(grid, BorderLayout.CENTER);
        
        return mainPanel;
    }

    //Swiping motion to go back to homepage
    private void addSwipeGesture() {
        Swipe swipeListener = new Swipe(new Swipe.SwipeCallback() {
            @Override
            public void onSwipeRight() {
                goBackToHomePage();
            }
        });
        
        this.addMouseListener(swipeListener);
        this.getContentPane().addMouseListener(swipeListener);
        addSwipeToAllComponents(this.getContentPane(), swipeListener);
    }
    
    private void addSwipeToAllComponents(Component component, Swipe listener) {
        component.addMouseListener(listener);
        
        if (component instanceof Container) {
            Container container = (Container) component;
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

    //When used update the header and update the grid (used in next and prev month)
    //Still uses the header and grid it does not update it itself (parang container lng siya)
    private void onNavigate(LocalDate newDate) {
        currentDate = newDate;
        header.updateDate(currentDate);
        grid.updateCalendar(currentDate, selectedDate);
    }

    //This also when used update the calendar but using the method from grid
    private void onDateSelected(LocalDate date) {
        selectedDate = date;
        grid.updateCalendar(currentDate, selectedDate);
    }
}