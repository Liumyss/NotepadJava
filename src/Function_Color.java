import java.awt.*;

public class Function_Color {

    GUI gui;

    public Function_Color(GUI gui) {
        this.gui = gui;
    }

    public void changeColor(String color) {

        switch (color) {
            case "White":
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
                gui.textArea.setForeground(Color.black);
                break;
            case "Black":
                gui.window.getContentPane().setBackground(Color.black);
                gui.textArea.setBackground(Color.black);
                gui.textArea.setForeground(Color.white);
                break;
            case "Blue":
                gui.window.getContentPane().setBackground(new Color(0, 153, 153));
                gui.textArea.setBackground(new Color(0, 153, 153));
                gui.textArea.setForeground(Color.white);
                break;
            case "Green":
                gui.window.getContentPane().setBackground(new Color(0, 153, 76));
                gui.textArea.setBackground(new Color(0, 153, 76));
                gui.textArea.setForeground(Color.white);
                break;
            case "Red":
                gui.window.getContentPane().setBackground(new Color(153, 0, 0));
                gui.textArea.setBackground(new Color(153, 0, 0));
                gui.textArea.setForeground(Color.white);
                break;
            case "Orange":
                gui.window.getContentPane().setBackground(new Color(204, 102, 0));
                gui.textArea.setBackground(new Color(204, 102, 0));
                gui.textArea.setForeground(Color.white);
                break;
            case "Yellow":
                gui.window.getContentPane().setBackground(new Color(153, 153, 0));
                gui.textArea.setBackground(new Color(153, 153, 0));
                gui.textArea.setForeground(Color.white);
                break;
            case "Pink":
                gui.window.getContentPane().setBackground(new Color(255, 153, 204));
                gui.textArea.setBackground(new Color(255, 153, 204));
                gui.textArea.setForeground(Color.white);
                break;
            case "Purple":
                gui.window.getContentPane().setBackground(new Color(153, 0, 153));
                gui.textArea.setBackground(new Color(153, 0, 153));
                gui.textArea.setForeground(Color.white);
                break;
        }
    }
}
