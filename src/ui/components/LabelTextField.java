package ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LabelTextField extends JPanel{

    static final int DEFAULT_TEXT_FIELD_SIZE = 100;
    private JTextField textField;

    public LabelTextField(String label) {
        this(label, DEFAULT_TEXT_FIELD_SIZE);
    }

    public LabelTextField(String label, int textFieldSize) {
        super();

        JLabel labelField = new JLabel(label);
        labelField.setBorder(new EmptyBorder(5,5,5,5));

        this.textField = new JTextField();
        this.setOpaque(false);

        this.setLayout(new BorderLayout());
        this.add(labelField, BorderLayout.WEST);
        this.add(textField, BorderLayout.CENTER);

        this.setBorder(new EmptyBorder(5,5,5,50));

    }

    public String getText() {
        return this.textField.getText();
    }

    public void setText(String text) {
        this.textField.setText(text);
    }

    public void setEditable(boolean editable) {
        this.textField.setEditable(editable);
    }
}
