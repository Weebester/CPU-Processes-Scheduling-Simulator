import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Gantt extends JPanel {
    excution_window[] sch;
    int unit = 100;

    Color[] ray={Color.CYAN,Color.GRAY,Color.pink,Color.magenta,Color.orange,Color.yellow};

    Gantt(excution_window[] sch) {
        this.sch = sch;
        int totalWidth = 200; // Initial padding
        for (excution_window ew : sch) {
            totalWidth += (ew.tw * unit);
        }
       // System.out.println(totalWidth);
        this.setPreferredSize(new Dimension(totalWidth,300));
        this.setSize(totalWidth,300);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D G = (Graphics2D) g;
        G.setFont(new Font("Serif",Font.BOLD,20));
        int base = 100;
        int i=0;

        for (excution_window ew : sch) {
            G.setColor(ray[i]);
            i=(i+1)%ray.length;
            int w=ew.tw * unit;
            G.fillRect(base, 100, w, 200);
            G.setColor(Color.BLACK);
            G.drawRect(base, 100, w, 200);
            G.drawString(ew.p.name, base + 40, 200);
            G.drawString(""+(base/100-1), base , 80);
            base += w;
            // System.out.println(w);
        }
        G.drawString(""+(base/100-1), base , 80);
    }
}

