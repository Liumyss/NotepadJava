import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GUI gui;

    public KeyHandler(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // DISPLAY MENUS
        if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_F) {
            gui.menuFile.doClick();
        }
        if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_E) {
            gui.menuEdit.doClick();
        }
        if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_T) {
            gui.menuFormat.doClick();
        }
        if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_C) {
            gui.menuColor.doClick();
        }

        // ACTIVATE FUNCTIONS OF menuFILE
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
            gui.file.newFile();
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
            gui.file.openFile();
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                gui.file.saveFile();
        }
        if (e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            gui.file.saveAsFile();
        }
        if (e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_E) {
            gui.file.exitApp();
        }

        // ACTIVATE FUNCTIONS OF editFILE
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_U) {
            gui.edit.undo();
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R) {
            gui.edit.redo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
