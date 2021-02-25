package ui;

import db.BadgeInformation;
import ui.components.LabelTextField;
import ui.components.TopInfoPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class SettingsPanel2 extends JPanel {

    private final MainFrame mainFrame;

    private DefaultListModel<String> badgeNamesListModel;

    private JList<String> journeyList;

    private DefaultListModel<String> journeyNamesListModel;
    private JList<String> JourneyList;

    JButton badgeSettingsButton = new JButton(" Badge Settings ");
    JButton journeySettingsButton = new JButton(" Journey Settings ");

    private final LabelTextField step1Field = new LabelTextField("Step 1 : ");
    private final LabelTextField step2Field = new LabelTextField("Step 2 : ");
    private final LabelTextField step3Field = new LabelTextField("Step 3 : ");
    private final LabelTextField step4Field = new LabelTextField("Step 4 : ");
    private final LabelTextField step5Field = new LabelTextField("Step 5 : ");
    private final LabelTextField descriptionField = new LabelTextField("Badge Description : ");

    JButton addButton = new JButton(" Add ");
    JButton editButton = new JButton(" Edit ");
    JButton saveButton = new JButton(" Save ");

    private BadgeInformation selectedBadgeInformation;
    private BadgeInformation selectedJourneyInformation;

    public SettingsPanel2(MainFrame mainFrame) {
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

        JPanel badgeSettingsPanel = createBadgeSettingsPanel();
        JPanel journeySettingsPanel = createJourneySettingsPanel();

        settingsDisplayPanel.add(badgeSettingsPanel, "Badges");
        settingsDisplayPanel.add(journeySettingsPanel, "Journeys");
        settingsDisplayLayout.show(settingsDisplayPanel, "Badges");

        this.badgeSettingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsDisplayLayout.show(settingsDisplayPanel, "Badges");
            }
        });

        this.journeySettingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsDisplayLayout.show(settingsDisplayPanel, "Journeys");
            }
        });

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(createBadgeJourneyButtonsPanel(), BorderLayout.NORTH);
        centerPanel.add(settingsDisplayPanel, BorderLayout.CENTER);
        //centerPanel.add(createAddEditSaveButtonsPanel(), BorderLayout.SOUTH);

        return centerPanel;
    }

    private JPanel createBadgeJourneyButtonsPanel() {
        JPanel badgeJourneyButtonsPanel = new JPanel();
        badgeJourneyButtonsPanel.setOpaque(false);
        badgeJourneyButtonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

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

    private JPanel createAddEditSaveButtonsPanel() {
        JPanel addEditSaveButtonsPanel = new JPanel();
        addEditSaveButtonsPanel.setOpaque(false);

        /*addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //add();
            }
        });

        editButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //edit();
            }
        });

        saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    save();
                } catch (DatabaseException databaseException) {
                    databaseException.printStackTrace();
                }
            }
        });*/

        addEditSaveButtonsPanel.add(addButton);
        addEditSaveButtonsPanel.add(editButton);
        addEditSaveButtonsPanel.add(saveButton);

        return addEditSaveButtonsPanel;
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
        createBadgeSettingsPanel.add(createBadgeListPanel(), BorderLayout.WEST);
        createBadgeSettingsPanel.add(createBadgeInformationPanel(), BorderLayout.CENTER);
        createBadgeSettingsPanel.add(createAddEditSaveButtonsPanel());
        return createBadgeSettingsPanel;
    }

    private JPanel createBadgeListPanel() {
        JPanel badgeListPanel = new JPanel();

        this.badgeNamesListModel = new DefaultListModel<>();
        this.journeyList = new JList<>(this.badgeNamesListModel);
        this.journeyList.setFixedCellHeight(25);
        this.journeyList.setOpaque(false);
        //this.badgeList.addListSelectionListener(e -> showBadge(badgeList.getSelectedValue()));

        badgeListPanel.setLayout(new BorderLayout());
        badgeListPanel.add(journeyList, BorderLayout.CENTER);
        badgeListPanel.setPreferredSize(new Dimension(200, 0));
        TitledBorder titledBorder = new TitledBorder(" Badge Names ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 13f));
        badgeListPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        badgeListPanel.setOpaque(false);
        return badgeListPanel;
    }

    private JPanel createBadgeInformationPanel() {
        JPanel badgeInformationPanel = new JPanel();
        badgeInformationPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(6, 0, 10, 5);
        badgeInformationPanel.setLayout(gridLayout);

        badgeInformationPanel.add(this.descriptionField);
        badgeInformationPanel.add(this.step1Field);
        badgeInformationPanel.add(this.step2Field);
        badgeInformationPanel.add(this.step3Field);
        badgeInformationPanel.add(this.step4Field);
        badgeInformationPanel.add(this.step5Field);

        return badgeInformationPanel;
    }

    private JPanel createJourneySettingsPanel() {
        JPanel createJourneySettingsPanel = new JPanel();
        createJourneySettingsPanel.setLayout(new BorderLayout());
        createJourneySettingsPanel.setOpaque(false);

        TitledBorder titledBorder = new TitledBorder(" Journey Settings ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 13f));
        createJourneySettingsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        createJourneySettingsPanel.add(createJourneyListPanel(), BorderLayout.WEST);
        createJourneySettingsPanel.add(createJourneyInformationPanel(), BorderLayout.CENTER);
        return createJourneySettingsPanel;
    }


    private JPanel createJourneyListPanel() {
        JPanel journeyListPanel = new JPanel();

        this.journeyNamesListModel = new DefaultListModel<>();
        this.journeyList = new JList<>(this.journeyNamesListModel);
        this.journeyList.setFixedCellHeight(25);
        this.journeyList.setOpaque(false);
        //this.journeyList.addListSelectionListener(e -> showJourney(journeyList.getSelectedValue()));

        journeyListPanel.setLayout(new BorderLayout());
        journeyListPanel.add(journeyList, BorderLayout.CENTER);
        journeyListPanel.setPreferredSize(new Dimension(200, 0));
        TitledBorder titledBorder = new TitledBorder(" Journey Names ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 13f));
        journeyListPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        journeyListPanel.setOpaque(false);
        return journeyListPanel;
    }

    private JPanel createJourneyInformationPanel() {
        JPanel journeyInformationPanel = new JPanel();
        journeyInformationPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(6, 0, 10, 5);
        journeyInformationPanel.setLayout(gridLayout);

        journeyInformationPanel.add(this.descriptionField);
        journeyInformationPanel.add(this.step1Field);
        journeyInformationPanel.add(this.step2Field);
        journeyInformationPanel.add(this.step3Field);
        journeyInformationPanel.add(this.step4Field);
        journeyInformationPanel.add(this.step5Field);

        return journeyInformationPanel;
    }

    public boolean tryLoadData() {
        this.loadData();
        return true;
    }

    void loadData() {

        ArrayList<String> badgeNames = this.mainFrame.getDb().listBadgeNames();

        this.badgeNamesListModel.clear();
        this.badgeNamesListModel.addAll(badgeNames);

        this.journeyList.requestFocus();
        if (!this.badgeNamesListModel.isEmpty()) {
            this.journeyList.setSelectedIndex(0);
        }
    }

}

