
package ui;

import javax.swing.*;
import java.awt.*;

public class Utility {

    // makes the component's font bolded
    public static void setBoldFont(JComponent component) {
        Font f = component.getFont();
        component.setFont(f.deriveFont(f.getStyle() | Font.BOLD ));
    }

    // showing an exception message dialog
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