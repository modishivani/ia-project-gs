package ui.components;

import ui.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LabelTextArea extends JPanel{

    private JLabel textLabel;

    public LabelTextArea(String label) {
        super();

        JLabel fieldTitle = new JLabel(label);
        Utility utility = new Utility();
        utility.setBoldFont(fieldTitle);
        fieldTitle.setForeground(new Color(9,95,54));
        fieldTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.textLabel = new JLabel();
        this.setOpaque(false);

        this.textLabel.setVerticalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.add(fieldTitle, BorderLayout.WEST);
        this.add(textLabel, BorderLayout.CENTER);

        this.setBorder(new EmptyBorder(5,5,5,10));

    }

    public void setText(String text) {
        this.textLabel.setText(
                String.format("<html><body style=\"text-align: left; \">%s</body></html>",
                        text));
    }

}
