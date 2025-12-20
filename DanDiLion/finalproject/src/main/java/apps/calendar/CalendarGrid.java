package apps.calendar;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.function.Consumer;

public class CalendarGrid extends JPanel {
    private static final String[] DAY_NAMES = {"S", "M", "T", "W", "T", "F", "S"};

    private Consumer<LocalDate> dateCallback;   //Stores data but does not return 
                                                //It also lets you pass the behaviour 
                                                //stores value Localdate input but does not have any return value, it just 
                                            // it just does something with the input
                                            //It allows event driven methods to be passed onto it
                                            //When a button that is consumer is clicked it just passes that it is clicked and
                                            // and lets the app decide what is its behaviour

    public CalendarGrid(LocalDate date, LocalDate selected, Consumer<LocalDate> callback) {
        this.dateCallback = callback;
        setLayout(new GridLayout(0, 7, 0, 0));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        updateCalendar(date, selected);   //Update on date and selected
    }

    //Updating the calendar by removing, and adding all of them again (after next and prev)
    public void updateCalendar(LocalDate date, LocalDate selected) {
        removeAll();
        addDayHeaders();
        addEmptyCells(date);
        addDayCells(date, selected);
        revalidate();
        repaint();
    }

    //add day of week
    private void addDayHeaders() {
        for (String day : DAY_NAMES) {
            add(new DayHeader(day));
        }
    }

    //This just basically adds all the cells with blank so that the first day would align correctly
    private void addEmptyCells(LocalDate date) {
        LocalDate firstOfMonth = date.withDayOfMonth(1);
        int firstDayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7;
        
        for (int i = 0; i < firstDayOfWeek; i++) {
            JPanel emptyCell = new JPanel();
            emptyCell.setBackground(Color.BLACK);
            add(emptyCell);
        }
    }

    //add first days 
    private void addDayCells(LocalDate date, LocalDate selected) {
        int daysInMonth = date.lengthOfMonth();
        
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate cellDate = date.withDayOfMonth(day);
            add(new DayCell(cellDate, selected, dateCallback));
        }
    }
}