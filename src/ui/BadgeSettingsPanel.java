package ui;

import java.util.ArrayList;
import db.*;

public class BadgeSettingsPanel extends ActivitySettingsPanel {

    public BadgeSettingsPanel(MainFrame mainFrame) {
        super(mainFrame, "Badge");
    }

    @Override
    protected ActivityInformation createActivityInformation() {
        return new BadgeInformation();
    }

    @Override
    protected ArrayList<String> getActivityNames() {
        return this.mainFrame.getDb().listBadgeNames();
    }

    @Override
    protected ActivityInformation getActivityInformation(String name) throws DatabaseException {
        return this.mainFrame.getDb().getBadge(name);
    }

    @Override
    protected void addOrModifyActivityInformation(ActivityInformation activity) throws DatabaseException {
        this.mainFrame.getDb().addOrModifyBadge((BadgeInformation) activity);
    }

    @Override
    protected void removeActivityInformation(String name) {
        this.mainFrame.getDb().removeBadge(name);
    }
}
