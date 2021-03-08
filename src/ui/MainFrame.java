package ui;

import db.Database;
import db.exceptions.DatabaseException;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    private JPanel framePanel;
    private CardLayout framePanelLayout;
    private Database db;

    private HomePanel homePanel;
    private BadgeProgressContentPanel badgeProgressPanel;
    private JourneyProgressContentPanel journeyProgressContentPanel;
    private MemberInfoContentPanel memberInfoContentPanel;
    private SettingsContentPanel settingsContentPanel;

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
        this.homePanel = new HomePanel(this);
        this.badgeProgressPanel = new BadgeProgressContentPanel(this);
        this.journeyProgressContentPanel = new JourneyProgressContentPanel(this);
        this.memberInfoContentPanel = new MemberInfoContentPanel(this);
        this.settingsContentPanel = new SettingsContentPanel(this);

        this.framePanel.add(homePanel, PanelNames.HOME);
        this.framePanel.add(badgeProgressPanel, PanelNames.BADGE_PROGRESS);
        this.framePanel.add(journeyProgressContentPanel, PanelNames.JOURNEY_PROGRESS);
        this.framePanel.add(memberInfoContentPanel, PanelNames.MEMBER_INFO);
        this.framePanel.add(settingsContentPanel, PanelNames.SETTINGS);

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
                if (this.memberInfoContentPanel.tryLoadData()) {
                    this.framePanelLayout.show(this.framePanel, name);
                }
                break;
            case PanelNames.BADGE_PROGRESS:
                if (this.badgeProgressPanel.tryLoadData()) {
                    this.framePanelLayout.show(this.framePanel, name);
                }
                break;
            case PanelNames.JOURNEY_PROGRESS:
                if (this.journeyProgressContentPanel.tryLoadData()) {
                    this.framePanelLayout.show(this.framePanel, name);
                }
                break;
            case PanelNames.SETTINGS:
                if (this.settingsContentPanel.tryLoadData()) {
                    this.framePanelLayout.show(this.framePanel, name);
                }
                break;
            default:
                this.framePanelLayout.show(this.framePanel, name);
        }


    }

    Database getDb() {
        return this.db;
    }
}
