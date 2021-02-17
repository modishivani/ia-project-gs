package ui;

import ui.components.IconCaptionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MemberInfoPanel extends JPanel {
    private final MainFrame mainFrame;

    public MemberInfoPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        this.add(createTopPanel(), BorderLayout.NORTH);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        IconCaptionButton homeButton = new IconCaptionButton(
                "/images/homeIcon.png",
                "Home");
        homeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainPanel(PanelNames.HOME);
            }
        });
        JLabel badgesLabel = new JLabel(" Member Information");
        badgesLabel.setFont(badgesLabel.getFont().deriveFont(45f));
        badgesLabel.setHorizontalAlignment(JLabel.LEFT);

        topPanel.add(homeButton, BorderLayout.WEST);
        topPanel.add(badgesLabel, BorderLayout.CENTER);

        return topPanel;
    }


}
