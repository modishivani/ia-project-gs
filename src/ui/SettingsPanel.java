package ui;

import ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {

    private final MainFrame mainFrame;

    JButton badgeSettingsButton = new JButton(" Badge Settings ");
    JButton journeySettingsButton = new JButton(" Journey Settings ");

    private BadgeSettingsPanel badgeSettingsPanel;
    private JourneySettingsPanel journeySettingsPanel;

    public SettingsPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new TopInfoPanel(this.mainFrame, " Badge and Journey Settings");
        topPanel.setBackground(new Color(233,246,220));
        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(247,252,242));

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
        this.badgeSettingsButton.setForeground(new Color(9,95,54));
        this.journeySettingsButton.setIcon(journeysIcon);
        this.journeySettingsButton.setForeground(new Color(9,95,54));

        badgeJourneyButtonsPanel.add(this.badgeSettingsButton);
        badgeJourneyButtonsPanel.add(this.journeySettingsButton);

        return badgeJourneyButtonsPanel;
    }

    public boolean tryLoadData() {
        return true;
    }
}

