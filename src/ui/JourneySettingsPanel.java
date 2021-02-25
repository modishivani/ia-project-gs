package ui;

import java.util.ArrayList;
import db.*;

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
    protected void addActivityInformation(ActivityInformation activity) throws DatabaseException {
        this.mainFrame.getDb().addJourney((JourneyInformation) activity);
    }

    @Override
    protected void removeActivityInformation(String name) {
        this.mainFrame.getDb().removeJourney(name);
    }

    @Override
    protected void modifyActivityInformation(ActivityInformation activity)
            throws DatabaseException {
        this.mainFrame.getDb().modifyJourney((JourneyInformation) activity);
    }
}
