package apps.calendar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Buttons settings of the prev and next
public class NavButton extends JButton {
    public NavButton(String text) {
        super(text);
        setPreferredSize(new Dimension(50, 30));
        setFont(new Font("Arial", Font.BOLD, 16));
        setBackground(Color.BLACK);
        setForeground(new Color(255, 87, 87));
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(new Color(255, 120, 120));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(255, 87, 87));
            }
        });
    }
}