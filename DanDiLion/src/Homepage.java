
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import apps.clock;


public class Homepage extends JFrame implements ActionListener{
    JButton clockButton;
    JPanel panel, appPanel;
    JPanel panelTime, statusBar;
    JPanel upperApps,test;

    Homepage(){
        //Frame
        this.setTitle("DanDieLion");    
        this.setSize(550,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());

        statusBar = createStatusBar();
        appPanel = createBorderAppPanel();
        panelTime = timePanel();

        ImageIcon icon = new ImageIcon("assets/clock.jpg"); 

        JPanel clockApp = CreateUpperApps();
        JPanel clockApp2 = CreateUpperApps();
        JPanel clockApp3 = CreateUpperApps();
        JPanel clockApp4 = CreateUpperApps();
        JPanel clockApp5 = CreateUpperApps();
        JPanel clockApp6 = CreateUpperApps();
        JPanel clockApp7 = CreateUpperApps();
        JPanel clockApp8 = CreateUpperApps();

        appPanel.add(clockApp);
        appPanel.add(clockApp2);
        appPanel.add(clockApp3);
        appPanel.add(clockApp4);
        appPanel.add(clockApp5);
        appPanel.add(clockApp6);
        appPanel.add(clockApp7);
        appPanel.add(clockApp8);


        this.add(statusBar,BorderLayout.NORTH);
        this.add(panelTime, BorderLayout.CENTER);
        this.add(appPanel, BorderLayout.SOUTH);

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
        panelBorderApp.setLayout(new GridLayout(2,4,20,20));
        panelBorderApp.setPreferredSize(new Dimension(0,500));
        panelBorderApp.setBackground(Color.gray);
        panelBorderApp.setBorder(BorderFactory.createEmptyBorder(10, 20 ,10 ,20 ));
        return panelBorderApp;
    }

    public JPanel timePanel(){
        JPanel panelTime = new JPanel();
        panelTime.setPreferredSize(new Dimension(0,100));
        panelTime.setBackground(Color.blue);
        return panelTime;
    }

    public JPanel CreateUpperApps(){
        JPanel Apps = new JPanel();     
        Apps.setLayout(new BorderLayout());   // removes gaps
        Apps.setPreferredSize(new Dimension(20,20));
        Apps.setBackground(Color.red);
         
        JButton appButton = new JButton();

        Apps.add(appButton, BorderLayout.CENTER);

        return Apps;
    }


}