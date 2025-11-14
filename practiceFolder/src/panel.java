package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.*;

import javax.swing.JPanel;

public class panel extends JPanel {
    panel(){
        this.setPreferredSize(new Dimension(250,500));
        this.setLayout(new GridLayout(3,3));
    }


    public void changeColor(){
        this.setBackground(Color.blue);
    }

    public void changeBounds(){
        this.setBounds(250,0,250,250);
    }

}
