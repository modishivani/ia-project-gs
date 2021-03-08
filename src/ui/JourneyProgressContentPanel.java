package ui;

import db.ActivityInformation;
import db.ActivityProgress;
import db.exceptions.DatabaseException;
import db.MemberInformation;


import java.util.ArrayList;

public class JourneyProgressContentPanel extends ActivityProgressContentPanel {

    public JourneyProgressContentPanel(MainFrame mainFrame) throws DatabaseException {
        super(mainFrame, "Journey");
    }

    @Override
    protected ArrayList<String> getActivityNames() {
        return this.mainFrame.getDb().listJourneyNames();
    }

    @Override
    protected ActivityInformation getActivityInformation(String name) throws DatabaseException {
        return this.mainFrame.getDb().getJourney(name);
    }

    @Override
    protected ActivityProgress getActivityProgress(MemberInformation member, String activityName) {
        if (member.getJourneyProgress().containsKey(activityName)) {
            return member.getJourneyProgress().get(activityName);
        } else {
            return null;
        }
    }

    @Override
    protected void addOrModifyActivityProgress(MemberInformation member, ActivityProgress activityProgress) {
        member.getJourneyProgress().put(activityProgress.getName(), activityProgress);
    }

}