/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sarisaristoreinventoryandsalesmanagementsytem;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.DefaultDesktopManager;

public class InternalFrameUtils {

    public static void setupInternalFrame(JInternalFrame frame) {

        // Disable window actions
        frame.setClosable(false);
        frame.setIconifiable(false);
        frame.setMaximizable(false);
        frame.setResizable(false);

        // Remove title/header
        BasicInternalFrameUI ui =
                (BasicInternalFrameUI) frame.getUI();

        ui.setNorthPane(null);

        // Remove border
        frame.setBorder(null);
    }

    public static void setupDesktopPane(JDesktopPane desktopPane) {
        desktopPane.setDesktopManager(new NonMovableDesktopManager());
    }

    private static class NonMovableDesktopManager
            extends DefaultDesktopManager {

        @Override
        public void beginDraggingFrame(JComponent f) {
            // Prevent dragging
        }
    }
}