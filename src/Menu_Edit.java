public class Menu_Edit {

    GUI gui;

    public Menu_Edit(GUI gui) {

        this.gui = gui;
    }

    // Undo some text in the text area
    public void undo() {

        gui.um.undo();
    }

    // Redo some text in the text area
    public void redo() {

        gui.um.redo();
    }

    // Select all the text in the text area
    public void selectAll() {
        gui.textArea.selectAll();
    }
}
