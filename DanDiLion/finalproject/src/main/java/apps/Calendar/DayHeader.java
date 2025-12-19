package apps.Calendar;

import java.awt.*;
import javax.swing.*;

//design for each days
public class DayHeader extends JLabel {
    public DayHeader(String dayName) {
        super(dayName, SwingConstants.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 12));
        setForeground(Color.GRAY);
        setPreferredSize(new Dimension(50, 30));
    }
}