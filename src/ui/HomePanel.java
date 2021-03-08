package ui;

import ui.MainFrame;
import ui.PanelNames;
import ui.components.ColorScheme;
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
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BorderLayout());

        JLabel titleText = new JLabel("Girl Scout Troop 42162");
        titleText.setFont(titleText.getFont().deriveFont(64f));
        titleText.setHorizontalAlignment(JLabel.CENTER);

        IconPanel gsLogo = new IconPanel("/images/girlScoutLogo.png");
        gsLogo.setBorder(new EmptyBorder(10,10,10,10));
        titlePanel.add(titleText, BorderLayout.NORTH);
        titlePanel.add(gsLogo, BorderLayout.CENTER);

        //// layout components
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(createButtonPanel(), BorderLayout.CENTER);
        this.setBorder(new EmptyBorder(25, 5, 10, 5));
        this.setBackground(ColorScheme.LightGreen);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());

        LargeIconButton membersButton = new LargeIconButton(
                "/images/memberIcon.png",
                "Member Information",
                "Add New Members and Access Current Member Information");
        membersButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.MEMBER_INFO);
            }
        });

        LargeIconButton badgesButton = new LargeIconButton(
                "/images/badgeIcon.png",
                "Badge Progress",
                "View and Track Current Badge Progress by Member");
        badgesButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.BADGE_PROGRESS);
            }
        });

        LargeIconButton journeysButton = new LargeIconButton(
                "/images/journeyIcon.png",
                "Journey Progress",
                "View and Track Current Journey Progress by Member");
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

        buttonPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        buttonPanel.add(membersButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(badgesButton);
        buttonPanel.add(journeysButton);

        return buttonPanel;
    }
}
