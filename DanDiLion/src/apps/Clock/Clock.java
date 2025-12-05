package apps.Clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import homepage.StatusBar;
import homepage.BaseApp;

public class Clock extends BaseApp implements ActionListener{

    public Clock() {
        super("Clock");

        this.add(HeaderTab());

    }

    public JPanel HeaderTab(){
        JPanel headerTab = new JPanel();
        headerTab.setLayout(new BorderLayout());
        headerTab.setBackground(new Color(30, 30, 30));
        headerTab.setPreferredSize(new Dimension(0, 120));

        headerTab.add(new StatusBar(), BorderLayout.NORTH);
        headerTab.add(clockTabs(), BorderLayout.SOUTH);

        return headerTab;
    }

    public JPanel clockTabs(){
        JPanel clockTabs = new JPanel();
        clockTabs.setLayout(new GridLayout(1,4));  
        clockTabs.setPreferredSize(new Dimension(0,10));
        clockTabs.setOpaque(false);

        return clockTabs;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }

}
