
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import apps.clock;


public class Homepage extends JFrame implements ActionListener{
    JButton clockButton;
    JPanel panel, lowAppPanel;
    JPanel panelTime, statusBar;

    Homepage(){
        //Frame
        this.setTitle("DanDieLion");    
        this.setSize(550,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());

        statusBar = createStatusBar();
        lowAppPanel = createBorderAppPanel();
        panelTime = timePanel();

        this.add(statusBar,BorderLayout.NORTH);
        this.add(panelTime, BorderLayout.CENTER);
        this.add(lowAppPanel, BorderLayout.SOUTH);

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


    //Make an abstract panel for all of them
    public JPanel createStatusBar(){
        JPanel statusBar = new JPanel();
        statusBar.setPreferredSize(new Dimension(0,35));
        statusBar.setBackground(Color.green);
        return statusBar;
    }

    public JPanel createBorderAppPanel(){
        JPanel panelBorderApp = new JPanel();
        panelBorderApp.setPreferredSize(new Dimension(0,500));
        panelBorderApp.setBackground(Color.gray);
        return panelBorderApp;
    }

    public JPanel timePanel(){
        JPanel panelTime = new JPanel();
        panelTime.setPreferredSize(new Dimension(0,100));
        panelTime.setBackground(Color.blue);
        return panelTime;
    }


}