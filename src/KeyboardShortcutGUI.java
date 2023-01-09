import javax.swing.*;

public class KeyboardShortcutGUI {

    JFrame shortcutsWindow;

    public KeyboardShortcutGUI() {

        createWindow();

        shortcutsWindow.setVisible(true);
    }

    public void createWindow() {

        shortcutsWindow = new JFrame("Keyboard Shortcuts");
        shortcutsWindow.setSize(600, 200);
    }
}
