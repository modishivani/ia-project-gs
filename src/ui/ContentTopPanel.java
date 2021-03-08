package ui;

import ui.MainFrame;
import ui.PanelNames;
import ui.components.IconCaptionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContentTopPanel extends JPanel {

    public ContentTopPanel(MainFrame mainFrame, String infoName) {
        this.setLayout(new BorderLayout());

        IconCaptionButton homeButton = new IconCaptionButton(
                "/images/homeIcon.png",
                "Home");
        homeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.HOME);
            }
        });
        JLabel badgesLabel = new JLabel(infoName);
        badgesLabel.setFont(badgesLabel.getFont().deriveFont(45f));
        badgesLabel.setHorizontalAlignment(JLabel.LEFT);

        this.add(homeButton, BorderLayout.WEST);
        this.add(badgesLabel, BorderLayout.CENTER);
        this.setBorder(
            BorderFactory.createEmptyBorder(10,10,10,10));
    }
}
