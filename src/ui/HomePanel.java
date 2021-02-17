package ui;

import ui.components.IconPanel;
import ui.components.LargeIconButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomePanel extends JPanel {

    private final MainFrame mainFrame;

    public HomePanel(
        MainFrame mainFrame)
    {
        super();

        this.mainFrame = mainFrame;

        //// create components
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());

        JLabel titleText = new JLabel("Girl Scout Troop 42162");
        titleText.setFont(titleText.getFont().deriveFont(64f));
        titleText.setHorizontalAlignment(JLabel.CENTER);

        IconPanel gsLogo = new IconPanel("/images/girlScoutLogo.png");

        titlePanel.add(titleText, BorderLayout.NORTH);
        titlePanel.add(gsLogo, BorderLayout.CENTER);

        //// layout components
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(createButtonPanel(), BorderLayout.CENTER);

        //// add action listeners
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        LargeIconButton membersButton = new LargeIconButton(
                "/images/memberIcon.png",
                "Members",
                "Add New Members and Access Current Member Information");
        membersButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.MEMBER_INFO);
            }
        });

        LargeIconButton badgesButton = new LargeIconButton(
                "/images/badgeIcon.png",
                "Badges",
                "View and Track Current Badge Progress by Girl");
        badgesButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.BADGE_PROGRESS);
            }
        });

        LargeIconButton journeysButton = new LargeIconButton(
                "/images/journeyIcon.png",
                "Journeys",
                "View and Track Current Journey Progress by Girl");
        journeysButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.JOURNEY_PROGRESS);
            }
        });

        LargeIconButton settingsButton = new LargeIconButton(
                "/images/settingsIcon.png",
                "Settings",
                "Input New Badges and Journeys with their Steps");
        settingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.SETTINGS);
            }
        });

        LargeIconButton eventsButton = new LargeIconButton(
                "/images/calendarIcon.png",
                "Key Events",
                "Track and View Upcoming Troop Events");
        eventsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.EVENTS);
            }
        });

        buttonPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        buttonPanel.add(membersButton);
        buttonPanel.add(eventsButton);
        buttonPanel.add(badgesButton);
        buttonPanel.add(journeysButton);
        buttonPanel.add(settingsButton);

        return buttonPanel;
    }

}
