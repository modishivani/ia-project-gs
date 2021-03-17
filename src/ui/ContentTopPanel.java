package ui;

import ui.MainFrame;
import ui.PanelNames;
import ui.components.IconCaptionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContentTopPanel extends JPanel {

    // create a top panel to use for all internal content panels of the application
    public ContentTopPanel(MainFrame mainFrame, String infoName) {
        this.setLayout(new BorderLayout());
        // create components

        // make the home button
        IconCaptionButton homeButton = new IconCaptionButton(
                "/images/homeIcon.png",
                "Home");
        homeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.HOME);
            }
        });

        // make the title of the page
        JLabel badgesLabel = new JLabel(infoName);
        badgesLabel.setFont(badgesLabel.getFont().deriveFont(45f));
        badgesLabel.setHorizontalAlignment(JLabel.LEFT);

        // add component
        this.add(homeButton, BorderLayout.WEST);
        this.add(badgesLabel, BorderLayout.CENTER);
        this.setBorder(
            BorderFactory.createEmptyBorder(10,10,10,10));
    }
}
