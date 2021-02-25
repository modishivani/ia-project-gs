package ui;

import db.ActivityInformation;
import db.DatabaseException;
import ui.components.LabelTextField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class ActivitySettingsPanel extends JPanel {

    protected final MainFrame mainFrame;

    private String activityKind;

    private DefaultListModel<String> activityListModel;
    private JList<String> activityList;

    private final LabelTextField step1Field = new LabelTextField("Step 1 : ");
    private final LabelTextField step2Field = new LabelTextField("Step 2 : ");
    private final LabelTextField step3Field = new LabelTextField("Step 3 : ");
    private final LabelTextField step4Field = new LabelTextField("Step 4 : ");
    private final LabelTextField step5Field = new LabelTextField("Step 5 : ");
    private final LabelTextField descriptionField = new LabelTextField("Description : ");

    JButton addButton = new JButton(" Add ");
    JButton editButton = new JButton(" Edit ");
    JButton saveButton = new JButton(" Save ");
    JButton deleteButton = new JButton(" Delete ");

    private ActivityInformation selectedActivityInformation;

    public ActivitySettingsPanel(MainFrame mainFrame, String activityKind) {
        this.mainFrame = mainFrame;
        this.activityKind = activityKind;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        TitledBorder titledBorder = new TitledBorder(" " + this.activityKind + " Settings ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        this.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));
        this.add(createActivityListPanel(), BorderLayout.WEST);
        this.add(createActivityInformationPanel(), BorderLayout.CENTER);
    }

    private JPanel createActivityListPanel() {
        JPanel activityListPanel = new JPanel();

        this.activityListModel = new DefaultListModel<>();
        this.activityList = new JList<>(this.activityListModel);
        this.activityList.setFixedCellHeight(25);
        this.activityList.setOpaque(false);
        this.activityList.addListSelectionListener(e -> showActivity(activityList.getSelectedValue()));

        activityListPanel.setLayout(new BorderLayout());
        activityListPanel.add(activityList, BorderLayout.CENTER);
        activityListPanel.setPreferredSize(new Dimension(200, 0));
        TitledBorder titledBorder = new TitledBorder(" " + this.activityKind + " Names ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 13f));
        activityListPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        activityListPanel.setOpaque(false);
        return activityListPanel;
    }

    private JPanel createActivityInformationPanel() {
        JPanel activityInformationPanel = new JPanel();
        activityInformationPanel.setOpaque(false);
        TitledBorder titledBorder = new TitledBorder(" Information ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        activityInformationPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 10, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        activityInformationPanel.setLayout(new BorderLayout());
        activityInformationPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        return activityInformationPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActivity();
            }
        });

        editButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editActivity();
            }
        });

        saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveActivity();
            }
        });

        deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteActivity();
            }
        });

        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);

        return buttonsPanel;
    }

    private void addActivity() {
    }

    private void editActivity() {
    }

    private void saveActivity() {
    }

    private void deleteActivity() {
    }

    private void showActivity(String selectedValue) {
    }

    public boolean tryLoadData() {
        this.loadData();
        return true;
    }

    void loadData() {

        ArrayList<String> activityNames = this.getActivityNames();

        this.activityListModel.clear();
        this.activityListModel.addAll(activityNames);

        this.activityList.requestFocus();
        if (!this.activityListModel.isEmpty()) {
            this.activityList.setSelectedIndex(0);
        }
    }

    protected abstract ActivityInformation createActivityInformation();
    protected abstract ArrayList<String> getActivityNames();
    protected abstract ActivityInformation getActivityInformation(String name) throws DatabaseException;
    protected abstract void addActivityInformation(ActivityInformation activity) throws DatabaseException;
    protected abstract void modifyActivityInformation(ActivityInformation activity) throws DatabaseException;
    protected abstract void removeActivityInformation(String name);
}
