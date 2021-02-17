package ui.components;

import ui.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IconPanel extends JPanel {

    static final int DEFAULT_SIZE = 200;

    public IconPanel(
        String imageLocation) {
        this(imageLocation, DEFAULT_SIZE);
    }

    public IconPanel(
        String imageLocation,
        int size) {

        super();

        this.setOpaque(false);

        // image
        ImageIcon tmpIcon = new ImageIcon(this.getClass().getResource(imageLocation));
        Image image = tmpIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

        JLabel iconLabel = new JLabel(new ImageIcon(image));
        iconLabel.setBorder(new EmptyBorder(3, 3, 3, 3));
        iconLabel.setOpaque(false);

        //// layout components
        this.setLayout(new BorderLayout());
        this.add(iconLabel, BorderLayout.CENTER);
    }
}