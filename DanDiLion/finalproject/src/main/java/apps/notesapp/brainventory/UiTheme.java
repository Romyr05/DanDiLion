package apps.notesapp.brainventory;

import java.awt.*;

// UiTheme: central place for fonts, colors and spacing constants
// Improves consistency across panels and components.

public final class UiTheme {
    private UiTheme() {}

    // Colors - Dark mobile theme
    public static final Color BG = new Color(18, 18, 18); // Dark background
    public static final Color PRIMARY = new Color(0, 122, 255); // iOS Blue
    public static final Color CARD = new Color(28, 28, 30); // Dark gray cards
    public static final Color TEXT_PRIMARY = new Color(255, 255, 255); // White text
    public static final Color TEXT_SECONDARY = new Color(142, 142, 147); // Light gray text
    public static final Color CARD_DARK = new Color(44, 44, 46); // Darker cards

    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FORM_LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font TIMER_FONT = new Font("Segoe UI", Font.BOLD, 56);
    public static final Font MONO_FONT = new Font("Segoe UI", Font.PLAIN, 13);

    // Spacing
    public static final int PANEL_PADDING = 16;
    public static final int CARD_RADIUS = 20;
}

