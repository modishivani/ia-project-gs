package ui;

import ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsContentPanel extends ContentPanel {

    private JButton badgeSettingsButton;
    private JButton journeySettingsButton;

    private BadgeSettingsPanel badgeSettingsPanel;
    private JourneySettingsPanel journeySettingsPanel;

    public SettingsContentPanel(MainFrame mainFrame) {
        super(mainFrame, "Badge and Journey Settings");
        this.createComponents();
        this.initialize();
    }

    protected JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(ColorScheme.LightPastelGreen);

        JPanel settingsDisplayPanel = new JPanel();
        settingsDisplayPanel.setOpaque(false);
        CardLayout settingsDisplayLayout = new CardLayout();
        settingsDisplayPanel.setLayout(settingsDisplayLayout);

        this.badgeSettingsPanel = new BadgeSettingsPanel(this.mainFrame);
        this.journeySettingsPanel = new JourneySettingsPanel(this.mainFrame);

        settingsDisplayPanel.add(this.badgeSettingsPanel, "Badges");
        settingsDisplayPanel.add(this.journeySettingsPanel, "Journeys");

        settingsDisplayLayout.show(settingsDisplayPanel, "Badges");
        this.badgeSettingsPanel.loadData();
        badgeSettingsButton.setEnabled(false);
        journeySettingsButton.setEnabled(true);

        this.badgeSettingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            settingsDisplayLayout.show(settingsDisplayPanel, "Badges");
            badgeSettingsPanel.loadData();
            badgeSettingsButton.requestFocus();
            badgeSettingsButton.setEnabled(false);
            journeySettingsButton.setEnabled(true);
            }
        });

        this.journeySettingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            settingsDisplayLayout.show(settingsDisplayPanel, "Journeys");
            journeySettingsPanel.loadData();
            journeySettingsButton.requestFocus();
            badgeSettingsButton.setEnabled(true);
            journeySettingsButton.setEnabled(false);
            }
        });

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(createBadgeJourneyButtonsPanel(), BorderLayout.NORTH);
        centerPanel.add(settingsDisplayPanel, BorderLayout.CENTER);

        return centerPanel;
    }

    private void createComponents() {

        this.badgeSettingsButton = new JButton(" Badge Settings ");
        this.journeySettingsButton = new JButton(" Journey Settings ");
    }

    private JPanel createBadgeJourneyButtonsPanel() {
        JPanel badgeJourneyButtonsPanel = new JPanel();
        badgeJourneyButtonsPanel.setOpaque(false);
        badgeJourneyButtonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ImageIcon tmpIcon = new ImageIcon(this.getClass().getResource("/images/badgeIcon.png"));
        Image image = tmpIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon badgesIcon = new ImageIcon(image);

        ImageIcon tmp2Icon = new ImageIcon(this.getClass().getResource("/images/journeyIcon.png"));
        Image image2 = tmp2Icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon journeysIcon = new ImageIcon(image2);

        this.badgeSettingsButton.setIcon(badgesIcon);
        this.badgeSettingsButton.setForeground(ColorScheme.DarkGreen);
        this.journeySettingsButton.setIcon(journeysIcon);
        this.journeySettingsButton.setForeground(ColorScheme.DarkGreen);

        badgeJourneyButtonsPanel.add(this.badgeSettingsButton);
        badgeJourneyButtonsPanel.add(this.journeySettingsButton);

        return badgeJourneyButtonsPanel;
    }

    public boolean tryLoadData() {
        return true;
    }
}

