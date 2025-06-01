import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Window win = new Window();

    }
}