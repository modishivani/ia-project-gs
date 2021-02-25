package db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database {

    private final String rootPath;
    private final String troopNo;
    private final String year;

    private final String membersDirectory;
    private final String badgesDirectory;
    private final String journeysDirectory;
    private final String eventsDirectory;

    public Database() {
        this("42162", "2020-2021");
    }

    public Database(
        String troopNo,
        String year) {

        this.rootPath = System.getProperty("user.dir");
        this.troopNo = troopNo;
        this.year = year;

        this.membersDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "members").toString();
        EnsureDirectory(this.membersDirectory);

        this.badgesDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "badges").toString();
        EnsureDirectory(this.badgesDirectory);

        this.journeysDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "journeys").toString();
        EnsureDirectory(this.journeysDirectory);

        this.eventsDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "events").toString();
        EnsureDirectory(this.eventsDirectory);
    }

    public String getTroopNo() {
        return this.troopNo;
    }

    public String getYear() {
        return this.year;
    }

    /*
       Members
    */
    public void addOrModifyMember(MemberInformation memberInformation)
        throws DatabaseException {
        Argument.ensureNotNull("memberInformation", memberInformation);
        memberInformation.validate();

        this.writeObject(
            memberInformation,
            getMemberFileName(memberInformation.getName())
        );
    }

    public void removeMember(String name) {
        Argument.ensureNotNull("name", name);

        String memberFileName = getMemberFileName(name);
        File memberFile = new File(memberFileName);
        if (memberFile.exists()) {
            memberFile.delete();
        }
    }

    public ArrayList<String> listMemberNames() {

        ArrayList<String> memberNames = new ArrayList<>();
        File memberDirectory = new File(this.membersDirectory);
        String[] files = memberDirectory.list();

        if (files != null) {
            for (String fileName : files) {
                if (fileName.endsWith(".json")) {
                    fileName = fileName.substring(0, fileName.length() - 5);
                    memberNames.add(fileName);
                }
            }
        }

        return memberNames;
    }

    public MemberInformation getMember(String name)
        throws DatabaseException{
        Argument.ensureNotNull("name", name);

        return (MemberInformation) readObject(
                getMemberFileName(name),
                MemberInformation.class
        );
    }


    private String getMemberFileName(String name) {
        return Paths.get(this.membersDirectory, name + ".json").toString();
    }


    /*
      Badges
     */
    public void addBadge(
        BadgeInformation badgeInformation) throws DatabaseException {

        if (badgeInformation.getName() == null) {
            //throw exception
        }


        writeObject(
            badgeInformation,
            getBadgeFileName(badgeInformation.getName())
        );
    }

    public void removeBadge(
        String name) {
        String badgeFileName = getBadgeFileName(name);
        File badgeFile = new File(badgeFileName);
        if (badgeFile.exists()) {
            badgeFile.delete();
        }
    }

    public void modifyBadge(BadgeInformation badgeInformation) throws DatabaseException {

        addBadge(badgeInformation);
    }

    public ArrayList<String> listBadgeNames() {

        ArrayList<String> badgeNames = new ArrayList<>();
        File badgeDirectory = new File(this.badgesDirectory);
        String[] files = badgeDirectory.list();

        if (files != null) {
            for (String fileName : files) {
                if (fileName.endsWith(".json")) {
                    fileName = fileName.substring(0, fileName.length() - 5);
                    badgeNames.add(fileName);
                }
            }
        }

        return badgeNames;
    }

    public BadgeInformation getBadge(
        String name)
        throws DatabaseException {

        return (BadgeInformation) readObject(
            getBadgeFileName(name),
            BadgeInformation.class
        );
    }


    private String getBadgeFileName(String name) {
        return Paths.get(this.badgesDirectory, name + ".json").toString();
    }


    /*
      Journeys
     */
    public void addJourney(JourneyInformation journeyInformation) throws DatabaseException {

        if (journeyInformation.getName() == null) {
            //throw exception
        }

        writeObject(
                journeyInformation,
                getJourneyFileName(journeyInformation.getName())
        );
    }

    public void removeJourney(String name) {
        String journeyFileName = getJourneyFileName(name);
        File journeyFile = new File(journeyFileName);
        if (journeyFile.exists()) {
            journeyFile.delete();
        }
    }

    public void modifyJourney(JourneyInformation journeyInformation) throws DatabaseException {

        addJourney(journeyInformation);
    }

    public ArrayList<String> listJourneyNames() {

        ArrayList<String> journeyNames = new ArrayList<>();
        File journeyDirectory = new File(this.journeysDirectory);
        String[] files = journeyDirectory.list();

        if (files != null) {
            for (String fileName : files) {
                if (fileName.endsWith(".json")) {
                    fileName = fileName.substring(0, fileName.length() - 5);
                    journeyNames.add(fileName);
                }
            }
        }

        return journeyNames;
    }

    public JourneyInformation getJourney(
            String name)
            throws DatabaseException {

        return (JourneyInformation) readObject(
                getJourneyFileName(name),
                JourneyInformation.class
        );
    }

    private String getJourneyFileName(String name) {
        return Paths.get(this.journeysDirectory, name + ".json").toString();
    }



    /*
      Events
     */
    public void addEvent(EventInformation eventInformation) throws DatabaseException {

        if (eventInformation.getName() == null) {
            //throw exception
        }

        writeObject(
                eventInformation,
                getEventFileName(eventInformation.getName())
        );
    }

    public void removeEvent(String name) {
        String eventFileName = getEventFileName(name);
        File eventFile = new File(eventFileName);
        if (eventFile.exists()) {
            eventFile.delete();
        }
    }

    public void modifyEvent(EventInformation eventInformation) throws DatabaseException {

        addEvent(eventInformation);
    }

    public ArrayList<String> listEventNames() {

        ArrayList<String> eventNames = new ArrayList<>();
        File eventDirectory = new File(this.eventsDirectory);
        String[] files = eventDirectory.list();

        if (files != null) {
            for (String fileName : files) {
                if (fileName.endsWith(".json")) {
                    fileName = fileName.substring(0, fileName.length() - 5);
                    eventNames.add(fileName);
                }
            }
        }

        return eventNames;
    }

    public EventInformation getEvent(String name)
    throws DatabaseException{

        return (EventInformation) readObject(
                getEventFileName(name),
                EventInformation.class
        );
    }

    private String getEventFileName(String name) {
        return Paths.get(this.eventsDirectory, name + ".json").toString();
    }

    private void EnsureDirectory(String directory) {
        File f = new File(directory);
        f.mkdirs();
    }

    private void writeObject(
        Object src,
        String fileName)
        throws DatabaseException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(gson.toJson(src));
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseException(
                "Failed to save item in the database. FileName = " + fileName, e);
        }
    }

    private Object readObject(
        String fileName,
        Type objectType)
        throws DatabaseException {
        Gson gson = new Gson();
        FileReader fileReader = null;
        JsonReader jsonReader = null;
        Object retValue;
        try {
            fileReader =  new FileReader(fileName);
            jsonReader = new JsonReader(fileReader);

            retValue = gson.fromJson(jsonReader, objectType);
            return retValue;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DatabaseException(
                "Failed to read item from the database. FileName = " + fileName, e);
        } finally {
            if (jsonReader != null) {
                try { jsonReader.close(); } catch(Exception e) {}
            }
            if (fileReader != null) {
                try { fileReader.close(); } catch(Exception e) {}
            }
        }
    }
}
