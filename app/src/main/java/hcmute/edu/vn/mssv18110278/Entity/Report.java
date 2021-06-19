package hcmute.edu.vn.mssv18110278.Entity;

public class Report {
    private int time;
    private int value;

    public Report(int time, int value) {
        this.time = time;
        this.value = value;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
