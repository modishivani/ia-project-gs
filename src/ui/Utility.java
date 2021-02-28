
package ui;

import db.DatabaseException;

import javax.swing.*;
import java.awt.*;

public class Utility {

    public static void setBoldFont(JComponent component) {
        Font f = component.getFont();
        component.setFont(f.deriveFont(f.getStyle() | Font.BOLD ));
    }

    public static void showException(JFrame owner, Exception e) {
        showException(owner, e, "");
    }

    public static void showException(JFrame owner, Exception e, String message) {
        JOptionPane.showMessageDialog(
            owner,
            e.getMessage() + message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}