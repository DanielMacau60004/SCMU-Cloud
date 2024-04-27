package main.java.scc.data;

import java.util.Collections;

public class DataDAO {

    private String _rid;
    private String _ts;

    private String id;
    private final float temp;
    private final float hum;
    private final long t;

    public DataDAO(Data d) {
        this(d.getId(), d.getTemp(), d.getHum(), d.getT());
    }

    public DataDAO(String id, float temp, float hum, long t) {
        super();
        this.temp = temp;
        this.hum = hum;
        this.t = t;
    }

    public String getId() {
        return id;
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
        return new Data(id, temp, hum, t);
    }

    @Override
    public String toString() {
        return "DataDAO[_rid=" + _rid + ", id=" + id + ", _ts=" + _ts + ", temp=" +
                temp + ", hum=" + hum + ", t=" + t + "]";
    }

}
