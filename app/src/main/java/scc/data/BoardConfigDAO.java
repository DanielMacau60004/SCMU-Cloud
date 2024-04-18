package main.java.scc.data;

import java.util.Collections;

public class BoardConfigDAO {

    private String _rid;
    private String _ts;

    private final String id;
    private boolean active;
    private long nextTime;
    private long currentTime;
    private long duration;
    private long hourToStart;
    private boolean[] rotation;

    public BoardConfigDAO(BoardConfig b) {
        this(b.getId(), b.isActive(), b.getNextTime(), b.getCurrentTime()
                , b.getDuration(), b.getHourToStart(), b.getRotation());
    }

    public BoardConfigDAO(String id, boolean active, long nextTime, long currentTime, long duration,
                          long hourToStart, boolean[] rotation) {
        super();
        this.id = id;
        this.active = active;
        this.nextTime = nextTime;
        this.duration = duration;
        this.currentTime = currentTime;
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

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setNextTime(long nextTime) {
        this.nextTime = nextTime;
    }

    public long getNextTime() {
        return nextTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getCurrentTime() {
        return currentTime;
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

    public BoardConfig toBoardConfig() {
        return new BoardConfig(id, active, nextTime, currentTime, duration,
                hourToStart, rotation);
    }

    @Override
    public String toString() {
        return "BoardConfigDAO[_rid=" + _rid + ", _ts=" + _ts + ", id=" + id + ", active=" + active + ", nextTime=" + nextTime
                + ", currentTime=" + currentTime + ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + "]";
    }

}
