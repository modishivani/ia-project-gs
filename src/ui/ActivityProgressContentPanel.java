package ui;

import db.ActivityInformation;
import db.ActivityProgress;
import db.exceptions.DatabaseException;
import db.MemberInformation;
import ui.components.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.*;
import java.awt.*;


public abstract class ActivityProgressContentPanel extends ContentPanel {

    private String activityKind;

    private DefaultListModel<String> memberNamesListModel;
    private JList<String> memberList;

    private JComboBox<String> activitiesDropDown;
    private LabelTextArea activityNameField;
    private LabelTextArea activityDescription;
    private LabelCheckBox step1Checkbox;
    private LabelCheckBox step2Checkbox;
    private LabelCheckBox step3Checkbox;
    private LabelCheckBox step4Checkbox;
    private LabelCheckBox step5Checkbox;
    private JButton saveProgressButton;

    private MemberInformation selectedMemberInformation;
    private ActivityInformation selectedActivityInformation;

    public ActivityProgressContentPanel(MainFrame mainFrame, String activityKind) throws DatabaseException {
        super(mainFrame, activityKind + " Progress");
        //setting activity kind to badge or journey based on the panel the user is in
        this.activityKind = activityKind;

        this.createComponents();
        this.initialize();
    }

    protected JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(ColorScheme.LightPastelGreen);
        centerPanel.setLayout(new BorderLayout());
        //add components
        centerPanel.add(createMemberListPanel(), BorderLayout.WEST);
        centerPanel.add(createProgressInformationPanel(), BorderLayout.CENTER);
        return centerPanel;
    }

    private void createComponents() {
        //create fields to show the database information
        this.activityNameField = new LabelTextArea(" " + this.activityKind + " Name: ");
        this.activityDescription = new LabelTextArea(" " + this.activityKind + " Description: ");
        //create save button
        this.saveProgressButton = new JButton(" Save " + this.activityKind + " Progress ");
        //create drop down list for activities
        this.activitiesDropDown = new JComboBox<>();
        //create checkboxes for the steps
        this.step1Checkbox = new LabelCheckBox("Step 1:");
        this.step2Checkbox = new LabelCheckBox("Step 2:");
        this.step3Checkbox = new LabelCheckBox("Step 3:");
        this.step4Checkbox = new LabelCheckBox("Step 4:");
        this.step5Checkbox = new LabelCheckBox("Step 5:");
    }

    private JPanel createMemberListPanel() {
        JPanel memberListPanel = new JPanel();
        memberListPanel.setOpaque(false);

        this.memberNamesListModel = new DefaultListModel<>();
        this.memberList = new JList<>(this.memberNamesListModel);
        this.memberList.setFixedCellHeight(25);
        this.memberList.setOpaque(false);
        //set a member as selected when the user clicks on it
        this.memberList.addListSelectionListener(e -> setSelectedMember());
        //set size and borders
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
        //add the list information
        memberListPanel.add(this.memberList, BorderLayout.CENTER);

        return memberListPanel;
    }

    private JPanel createProgressInformationPanel() {
        JPanel progressInformationPanel = new JPanel();
        progressInformationPanel.setOpaque(false);
        progressInformationPanel.setLayout(new BorderLayout());
        //set borders
        TitledBorder titledBorder = new TitledBorder(" " + this.activityKind + " Progress Details ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        progressInformationPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 5, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5))));

        //add components
        progressInformationPanel.add(createActivityDropDownPanel(), BorderLayout.NORTH);
        progressInformationPanel.add(createStepsDetailsPanel(), BorderLayout.CENTER);
        return progressInformationPanel;
    }

    private JPanel createActivityDropDownPanel() {
        JLabel dropDownLabel = new JLabel("Choose a " + this.activityKind + " From the List to View Progress for the Selected Member: ");
        Utility.setBoldFont(dropDownLabel);
        dropDownLabel.setForeground(ColorScheme.DarkGreen);
        JPanel activityDropDownPanel = new JPanel();
        //drop down items cannot be edited
        this.activitiesDropDown.setEditable(false);
        //when a user clicks on an activity, set it as the selected activity
        this.activitiesDropDown.addActionListener(e -> setSelectedActivity());
        //add components
        activityDropDownPanel.add(dropDownLabel, BorderLayout.WEST);
        activityDropDownPanel.add(this.activitiesDropDown, BorderLayout.EAST);
        activityDropDownPanel.setOpaque(false);
        return activityDropDownPanel;
    }

    private JPanel createStepsDetailsPanel() {
        JPanel stepsDetailsPanel = new JPanel();
        stepsDetailsPanel.setOpaque(false);
        //set borders
        TitledBorder titledBorder = new TitledBorder(" Steps Checklist ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 13f));
        stepsDetailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(10, 5, 2, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 20, 5, 20))));

        stepsDetailsPanel.setLayout(new BorderLayout());

        //add the step lists and the buttons
        stepsDetailsPanel.add(createStepsChecklistPanel(), BorderLayout.NORTH);
        stepsDetailsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        return stepsDetailsPanel;
    }

    private JPanel createStepsChecklistPanel() {
        JPanel stepsChecklistPanel = new JPanel();
        stepsChecklistPanel.setOpaque(false);
        //layout the steps
        GridLayout gridLayout = new GridLayout(7, 0, 10, 2);
        stepsChecklistPanel.setLayout(gridLayout);

        //add the components
        stepsChecklistPanel.add(this.activityNameField);
        stepsChecklistPanel.add(this.activityDescription);

        stepsChecklistPanel.add(this.step1Checkbox);
        stepsChecklistPanel.add(this.step2Checkbox);
        stepsChecklistPanel.add(this.step3Checkbox);
        stepsChecklistPanel.add(this.step4Checkbox);
        stepsChecklistPanel.add(this.step5Checkbox);

        //if any boxes are clicked, enable the save button as the progress has changed and needs to be updated in the database
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
        } catch (Exception e) {
            //show any error messages in a dialog box if they occur
            Utility.showException(this.mainFrame, e);
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
        } catch (Exception e) {
            //show any error messages in a dialog box if they occur
            Utility.showException(this.mainFrame, e);
            return;
        }
    }

    private void showMemberActivityProgress() {
        //if nothing is selected, do nothing and return
        if ((this.selectedMemberInformation == null) || (this.selectedActivityInformation == null)) {
            return;
        }
        //set the text of the fields to the selected activity information
        String[] stepNames = this.selectedActivityInformation.getSteps();
        this.activityNameField.setText(this.selectedActivityInformation.getName());
        this.activityDescription.setText(this.selectedActivityInformation.getDescription());
        this.step1Checkbox.setText(stepNames[0]);
        this.step2Checkbox.setText(stepNames[1]);
        this.step3Checkbox.setText(stepNames[2]);
        this.step4Checkbox.setText(stepNames[3]);
        this.step5Checkbox.setText(stepNames[4]);

        //get activity progress
        ActivityProgress activityProgress =
            this.getActivityProgress(this.selectedMemberInformation, this.selectedActivityInformation.getName());

        //if there is no progress, all checkboxes are not checked
        if (activityProgress == null) {
            this.step1Checkbox.setChecked(false);
            this.step2Checkbox.setChecked(false);
            this.step3Checkbox.setChecked(false);
            this.step4Checkbox.setChecked(false);
            this.step5Checkbox.setChecked(false);
        } else {
            //check boxes based on what is marked as true in the database
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
        //when the save button is clicked, call the appropriate method
        saveProgressButton.addActionListener(e -> saveProgress());
        buttonsPanel.add(saveProgressButton);
        return buttonsPanel;
    }

    private void saveProgress() {
        //if nothing is selected, return
        if ((this.selectedMemberInformation == null) || (this.selectedActivityInformation == null)) {
            return;
        }

        //get the appropriate information from the database
        ActivityProgress activityProgress =
            this.getActivityProgress(this.selectedMemberInformation, this.selectedActivityInformation.getName());
        if (activityProgress == null) { //if there is no previous progress, create new activity progress
            activityProgress = new ActivityProgress(this.selectedActivityInformation);
        }

        //set step progress based on what is checked and not checked in the UI
        activityProgress.setStepProgress(0, this.step1Checkbox.isChecked());
        activityProgress.setStepProgress(1, this.step2Checkbox.isChecked());
        activityProgress.setStepProgress(2, this.step3Checkbox.isChecked());
        activityProgress.setStepProgress(3, this.step4Checkbox.isChecked());
        activityProgress.setStepProgress(4, this.step5Checkbox.isChecked());

        this.addOrModifyActivityProgress(this.selectedMemberInformation, activityProgress);
        try {
            //try saving activity progress under the member information to the database
            this.mainFrame.getDb().addOrModifyMember(this.selectedMemberInformation);
            this.saveProgressButton.setEnabled(false);
        } catch (Exception e) {
            //show any error messages in a dialog box if they occur
           Utility.showException(this.mainFrame, e);
           return;
        }
    }

    public boolean tryLoadData() {
        this.loadData();
        return true;
    }

    void loadData() {

        ArrayList<String> memberNames = this.mainFrame.getDb().listMemberNames();

        //add names for the list of members
        this.memberNamesListModel.clear();
        this.memberNamesListModel.addAll(memberNames);

        //set the focus to the first element in the list upon entering the panel
        this.memberList.requestFocus();
        if (!this.memberNamesListModel.isEmpty()) {
            this.memberList.setSelectedIndex(0);
            this.setSelectedMember();
        }

        //ensure the list is clear, then add the activity names to the dropdown list
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

    protected abstract ArrayList<String> getActivityNames();
    protected abstract ActivityInformation getActivityInformation(String name)
           throws DatabaseException;
    protected abstract ActivityProgress getActivityProgress(MemberInformation member, String activityName);
    protected abstract void addOrModifyActivityProgress(MemberInformation member, ActivityProgress activityProgress);
}
