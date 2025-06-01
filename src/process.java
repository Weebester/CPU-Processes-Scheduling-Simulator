public class process {
    String name;
    int at;
    int et;
    int bt;
    int rt;
    int wt;
    int tt;
    int pr;

    process(String name, int at, int bt, int pr) {
        this.name = name;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
        this.pr = pr;
    }

    void calc_tt() {
        tt = et - at;
    }

    void calc_wt() {
        wt = tt - bt;
    }


    String init(){
        return (""+name+" ( arrival time:"+at+" , burst time:"+bt+" , priority:"+pr+" )");
    }


    String stat(){
        return (""+name+" ( turnaround time:"+tt+" , wait time:"+wt+" , exit time:"+et+" )");
    }

}