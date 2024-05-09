package main.java.scmu.data;

public class DataDAO {

    private String _rid;
    private String _ts;

    private String id;
    private String board;
    private float temp;
    private float hum;
    private long t;

    public DataDAO(){}

    public DataDAO(BoardDAO board, Data d) {
        this(board.getId()+d.getT(), board.getId(), d.getTemp(), d.getHum(), d.getT());
    }

    public DataDAO(String id, String board, float temp, float hum, long t) {
        super();
        this.id = id;
        this.board = board;
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

    public String getBoard() {
        return board;
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
        return "DataDAO[_rid=" + _rid + ", id=" + id + ", board=" + board + ", _ts=" + _ts + ", temp=" +
                temp + ", hum=" + hum + ", t=" + t + "]";
    }

}
