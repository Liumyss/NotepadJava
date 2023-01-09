import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    JFrame window;

    // TEXT AREA
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    // TOP MENU BAR
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor, menuHelp;
    // FILE MENU
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    // EDIT MENU
    JMenuItem iUndo, iRedo;
    // FORMAT MENU
    JMenuItem iWrap, iFontArial, iFontCMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont, menuFontSize;
    // COLOR MENU
    JMenuItem iBlack, iWhite, iBlue, iGreen, iRed, iOrange, iPurple, iYellow, iPink;
    // HELP MENU
    JMenuItem iShortcuts;

    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);
    Function_Help help = new Function_Help(this);
    KeyHandler kHandler = new KeyHandler(this);
    UndoManager um = new UndoManager();

    public static void main(String[] args) {

        new GUI();
    }

    public GUI() {

        // Create the window and the menu
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();
        createHelpMenu();

        // Default Values
        format.selectedFont = "Arial";
        format.createFont(12);
        format.wordWrap();
        color.changeColor("White");

        window.setVisible(true);

    }

    public void createWindow() {

        window = new JFrame("Notepad made with Java");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {

        textArea = new JTextArea();

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                }
        );

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createMenuBar() {

        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);

        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
    }

    public void createFileMenu() {

        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);

    }

    public void createEditMenu() {

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    public void createFormatMenu() {

        iWrap = new JMenuItem("Word Wrap: OFF");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("WordWrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCMS = new JMenuItem("Comic Sans MS");
        iFontCMS.addActionListener(this);
        iFontCMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("size8");
        menuFontSize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("size12");
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("size16");
        menuFontSize.add(iFontSize16);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("size24");
        menuFontSize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("size28");
        menuFontSize.add(iFontSize28);
    }

    public void createColorMenu() {

        iWhite = new JMenuItem("White");
        iWhite.addActionListener(this);
        iWhite.setActionCommand("White");
        menuColor.add(iWhite);

        iBlack = new JMenuItem("Black");
        iBlack.addActionListener(this);
        iBlack.setActionCommand("Black");
        menuColor.add(iBlack);

        iBlue = new JMenuItem("Blue");
        iBlue.addActionListener(this);
        iBlue.setActionCommand("Blue");
        menuColor.add(iBlue);

        iGreen = new JMenuItem("Green");
        iGreen.addActionListener(this);
        iGreen.setActionCommand("Green");
        menuColor.add(iGreen);

        iRed = new JMenuItem("Red");
        iRed.addActionListener(this);
        iRed.setActionCommand("Red");
        menuColor.add(iRed);

        iOrange = new JMenuItem("Orange");
        iOrange.addActionListener(this);
        iOrange.setActionCommand("Orange");
        menuColor.add(iOrange);

        iYellow = new JMenuItem("Yellow");
        iYellow.addActionListener(this);
        iYellow.setActionCommand("Yellow");
        menuColor.add(iYellow);

        iPink = new JMenuItem("Pink");
        iPink.addActionListener(this);
        iPink.setActionCommand("Pink");
        menuColor.add(iPink);

        iPurple= new JMenuItem("Purple");
        iPurple.addActionListener(this);
        iPurple.setActionCommand("Purple");
        menuColor.add(iPurple);
    }

    public void createHelpMenu() {

        iShortcuts = new JMenuItem("Shortcut");
        iShortcuts.addActionListener(this);
        iShortcuts.setActionCommand("Shortcut");
        menuHelp.add(iShortcuts);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command) {
            case "New":
                file.newFile();
                break;
            case "Open":
                file.openFile();
                break;
            case "Save":
                file.saveFile();
                break;
            case "SaveAs":
                file.saveAsFile();
                break;
            case "Exit":
                file.exitApp();
                break;
            case "Undo":
                edit.undo();
                break;
            case "Redo":
                edit.redo();
                break;
            case "WordWrap":
                format.wordWrap();
                break;
            case "Arial", "Comic Sans MS", "Times New Roman":
                format.setFont(command);
                break;
            case "size8":
                format.createFont(8);
                break;
            case "size12":
                format.createFont(12);
                break;
            case "size16":
                format.createFont(16);
                break;
            case "size20":
                format.createFont(20);
                break;
            case "size24":
                format.createFont(24);
                break;
            case "size28":
                format.createFont(28);
                break;
            case "White", "Black", "Blue", "Green", "Red", "Orange", "Yellow", "Pink", "Purple":
                color.changeColor(command);
                break;
            case "Shortcut":
                help.displayShortcuts();
                break;
        }
    }
}
