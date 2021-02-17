package ui.components;

import ui.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IconCaptionButton extends JButton {

    static final int ICON_SIZE = 40;

    private final String imageLocation;
    private final String title;

    public IconCaptionButton(
            String imageLocation,
            String title) {

        super();
        this.imageLocation = imageLocation;
        this.title = title;

        // create components
        this.createTopPanel();
        this.createBottomPanel();

        // layout components
        this.setLayout(new BorderLayout());
        this.add(this.createTopPanel(), BorderLayout.NORTH);
        this.add(this.createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        //// create components

        // image
        ImageIcon tmpIcon = new ImageIcon(this.getClass().getResource(this.imageLocation));
        Image image = tmpIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);

        JLabel iconLabel = new JLabel(new ImageIcon(image));
        iconLabel.setBorder(new EmptyBorder(3,3,3,3));
        iconLabel.setOpaque(false);

        //// layout components
        panel.setLayout(new BorderLayout());
        panel.add(iconLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        //// create component

        // title
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(3,3,3,3));
        titleLabel.setOpaque(false);
        Utility.setBoldFont(titleLabel);

        //// layout components
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.CENTER);
        return panel;
    }
}
