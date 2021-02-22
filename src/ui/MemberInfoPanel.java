package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.DatabaseException;
import db.MemberInformation;
import ui.components.IconCaptionButton;
import ui.components.LabelTextField;
import ui.components.TopInfoPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MemberInfoPanel extends JPanel {

    private final MainFrame mainFrame;
    private DefaultListModel<String> memberNamesListModel;

    private JList<String> memberList;

    private LabelTextField nameField = new LabelTextField("Name : ");
    private LabelTextField ageField = new LabelTextField("Age : ");
    private LabelTextField gradeField = new LabelTextField("Grade : ");
    private LabelTextField emailField = new LabelTextField("Email : ");
    private LabelTextField parentNameField = new LabelTextField("Parent Name : ");
    private LabelTextField parentEmailField = new LabelTextField("Parent Email : ");


    public MemberInfoPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        return new TopInfoPanel(this.mainFrame, "   Member Information");
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();

        /*
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(30);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setLeftComponent(createMemberListPanel());
        splitPane.setRightComponent(createMemberDetailsPanel());
        splitPane.sete
*/
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
        this.memberList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                loadMemberDetails(memberList.getSelectedValue());
            }
        });

        memberListPanel.setLayout(new BorderLayout());
        memberListPanel.add(memberList, BorderLayout.CENTER);
        memberListPanel.setPreferredSize(new Dimension(200, 0));
        memberListPanel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 10, 5),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(" Names "),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        memberListPanel.setOpaque(false);
        return memberListPanel;
    }

    private void loadMemberDetails(String selectedValue) {
        try {

            if (selectedValue != null) {
                MemberInformation memberInformation = this.mainFrame.getDb().getMember(selectedValue);
                this.nameField.setText(memberInformation.getName());
                this.nameField.setEditable(false);

                if (memberInformation.getAge() != 0) {
                    this.ageField.setText(Integer.toString(memberInformation.getAge()));
                } else {
                    this.ageField.setText("");
                }
                this.ageField.setEditable(false);

                if (memberInformation.getGrade() != 0) {
                    this.gradeField.setText(Integer.toString(memberInformation.getGrade()));
                } else {
                    this.gradeField.setText("");
                }
                this.gradeField.setEditable(false);

                this.emailField.setText(memberInformation.getEmail());
                this.emailField.setEditable(false);

                this.parentNameField.setText(memberInformation.getParentName());
                this.parentNameField.setEditable(false);

                this.parentEmailField.setText(memberInformation.getParentEmail());
                this.parentEmailField.setEditable(false);
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
    }

    private JPanel createMemberDetailsPanel() {
        JPanel memberDetailsPanel = new JPanel();
        memberDetailsPanel.setLayout(new BorderLayout());
        memberDetailsPanel.add(createMemberFieldsPanel(), BorderLayout.NORTH);
        memberDetailsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        memberDetailsPanel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 10, 5),
                BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder(" Information "),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20))));

        return memberDetailsPanel;
    }

    private JPanel createMemberFieldsPanel() {
        JPanel memberFieldsPanel = new JPanel();
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
        JButton addMemberButton = new JButton(" Add Member ");
        addMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addMember();
                } catch (DatabaseException databaseException) {
                    databaseException.printStackTrace();
                }
            }
        });

        JButton editMemberButton = new JButton(" Edit Member ");
        editMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMember();
            }
        });

        JButton saveMemberButton = new JButton(" Save Member ");
        saveMemberButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveMember();
                } catch (DatabaseException databaseException) {
                    databaseException.printStackTrace();
                }
            }
        });

        JButton deleteMemberButton = new JButton(" Delete Member ");
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

    private void addMember() throws DatabaseException {
        this.nameField.setEditable(true);
        this.ageField.setEditable(true);
        this.gradeField.setEditable(true);
        this.emailField.setEditable(true);
        this.parentNameField.setEditable(true);
        this.parentEmailField.setEditable(true);
        /*MemberInformation tempMemberInformation = new MemberInformation();
        tempMemberInformation.setName("  ");
        this.mainFrame.getDb().addMember(tempMemberInformation);
        createMemberListPanel();*/
    }

    private void editMember() {
        this.nameField.setEditable(false);
        this.ageField.setEditable(true);
        this.gradeField.setEditable(true);
        this.emailField.setEditable(true);
        this.parentNameField.setEditable(true);
        this.parentEmailField.setEditable(true);
    }

    private void saveMember() throws DatabaseException {
        MemberInformation memberInformation = this.mainFrame.getDb().getMember(this.nameField.getText());

       /* MemberInformation tempMemberInformation = new MemberInformation();
        tempMemberInformation.setName(this.nameField.getText());
        this.mainFrame.getDb().addMember(tempMemberInformation);
        createMemberListPanel();*/

        if (!this.ageField.getText().equals("")) {
            memberInformation.setAge(Integer.parseInt(this.ageField.getText()));
        }
        if (!this.gradeField.getText().equals("")) {
            memberInformation.setGrade(Integer.parseInt(this.gradeField.getText()));
        }
        memberInformation.setEmail(this.emailField.getText());
        memberInformation.setParentName(this.parentNameField.getText());
        memberInformation.setParentEmail(this.parentEmailField.getText());

        this.mainFrame.getDb().addMember(memberInformation);

        this.nameField.setEditable(false);
        this.ageField.setEditable(false);
        this.gradeField.setEditable(false);
        this.emailField.setEditable(false);
        this.parentNameField.setEditable(false);
        this.parentEmailField.setEditable(false);
    }

    private void deleteMember() {
        String selectedValue = memberList.getSelectedValue();
        this.mainFrame.getDb().removeMember(selectedValue);
    }

    boolean tryLoadData() {

        try {
            loadData();

            return true;
        } catch (DatabaseException db) {

            //MessageBox.
            return false;
        }

    }

    void loadData()
        throws DatabaseException {

        ArrayList<String> memberNames = this.mainFrame.getDb().listMemberNames();

        this.memberNamesListModel.clear();
        this.memberNamesListModel.addAll(memberNames);

        this.memberList.requestFocus();
        if (!this.memberNamesListModel.isEmpty()) {
            this.memberList.setSelectedIndex(0);
        }

    }
}
