package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class button extends JButton implements ActionListener {
    
    button(String text){
        this.setText(text);
        this.setBounds(0,0,100,50);
        this.setText("hello");
        this.addActionListener(this);
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this){
            System.out.println("hello1");
        }
    }
}
