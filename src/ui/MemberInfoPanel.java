package ui;

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
import java.util.ArrayList;

public class MemberInfoPanel extends JPanel {

    private final MainFrame mainFrame;
    private DefaultListModel<String> memberNamesListModel;

    private JList<String> memberList;

    private LabelTextField nameField = new LabelTextField("Name : ");
    private LabelTextField emailField = new LabelTextField("Email : ");


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


                this.emailField.setText(memberInformation.getEmail());
                this.emailField.setEditable(false);
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
        GridLayout gridLayout = new GridLayout(2, 0, 10, 5);
        memberFieldsPanel.setLayout(gridLayout);

        memberFieldsPanel.add(this.nameField);
        memberFieldsPanel.add(this.emailField);
        return memberFieldsPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        JButton addMember = new JButton(" Add ");
        JButton saveMember = new JButton(" Save ");
        JButton deleteMember = new JButton(" Delete ");
        buttonsPanel.add(addMember);
        buttonsPanel.add(saveMember);
        buttonsPanel.add(deleteMember);
        return buttonsPanel;
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
