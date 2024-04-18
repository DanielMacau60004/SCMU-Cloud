package main.java.scc.data;

import java.util.Collections;

public class DataDAO {

    private String _rid;
    private String _ts;

    private final float temp;
    private final float hum;
    private final long t;

    public DataDAO(Data d) {
        this(d.getTemp(), d.getHum(), d.getT());
    }

    public DataDAO(float temp, float hum, long t) {
        super();
        this.temp = temp;
        this.hum = hum;
        this.t = t;
    }

    public String get_rid() {
        return _rid;
    }

    public void set_rid(String _rid) {
        this._rid = _rid;
    }

    public String get_ts() {
        return _ts;
    }

    public void set_ts(String _ts) {
        this._ts = _ts;
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
        return "DataDAO[_rid=" + _rid + ", _ts=" + _ts + ", temp=" +
                temp + ", hum=" + hum + ", t=" + t + "]";
    }

}
