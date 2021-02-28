
package ui;

import db.DatabaseException;

import javax.swing.*;
import java.awt.*;

public class Utility {

    public static void setBoldFont(JComponent component) {
        Font f = component.getFont();
        component.setFont(f.deriveFont(f.getStyle() | Font.BOLD ));
    }

    public static void showDatabaseException(JFrame owner, DatabaseException dbe) {
        showDatabaseException(owner, dbe, "");
    }

    public static void showDatabaseException(JFrame owner, DatabaseException dbe, String message) {
        JOptionPane.showMessageDialog(
            owner,
            dbe.getMessage() + message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}