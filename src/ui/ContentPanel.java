package ui;

import ui.components.ColorScheme;

import javax.swing.*;
import java.awt.*;

// create an content panel class to extend for the other internal panels of the applciaiton
public abstract class ContentPanel extends JPanel {
    protected final MainFrame mainFrame;
    private final String contentPanelName;

    public ContentPanel(MainFrame mainFrame, String contentName) {
        super();
        this.mainFrame = mainFrame;
        this.contentPanelName = contentName;
    }

    protected void initialize() {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        // add components
        this.add(createTopPanel(this.contentPanelName), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel(String contentPanelName) {
        // uses content top panel to create the top panel component with a home button and panel title
        JPanel topPanel = new ContentTopPanel(this.mainFrame, " " + contentPanelName);
        topPanel.setBackground(ColorScheme.LightGreen);
        return topPanel;
    }

    protected abstract JPanel createCenterPanel();
}
