package ui;

import db.exceptions.DatabaseException;
import db.MemberInformation;
import ui.components.ColorScheme;
import ui.components.LabelTextField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MemberInfoContentPanel extends ContentPanel {

    private DefaultListModel<String> memberNamesListModel;
    private JList<String> memberList;

    private LabelTextField nameField;
    private LabelTextField ageField;
    private LabelTextField gradeField;
    private LabelTextField emailField;
    private LabelTextField parentNameField;
    private LabelTextField parentEmailField;

    JButton addMemberButton;
    JButton editMemberButton;
    JButton saveMemberButton;
    JButton deleteMemberButton;

    private MemberInformation selectedMemberInformation;

    public MemberInfoContentPanel(MainFrame mainFrame) {
        super(mainFrame, "Member Information");
        this.createComponents();
        this.initialize();
    }

    protected JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(ColorScheme.LightPastelGreen);
        centerPanel.setLayout(new BorderLayout());

        // add list and information details panels
        centerPanel.add(createMemberListPanel(), BorderLayout.WEST);
        centerPanel.add(createMemberDetailsPanel(), BorderLayout.CENTER);

        return centerPanel;
    }

    private void createComponents() {

        // create information field labels
        this.nameField = new LabelTextField("Name : ");
        this.ageField = new LabelTextField("Age : ");
        this.gradeField = new LabelTextField("Grade : ");
        this.emailField = new LabelTextField("Email : ");
        this.parentNameField = new LabelTextField("Parent Name : ");
        this.parentEmailField = new LabelTextField("Parent Email : ");

        // create buttons with their labels
        this.addMemberButton = new JButton(" Add Member ");
        this.editMemberButton = new JButton(" Edit Member ");
        this.saveMemberButton = new JButton(" Save Member ");
        this.deleteMemberButton = new JButton(" Delete Member ");
    }

    private JPanel createMemberListPanel() {
        JPanel memberListPanel = new JPanel();
        this.memberNamesListModel = new DefaultListModel<>();
        this.memberList = new JList<>(this.memberNamesListModel);
        this.memberList.setFixedCellHeight(25);
        this.memberList.setOpaque(false);
        this.memberList.addListSelectionListener(e -> showMember(memberList.getSelectedValue()));
        memberListPanel.setLayout(new BorderLayout());

        // add the member list component
        memberListPanel.add(memberList, BorderLayout.CENTER);

        // set borders for the member list
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


    private JPanel createMemberDetailsPanel() {
        JPanel memberDetailsPanel = new JPanel();
        memberDetailsPanel.setOpaque(false);
        memberDetailsPanel.setLayout(new BorderLayout());

        // add fields and buttons components
        memberDetailsPanel.add(createMemberFieldsPanel(), BorderLayout.NORTH);
        memberDetailsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        // set layout and borders for the member details panel
        TitledBorder titledBorder = new TitledBorder(" Information ");
        titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC, 14f));
        memberDetailsPanel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 10, 5),
                BorderFactory.createCompoundBorder(
                    titledBorder,
                    BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        return memberDetailsPanel;
    }

    private JPanel createMemberFieldsPanel() {
        //set layout and look
        JPanel memberFieldsPanel = new JPanel();
        memberFieldsPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(6, 0, 10, 5);
        memberFieldsPanel.setLayout(gridLayout);

        // add information fields
        memberFieldsPanel.add(this.nameField);
        memberFieldsPanel.add(this.ageField);
        memberFieldsPanel.add(this.gradeField);
        memberFieldsPanel.add(this.emailField);
        memberFieldsPanel.add(this.parentNameField);
        memberFieldsPanel.add(this.parentEmailField);

        return memberFieldsPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        // call appropriate methods when each button is clicked
        addMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });

        editMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMember();
            }
        });

        saveMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveMember();
                } catch (Exception e1) {
                    // show exception dialog box for invalid data or database exceptions
                    Utility.showException(mainFrame, e1);
                }
            }
        });

        deleteMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });

        // add components
        buttonsPanel.add(addMemberButton);
        buttonsPanel.add(editMemberButton);
        buttonsPanel.add(saveMemberButton);
        buttonsPanel.add(deleteMemberButton);
        return buttonsPanel;
    }

    private void showMember(String selectedValue) {
        try {
            if (selectedValue != null) {
                // show  existing information for the selected member
                MemberInformation memberInformation = this.mainFrame.getDb().getMember(selectedValue);
                this.nameField.setText(memberInformation.getName());

                if (memberInformation.getAge() != 0) {
                    this.ageField.setText(Integer.toString(memberInformation.getAge()));
                } else {
                    this.ageField.setText("");
                }

                if (memberInformation.getGrade() != 0) {
                    this.gradeField.setText(Integer.toString(memberInformation.getGrade()));
                } else {
                    this.gradeField.setText("");
                }

                this.emailField.setText(memberInformation.getEmail());
                this.parentNameField.setText(memberInformation.getParentName());
                this.parentEmailField.setText(memberInformation.getParentEmail());

                this.selectedMemberInformation = memberInformation;
                this.setMemberDetailEditable(false, false);
            } else {
                this.selectedMemberInformation = null;
            }

        } catch (Exception e) {
            // show exception if the member information cannot be shown
           Utility.showException(this.mainFrame, e);
        }
    }

    private void addMember() {
        // when the add member button is clicked, all information fields need to be blank and editable
        this.selectedMemberInformation = null;
        this.setMemberDetailEditable(true, true);
        ListSelectionModel sm = memberList.getSelectionModel();
        sm.clearSelection();
    }

    private void editMember() {
        // when the edit member button is clicked, all fields besides the name should be editable
        this.setMemberDetailEditable(true, false);
    }

    private void saveMember() {
        MemberInformation memberInformation = this.selectedMemberInformation;
        if (memberInformation == null) {
            // for when a member is being added
            memberInformation = new MemberInformation();
        }

        // set the member information to the information in the fields
        memberInformation.setName(this.nameField.getText());
        if (!this.ageField.getText().equals("")) {
            memberInformation.setAge(this.ageField.getText());
        }
        if (!this.gradeField.getText().equals("")) {
            memberInformation.setGrade(this.gradeField.getText());
        }
        memberInformation.setEmail(this.emailField.getText());
        memberInformation.setParentName(this.parentNameField.getText());
        memberInformation.setParentEmail(this.parentEmailField.getText());

        // save that member information to the database
        try {
            this.mainFrame.getDb().addOrModifyMember(memberInformation);

            // if the member name is not already in the list, add it
            if (!this.memberNamesListModel.contains(memberInformation.getName())) {
                int index = this.memberNamesListModel.size();
                this.memberNamesListModel.add(
                        index,
                        memberInformation.getName());

                ListSelectionModel sm = memberList.getSelectionModel();
                sm.clearSelection();
                sm.setSelectionInterval(index, index);
            }
            // set the fields not editable after saving
            this.setMemberDetailEditable(false, false);
        } catch (Exception e){
            // show exception dialog box
            Utility.showException(this.mainFrame, e, e.getMessage());
        }
    }

    private void deleteMember() {
        String selectedValue = memberList.getSelectedValue();
        // show deletion confirmation message
        int input = JOptionPane.showConfirmDialog(  //yes = 0, no = 1
                this.mainFrame,
                "Are you sure you want to delete " + selectedValue + " ?",
                "",
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) { // user confirms the deletion
            // delete from database
            this.mainFrame.getDb().removeMember(selectedValue);
            // delete from member list
            int selectedIndex = memberList.getSelectedIndex();
            this.memberNamesListModel.remove(selectedIndex);

            // show the information of the next member in the list
            if (selectedIndex != 0) {
                selectedIndex--;
            }

            ListSelectionModel sm = memberList.getSelectionModel();
            sm.clearSelection();
            sm.setSelectionInterval(selectedIndex, selectedIndex);
        }
    }

    private void setMemberDetailEditable(boolean editable, boolean nameFieldEditable) {
        this.nameField.setEditable(nameFieldEditable);
        this.ageField.setEditable(editable);
        this.gradeField.setEditable(editable);
        this.emailField.setEditable(editable);
        this.parentNameField.setEditable(editable);
        this.parentEmailField.setEditable(editable);

        // for adding a new member, all the fields would be editable and blank,
        if (nameFieldEditable) {
            this.nameField.setText("");
            this.ageField.setText("");
            this.gradeField.setText("");
            this.emailField.setText("");
            this.parentNameField.setText("");
            this.parentEmailField.setText("");
        }

        // if none of the fields are editable, all buttons besides save should be enabled, since there is nothing to save
        if (!editable && !nameFieldEditable) {
            this.addMemberButton.setEnabled(true);
            this.deleteMemberButton.setEnabled(true);
            this.editMemberButton.setEnabled(true);
            this.saveMemberButton.setEnabled(false);
        }

        //if everything but name is editable, this is editing a member - so the edit member button has been clicked and should not be enabled
        if (editable && !nameFieldEditable) {
            this.addMemberButton.setEnabled(true);
            this.deleteMemberButton.setEnabled(true);
            this.editMemberButton.setEnabled(false);
            this.saveMemberButton.setEnabled(true);
        }

        //if everything is enabled, this is adding a member, so only the save button should be enabled
        if (editable && nameFieldEditable) {
            this.addMemberButton.setEnabled(false);
            this.deleteMemberButton.setEnabled(false);
            this.editMemberButton.setEnabled(false);
            this.saveMemberButton.setEnabled(true);
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
        }
    }
}
