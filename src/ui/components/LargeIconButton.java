package ui.components;

import ui.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LargeIconButton extends JButton {

    static final int ICON_SIZE = 40;
    static final int DEFAULT_HEIGHT = 60;
    static final int DEFAULT_WIDTH = 200;

    private final String imageLocation;
    private final String title;
    private final String description;
    private final int width;
    private final int height;

    public LargeIconButton(
            String imageLocation,
            String title,
            String description) {
        this(
            imageLocation,
            title,
            description,
            DEFAULT_WIDTH,
            DEFAULT_HEIGHT);
    }

    public LargeIconButton(
            String imageLocation,
            String title,
            String description,
            int width,
            int height) {

        super();
        this.imageLocation = imageLocation;
        this.title = title;
        this.description = description;
        this.width = width;
        this.height = height;

        // create components
        this.createLeftPanel();
        this.createRightPanel();

        // layout components
        this.setLayout(new BorderLayout());
        this.add(this.createLeftPanel(), BorderLayout.WEST);
        this.add(this.createRightPanel(), BorderLayout.CENTER);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        //// create components

        // image
        ImageIcon tmpIcon = new ImageIcon(this.getClass().getResource(this.imageLocation));
        Image image = tmpIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);

        JLabel iconLabel = new JLabel(new ImageIcon(image));
        iconLabel.setBorder(new EmptyBorder(10,5,5,5));
        iconLabel.setOpaque(false);

        //// layout components
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(ICON_SIZE, this.height));
        panel.add(iconLabel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        //// create component

        // title
        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setBorder(new EmptyBorder(5,10,0,5));
        titleLabel.setForeground(ColorScheme.DarkGreen);
        titleLabel.setOpaque(false);
        Utility.setBoldFont(titleLabel);

        // description
        JLabel descriptionArea = new JLabel();
        descriptionArea.setVerticalAlignment(JLabel.TOP);
        descriptionArea.setText(
                String.format("<html><body style=\"text-align: left; \">%s</body></html>",
                        description));
        descriptionArea.setBorder(new EmptyBorder(0, 10, 5, 5));
        descriptionArea.setPreferredSize(new Dimension(this.width, 0));
        descriptionArea.setOpaque(false);
        descriptionArea.setEnabled(false);

        //// layout components
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(descriptionArea, BorderLayout.CENTER);
        return panel;
    }
}
