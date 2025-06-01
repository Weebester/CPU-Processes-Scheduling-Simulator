import javax.swing.*;
import java.util.Comparator;
import java.util.LinkedList;

public class Scheduler {

    static LinkedList<excution_window> ew;
    static LinkedList<process> unscheduled;
    static LinkedList<process> working_on;
    static LinkedList<process> finished;

    static void FCFS() {

        int wc = 0;


        unscheduled.sort(Comparator.comparingInt(p -> p.at));
        working_on.addAll(unscheduled);
        unscheduled.clear();

        for (int i = 0; i < working_on.size(); i++) {
            working_on.get(i).wt = wc;
            wc += working_on.get(i).bt;
            working_on.get(i).rt -= working_on.get(i).bt;
            working_on.get(i).et = wc;
            working_on.get(i).calc_tt();
            working_on.get(i).calc_wt();
            ew.add(new excution_window(working_on.get(i), working_on.get(i).bt));
        }

        JFrame J = new JFrame("gantt chart First Come First Serve");
        J.setSize(800, 400);
        JPanel chart = new Gantt(ew.toArray(excution_window[]::new));
        JScrollPane p = new JScrollPane(chart);
        J.add(p);
        J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        J.setVisible(true);
    }

    static void SJFS(boolean at0) {


        unscheduled.sort(Comparator.comparingInt(p -> p.at));

        int c = 0;
        int c2 = 0;

        if (at0) {
            working_on.addAll(unscheduled);
            unscheduled.clear();
        } else {
            working_on.add(unscheduled.getFirst());
            unscheduled.removeFirst();
        }


        while (!working_on.isEmpty()) {

            ////main loop
            working_on.getFirst().rt -= 1;
            c2++;


            //////on process exit
            if (working_on.getFirst().rt == 0) {
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.getFirst().et = c2;
                working_on.getFirst().calc_tt();
                working_on.getFirst().calc_wt();
                finished.add(working_on.getFirst());
                working_on.removeFirst();

                if (!working_on.isEmpty()) {
                    working_on.sort(Comparator.comparingInt(p -> p.bt));
                }
            }


            ///on new arrival
            if (!unscheduled.isEmpty() && c2 >= unscheduled.getFirst().at) {
                working_on.add(unscheduled.getFirst());
                unscheduled.removeFirst();
            } else
                //on idle
                if (working_on.isEmpty() && !unscheduled.isEmpty()) {
                    c2 = unscheduled.getFirst().at;
                    ew.add(new excution_window(new process("idle", 0, 0, 0), c2 - c));
                    c = c2;
                    working_on.add(unscheduled.getFirst());
                    unscheduled.removeFirst();
                }


        }

        JFrame J = new JFrame("gantt chart Shortest Job First");
        J.setSize(800, 400);
        JPanel chart = new Gantt(ew.toArray(excution_window[]::new));
        JScrollPane p = new JScrollPane(chart);
        J.add(p);
        J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        J.setVisible(true);

    }

    static void SRFS() {

        unscheduled.sort(Comparator.comparingInt(p -> p.at));

        int c = 0;
        int c2 = 0;

        working_on.add(unscheduled.getFirst());
        unscheduled.removeFirst();

        while (!working_on.isEmpty()) {
            ////main loop
            working_on.getFirst().rt -= 1;
            c2++;

            //////on process exit
            if (working_on.getFirst().rt == 0) {
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.getFirst().et = c2;
                working_on.getFirst().calc_tt();
                working_on.getFirst().calc_wt();
                finished.add(working_on.getFirst());
                working_on.removeFirst();
            }

            //on idle
            if (working_on.isEmpty() && !unscheduled.isEmpty()) {
                working_on.add(unscheduled.getFirst());
                c2 = working_on.getFirst().at;
                ew.add(new excution_window(new process("idle", 0, 0, 0), c2 - c));
                c = c2;
                unscheduled.removeFirst();
                continue;
            }

            ///on new arrival
            if (!unscheduled.isEmpty() && c2 >= unscheduled.getFirst().at) {
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.add(unscheduled.getFirst());
                unscheduled.removeFirst();
                for (int j = 0; j < working_on.size() - 1; j++) {
                    for (int k = 0; k < working_on.size() - 1 - j; k++) {
                        if (working_on.get(k).rt > working_on.get(k + 1).rt) {
                            process t = working_on.get(k);
                            process t2 = working_on.get(k + 1);
                            working_on.set(k, t2);
                            working_on.set(k + 1, t);
                        }
                    }
                }


            }

        }

        JFrame J = new JFrame("gantt chart Shortest Remaining Time First");
        J.setSize(800, 400);
        JPanel chart = new Gantt(ew.toArray(excution_window[]::new));
        JScrollPane p = new JScrollPane(chart);
        J.add(p);
        J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        J.setVisible(true);

    }


    static private int quantum = 3;

    static void setQ(int Q) {
        quantum = Q;
    }

