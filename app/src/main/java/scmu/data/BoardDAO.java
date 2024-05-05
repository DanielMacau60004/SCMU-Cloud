package main.java.scmu.data;

import java.util.Collections;

public class BoardDAO {

    private String _rid;
    private String _ts;

    private String id;
    private boolean active;
    private long duration;
    private long hourToStart;
    private boolean[] rotation;

    public BoardDAO() {

    }

    public BoardDAO(Board b) {
        this(b.getId(), b.isActive()
                , b.getDuration(), b.getHourToStart(), b.getRotation());
    }

    public BoardDAO(String id, boolean active, long duration,
                    long hourToStart, boolean[] rotation) {
        super();
        this.id = id;
        this.active = active;
        this.duration = duration;
        this.hourToStart = hourToStart;
        this.rotation = rotation;

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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setHourToStart(long hourToStart) {
        this.hourToStart = hourToStart;
    }

    public long getHourToStart() {
        return hourToStart;
    }

    public void setRotation(boolean[] rotation) {
        this.rotation = rotation;
    }

    public boolean[] getRotation() {
        return rotation;
    }

    public Board toBoard() {
        return new Board(id, active, duration,
                hourToStart, rotation);
    }

    @Override
    public String toString() {
        return "BoardDAO[_rid=" + _rid + ", _ts=" + _ts + ", id=" + id + ", active=" + active +
                ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + "]";
    }

}
