package ui;

import db.DatabaseException;
import ui.components.IconCaptionButton;
import ui.components.TopInfoPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class BadgeProgressPanel extends JPanel {
    private final MainFrame mainFrame;
    private DefaultListModel<String> memberNamesListModel;

    private JList<String> memberList;
    JButton saveProgressButton = new JButton(" Save Badge Progress ");

    public BadgeProgressPanel(MainFrame mainFrame) {
        super();

        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);

    }

    private JPanel createTopPanel() {
        JPanel topPanel = new TopInfoPanel(this.mainFrame, " Badge Progress");
        topPanel.setBackground(new Color(233,246,220));
        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(247,252,242));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(createMemberListPanel(), BorderLayout.WEST);
        centerPanel.add(createBadgeProgressDetailsPanel(), BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createMemberListPanel() {
        JPanel memberListPanel = new JPanel();

        this.memberNamesListModel = new DefaultListModel<>();
        this.memberList = new JList<>(this.memberNamesListModel);
        this.memberList.setFixedCellHeight(25);
        this.memberList.setOpaque(false);
        //this.memberList.addListSelectionListener(e -> showMember(memberList.getSelectedValue()));

        memberListPanel.setLayout(new BorderLayout());
        memberListPanel.add(memberList, BorderLayout.CENTER);
        memberListPanel.setPreferredSize(new Dimension(200, 0));
        TitledBorder titledBorder = new TitledBorder(" Names ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        memberListPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        memberListPanel.setOpaque(false);
        return memberListPanel;
    }

    private JPanel createBadgeProgressDetailsPanel() {
        JPanel badgeProgressDetailsPanel = new JPanel();
        badgeProgressDetailsPanel.setOpaque(false);
        badgeProgressDetailsPanel.setLayout(new BorderLayout());
        badgeProgressDetailsPanel.add(createBadgeDropDownPanel(), BorderLayout.NORTH);
        badgeProgressDetailsPanel.add(createBadgeStepChecklistPanel(), BorderLayout.SOUTH);
        TitledBorder titledBorder = new TitledBorder(" Badge Progress Details ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        badgeProgressDetailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        return badgeProgressDetailsPanel;
    }

    private JPanel createBadgeDropDownPanel() {
        JPanel badgeDropDown = new JPanel();
        JComboBox<String> badges = new JComboBox<>();
        badges.setEditable(false);
        badges.addItem("Badge a");
        badges.addItem("Badge b");
        badgeDropDown.add(badges);
        return badgeDropDown;
    }

    private JPanel createBadgeStepChecklistPanel() {
        JPanel badgeStepChecklistPanel = new JPanel();
        badgeStepChecklistPanel.setOpaque(false);
        badgeStepChecklistPanel.setOpaque(false);
        badgeStepChecklistPanel.setLayout(new BorderLayout());
        badgeStepChecklistPanel.add(createStepFieldsPanel(), BorderLayout.NORTH);
        badgeStepChecklistPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        return badgeStepChecklistPanel;
    }

    private JPanel createStepFieldsPanel() {
        JPanel stepFieldsPanel = new JPanel();
        stepFieldsPanel.setOpaque(false);
        return stepFieldsPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        saveProgressButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveProgress();
                } catch (DatabaseException databaseException) {
                    databaseException.printStackTrace();
                }
            }
        });

        buttonsPanel.add(saveProgressButton);
        return buttonsPanel;
    }

    private void saveProgress() throws DatabaseException {
    }

    public boolean tryLoadData() {
        this.loadData();
        return true;
    }

    void loadData() {

        ArrayList<String> memberNames = this.mainFrame.getDb().listMemberNames();

        this.memberNamesListModel.clear();
        this.memberNamesListModel.addAll(memberNames);

        this.memberList.requestFocus();
        if (!this.memberNamesListModel.isEmpty()) {
            this.memberList.setSelectedIndex(0);
        }
    }


}
