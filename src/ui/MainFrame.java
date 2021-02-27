package ui;

import db.Database;
import db.DatabaseException;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;


    private JPanel framePanel;
    private CardLayout framePanelLayout;
    private Database db;
    private BadgeProgressPanel badgeProgressPanel;
    private JourneyProgressPanel journeyProgressPanel;
    private MemberInfoPanel memberInfoPanel;
    private SettingsPanel settingsPanel;
    private EventsPanel eventsPanel;

    public MainFrame() throws DatabaseException {
        super();

        this.db = new Database();
        
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Girl Scout Troop " + this.db.getTroopNo());

        // create frame panel
        this.framePanel = new JPanel();
        this.framePanelLayout = new CardLayout();
        this.framePanel.setLayout(this.framePanelLayout);

        // create and add main panels to the frame panel
        HomePanel homePanel = new HomePanel(this);
        this.badgeProgressPanel = new BadgeProgressPanel(this);
        this.journeyProgressPanel = new JourneyProgressPanel(this);
        this.memberInfoPanel = new MemberInfoPanel(this);
        this.settingsPanel = new SettingsPanel(this);
        this.eventsPanel = new EventsPanel(this);

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


        switch(name) {
            case PanelNames.MEMBER_INFO:
                if (this.memberInfoPanel.tryLoadData()) {
                    this.framePanelLayout.show(this.framePanel, name);
                }
                break;
            case PanelNames.BADGE_PROGRESS:
                if (this.badgeProgressPanel.tryLoadData()) {
                    this.framePanelLayout.show(this.framePanel, name);
                }
            case PanelNames.SETTINGS:
                if (this.settingsPanel.tryLoadData()) {
                    this.framePanelLayout.show(this.framePanel, name);
                }
            default:
                this.framePanelLayout.show(this.framePanel, name);
        }


    }

    Database getDb() {
        return this.db;
    }
}
