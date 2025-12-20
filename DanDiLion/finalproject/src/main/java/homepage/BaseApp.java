// Author: John Romyr Lopez
package homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public abstract class BaseApp extends JFrame {
    protected int arc = 40;
    
    public BaseApp(String title) {
        setupFrame(title);
    }
    
    protected void setupFrame(String title) {
        this.setTitle(title);
        this.setSize(550, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setShape(new RoundRectangle2D.Double(
            0, 0, getWidth(), getHeight(), arc, arc
        ));
    }

}

