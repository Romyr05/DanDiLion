package apps.notesapp.brainventory;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;

// SessionsPanel: UI panel that displays reports (daily / weekly)
// - Provides controls to pick date and switch between daily/weekly views
// - Notifies listeners when the user changes the report selection
public class SessionsPanel extends RoundedPanel implements PomodoroStudy.SessionSaveListener {

    private final ISessionRepository sessionManager;
    private final SubjectManager subjectManager;
    private final StudyMetadataManager metadataManager;
    private final ReportGenerator reportGenerator;

    // UI components: a simple output area (debug) and the modern report renderer
    private final JTextArea outputArea = new JTextArea();
    private final ModernReportDisplay reportDisplay = new ModernReportDisplay();
    
    private LocalDate currentReportDate = LocalDate.now();
    private String currentReportType = "daily"; // "daily" or "weekly"
    private java.util.List<PomodoroStudy.SessionSaveListener> reportListeners = new ArrayList<>();

    public SessionsPanel(ISessionRepository sessionManager, SubjectManager subjectManager, StudyMetadataManager metadataManager){
        super(35, UiTheme.CARD);
        this.sessionManager = sessionManager;
        this.subjectManager = subjectManager;
        this.metadataManager = metadataManager;
        this.reportGenerator = new ReportGenerator(sessionManager, metadataManager);
        setLayout(new BorderLayout(12,12));
        // removed outer padding so the sessions/report panel can fill available space
        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        // Controls
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        controls.setOpaque(false);

        // date picker (DaySpinnerModel steps by one day)
        SpinnerDateModel model = new DaySpinnerModel(new Date(), null, null);
        JSpinner dateSpinner = new JSpinner(model);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        dateSpinner.setPreferredSize(new Dimension(120, 32));
        JLabel dateLabel = new JLabel("Date:", SwingConstants.CENTER);
        dateLabel.setFont(UiTheme.BODY_FONT);
        dateLabel.setForeground(UiTheme.TEXT_SECONDARY);
        controls.add(dateLabel);
        controls.add(dateSpinner);

        MyButton dailyBtn = new MyButton("Daily", UiTheme.PRIMARY, new Color(0, 100, 220));
        MyButton weeklyBtn = new MyButton("Weekly", UiTheme.CARD_DARK, new Color(60, 60, 60));

        dailyBtn.setPreferredSize(new Dimension(90, 32));
        weeklyBtn.setPreferredSize(new Dimension(90, 32));

        controls.add(dailyBtn);
        controls.add(weeklyBtn);

        add(controls, BorderLayout.NORTH);

        // Use JScrollPane with reportDisplay for modern rendering
        JScrollPane sc = new JScrollPane(reportDisplay);
        sc.setBorder(BorderFactory.createEmptyBorder());
        sc.setOpaque(false);
        sc.getViewport().setOpaque(false);
        add(sc, BorderLayout.CENTER);

        // Daily button: generate and display a daily report for selected date
        dailyBtn.addActionListener(e -> {
            Date d = (Date) dateSpinner.getValue();
            LocalDate ld = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            currentReportType = "daily";
            currentReportDate = ld;
            String json = reportGenerator.dailyReport(ld);
            displayDailyReport(ld, json);
            notifyReportChange();
        });

        // Weekly button: generate and display a weekly report (Mon-Sun)
        weeklyBtn.addActionListener(e -> {
            Date d = (Date) dateSpinner.getValue();
            LocalDate ld = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            currentReportType = "weekly";
            currentReportDate = ld;
            String json = reportGenerator.weeklyReport(ld);
            displayWeeklyReport(ld, json);
            notifyReportChange();
        });
    }

