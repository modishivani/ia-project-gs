import com.formdev.flatlaf.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ui.MainFrame;
import ui.SettingsPanel;
import ui.components.LargeIconButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import db.Member;

public class Main {
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 800;

    public static void main(String args[]) {

        Member m = new Member();
        m.setName("Shivani");
        m.setEmail("shivani@something.com");


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("shivani.json")) {
            writer.write(gson.toJson(m));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
         */
    }



}
