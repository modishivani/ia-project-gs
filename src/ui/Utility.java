
package ui;

import javax.swing.*;
import java.awt.*;

public class Utility {

    public static void setBoldFont(JComponent component) {
        Font f = component.getFont();
        component.setFont(f.deriveFont(f.getStyle() | Font.BOLD ));
    }
}