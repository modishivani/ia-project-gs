package ui.components;

import ui.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LabelCheckBox extends JPanel{

    private JCheckBox checkBox;
    private JTextField textField;

    public LabelCheckBox(String label) {
        super();

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());
        westPanel.setOpaque(false);

        this.checkBox = new JCheckBox();

        //create label field for the title of the box
        JLabel labelField = new JLabel(label);
        labelField.setBorder(new EmptyBorder(5,5,5,5));
        Utility utility = new Utility();
        utility.setBoldFont(labelField);
        labelField.setForeground(ColorScheme.DarkGreen);

        //add checkbox and title label components
        westPanel.add(checkBox, BorderLayout.WEST);
        westPanel.add(labelField, BorderLayout.CENTER);

        //add text field to be populated with parameter information
        this.textField = new JTextField();
        this.textField.setEditable(false);
        this.textField.setOpaque(false);
        this.textField.setBorder(new EmptyBorder(0,0,0,0));

        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        //add components
        this.add(westPanel, BorderLayout.WEST);
        this.add(textField, BorderLayout.CENTER);

        this.setBorder(new EmptyBorder(2,5,2,5));
        this.setOpaque(false);

    }

    public void setText(String text) {
        this.textField.setText(text);
    }

    public boolean isChecked() {
        //returns whether or not the box is checked in the UI
        if (this.checkBox.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public void setChecked(boolean checked) {
        //sets the checkbox as checked or not in the UI
        if (checked)  {
            this.checkBox.setSelected(true);
        } else {
            this.checkBox.setSelected(false);
        }
    }

    public JCheckBox getCheckBox() {
        return this.checkBox;
    }
}