    static void RR(boolean at0) {

        unscheduled.sort(Comparator.comparingInt(p -> p.at));

        int q = 0;
        int c = 0;
        int c2 = 0;

        if (at0) {
            working_on.addAll(unscheduled);
            unscheduled.clear();
        } else {
            working_on.add(unscheduled.getFirst());
            unscheduled.removeFirst();
        }

        while (!working_on.isEmpty()) {

            ////main loop
            working_on.getFirst().rt -= 1;
            c2++;
            q++;


            //////on process exit
            if (working_on.getFirst().rt == 0) {
                q = 0;
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.getFirst().et = c2;
                working_on.getFirst().calc_wt();
                working_on.getFirst().calc_tt();
                finished.add(working_on.getFirst());
                working_on.removeFirst();

            }

            //////on quantum
            if (q == quantum && working_on.getFirst().rt != 0) {
                q = 0;
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.getFirst().et = c2;
                process temp = working_on.getFirst();
                working_on.removeFirst();
                working_on.add(temp);
            }


            ///on new arrival
            if (!unscheduled.isEmpty() && c2 >= unscheduled.getFirst().at) {
                working_on.add(unscheduled.getFirst());
                unscheduled.removeFirst();
            } else
                //on idle
                if (working_on.isEmpty() && !unscheduled.isEmpty()) {
                    c2 = unscheduled.getFirst().at;
                    ew.add(new excution_window(new process("idle", 0, 0, 0), c2 - c));
                    c = c2;
                    working_on.add(unscheduled.getFirst());
                    unscheduled.removeFirst();
                }


        }

        JFrame J = new JFrame("gantt chart Round Robin");
        J.setSize(800, 400);
        JPanel chart = new Gantt(ew.toArray(excution_window[]::new));
        JScrollPane p = new JScrollPane(chart);
        J.add(p);
        J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        J.setVisible(true);


    }

    static void RRP(boolean at0) {
        unscheduled.sort(Comparator.comparingInt(p -> p.at));

        if (at0) {
            working_on.addAll(unscheduled);
            working_on.sort(Comparator.comparingInt(p -> p.pr));
            unscheduled.clear();
        } else {
            working_on.add(unscheduled.getFirst());
            unscheduled.removeFirst();
        }


        int q = 0;
        int c = 0;
        int c2 = 0;

        while (!working_on.isEmpty()) {

            ////main loop
            working_on.getFirst().rt -= 1;
            c2++;
            q++;


            //////on process exit
            if (working_on.getFirst().rt == 0) {
                q = 0;
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.getFirst().et = c2;
                working_on.getFirst().calc_tt();
                working_on.getFirst().calc_wt();
                finished.add(working_on.getFirst());
                working_on.removeFirst();

            }

            //////on quantum
            if (q == quantum && working_on.getFirst().rt != 0) {
                q = 0;
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.getFirst().et = c2;
                process temp = working_on.getFirst();
                working_on.removeFirst();
                working_on.add(temp);
                working_on.sort(Comparator.comparingInt(p -> p.pr));
            }


            ///on new arrival
            if (!unscheduled.isEmpty() && c2 >= unscheduled.getFirst().at) {
                working_on.add(unscheduled.getFirst());
                q = 0;
                ew.add(new excution_window(working_on.getFirst(), c2 - c));
                c = c2;
                working_on.getFirst().et = c2;
                process temp = working_on.getFirst();
                working_on.removeFirst();
                working_on.add(temp);
                working_on.sort(Comparator.comparingInt(p -> p.pr));
                unscheduled.removeFirst();
            } else
                //on idle
                if (working_on.isEmpty() && !unscheduled.isEmpty()) {
                    c2 = unscheduled.getFirst().at;
                    ew.add(new excution_window(new process("idle", 0, 0, 0), c2 - c));
                    c = c2;
                    working_on.add(unscheduled.getFirst());
                    unscheduled.removeFirst();
                }


        }

        JFrame J = new JFrame("gantt chart Round Robin with priority");
        J.setSize(800, 400);
        JPanel chart = new Gantt(ew.toArray(excution_window[]::new));
        JScrollPane p = new JScrollPane(chart);
        J.add(p);
        J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        J.setVisible(true);

    }

    static void init(LinkedList<process> unscheduled) {
        Scheduler.unscheduled = new LinkedList<>();
        Scheduler.unscheduled.addAll(unscheduled);
        ew = new LinkedList<>();
        working_on = new LinkedList<>();
        finished = new LinkedList<>();

    }

    static void dispose() {
        for (int i = 0; i < Scheduler.unscheduled.size(); i++) {
            Scheduler.unscheduled.set(i, null);
        }
        Scheduler.unscheduled.clear();
        Scheduler.unscheduled = null;

        for (int i = 0; i < ew.size(); i++) {
            ew.set(i, null);
        }
        ew.clear();
        ew = null;

        for (int i = 0; i < working_on.size(); i++) {
            working_on.set(i, null);
        }
        working_on.clear();
        working_on = null;

        for (int i = 0; i < finished.size(); i++) {
            finished.set(i, null);
        }
        finished.clear();
        finished = null;

        System.gc();
        Runtime.getRuntime().gc();
    }


}
