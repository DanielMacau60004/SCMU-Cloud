package main.java.scmu.data;

public class Status {

    private int status;
    private long t;

    public Status() {
    }

    public Status(int status, long t) {
        super();
        this.status = status;
        this.t = t;
    }

    public int getStatus() {
        return status;
    }

    public long getT() {
        return t;
    }

    @Override
    public String toString() {
        return "Data[status=" + status + ", t=" + t + "]";
    }

}
