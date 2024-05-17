package main.java.scmu.data;

import java.util.Collections;

public class BoardDAO {

    private String _rid;
    private String _ts;

    private String id;
    private String pwd;
    private boolean active;
    private long duration;
    private long hourToStart;
    private boolean[] rotation;
    private int state;

    private int currentState;
    private long lastUpdate;

    public BoardDAO() {

    }

    public BoardDAO(Board b) {
        this(b.getId(), b.getPwd(), b.isActive()
                , b.getDuration(), b.getHourToStart(), b.getRotation(), b.getState(),
                b.getCurrentState(), b.getLastUpdate());
    }

    public BoardDAO(String id, String pwd, boolean active, long duration,
                    long hourToStart, boolean[] rotation, int state, int currentState, long lastUpdate) {
        super();
        this.id = id;
        this.pwd = pwd;
        this.active = active;
        this.duration = duration;
        this.hourToStart = hourToStart;
        this.rotation = rotation;
        this.state = state;
        this.currentState = currentState;
        this.lastUpdate = lastUpdate;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Board toBoard() {
        return new Board(id, pwd, active, duration,
                hourToStart, rotation, state, currentState, lastUpdate);
    }

    @Override
    public String toString() {
        return "BoardDAO[_rid=" + _rid + ", _ts=" + _ts + ", id=" + id + ", pwd=" + pwd +", active=" + active +
                ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + ", state=" + state
                + " , currentState=" + currentState + " , lastUpdate=" + lastUpdate + "]";
    }

}
