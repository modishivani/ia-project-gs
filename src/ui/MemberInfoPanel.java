package ui;

import db.DatabaseException;
import db.MemberInformation;
import ui.components.LabelTextField;
import ui.components.TopInfoPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MemberInfoPanel extends JPanel {

    private final MainFrame mainFrame;

    private DefaultListModel<String> memberNamesListModel;
    private JList<String> memberList;

    private final LabelTextField nameField = new LabelTextField("Name : ");
    private final LabelTextField ageField = new LabelTextField("Age : ");
    private final LabelTextField gradeField = new LabelTextField("Grade : ");
    private final LabelTextField emailField = new LabelTextField("Email : ");
    private final LabelTextField parentNameField = new LabelTextField("Parent Name : ");
    private final LabelTextField parentEmailField = new LabelTextField("Parent Email : ");

    JButton addMemberButton = new JButton(" Add Member ");
    JButton editMemberButton = new JButton(" Edit Member ");
    JButton saveMemberButton = new JButton(" Save Member ");
    JButton deleteMemberButton = new JButton(" Delete Member ");

    private MemberInformation selectedMemberInformation;

    public MemberInfoPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new TopInfoPanel(this.mainFrame, " Member Information");
        topPanel.setBackground(new Color(233,246,220));
        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(247,252,242));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(createMemberListPanel(), BorderLayout.WEST);
        centerPanel.add(createMemberDetailsPanel(), BorderLayout.CENTER);


        return centerPanel;
    }

    private JPanel createMemberListPanel() {
        JPanel memberListPanel = new JPanel();

        this.memberNamesListModel = new DefaultListModel<>();
        this.memberList = new JList<>(this.memberNamesListModel);
        this.memberList.setFixedCellHeight(25);
        this.memberList.setOpaque(false);
        this.memberList.addListSelectionListener(e -> showMember(memberList.getSelectedValue()));

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


    private JPanel createMemberDetailsPanel() {
        JPanel memberDetailsPanel = new JPanel();
        memberDetailsPanel.setOpaque(false);
        memberDetailsPanel.setLayout(new BorderLayout());
        memberDetailsPanel.add(createMemberFieldsPanel(), BorderLayout.NORTH);
        memberDetailsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
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
        JPanel memberFieldsPanel = new JPanel();
        memberFieldsPanel.setOpaque(false);
        GridLayout gridLayout = new GridLayout(6, 0, 10, 5);
        memberFieldsPanel.setLayout(gridLayout);

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
                } catch (DatabaseException databaseException) {
                    Utility.showDatabaseException(mainFrame, databaseException);
                    return;
                }
            }
        });

        deleteMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });
        buttonsPanel.add(addMemberButton);
        buttonsPanel.add(editMemberButton);
        buttonsPanel.add(saveMemberButton);
        buttonsPanel.add(deleteMemberButton);
        return buttonsPanel;
    }

    private void showMember(String selectedValue) {
        try {

            if (selectedValue != null) {
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

        } catch (DatabaseException e) {
           Utility.showDatabaseException(this.mainFrame, e);
           return;
        }
    }

    private void addMember() {
        this.selectedMemberInformation = null;
        this.setMemberDetailEditable(true, true);

        ListSelectionModel sm = memberList.getSelectionModel();
        sm.clearSelection();
    }

    private void editMember() {
        this.setMemberDetailEditable(true, false);
    }

    private void saveMember() throws DatabaseException {

        String memberName = this.nameField.getText();

        MemberInformation memberInformation = this.selectedMemberInformation;
        if (memberInformation == null) {
            memberInformation = new MemberInformation();
        }
        memberInformation.setName(this.nameField.getText());
        if (!this.ageField.getText().equals("")) {
            memberInformation.setAge(Integer.parseInt(this.ageField.getText()));
        }
        if (!this.gradeField.getText().equals("")) {
            memberInformation.setGrade(Integer.parseInt(this.gradeField.getText()));
        }
        memberInformation.setEmail(this.emailField.getText());
        memberInformation.setParentName(this.parentNameField.getText());
        memberInformation.setParentEmail(this.parentEmailField.getText());

        try {
            this.mainFrame.getDb().addOrModifyMember(memberInformation);
            if (!this.memberNamesListModel.contains(memberInformation.getName())) {
                int index = this.memberNamesListModel.size();
                this.memberNamesListModel.add(
                        index,
                        memberInformation.getName());

                ListSelectionModel sm = memberList.getSelectionModel();
                sm.clearSelection();
                sm.setSelectionInterval(index, index);
            }
            this.setMemberDetailEditable(false, false);
        } catch (Exception e){
            int input = JOptionPane.showConfirmDialog(
                    this.mainFrame,
                    e.getMessage(),
                    "Error",
                    JOptionPane.CLOSED_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteMember() {
        String selectedValue = memberList.getSelectedValue();
        int input = JOptionPane.showConfirmDialog(  //yes = 0, no = 1
                this.mainFrame,
                "Are you sure you want to delete " + selectedValue + " ?",
                "",
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) {
            this.mainFrame.getDb().removeMember(selectedValue);

            int selectedIndex = memberList.getSelectedIndex();
            this.memberNamesListModel.remove(selectedIndex);

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

        if (nameFieldEditable) {
            this.nameField.setText("");
            this.ageField.setText("");
            this.gradeField.setText("");
            this.emailField.setText("");
            this.parentNameField.setText("");
            this.parentEmailField.setText("");
        }

        if (!editable && !nameFieldEditable) {
            this.addMemberButton.setEnabled(true);
            this.deleteMemberButton.setEnabled(true);
            this.editMemberButton.setEnabled(true);
            this.saveMemberButton.setEnabled(false);
        }

        if (editable && !nameFieldEditable) {
            this.addMemberButton.setEnabled(true);
            this.deleteMemberButton.setEnabled(true);
            this.editMemberButton.setEnabled(false);
            this.saveMemberButton.setEnabled(true);
        }

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
