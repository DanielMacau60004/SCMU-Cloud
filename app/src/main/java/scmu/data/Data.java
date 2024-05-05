package main.java.scmu.data;

public class Data {

    private float temp;
    private float hum;
    private long t;

    public Data() {
    }

    public Data(float temp, float hum, long t) {
        super();
        this.temp = temp;
        this.hum = hum;
        this.t = t;
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
        return new Data(temp, hum, t);
    }

    @Override
    public String toString() {
        return "Data[temp=" + temp + ", hum=" + hum + ", t=" + t + "]";
    }

}
