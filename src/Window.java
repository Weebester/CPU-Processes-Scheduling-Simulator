

import javax.swing.*;

public class Window extends JFrame {



    Window(){
        this.setTitle("Processes schedule calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.add(new UI());

        this.setVisible(true);
    }

}
