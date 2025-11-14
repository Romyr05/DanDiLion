
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import apps.clock;


public class Homepage extends JFrame implements ActionListener{
    JButton clockButton;
    JPanel panel;

    Homepage(){
        //Frame
        this.setTitle("DanDieLion");    
        this.setSize(550,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());

        
        panel = CreateAppPanel();

        this.add(panel, BorderLayout.SOUTH);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clockButton){
            this.dispose();
            clock window = new clock();
            System.out.println("test");
        }
    }



    public JPanel CreateAppPanel(){
        JPanel panel = new JPanel();
        panel.setSize(500,500);
        panel.setLayout (new FlowLayout());
        panel.setBackground(Color.gray);

        

        return panel;
    }


}