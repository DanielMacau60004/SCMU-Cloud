package main.java.scc.data;

public class Data {

    private String id;
    private float temp;
    private float hum;
    private long t;

    public Data() {
    }

    public Data(String id, float temp, float hum, long t) {
        super();
        this.id = id;
        this.temp = temp;
        this.hum = hum;
        this.t = t;
    }

    public String getId() {
        return id;
    }

    public float getTemp() {
        return temp;
    }

    public float getHum() {
        return hum;
    }

    public long getT() {
        return t;
    }

    public Data toData() {
        return new Data(id, temp, hum, t);
    }

    @Override
    public String toString() {
        return "Data[id=" + id + ", temp=" + temp + ", hum=" + hum + ", t=" + t + "]";
    }

}
