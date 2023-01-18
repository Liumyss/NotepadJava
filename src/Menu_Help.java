import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_Help extends JFrame {

    GUI gui;
    private JTextField searchField;
    private JButton searchButton;

    public Menu_Help(GUI gui) {

        this.gui = gui;
    }

    // Display the Keyboard Shortcut Frame
    public void displayShortcuts() {

        new KeyboardShortcutFrame();
    }

    // Display the Search Bar Frame
    public void searchBar() {

        setTitle("Search");
        setSize(300, 65);

        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchListener());

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchTerm = searchField.getText().toLowerCase();
            String text = gui.textArea.getText().toLowerCase();
            if (text.contains(searchTerm)) {
                int startIndex = text.indexOf(searchTerm);
                int endIndex = startIndex + searchTerm.length();
                gui.textArea.setSelectionStart(startIndex);
                gui.textArea.setSelectionEnd(endIndex);
                gui.textArea.grabFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Search term not found.");
            }
        }
    }
}
