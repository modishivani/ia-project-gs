import com.formdev.flatlaf.*;
import db.exceptions.DatabaseException;
import ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String args[]) throws DatabaseException {


        // set look and feel for the entire application
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        // create mainframe
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);


        // exit confirmation dialog
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }



}
