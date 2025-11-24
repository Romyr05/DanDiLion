
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import apps.clock;


public class Homepage extends JFrame implements ActionListener{
    JButton clockButton;
    JPanel panel, appPanel;
    JPanel panelTime, statusBar;
    JPanel upperApps,lowerApps;

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

        JPanel upperApps = CreateUpperApps();


        JPanel lowerApps = createLowerApps();

        appPanel.add(upperApps, BorderLayout.CENTER);
        appPanel.add(lowerApps, BorderLayout.SOUTH);


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
        panelBorderApp.setLayout(new BorderLayout());
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

    public JPanel createIconButton() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        wrapper.setPreferredSize(new Dimension(70, 150)); 
        wrapper.setBackground(Color.green);

        ImageIcon icon = new ImageIcon("assets/clock.jpg");
        JButton btn = new JButton();
        btn.setIcon(icon);

        wrapper.add(btn, BorderLayout.CENTER);
        return wrapper;
    }

    public JPanel CreateUpperApps(){
        JPanel upperApps = new JPanel();     
        upperApps.setLayout(new GridLayout(2,4,10,10));   // removes gaps
        upperApps.setPreferredSize(new Dimension(7,5));
        upperApps.setBackground(Color.red);
         
        for (int i = 1; i <= 8; i++) {
            upperApps.add(createIconButton());
        }


        return upperApps;
    }

    //GRIDBAGS (?)

    public JPanel createLowerApps(){
        JPanel lowerApps = new JPanel(new GridLayout(1, 4, 10, 10));
        lowerApps.setBackground(Color.red);

        for (int i = 1; i <= 4; i++) {
            lowerApps.add(createIconButton());
        }
        return lowerApps;

    }
}