package ui;

import db.ActivityInformation;
import db.ActivityProgress;
import db.DatabaseException;
import db.MemberInformation;
import ui.components.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.*;


public abstract class ActivityProgressPanel extends JPanel {

    protected final MainFrame mainFrame;

    private final String activityKind;

    private DefaultListModel<String> memberNamesListModel;
    private JList<String> memberList;

    private final JComboBox<String> activitiesDropDown = new JComboBox<>();

    private final LabelTextArea activityNameField;
    private final LabelTextArea activityDescription;
    private final LabelCheckBox step1Checkbox = new LabelCheckBox("Step 1:");
    private final LabelCheckBox step2Checkbox = new LabelCheckBox("Step 2:");
    private final LabelCheckBox step3Checkbox = new LabelCheckBox("Step 3:");
    private final LabelCheckBox step4Checkbox = new LabelCheckBox("Step 4:");
    private final LabelCheckBox step5Checkbox = new LabelCheckBox("Step 5:");

    private final JButton saveProgressButton;

    private MemberInformation selectedMemberInformation;
    private ActivityInformation selectedActivityInformation;

    public ActivityProgressPanel(MainFrame mainFrame, String activityKind) throws DatabaseException {

        this.mainFrame = mainFrame;
        this.activityKind = activityKind;
        this.activityNameField = new LabelTextArea(" " + this.activityKind + " Name: ");
        this.activityDescription = new LabelTextArea(" " + this.activityKind + " Description: ");
        this.saveProgressButton = new JButton(" Save " + this.activityKind + " Progress ");
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new TopInfoPanel(this.mainFrame, " " + this.activityKind + " Progress");
        topPanel.setBackground(new Color(233, 246, 220));
        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(247, 252, 242));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(createMemberListPanel(), BorderLayout.WEST);
        centerPanel.add(createProgressInformationPanel(), BorderLayout.CENTER);
        return centerPanel;
    }

    private JPanel createMemberListPanel() {
        JPanel memberListPanel = new JPanel();
        memberListPanel.setOpaque(false);

        this.memberNamesListModel = new DefaultListModel<>();
        this.memberList = new JList<>(this.memberNamesListModel);
        this.memberList.setFixedCellHeight(25);
        this.memberList.setOpaque(false);
        this.memberList.addListSelectionListener(e -> setSelectedMember());

        memberListPanel.setPreferredSize(new Dimension(150, 0));
        TitledBorder titledBorder = new TitledBorder(" Names ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        memberListPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 5, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5))));

        memberListPanel.setLayout(new BorderLayout());
        memberListPanel.add(this.memberList, BorderLayout.CENTER);

        return memberListPanel;
    }

    private JPanel createProgressInformationPanel() {
        JPanel progressInformationPanel = new JPanel();
        progressInformationPanel.setOpaque(false);
        progressInformationPanel.setLayout(new BorderLayout());

        TitledBorder titledBorder = new TitledBorder(" " + this.activityKind + " Progress Details ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        progressInformationPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 5, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5))));

        progressInformationPanel.add(createActivityDropDownPanel(), BorderLayout.NORTH);
        progressInformationPanel.add(createStepsDetailsPanel(), BorderLayout.CENTER);
        return progressInformationPanel;
    }

    private JPanel createActivityDropDownPanel() {
        JLabel dropDownLabel = new JLabel("Choose a " + this.activityKind + " From the List to View Progress for the Selected Member: ");
        Utility utility = new Utility();
        Utility.setBoldFont(dropDownLabel);
        dropDownLabel.setForeground(new Color(9,95,54));
        JPanel activityDropDownPanel = new JPanel();

        this.activitiesDropDown.setEditable(false);

        this.activitiesDropDown.addActionListener(e -> setSelectedActivity());

        activityDropDownPanel.add(dropDownLabel, BorderLayout.WEST);
        activityDropDownPanel.add(this.activitiesDropDown, BorderLayout.EAST);
        activityDropDownPanel.setOpaque(false);
        return activityDropDownPanel;
    }

    private JPanel createStepsDetailsPanel() {
        JPanel stepsDetailsPanel = new JPanel();
        stepsDetailsPanel.setOpaque(false);

        TitledBorder titledBorder = new TitledBorder(" Steps Checklist ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 13f));
        stepsDetailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(10, 5, 2, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 20, 5, 20))));

        stepsDetailsPanel.setLayout(new BorderLayout());
        stepsDetailsPanel.add(createStepsChecklistPanel(), BorderLayout.NORTH);
        stepsDetailsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        return stepsDetailsPanel;
    }

    private JPanel createStepsChecklistPanel() {
        JPanel stepsChecklistPanel = new JPanel();
        stepsChecklistPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(7, 0, 10, 2);
        stepsChecklistPanel.setLayout(gridLayout);
        stepsChecklistPanel.add(this.activityNameField);
        stepsChecklistPanel.add(this.activityDescription);
        stepsChecklistPanel.add(this.step1Checkbox);
        stepsChecklistPanel.add(this.step2Checkbox);
        stepsChecklistPanel.add(this.step3Checkbox);
        stepsChecklistPanel.add(this.step4Checkbox);
        stepsChecklistPanel.add(this.step5Checkbox);

        this.step1Checkbox.getCheckBox().addActionListener(e->enableSaveButton());
        this.step2Checkbox.getCheckBox().addActionListener(e->enableSaveButton());
        this.step3Checkbox.getCheckBox().addActionListener(e->enableSaveButton());
        this.step4Checkbox.getCheckBox().addActionListener(e->enableSaveButton());
        this.step5Checkbox.getCheckBox().addActionListener(e->enableSaveButton());

        return stepsChecklistPanel;
    }

    private void enableSaveButton() {
        this.saveProgressButton.setEnabled(true);
    }

    private void setSelectedActivity() {
        try {
            int selectedIndex = this.activitiesDropDown.getSelectedIndex();
            if (selectedIndex != -1) {
                String activityName = this.activitiesDropDown.getItemAt(selectedIndex);
                this.selectedActivityInformation = getActivityInformation(activityName);
                this.showMemberActivityProgress();
            } else {
                this.selectedActivityInformation = null;
            }
        } catch (DatabaseException databaseException) {
            Utility.showDatabaseException(this.mainFrame, databaseException);
            return;
        }
    }

    private void setSelectedMember() {
        try {
            String selectedValue = this.memberList.getSelectedValue();
            if (selectedValue != null) {
                this.selectedMemberInformation = this.mainFrame.getDb().getMember(selectedValue);
                this.showMemberActivityProgress();
            } else {
                this.selectedMemberInformation = null;
            }
        } catch (DatabaseException e) {
            Utility.showDatabaseException(this.mainFrame, e);
            return;
        }
    }

    private void showMemberActivityProgress() {
        if ((this.selectedMemberInformation == null) || (this.selectedActivityInformation == null)) {
            return;
        }

        String[] stepNames = this.selectedActivityInformation.getSteps();
        this.activityNameField.setText(this.selectedActivityInformation.getName());
        this.activityDescription.setText(this.selectedActivityInformation.getDescription());
        this.step1Checkbox.setText(stepNames[0]);
        this.step2Checkbox.setText(stepNames[1]);
        this.step3Checkbox.setText(stepNames[2]);
        this.step4Checkbox.setText(stepNames[3]);
        this.step5Checkbox.setText(stepNames[4]);

        ActivityProgress activityProgress =
            this.getActivityProgress(this.selectedMemberInformation, this.selectedActivityInformation.getName());

        if (activityProgress == null) {
            this.step1Checkbox.setChecked(false);
            this.step2Checkbox.setChecked(false);
            this.step3Checkbox.setChecked(false);
            this.step4Checkbox.setChecked(false);
            this.step5Checkbox.setChecked(false);
        } else {
            this.step1Checkbox.setChecked(activityProgress.getStepProgress(0));
            this.step2Checkbox.setChecked(activityProgress.getStepProgress(1));
            this.step3Checkbox.setChecked(activityProgress.getStepProgress(2));
            this.step4Checkbox.setChecked(activityProgress.getStepProgress(3));
            this.step5Checkbox.setChecked(activityProgress.getStepProgress(4));
        }

        this.saveProgressButton.setEnabled(false);
    }


    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        saveProgressButton.addActionListener(e -> saveProgress());
        buttonsPanel.add(saveProgressButton);
        return buttonsPanel;
    }

    private void saveProgress() {

        if ((this.selectedMemberInformation == null) || (this.selectedActivityInformation == null)) {
            return;
        }

        ActivityProgress activityProgress =
            this.getActivityProgress(this.selectedMemberInformation, this.selectedActivityInformation.getName());
        if (activityProgress == null) {
            activityProgress = new ActivityProgress(this.selectedActivityInformation);
        }

        activityProgress.setStepProgress(0, this.step1Checkbox.isChecked());
        activityProgress.setStepProgress(1, this.step2Checkbox.isChecked());
        activityProgress.setStepProgress(2, this.step3Checkbox.isChecked());
        activityProgress.setStepProgress(3, this.step4Checkbox.isChecked());
        activityProgress.setStepProgress(4, this.step5Checkbox.isChecked());

        this.addOrModifyActivityProgress(this.selectedMemberInformation, activityProgress);
        try {
            this.mainFrame.getDb().addOrModifyMember(this.selectedMemberInformation);
            this.saveProgressButton.setEnabled(false);
        } catch (DatabaseException e) {
           Utility.showDatabaseException(this.mainFrame, e);
           return;
        }
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
            this.setSelectedMember();
        }

        this.activitiesDropDown.removeAllItems();
        ArrayList<String> activityNames = this.getActivityNames();
        for (int i = 0; i < activityNames.size(); i++) {
            this.activitiesDropDown.addItem(activityNames.get(i));
        }

        if (this.activitiesDropDown.getItemCount() != 0) {
            this.activitiesDropDown.setSelectedIndex(0);
            this.setSelectedActivity();
        }

        this.showMemberActivityProgress();
    }

    //protected abstract ActivityInformation createActivityInformation();
    protected abstract ArrayList<String> getActivityNames();
    protected abstract ActivityInformation getActivityInformation(String name)
           throws DatabaseException;
    protected abstract ActivityProgress getActivityProgress(MemberInformation member, String activityName);
    protected abstract void addOrModifyActivityProgress(MemberInformation member, ActivityProgress activityProgress);
}
