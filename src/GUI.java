import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    JFrame window;

    // TEXT AREA
    public JTextArea textArea;
    JScrollPane scrollPane;
    JLabel wordCountLabel;
    boolean wordWrapOn = false;
    // TOP MENU BAR
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor, menuHelp;
    // FILE MENU
    JMenuItem iNew, iNewWindow, iOpen, iSave, iSaveAs, iPrint, iExit;
    // EDIT MENU
    JMenuItem iUndo, iRedo, iSelectAll;
    // FORMAT MENU
    JMenuItem iWrap, iFontArial, iFontCMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont, menuFontSize;
    // COLOR MENU
    JMenuItem iBlack, iWhite, iBlue, iGreen, iRed, iOrange, iPurple, iYellow, iPink;
    // HELP MENU
    JMenuItem iShortcuts, iSearch;

    Menu_File file = new Menu_File(this);
    Menu_Format format = new Menu_Format(this);
    Menu_Color color = new Menu_Color(this);
    Menu_Edit edit = new Menu_Edit(this);
    Menu_Help help = new Menu_Help(this);
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

        // Create a new text area for the Notepad
        textArea = new JTextArea();

        // To handle the use of Keyboard Shortcuts
        textArea.addKeyListener(kHandler);

        // To handle the use of Redo and Undo functions in the text area
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                }
        );

        // Count the number of paragraphs, words and characters in the text area
        textArea.getDocument().addDocumentListener(new WordCountListener());
        wordCountLabel = new JLabel("Paragraph count: 0 | Word count: 0 | Character count : 0");

        // Display the wordCountLabel in the bottom right of the screen
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(wordCountLabel);
        window.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 5));

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    private class WordCountListener implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
            updateWordCount();
        }

        public void insertUpdate(DocumentEvent e) {
            updateWordCount();
        }

        public void removeUpdate(DocumentEvent e) {
            updateWordCount();
        }

        // UPDATE THE COUNT OF WORDS, CHARS and LINES (By default a line in this notepad will become a new paragraph)
        private void updateWordCount() {
            String text = textArea.getText();
            int wordCount = text.split("\\s+").length;
            int charCount = text.length();
            int lineCount = text.split("\n").length;
            wordCountLabel.setText("Paragraph count: " + lineCount + " | Word count: " + wordCount + " | Character count: " + charCount);
        }
    }

    // Create the Menu Bar
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

    // Create the Menu Items under the Menu File
    public void createFileMenu() {

        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iNewWindow = new JMenuItem("New Window");
        iNewWindow.addActionListener(this);
        iNewWindow.setActionCommand("New Window");
        menuFile.add(iNewWindow);

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

        iPrint = new JMenuItem("Print");
        iPrint.addActionListener(this);
        iPrint.setActionCommand("Print");
        menuFile.add(iPrint);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);

    }

    // Create the Menu Items under the Menu Edit
    public void createEditMenu() {

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);

        iSelectAll = new JMenuItem("Select All");
        iSelectAll.addActionListener(this);
        iSelectAll.setActionCommand("Select All");
        menuEdit.add(iSelectAll);
    }

    // Create the Menu Items under the Menu Format
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

    // Create the Menu Items under the Menu Color
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

    // Create the Menu Items under the Menu Help
    public void createHelpMenu() {

        iShortcuts = new JMenuItem("Shortcuts");
        iShortcuts.addActionListener(this);
        iShortcuts.setActionCommand("Shortcuts");
        menuHelp.add(iShortcuts);

        iSearch = new JMenuItem("Search");
        iSearch.addActionListener(this);
        iSearch.setActionCommand("Search");
        menuHelp.add(iSearch);
    }

    // Perform all the different actions you can activate in the Notepad
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command) {
            case "New":
                file.newFile(); // Create a New File
                break;
            case "New Window":
                new GUI(); // Create a New File in New Window
                break;
            case "Open":
                file.openFile(); // Open a File from the directory
                break;
            case "Save":
                file.saveFile(); // Save a File
                break;
            case "SaveAs":
                file.saveAsFile(); // Save a New File in a selected location
                break;
            case "Print":
                file.print(); // Open the Print Window
                break;
            case "Exit":
                file.exitApp(); // Exit the Program
                break;
            case "Undo":
                edit.undo(); // Undo some text written in the text area
                break;
            case "Redo":
                edit.redo(); // Redo some text written in the text area
                break;
            case "Select All":
                edit.selectAll(); // Select all the text in the text area
                break;
            case "WordWrap":
                format.wordWrap(); // Function to wrap or unwrap your text
                break;
            case "Arial", "Comic Sans MS", "Times New Roman":
                format.setFont(command); // Set your text with the selected font
                break;
            case "size8":
                format.createFont(8); // Display your text in size 8
                break;
            case "size12":
                format.createFont(12); // Display your text in size 12
                break;
            case "size16":
                format.createFont(16); // Display your text in size 16
                break;
            case "size20":
                format.createFont(20); // Display your text in size 20
                break;
            case "size24":
                format.createFont(24); // Display your text in size 24
                break;
            case "size28":
                format.createFont(28); // Display your text in size 28
                break;
            case "White", "Black", "Blue", "Green", "Red", "Orange", "Yellow", "Pink", "Purple":
                color.changeColor(command); // Change the color of the background with the selected color
                break;
            case "Shortcuts":
                help.displayShortcuts(); // Open the Keyboard Shortcuts window
                break;
            case "Search":
                help.searchBar(); // Open the Search Bar Window
                break;
        }
    }
}
