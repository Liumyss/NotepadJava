import java.awt.*;
import java.io.BufferedReader;
import java.awt.print.*;
import java.io.FileReader;
import java.io.FileWriter;

public class Menu_File {

    GUI gui;
    String fileName;
    String fileAddress;
    public Menu_File(GUI gui) {
        this.gui = gui;
    }

    // Open a new empty File
    public void newFile() {

        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }

    // function to read a file that you opened in your computer
    public void openFile() {

        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText("");
            String line =  null;

            while ((line = br.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }

            br.close();

        } catch (Exception e){
            System.out.println("FILE NOT OPENED");
        }
    }

    // Save your file
    public void saveFile() {

        if (fileName == null) {
            saveAsFile();
        } else {
            try {

                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                fw.close();

            } catch(Exception e) {
            System.out.println("SAVING NOT WORKING");
            }
        }
    }

    // Save a new file in a new location
    public void saveAsFile() {

        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {

            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();

        } catch(Exception e) {
            System.out.println("SAVING NOT WORKING");
        }
    }

    // Exit the program
    public void exitApp() {
        System.exit(0);
    }

    // Print your text
    public void print() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//              getContentPane().printAll(graphics);
                return PAGE_EXISTS;
            }
        });
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                // Handle print job exception
            }
        }
    }
}
