import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import homepage.*;
import apps.Calendar.*;
import apps.CalculatorApp.*;
import apps.Clock.Alarm.*;

/**
 * Tests for the phone app
 * Just checking if stuff works without crashing
 */
public class AppTest {

    // Test if images are there and correct file path
    // Testing only 4 images since they are the only ones needed
    @Test
    public void testImagesExist() {
        File assets = new File("src/main/java/assets");
        System.out.println("Looking for assets folder...");
        if (assets.exists()) {
            System.out.println("Found assets folder!");
        } else {
            System.out.println("Assets folder not found at: " + assets.getAbsolutePath());
        }

        File calcImg = new File("src/main/java/assets/calcImage.jpg");
        if (calcImg.exists()) {
            System.out.println("Calculator image found!");
        } else {
            System.out.println("Calculator image missing at: " + calcImg.getAbsolutePath());
        }

        File clockImg = new File("src/main/java/assets/clock.jpg");
        if (clockImg.exists()) {
            System.out.println("Clock image found!");
        } else {
            System.out.println("Clock image missing - add it here: " + clockImg.getAbsolutePath());
        }

        File calendarImg = new File("src/main/java/assets/calendar.jpg");
        if (calendarImg.exists()) {
            System.out.println("Calendar image found!");
        } else {
            System.out.println("Calendar image missing - add it here: " + calendarImg.getAbsolutePath());
        }
    }

    // Test if homepage works
    @Test
    public void testHomepage() {
        try {
            Homepage home = new Homepage();
            home.setVisible(false);
            home.dispose();
            System.out.println("Homepage works!");
        } catch (Exception e) {
            System.out.println("Homepage broke: " + e.getMessage());
            fail("Homepage didn't work");
        }
    }

    // Test if calendar works
    @Test
    public void testCalendar() {
        try {
            CalendarApp calendar = new CalendarApp();
            calendar.setVisible(false);
            calendar.dispose();
            System.out.println("Calendar app works!");
        } catch (Exception e) {
            System.out.println("Calendar broke: " + e.getMessage());
            fail("Calendar didn't work");
        }
    }

    // Test if calculator works
    @Test
    public void testCalculator() {
        try {
            CalculatorApp calc = new CalculatorApp();
            calc.setVisible(false);
            calc.dispose();
            System.out.println("Calculator app works!");
        } catch (Exception e) {
            System.out.println("Calculator broke: " + e.getMessage());
            fail("Calculator didn't work");
        }
    }

    // Test if clock/alarm works
    @Test
    public void testClock() {
        try {
            Alarm clock = new Alarm();
            clock.setVisible(false);
            clock.dispose();
            System.out.println("Clock app works!");
        } catch (Exception e) {
            System.out.println("Clock broke: " + e.getMessage());
            fail("Clock didn't work");
        }
    }

    // Test if time stuff works
    @Test
    public void testTimeDate() {
        try {
            String time = TimeDate.getFormattedTime();
            String day = TimeDate.getFormattedDay();
            System.out.println("Time right now: " + time);
            System.out.println("Date today: " + day);
        } catch (Exception e) {
            System.out.println("TimeDate broke: " + e.getMessage());
            fail("TimeDate didn't work");
        }
    }

    // Test if buttons work
    @Test
    public void testButtons() {
        try {
            AppIconButton button = new AppIconButton("test.jpg");
            System.out.println("Buttons work!");
        } catch (Exception e) {
            System.out.println("Buttons broke: " + e.getMessage());
            fail("Buttons didn't work");
        }
    }

    // Test if all the parts work
    @Test
    public void testAllParts() {
        try {
            ClockDisplay clock = new ClockDisplay();
            StatusBar status = new StatusBar();
            AppGrid grid = new AppGrid(null);
            System.out.println("All homepage parts work!");
        } catch (Exception e) {
            System.out.println("Some part broke: " + e.getMessage());
            fail("Homepage parts didn't work");
        }
    }
}