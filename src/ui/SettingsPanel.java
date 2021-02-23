package ui;

import ui.components.IconCaptionButton;
import ui.components.IconPanel;
import ui.components.TopInfoPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel {

    private final MainFrame mainFrame;
    private DefaultListModel<String> badgeNamesListModel;
    private JList<String> badgeList;
    private DefaultListModel<String> journeyNamesListModel;
    private JList<String> JourneyList;
    JButton badgeSettingsButton = new JButton(" Badge Settings ");
    JButton journeySettingsButton = new JButton(" Journey Settings ");

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
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(createBadgeJourneyButtonsPanel(), BorderLayout.NORTH);
        this.badgeSettingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.add(createBadgeSettingsPanel(), BorderLayout.CENTER);
            }
        });

        this.journeySettingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.add(createJourneySettingsPanel(), BorderLayout.CENTER);
            }
        });

        return centerPanel;
    }

    private JPanel createBadgeJourneyButtonsPanel() {
        JPanel badgeJourneyButtonsPanel = new JPanel();
        badgeJourneyButtonsPanel.setOpaque(false);
        badgeJourneyButtonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        /*ImageIcon tmpIcon = new ImageIcon(this.getClass().getResource("/images/badgeIcon.png"));
        Image image = tmpIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon badgesIcon = new ImageIcon(image);

        ImageIcon tmp2Icon = new ImageIcon(this.getClass().getResource("/images/journeyIcon.png"));
        Image image2 = tmp2Icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon journeysIcon = new ImageIcon(image2);

        this.badgeSettingsButton.setIcon(badgesIcon);
        this.badgeSettingsButton.setText("  Badge Settings  ");

        this.journeySettingsButton.setIcon(journeysIcon);
        this.journeySettingsButton.setText("  Journey Settings  ");
*/
        badgeJourneyButtonsPanel.add(this.badgeSettingsButton);
        badgeJourneyButtonsPanel.add(this.journeySettingsButton);

        return badgeJourneyButtonsPanel;
    }

    private JPanel createBadgeSettingsPanel() {
        JPanel createBadgeSettingsPanel = new JPanel();
        createBadgeSettingsPanel.setLayout(new BorderLayout());
        createBadgeSettingsPanel.setOpaque(false);

        TitledBorder titledBorder = new TitledBorder(" Badge Settings ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        createBadgeSettingsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));
        createBadgeSettingsPanel.add(new JLabel("hello"));
        /*createBadgeSettingsPanel.add(createBadgeList(), BorderLayout.WEST);
        createBadgeSettingsPanel.add(createBadgeInformationPanel(), BorderLayout.CENTER);*/
        return createBadgeSettingsPanel;
    }

    private JPanel createJourneySettingsPanel() {
        JPanel createJourneySettingsPanel = new JPanel();
        createJourneySettingsPanel.setLayout(new BorderLayout());
        createJourneySettingsPanel.setOpaque(false);

        TitledBorder titledBorder = new TitledBorder(" Journey Settings ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        createJourneySettingsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        /*createJourneySettingsPanel.add(createJourneyList(), BorderLayout.WEST);
        createJourneySettingsPanel.add(createJourneyInformationPanel(), BorderLayout.CENTER);*/
        return createJourneySettingsPanel;
    }

}

