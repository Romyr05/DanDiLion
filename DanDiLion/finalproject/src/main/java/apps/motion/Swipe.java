package apps.motion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Swipe extends MouseAdapter {
    private int startX;
    private int startY;
    private static final int MIN_SWIPE_DISTANCE = 100; // Minimum pixels for a swipe
    private static final int MAX_VERTICAL_DEVIATION = 80; // Max vertical movement allowed
    
    private SwipeCallback callback;
    
    public interface SwipeCallback {
        void onSwipeRight();
    }
    
    public Swipe(SwipeCallback callback) {
        this.callback = callback;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();
        
        int deltaX = endX - startX;
        int deltaY = Math.abs(endY - startY);
        
        // Check if it's a valid horizontal swipe
        if (deltaY < MAX_VERTICAL_DEVIATION) {
            if (deltaX > MIN_SWIPE_DISTANCE) {
                // Swipe right
                callback.onSwipeRight();
            } 
        }
    }
}

