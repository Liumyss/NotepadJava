import javax.swing.*;
import java.time.format.TextStyle;

public class KeyboardShortcutFrame{

    JFrame shortcutsWindow;
    JPanel textArea;

    public KeyboardShortcutFrame() {

        createWindow();
        createTextArea();

        shortcutsWindow.setVisible(true);
    }

    public void createWindow() {

        shortcutsWindow = new JFrame("Keyboard Shortcuts");
        shortcutsWindow.setSize(300, 400);
    }

    public void createTextArea() {

        textArea = new JPanel();
        JLabel newShortcut = new JLabel("CREATE A NEW FILE: CTRL + N");
        JLabel openShortcut = new JLabel("OPEN A FILE: CTRL + O    ");
        JLabel savingShortcut = new JLabel("SAVE A FILE: CTRL + S");
        JLabel savingAsShortcut = new JLabel("SAVE AS : CTRL + SHIFT + S");

        textArea.add(newShortcut);
        textArea.add(openShortcut);
        textArea.add(savingShortcut);
        textArea.add(savingAsShortcut);

        shortcutsWindow.add(textArea);
    }
}
