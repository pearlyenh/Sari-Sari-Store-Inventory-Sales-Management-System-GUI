/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sarisaristoreinventoryandsalesmanagementsytem;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class FrameUtils {

    public static Rectangle getScreenBounds() {

        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();

        GraphicsDevice[] screens =
                ge.getScreenDevices();

        GraphicsDevice screen = screens[0];

        GraphicsConfiguration gc =
                screen.getDefaultConfiguration();

        Rectangle bounds = gc.getBounds();

        Insets insets =
                Toolkit.getDefaultToolkit().getScreenInsets(gc);

        int x = bounds.x + insets.left;
        int y = bounds.y + insets.top;

        int width =
                bounds.width - insets.left - insets.right;

        int height =
                bounds.height - insets.top - insets.bottom;

        return new Rectangle(x, y, width, height);
    }
}