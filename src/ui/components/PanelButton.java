package ui.components;

import javax.swing.*;
import java.awt.*;

public class PanelButton extends JButton {

    public PanelButton(
        String imageLocation,
        String title,
        String description) {

        super();

        this.setLayout(new BorderLayout());
        this.add(this.getImageComponent(imageLocation), BorderLayout.WEST);
        this.add(this.getTitleComponent(title), BorderLayout.NORTH);
        this.add(this.getDescriptionComponent(description), BorderLayout.CENTER);
    }

    private JComponent getImageComponent(String imageLocation) {
        ImageIcon icon = new ImageIcon(imageLocation);
        JLabel label = new JLabel(icon);
        return label;
    }

    private JComponent getTitleComponent(String title) {
        JLabel label = new JLabel(title, JLabel.CENTER);
        return label;
    }

    private JComponent getDescriptionComponent(String description) {
        JLabel label = new JLabel(description, JLabel.CENTER);
        return label;
    }

}
