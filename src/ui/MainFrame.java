package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;


    private JPanel framePanel;
    private CardLayout framePanelLayout;

    public MainFrame() {
        super();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Girl Scout Troop");

        // create frame panel
        this.framePanel = new JPanel();
        this.framePanelLayout = new CardLayout();
        this.framePanel.setLayout(this.framePanelLayout);

        // create and add main panels to the frame panel
        HomePanel homePanel = new HomePanel(this);
        BadgeProgressPanel badgeProgressPanel = new BadgeProgressPanel(this);
        JourneyProgressPanel journeyProgressPanel = new JourneyProgressPanel(this);
        MemberInfoPanel memberInfoPanel = new MemberInfoPanel(this);
        SettingsPanel settingsPanel = new SettingsPanel(this);
        EventsPanel eventsPanel = new EventsPanel(this);

        this.framePanel.add(homePanel, PanelNames.HOME);
        this.framePanel.add(badgeProgressPanel, PanelNames.BADGE_PROGRESS);
        this.framePanel.add(journeyProgressPanel, PanelNames.JOURNEY_PROGRESS);
        this.framePanel.add(memberInfoPanel, PanelNames.MEMBER_INFO);
        this.framePanel.add(settingsPanel, PanelNames.SETTINGS);
        this.framePanel.add(eventsPanel, PanelNames.EVENTS);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(
            this.framePanel,
            BorderLayout.CENTER);

        // set the current panel
        setMainPanel(PanelNames.HOME);
    }

    public void setMainPanel(String name) {

        this.framePanelLayout.show(this.framePanel, name);
    }



}
