package apps.Calendar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.function.Consumer;

public class DayCell extends JPanel {
    public DayCell(LocalDate date, LocalDate selected, Consumer<LocalDate> callback) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.BLACK);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boolean isToday = date.equals(LocalDate.now());
        boolean isSelected = date.equals(selected);  //Selected date 
        
        JLabel dayLabel = new JLabel(String.valueOf(date.getDayOfMonth()), SwingConstants.CENTER);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Today styling (red color)
        if (isToday) {
            dayLabel.setForeground(new Color(255, 87, 87));
            dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        } 
        // Selected date styling
        else if (isSelected) {
            dayLabel.setForeground(new Color(255, 87, 87));
            dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        }
        // Past dates (grayed out)
        else if (date.isBefore(LocalDate.now())) {
            dayLabel.setForeground(new Color(80, 80, 80));
        }
        // Future dates
        else {
            dayLabel.setForeground(Color.WHITE);
        }
        
        add(dayLabel, BorderLayout.CENTER);
        
        // Click handler
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                callback.accept(date);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(30, 30, 30));   //just basically highlights the entire box
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.BLACK);
            }
        });
    }
}