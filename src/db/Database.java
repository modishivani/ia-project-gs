package db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database {

    private final String rootPath;
    private final String troopNo;
    private final String year;

    private final String badgesDirectory;

    public Database(
        String troopNo,
        String year) {

        this.rootPath = System.getProperty("user.dir");
        this.troopNo = troopNo;
        this.year = year;

        this.badgesDirectory = Paths.get(this.rootPath, this.troopNo, this.year, "badges").toString();
        EnsureDirectory(this.badgesDirectory);
    }

    /*
      Badges
     */
    public void addBadge(
        BadgeInformation badgeInformation) {

        // add validation and throw exception
        // if the badgeInformation.name is null

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
        String name) {

        return (BadgeInformation) readObject(
            getBadgeFileName(name),
            BadgeInformation.class
        );
    }

    public void modifyBadge(
        BadgeInformation badgeInformation) {

        addBadge(badgeInformation);
    }

    private void EnsureDirectory(String directory) {
        File f = new File(directory);
        f.mkdirs();
    }

    private String getBadgeFileName(String name) {
        return Paths.get(this.badgesDirectory, name + ".json").toString();
    }

    private void writeObject(
        Object src,
        String fileName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(gson.toJson(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readObject(
        String fileName,
        Type objectType)
    {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(fileName));
            return gson.fromJson(reader, objectType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
