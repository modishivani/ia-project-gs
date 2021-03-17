package ui;

import java.util.ArrayList;
import db.*;
import db.exceptions.DatabaseException;

public class BadgeSettingsPanel extends ActivitySettingsPanel {

    public BadgeSettingsPanel(MainFrame mainFrame) {
        super(mainFrame, "Badge");
    }
    // override Activity Settings Panel methods with badge methods
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
    protected void addOrModifyActivityInformation(ActivityInformation activity)
            throws DatabaseException {
        this.mainFrame.getDb().addOrModifyBadge((BadgeInformation) activity);
    }

    @Override
    protected void removeActivityInformation(String name, boolean removeProgress) throws DatabaseException {
        this.mainFrame.getDb().removeBadge(name, removeProgress);
    }
}
