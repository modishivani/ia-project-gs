package ui;

import db.ActivityInformation;
import db.ActivityProgress;
import db.exceptions.DatabaseException;
import db.MemberInformation;

import java.util.ArrayList;

public class BadgeProgressPanel extends ActivityProgressPanel {

    public BadgeProgressPanel(MainFrame mainFrame) throws DatabaseException {
        super(mainFrame, "Badge");
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
    protected ActivityProgress getActivityProgress(MemberInformation member, String activityName) {
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