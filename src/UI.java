import com.formdev.flatlaf.ui.FlatBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class UI extends JPanel {

    LinkedList<process> processes = new LinkedList<>();

    JButton AddProc = new JButton();
    JButton RestProc = new JButton();
    JButton SetQ = new JButton();
    JTextField PNameField = new JTextField("");
    JTextField PATField = new JTextField("0");
    JTextField PBTField = new JTextField("0");
    JTextField PPRField = new JTextField("0");
    JTextField QuantumField = new JTextField("3");
    JLabel PNameLabel = new JLabel("PName:");
    JLabel PATLabel = new JLabel("PAT:");
    JLabel PBTLabel = new JLabel("PBT:");
    JLabel PPRLabel = new JLabel("PPR:");
    JLabel QuantumLabel = new JLabel("Q:");
    KeyAdapter Validator = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                Integer.parseInt(PATField.getText());
                Integer.parseInt(PBTField.getText());
                Integer.parseInt(PPRField.getText());
                AddProc.setEnabled(true);
            } catch (Exception E) {
                AddProc.setEnabled(false);
            }
        }
    };
    JLabel PVLabel = new JLabel("PV:");
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list = new JList<>(listModel);
    JScrollPane PV = new JScrollPane(list);

    JLabel SVLabel = new JLabel("SV:");
    DefaultListModel<String> listModelS = new DefaultListModel<>();
    JList<String> listS = new JList<>(listModelS);
    JScrollPane SV = new JScrollPane(listS);

    JButton PRR = new JButton();
    JButton PRR_AT0 = new JButton();
    JButton RR = new JButton();
    JButton RR_AT0 = new JButton();
    JButton SJF = new JButton();
    JButton SJF_AT0 = new JButton();
    JButton SRF = new JButton();
    JButton FCFS = new JButton();

    UI() {
        this.setLayout(new RelativeLayout());
        this.setBackground(Color.decode("#dddddd"));

        AddProc.setText("Add");
        AddProc.putClientProperty("JButton.buttonType", "roundRect");
        AddProc.setBackground(Color.decode("#008080"));
        AddProc.setForeground(Color.decode("#eeeeee"));
        AddProc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        AddProc.addActionListener(e -> {
            processes.add(new process(PNameField.getText(), Integer.parseInt(PATField.getText()), Integer.parseInt(PBTField.getText()), Integer.parseInt(PPRField.getText())));
            listModel.addElement(processes.getLast().init());
        });

        RestProc.setText("Rest");
        RestProc.putClientProperty("JButton.buttonType", "roundRect");
        RestProc.setBackground(Color.decode("#008080"));
        RestProc.setForeground(Color.decode("#eeeeee"));
        RestProc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        RestProc.addActionListener(e -> {
            processes.clear();
            listModel.clear();
            listModelS.clear();
        });

        PRR.setText("PRR\n");
        PRR.putClientProperty("JButton.buttonType", "roundRect");
        PRR.setBackground(Color.decode("#008080"));
        PRR.setForeground(Color.decode("#eeeeee"));
        PRR.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        PRR.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, process.at, process.bt, process.pr));


            PRR.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.RRP(false);
            System.out.println("finish");


            double TT = 0.0, WT = 0.0;
            listModelS.addElement("Round Robin with priority");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            PRR.setEnabled(true);

        });


        PRR_AT0.setText("PRR AT(0)\n");
        PRR_AT0.putClientProperty("JButton.buttonType", "roundRect");
        PRR_AT0.setBackground(Color.decode("#008080"));
        PRR_AT0.setForeground(Color.decode("#eeeeee"));
        PRR_AT0.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        PRR_AT0.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, 0, process.bt, process.pr));

            PRR_AT0.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.RRP(true);
            System.out.println("finish");

            double TT = 0.0, WT = 0.0;
            listModelS.addElement("Round Robin with priority(AT0)");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            PRR_AT0.setEnabled(true);

        });


        RR.setText("RR\n");
        RR.putClientProperty("JButton.buttonType", "roundRect");
        RR.setBackground(Color.decode("#008080"));
        RR.setForeground(Color.decode("#eeeeee"));
        RR.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        RR.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, process.at, process.bt, process.pr));

            RR.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.RR(false);
            System.out.println("finish");

            double TT = 0.0, WT = 0.0;
            listModelS.addElement("Round Robin");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            RR.setEnabled(true);

        });

        RR_AT0.setText("RR AT(0)\n");
        RR_AT0.putClientProperty("JButton.buttonType", "roundRect");
        RR_AT0.setBackground(Color.decode("#008080"));
        RR_AT0.setForeground(Color.decode("#eeeeee"));
        RR_AT0.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        RR_AT0.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, 0, process.bt, process.pr));

            RR_AT0.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.RR(true);
            System.out.println("finish");

            double TT = 0.0, WT = 0.0;
            listModelS.addElement("Round Robin (AT0)");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            RR_AT0.setEnabled(true);

        });

        SJF.setText("SJF\n");
        SJF.putClientProperty("JButton.buttonType", "roundRect");
        SJF.setBackground(Color.decode("#008080"));
        SJF.setForeground(Color.decode("#eeeeee"));
        SJF.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        SJF.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, process.at, process.bt, process.pr));

            SJF.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.SJFS(false);
            System.out.println("finish");

            double TT = 0.0, WT = 0.0;
            listModelS.addElement("Shortest Job First");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            SJF.setEnabled(true);

        });

        SJF_AT0.setText("SJF AT(0)\n");
        SJF_AT0.putClientProperty("JButton.buttonType", "roundRect");
        SJF_AT0.setBackground(Color.decode("#008080"));
        SJF_AT0.setForeground(Color.decode("#eeeeee"));
        SJF_AT0.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        SJF_AT0.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, 0, process.bt, process.pr));

            SJF_AT0.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.SJFS(true);
            System.out.println("finish");

            double TT = 0.0, WT = 0.0;
            listModelS.addElement("Shortest Job First (AT0)");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            SJF_AT0.setEnabled(true);

        });

        FCFS.setText("FCFS\n");
        FCFS.putClientProperty("JButton.buttonType", "roundRect");
        FCFS.setBackground(Color.decode("#008080"));
        FCFS.setForeground(Color.decode("#eeeeee"));
        FCFS.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        FCFS.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, process.at, process.bt, process.pr));

            FCFS.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.FCFS();
            System.out.println("finish");

            double TT = 0.0, WT = 0.0;
            listModelS.addElement("First Come First Serve");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            FCFS.setEnabled(true);

        });

        SRF.setText("SRF\n");
        SRF.putClientProperty("JButton.buttonType", "roundRect");
        SRF.setBackground(Color.decode("#008080"));
        SRF.setForeground(Color.decode("#eeeeee"));
        SRF.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        SRF.addActionListener(e -> {
            LinkedList<process> Temp = new LinkedList<>();
            for (process process : processes) Temp.add(new process(process.name, process.at, process.bt, process.pr));

            SRF.setEnabled(false);
            Scheduler.init(Temp);
            Scheduler.SRFS();
            System.out.println("finish");

            double TT = 0.0, WT = 0.0;
            listModelS.addElement("Shortest Remaining First");
            for (process x : Temp) {
                TT += x.tt;
                WT += x.wt;
                listModelS.addElement(x.stat());
            }
            listModelS.addElement("AVG TT:" + (TT / Temp.size()) + " , AVG WT:" + (WT / Temp.size()));
            listModelS.addElement("********************************************************");

            Temp.clear();
            Temp = null;
            Scheduler.dispose();
            SRF.setEnabled(true);

        });

        SetQ.setText("SetQ\n");
        SetQ.putClientProperty("JButton.buttonType", "roundRect");
        SetQ.setBackground(Color.decode("#008080"));
        SetQ.setForeground(Color.decode("#eeeeee"));
        SetQ.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        SetQ.addActionListener(e -> {
            SetQ.setEnabled(false);
            Scheduler.setQ(Integer.parseInt(QuantumField.getText()));
            SetQ.setEnabled(true);
        });


        PNameField.putClientProperty("JComponent.roundRect", true);
        PNameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        PNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

        PATField.putClientProperty("JComponent.roundRect", true);
        PATField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        PATLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

        PBTField.putClientProperty("JComponent.roundRect", true);
        PBTField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        PBTLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

        PPRField.putClientProperty("JComponent.roundRect", true);
        PPRField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        PPRLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

        QuantumField.putClientProperty("JComponent.roundRect", true);
        QuantumField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        QuantumLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));


        QuantumField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    Integer.parseInt(QuantumField.getText());
                    SetQ.setEnabled(true);
                } catch (Exception E) {
                    SetQ.setEnabled(false);
                }
            }
        });

        PATField.addKeyListener(Validator);

        PBTField.addKeyListener(Validator);

        PPRField.addKeyListener(Validator);

        PV.setBorder(new FlatBorder() {
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(10, 10, 10, 10);
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#008080")); // Border color
                g2.fillRoundRect(x, y, width - 1, height - 1, 30, 30); // 30px rounded edges
            }
        });


        SV.setBorder(new FlatBorder() {
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(10, 10, 10, 10);
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#008080")); // Border color
                g2.fillRoundRect(x, y, width - 1, height - 1, 30, 30); // 30px rounded edges
            }
        });

        this.add(PNameLabel, new float[]{0.02f, 0.1f, 0.1f, 0.1f, 30.0f});
        this.add(PNameField, new float[]{0.08f, 0.1f, 0.1f, 0.1f, 30.0f});

        this.add(PATLabel, new float[]{0.19f, 0.1f, 0.1f, 0.1f, 30.0f});
        this.add(PATField, new float[]{0.23f, 0.1f, 0.1f, 0.1f, 30.0f});

        this.add(PBTLabel, new float[]{0.35f, 0.1f, 0.1f, 0.1f, 30.0f});
        this.add(PBTField, new float[]{0.39f, 0.1f, 0.1f, 0.1f, 30.0f});

        this.add(PPRLabel, new float[]{0.50f, 0.1f, 0.1f, 0.1f, 30.0f});
        this.add(PPRField, new float[]{0.54f, 0.1f, 0.1f, 0.1f, 30.0f});

        this.add(PVLabel, new float[]{0.05f, 0.25f, 0.1f, 0.1f, 30.0f});
        this.add(PV, new float[]{0.08f, 0.25f, 0.250f, 0.7f, 30.0f});

        this.add(SVLabel, new float[]{0.35f, 0.25f, 0.1f, 0.1f, 30.0f});
        this.add(SV, new float[]{0.38f, 0.25f, 0.250f, 0.7f, 30.0f});

        this.add(AddProc, new float[]{0.7f, 0.1f, 0.125f, 0.1f, 30.0f});
        this.add(RestProc, new float[]{0.85f, 0.1f, 0.125f, 0.1f, 30.0f});

        this.add(RR, new float[]{0.7f, 0.25f, 0.125f, 0.1f, 30.0f});
        this.add(RR_AT0, new float[]{0.85f, 0.25f, 0.125f, 0.1f, 30.0f});

        this.add(PRR, new float[]{0.7f, 0.4f, 0.125f, 0.1f, 30.0f});
        this.add(PRR_AT0, new float[]{0.85f, 0.4f, 0.125f, 0.1f, 30.0f});

        this.add(SJF, new float[]{0.7f, 0.55f, 0.125f, 0.1f, 30.0f});
        this.add(SJF_AT0, new float[]{0.85f, 0.55f, 0.125f, 0.1f, 30.0f});

        this.add(FCFS, new float[]{0.7f, 0.7f, 0.125f, 0.1f, 30.0f});

        this.add(SRF, new float[]{0.85f, 0.7f, 0.125f, 0.1f, 30.0f});

        this.add(QuantumLabel, new float[]{0.68f, 0.85f, 0.125f, 0.1f, 30.0f});
        this.add(QuantumField, new float[]{0.7f, 0.85f, 0.125f, 0.1f, 30.0f});
        this.add(SetQ, new float[]{0.85f, 0.85f, 0.125f, 0.1f, 30.0f});


    }


}
