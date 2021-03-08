package ui;

import ui.components.ColorScheme;

import javax.swing.*;
import java.awt.*;

public abstract class ContentPanel extends JPanel {
    protected final MainFrame mainFrame;
    private final String contentName;

    public ContentPanel(MainFrame mainFrame, String contentName) {
        super();
        this.mainFrame = mainFrame;
        this.contentName = contentName;
    }

    protected void initialize() {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(createTopPanel(this.contentName), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel(String contentName) {
        JPanel topPanel = new ContentTopPanel(this.mainFrame, " " + contentName);
        topPanel.setBackground(ColorScheme.LightGreen);
        return topPanel;
    }

    protected abstract JPanel createCenterPanel();
}
