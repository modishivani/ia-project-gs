package ui;

import db.ActivityInformation;
import db.DatabaseException;
import db.MemberInformation;
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

    private final LabelTextField activityNameField = new LabelTextField("Name : ");
    private final LabelTextField descriptionField = new LabelTextField("Description : ");
    private final LabelTextField step1Field = new LabelTextField("Step 1 : ");
    private final LabelTextField step2Field = new LabelTextField("Step 2 : ");
    private final LabelTextField step3Field = new LabelTextField("Step 3 : ");
    private final LabelTextField step4Field = new LabelTextField("Step 4 : ");
    private final LabelTextField step5Field = new LabelTextField("Step 5 : ");


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
                        BorderFactory.createEmptyBorder(0, 5, 5, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5))));
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
        TitledBorder titledBorder = new TitledBorder(" Names ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 13f));
        activityListPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2, 5, 2, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 20, 5, 20))));

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
                        BorderFactory.createEmptyBorder(2, 5, 2, 5),
                        BorderFactory.createCompoundBorder(
                                titledBorder,
                                BorderFactory.createEmptyBorder(5, 20, 5, 20))));

        activityInformationPanel.setLayout(new BorderLayout());
        activityInformationPanel.add(createActivityFieldsPanel(), BorderLayout.NORTH);
        activityInformationPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        return activityInformationPanel;
    }

    private JPanel createActivityFieldsPanel() {
        JPanel activityFieldsPanel = new JPanel();
        activityFieldsPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(7, 0, 10, 5);
        activityFieldsPanel.setLayout(gridLayout);

        activityFieldsPanel.add(this.activityNameField);
        activityFieldsPanel.add(this.descriptionField);
        activityFieldsPanel.add(this.step1Field);
        activityFieldsPanel.add(this.step2Field);
        activityFieldsPanel.add(this.step3Field);
        activityFieldsPanel.add(this.step4Field);
        activityFieldsPanel.add(this.step5Field);

        return activityFieldsPanel;
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
        this.selectedActivityInformation = null;
        this.setActivityDetailEditable(true, true);

        ListSelectionModel sm = activityList.getSelectionModel();
        sm.clearSelection();
    }

    private void editActivity() {
        this.setActivityDetailEditable(true, false);
    }

    private void saveActivity() {
        String activityName = this.activityNameField.getText();

        ActivityInformation activityInformation = this.selectedActivityInformation;
        if (activityInformation == null) {
            activityInformation = this.createActivityInformation();
        }

        activityInformation.setName(this.activityNameField.getText());
        activityInformation.setDescription(this.descriptionField.getText());
        String[] activitySteps = {
                this.step1Field.getText(),
                this.step2Field.getText(),
                this.step3Field.getText(),
                this.step4Field.getText(),
                this.step5Field.getText()
        };
        activityInformation.setSteps(activitySteps);

        try {
            //TODO: fix error when saving a new activity (activity information cannot be cast to badgeinformation)
            this.addOrModifyActivityInformation(activityInformation);
            if (!this.activityListModel.contains(activityInformation.getName())) {
                int index = this.activityListModel.size();
                this.activityListModel.add(
                        index,
                        activityInformation.getName());

                ListSelectionModel sm = activityList.getSelectionModel();
                sm.clearSelection();
                sm.setSelectionInterval(index, index);
            }
            this.setActivityDetailEditable(false, false);

        } catch (Exception e){
            int input = JOptionPane.showConfirmDialog(
                    this.mainFrame,
                    e.getMessage(),
                    "Error",
                    JOptionPane.CLOSED_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteActivity() {
        String selectedValue = activityList.getSelectedValue();
        int input = JOptionPane.showConfirmDialog(  //yes = 0, no = 1
                this.mainFrame,
                "Are you sure you want to delete " + selectedValue + " ?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) {
            this.removeActivityInformation(selectedValue);

            int selectedIndex = activityList.getSelectedIndex();
            this.activityListModel.remove(selectedIndex);

            if (selectedIndex != 0) {
                selectedIndex--;
            }

            ListSelectionModel sm = activityList.getSelectionModel();
            sm.clearSelection();
            sm.setSelectionInterval(selectedIndex, selectedIndex);
        }
    }

    private void showActivity(String selectedValue) {
        try {

            if (selectedValue != null) {
                ActivityInformation activityInformation = this.getActivityInformation(selectedValue);
                this.activityNameField.setText(activityInformation.getName());
                this.descriptionField.setText(activityInformation.getDescription());
                this.step1Field.setText(activityInformation.getSteps()[0]);
                this.step2Field.setText(activityInformation.getSteps()[1]);
                this.step3Field.setText(activityInformation.getSteps()[2]);
                this.step4Field.setText(activityInformation.getSteps()[3]);
                this.step5Field.setText(activityInformation.getSteps()[4]);

                this.selectedActivityInformation = activityInformation;
                this.setActivityDetailEditable(false, false);
            } else {
                this.selectedActivityInformation = null;
            }

        } catch (DatabaseException e) {
            // TODO: replace with message box
            System.out.println(e.toString());
        }
    }

    private void setActivityDetailEditable(boolean editable, boolean nameFieldEditable) {
        this.activityNameField.setEditable(nameFieldEditable);
        this.descriptionField.setEditable(editable);
        this.step1Field.setEditable(editable);
        this.step2Field.setEditable(editable);
        this.step3Field.setEditable(editable);
        this.step4Field.setEditable(editable);
        this.step5Field.setEditable(editable);

        if (nameFieldEditable) {
            this.activityNameField.setText("");
            this.descriptionField.setText("");
            this.step1Field.setText("");
            this.step2Field.setText("");
            this.step3Field.setText("");
            this.step4Field.setText("");
            this.step5Field.setText("");
        }

        if (!editable && !nameFieldEditable) {
            this.addButton.setEnabled(true);
            this.deleteButton.setEnabled(true);
            this.editButton.setEnabled(true);
            this.saveButton.setEnabled(false);
        }

        if (editable && !nameFieldEditable) {
            this.addButton.setEnabled(true);
            this.deleteButton.setEnabled(true);
            this.editButton.setEnabled(false);
            this.saveButton.setEnabled(true);
        }

        if (editable && nameFieldEditable) {
            this.addButton.setEnabled(false);
            this.deleteButton.setEnabled(false);
            this.editButton.setEnabled(false);
            this.saveButton.setEnabled(true);
        }
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
    protected abstract ActivityInformation getActivityInformation(String name)
            throws DatabaseException;
    protected abstract void addOrModifyActivityInformation(ActivityInformation activity)
            throws DatabaseException;
    protected abstract void removeActivityInformation(String name);
}
