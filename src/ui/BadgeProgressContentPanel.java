package ui;

import db.ActivityInformation;
import db.ActivityProgress;
import db.exceptions.DatabaseException;
import db.MemberInformation;

import java.util.ArrayList;

public class BadgeProgressContentPanel extends ActivityProgressContentPanel {

    public BadgeProgressContentPanel(MainFrame mainFrame) throws DatabaseException {
        super(mainFrame, "Badge");
    }
    // override Activity Progress Panel methods with badge methods
    @Override
    protected ArrayList<String> getActivityNames() {
        return this.mainFrame.getDb().listBadgeNames();
    }

    @Override
    protected ActivityInformation getActivityInformation(String name) throws DatabaseException {
        // get badge information from the database
        return this.mainFrame.getDb().getBadge(name);
    }

    @Override
    protected ActivityProgress getActivityProgress(MemberInformation member, String activityName) {
        // check if members have progress for the selected badge
        if (member.getBadgeProgress().containsKey(activityName)) {
            return member.getBadgeProgress().get(activityName);
        } else {
            return null;
        }
    }

    @Override
    protected void addOrModifyActivityProgress(MemberInformation member, ActivityProgress activityProgress) {
        member.getBadgeProgress().put(activityProgress.getName(), activityProgress);
    }

}