    private void displayDailyReport(LocalDate date, String json) {
        // Parse JSON and extract subjects
        try {
            com.google.gson.Gson gson = new com.google.gson.Gson();
            java.util.Map<String, Object> map = gson.fromJson(json, java.util.Map.class);

            int totalSessions = ((Number) map.get("totalSessions")).intValue();
            int totalSeconds = ((Number) map.get("totalSeconds")).intValue();
            java.util.List<java.util.Map<String, Object>> subjectsData = 
                (java.util.List<java.util.Map<String, Object>>) map.get("subjects");

            java.util.List<ReportGenerator.SubjectSummary> subjects = new ArrayList<>();
            if (subjectsData != null) {
                for (java.util.Map<String, Object> s : subjectsData) {
                    ReportGenerator.SubjectSummary ss = new ReportGenerator.SubjectSummary();
                    ss.subject = (String) s.get("subject");
                    ss.totalSeconds = ((Number) s.get("totalSeconds")).intValue();
                    ss.avgProductivity = ((Number) s.get("avgProductivity")).doubleValue();
                    ss.avgEnergy = ((Number) s.get("avgEnergy")).doubleValue();
                    subjects.add(ss);
                }
            }

            // Populate the modern report display with parsed daily data
            reportDisplay.setDailyReportData(totalSessions, totalSeconds, subjects, date.toString());
            reportDisplay.revalidate();
            reportDisplay.repaint();
        } catch (Exception ex) {
            outputArea.setText("Error displaying report: " + ex.getMessage());
        }
    }

    // programmatic API to show a daily report (used by Dashboard to auto-load today's data)
    public void showDailyReport(LocalDate date) {
        String json = reportGenerator.dailyReport(date);
        displayDailyReport(date, json);
    }

    // Build and render a weekly report (Mon - Sun) for a date within that week
    private void displayWeeklyReport(LocalDate date, String json) {
        // Parse JSON and extract subjects (same structure as daily)
        try {
            com.google.gson.Gson gson = new com.google.gson.Gson();
            java.util.Map<String, Object> map = gson.fromJson(json, java.util.Map.class);

            int totalSessions = ((Number) map.get("totalSessions")).intValue();
            int totalSeconds = ((Number) map.get("totalSeconds")).intValue();
            java.util.List<java.util.Map<String, Object>> subjectsData = 
                (java.util.List<java.util.Map<String, Object>>) map.get("subjects");

            java.util.List<ReportGenerator.SubjectSummary> subjects = new ArrayList<>();
            if (subjectsData != null) {
                for (java.util.Map<String, Object> s : subjectsData) {
                    ReportGenerator.SubjectSummary ss = new ReportGenerator.SubjectSummary();
                    ss.subject = (String) s.get("subject");
                    ss.totalSeconds = ((Number) s.get("totalSeconds")).intValue();
                    ss.avgProductivity = ((Number) s.get("avgProductivity")).doubleValue();
                    ss.avgEnergy = ((Number) s.get("avgEnergy")).doubleValue();
                    subjects.add(ss);
                }
            }

            // Get Monday of the week for date range label
            LocalDate weekStart = date.with(java.time.DayOfWeek.MONDAY);
            LocalDate weekEnd = weekStart.plusDays(6);
            // Use a human-friendly date range label when rendering weekly summary
            String dateRangeLabel = weekStart + " to " + weekEnd;

            reportDisplay.setDailyReportData(totalSessions, totalSeconds, subjects, dateRangeLabel);
            reportDisplay.revalidate();
            reportDisplay.repaint();
        } catch (Exception ex) {
            outputArea.setText("Error displaying report: " + ex.getMessage());
        }
    }

    public String getCurrentReportType() {
        return currentReportType;
    }

    public LocalDate getCurrentReportDate() {
        return currentReportDate;
    }

    public void addReportChangeListener(PomodoroStudy.SessionSaveListener listener) {
        reportListeners.add(listener);
    }

    private void notifyReportChange() {
        for (PomodoroStudy.SessionSaveListener listener : reportListeners) {
            listener.onSessionSaved();
        }
    }

    @Override
    public void onSessionSaved() {
        // When a session is saved elsewhere, refresh the current report view (on EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                sessionManager.reloadSessions();
                if ("weekly".equals(currentReportType)) {
                    String json = reportGenerator.weeklyReport(currentReportDate);
                    displayWeeklyReport(currentReportDate, json);
                } else {
                    showDailyReport(currentReportDate);
                }
            } catch (Exception ignore) {}
        });
    }
}

