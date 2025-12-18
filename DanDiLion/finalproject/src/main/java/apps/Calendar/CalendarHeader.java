package apps.Calendar;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.function.Consumer;

public class CalendarHeader extends JPanel {
    private JLabel monthYearLabel;
    private Consumer<LocalDate> callback;   //stores value Localdate input but does not have any return value, it just 
                                            // it just does something with the input
                                            //It allows event driven methods to be passed onto it
                                            //When a button that is consumer is clicked it just passes that it is clicked and
                                            // and lets the app decide what is its behaviour
    private LocalDate currentDate;

    public CalendarHeader(LocalDate date, Consumer<LocalDate> callback) {
        this.callback = callback;
        this.currentDate = date;
        
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        // Left: Previous button
        NavButton prevButton = new NavButton("<");
        prevButton.addActionListener(e -> {
            callback.accept(currentDate.minusMonths(1));
        });
        
        // Center: Month/Year     
        monthYearLabel = new JLabel();
        monthYearLabel.setFont(new Font("Arial", Font.BOLD, 18));
        monthYearLabel.setForeground(new Color(255, 87, 87));
        monthYearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateDate(date);
        
        // Right: Next button
        NavButton nextButton = new NavButton(">");
        nextButton.addActionListener(e -> {
            callback.accept(currentDate.plusMonths(1));
    });
        add(prevButton, BorderLayout.WEST);
        add(monthYearLabel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.EAST);
    }

    //update date based on the prev and next
    public void updateDate(LocalDate date) {
        this.currentDate = date;

        //Gets the month and year
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();  
        String year = String.valueOf(date.getYear());

        monthYearLabel.setText(month + " " + year); // Prints out the date
    }
}