package apps.Clock.NewGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomToggle extends JComponent {
        private boolean isOn = false;    
        private float circleX = 0; // Animation position
        private Timer animationTimer;  //for smooth animation
        
        public CustomToggle() {
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));  // For the cursor animation of the hand    
            
            addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {  //since we have the mouseclicked in the clock so we must do an event                                                                        
                toggleState();                 //Toggle when clicked                          
            }
        });
    }
        
        private void toggleState() {        
            if(isOn == true){
                isOn = false;
            }else{
                isOn = true;
            }
            animateToggle();    //start the animation
        }
        
        private void animateToggle() {
            //stop any existing animation (prevention if pressed many times)
            if (animationTimer != null && animationTimer.isRunning()) {
                animationTimer.stop();    //stop timer  
            }
            //for animation of the circle
            animationTimer = new Timer(10, new ActionListener() {
                float targetX = isOn ? getWidth() - getHeight() : 0;
                float step = (targetX - circleX) / 10;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Math.abs(targetX - circleX) < 1) {
                        circleX = targetX;
                        animationTimer.stop();
                    } else {
                        circleX += step;
                    }
                    repaint();
                }
            });
            animationTimer.start();
        }
        
        //drawing of the overall component
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int width = getWidth();
            int height = getHeight();
            
            // Draw background track
            Color trackColor = isOn ? new Color(76, 175, 80) : new Color(60, 60, 60);    //green if on , gray if off
            g2.setColor(trackColor);
            g2.fillRoundRect(0, 0, width, height, height, height);
            
            // Draw circle
            g2.setColor(Color.WHITE);
            int circleDiameter = height - 4;
            g2.fillOval((int)circleX + 2, 2, circleDiameter, circleDiameter);
            
            g2.dispose();
        }
        
        //getter and setter

        public boolean isOn() {
            return isOn;
        }
        
        public void setOn(boolean value){
            if(this.isOn != value){
                this.isOn = value;
                animateToggle();
            }
        }

    }

