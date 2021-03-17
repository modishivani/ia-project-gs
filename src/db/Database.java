package db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import db.exceptions.ActivityInUseException;
import db.exceptions.DatabaseException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;

public class Database {

    private final String rootPath;
    private final String troopNo;
    private final String year;

    private final String membersDirectory;
    private final String badgesDirectory;
    private final String journeysDirectory;

    public Database() {
        this("42162", "2020-2021");
    }

    public Database(String troopNo, String year) {

        this.rootPath = System.getProperty("user.dir");
        this.troopNo = troopNo;
        this.year = year;

        //get directory path for the different database directories
        this.membersDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "members").toString();
        EnsureDirectory(this.membersDirectory);

        this.badgesDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "badges").toString();
        EnsureDirectory(this.badgesDirectory);

        this.journeysDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "journeys").toString();
        EnsureDirectory(this.journeysDirectory);
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
        //delete .json from the names of the members, to populate the list with
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
    public void addOrModifyBadge(
        BadgeInformation badgeInformation) throws DatabaseException {
        badgeInformation.validate();

        writeObject(
            badgeInformation,
            getBadgeFileName(badgeInformation.getName())
        );
    }

    public void removeBadge(String name, boolean removeProgress)
            throws DatabaseException {

        int usedBy = 0;
        //count how many members have progress for the activity
        for(String memberName: this.listMemberNames()) {
            MemberInformation memberInformation = this.getMember(memberName);
            Hashtable<String, ActivityProgress> badgeProgress = memberInformation.getBadgeProgress();
            if (badgeProgress.containsKey(name)) {
                usedBy++;
                if (removeProgress) {
                    badgeProgress.remove(name);
                    addOrModifyMember(memberInformation);
                }
            }
        }

        //throw an exception if this activity is used by members
        if (!removeProgress && (usedBy > 0)) {
            throw new ActivityInUseException(usedBy + " member(s) have progress for this badge.");
        }

        String badgeFileName = getBadgeFileName(name);
        File badgeFile = new File(badgeFileName);
        if (badgeFile.exists()) {
            badgeFile.delete();
        }
    }

    public ArrayList<String> listBadgeNames() {

        ArrayList<String> badgeNames = new ArrayList<>();
        File badgeDirectory = new File(this.badgesDirectory);
        String[] files = badgeDirectory.list();

        //delete .json from the names of the members, to populate the list with
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
    public void addOrModifyJourney(
            JourneyInformation journeyInformation)
            throws DatabaseException {
        journeyInformation.validate();
        writeObject(
                journeyInformation,
                getJourneyFileName(journeyInformation.getName())
        );
    }

    public void removeJourney(String name, boolean removeProgress)
        throws DatabaseException {
        //count how many members have progress for the activity
        int usedBy = 0;
        for(String memberName: this.listMemberNames()) {
            MemberInformation memberInformation = this.getMember(memberName);
            Hashtable<String, ActivityProgress> journeyProgress = memberInformation.getJourneyProgress();
            if (journeyProgress.containsKey(name)) {
                usedBy++;
                if (removeProgress) {
                    journeyProgress.remove(name);
                    addOrModifyMember(memberInformation);
                }
            }
        }

        //if members use the activity, throw an exception
        if (!removeProgress && (usedBy > 0)) {
            throw new ActivityInUseException(
                    usedBy + " member(s) have progress for this journey.");
        }
        String journeyFileName = getJourneyFileName(name);
        File journeyFile = new File(journeyFileName);
        if (journeyFile.exists()) {
            journeyFile.delete();
        }
    }

    public ArrayList<String> listJourneyNames() {

        ArrayList<String> journeyNames = new ArrayList<>();
        File journeyDirectory = new File(this.journeysDirectory);
        String[] files = journeyDirectory.list();

        //delete .json from the names of the members, to populate the list with
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

    private void EnsureDirectory(String directory) {
        File f = new File(directory);
        f.mkdirs();
    }


    /*
    Write and Read Objects
     */

    private void writeObject(Object src, String fileName) throws DatabaseException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(gson.toJson(src));
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseException(
                "Failed to save item in the database. FileName = " + fileName, e);
        }
    }

    private Object readObject(String fileName, Type objectType) throws DatabaseException {
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
