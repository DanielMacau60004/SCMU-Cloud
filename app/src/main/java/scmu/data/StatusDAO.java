package main.java.scmu.data;

public class StatusDAO {

    private String _rid;
    private String _ts;

    private String id;
    private String board;
    private int status;
    private long t;

    public StatusDAO() {}

    public StatusDAO(BoardDAO board, Status s) {
        this(board.getId()+s.getT(), board.getId(), s.getStatus(), s.getT());
    }

    public StatusDAO(String id, String board, int status, long t) {
        super();
        this.id = id;
        this.board = board;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public long getT() {
        return t;
    }

    public Status toStatus() {
        return new Status(status, t);
    }

    @Override
    public String toString() {
        return "DataDAO[_rid=" + _rid + ", id=" + id + ", board=" + board + ", _ts=" + _ts + ", status=" +
                status + ", t=" + t + "]";
    }

}
