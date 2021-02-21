import com.formdev.flatlaf.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.*;
import ui.MainFrame;
import ui.SettingsPanel;
import ui.components.LargeIconButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import db.MemberInformation;

public class Main {
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 800;

    public static void main(String args[]) throws DatabaseException {

        /*
        MemberInformation m = new MemberInformation();
        m.setName("Shivani");
        m.setEmail("shivani@something.com");


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("shivani.json")) {
            writer.write(gson.toJson(m));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Database db = new Database(
                "42162",
                "2020-2021");

        for(int i = 0; i < 10; i ++)
        {
            BadgeInformation badgeInformation = new BadgeInformation();
            badgeInformation.setName("Badge " + i);
            badgeInformation.setDescription("This is a first badge");
            badgeInformation.setSteps(
                    new String[]{
                            "First Step",
                            "Second Step",
                            "Third Step",
                            "Fourth Step",
                            "Fifth Step"
                    }
            );

            db.addBadge(badgeInformation);
        }

        ArrayList<String> badgeNames = db.listBadgeNames();
        for (int i = 0; i < badgeNames.size(); i++) {
            System.out.println(badgeNames.get(i));
            BadgeInformation badgeInfo = db.getBadge(badgeNames.get(i));
            System.out.println(badgeInfo);
            //db.removeBadge(badgeNames.get(i));
        }

        //db.removeBadge(badgeInformation.getName());
        //System.out.println(db.getBadgeFilePath("Shivani Modi"));

 */

        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

    }



}
