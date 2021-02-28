package ui;

import java.util.ArrayList;
import db.*;
import db.exceptions.DatabaseException;

public class JourneySettingsPanel extends ActivitySettingsPanel {

    public JourneySettingsPanel(MainFrame mainFrame) {
        super(mainFrame, "Journey");
    }

    @Override
    protected ActivityInformation createActivityInformation() {
        return new JourneyInformation();
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
    protected void addOrModifyActivityInformation(ActivityInformation activity) throws DatabaseException {
        this.mainFrame.getDb().addOrModifyJourney((JourneyInformation) activity);
    }

    @Override
    protected void removeActivityInformation(String name, boolean removeProgress) throws DatabaseException {
        this.mainFrame.getDb().removeJourney(name, removeProgress);
    }

}

