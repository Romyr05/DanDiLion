package apps.NotesApp;

import apps.NotesApp.brainventory.*;
import apps.motion.Swipe;
import homepage.BaseApp;
import homepage.Homepage;
import homepage.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

// NotesApp: Main Brainventory application integrated into the phone app
// - Constructs shared managers and panels, registers listeners
// - Hosts CardLayout for Dashboard / Focus / Courses

public class NotesApp extends BaseApp {

    private JPanel mainContent;
    private CardLayout cardLayout;
    private CoursesPanel coursesPanel;

    // Shared session repository instance used by UI and reports
    private ISessionRepository sessionManager = new SessionManager();
    private SubjectManager subjectManager = new SubjectManager();
    private StudyMetadataManager metadataManager = new StudyMetadataManager();

    private PomodoroStudy myPomodoro = new PomodoroStudy(sessionManager, subjectManager, metadataManager);

    public NotesApp() {
        super("Brainventory");
        initializeComponents();
        addSwipeGesture();
    }

    private void initializeComponents() {
        getContentPane().setBackground(UiTheme.BG);

        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);
        mainContent.setOpaque(false);

        mainContent.add(createDashboardPanel(), "Dashboard");
        mainContent.add(createSessionsMainPanel(), "Focus");
        mainContent.add(createCoursesPanel(), "Courses");

        // Register CoursesPanel as a listener for session saves
        myPomodoro.addSessionSaveListener(coursesPanel);

        add(new StatusBar(), BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
        add(createBottomNav(), BorderLayout.SOUTH);
    }

    private JPanel createBottomNav() {
        JPanel nav = new JPanel(new GridLayout(1, 3));
        nav.setPreferredSize(new Dimension(550, 90));
        nav.setBackground(UiTheme.CARD);
        nav.setBorder(BorderFactory.createMatteBorder(
                1, 0, 0, 0, new Color(60, 60, 60)));

        nav.add(createNavButton("Dashboard", "Dashboard"));
        nav.add(createNavButton("Focus", "Focus"));
        nav.add(createNavButton("Courses", "Courses"));

        return nav;
    }

    private JButton createNavButton(String text, String card) {
        NavButton btn = new NavButton(
                text,
                UiTheme.CARD, 
                UiTheme.CARD_DARK
        );

        btn.setForeground(UiTheme.TEXT_PRIMARY);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setPreferredSize(new Dimension(183, 90));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.addActionListener(e -> cardLayout.show(mainContent, card));
        return btn;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // Title area
        JLabel label = new JLabel("Dashboard", SwingConstants.CENTER);
        label.setFont(UiTheme.TITLE_FONT);
        label.setForeground(UiTheme.TEXT_PRIMARY);
        panel.add(label, BorderLayout.NORTH);

        // Main content area: Daily report takes most space
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        
        // Daily report panel (main focus, takes most of the space)
        RoundedPanel reportCard = new RoundedPanel(20, UiTheme.CARD);
        reportCard.setLayout(new BorderLayout(12, 12));
        
        SessionsPanel reports = new SessionsPanel(sessionManager, subjectManager, metadataManager);
        reportCard.add(reports, BorderLayout.CENTER);
        // auto-load today's daily report when dashboard is created
        try {
            reports.showDailyReport(LocalDate.now());
        } catch (Exception ignore) {} 
        
        center.add(reportCard, BorderLayout.CENTER);
        
        // Chart at the bottom (smaller, for reference)
        RoundedPanel chartCard = new RoundedPanel(20, UiTheme.CARD);
        chartCard.setLayout(new BorderLayout());
        chartCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        chartCard.setPreferredSize(new Dimension(500, 220));
        
        DashboardChartsPanel charts = new DashboardChartsPanel(sessionManager, metadataManager);
        charts.setSessionsPanel(reports);  // so charts can query report type
        chartCard.add(charts, BorderLayout.CENTER);
        
        center.add(chartCard, BorderLayout.SOUTH);

        // register dashboard components to automatically update when a session is saved
        myPomodoro.addSessionSaveListener(charts);
        myPomodoro.addSessionSaveListener(reports);
        
        panel.add(center, BorderLayout.CENTER);

        return wrap(panel);
    }

    private JPanel createSessionsMainPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        // Focus panel shows only the pomodoro control (no reports here)
        content.add(myPomodoro);
        content.add(Box.createVerticalStrut(24));

        return wrap(content);
    }

    private JPanel createCoursesPanel() {
        coursesPanel = new CoursesPanel(subjectManager, sessionManager);
        return wrap(coursesPanel);
    }

    private JPanel wrap(JComponent inner) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        wrapper.add(inner, BorderLayout.CENTER);
        return wrapper;
    }

    //Swiping motion to go back to homepage
    private void addSwipeGesture() {
        Swipe swipeListener = new Swipe(new Swipe.SwipeCallback() {
            @Override
            public void onSwipeRight() {
                goBackToHomePage();
            }
        });
        
        this.addMouseListener(swipeListener);
        this.getContentPane().addMouseListener(swipeListener);
        addSwipeToAllComponents(this.getContentPane(), swipeListener);
    }
    
    private void addSwipeToAllComponents(Component component, Swipe listener) {
        component.addMouseListener(listener);
        
        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                addSwipeToAllComponents(child, listener);
            }
        }
    }

    private void goBackToHomePage() {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            Homepage homePage = new Homepage();
            homePage.setVisible(true);
        });
    }
}
