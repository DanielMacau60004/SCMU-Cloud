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
    private int state;

    private int currentState;
    private long currentDate;
    private long lastUpdate;

    private boolean smart;
    private float currentTemp;
    private float currentHum;

    private String timeZone;
    private String password;

    public BoardDAO() {

    }

    public BoardDAO(Board b) {
        this(b.getId(), b.isActive()
                , b.getDuration(), b.getHourToStart(), b.getRotation(), b.getState(),
                b.getCurrentState(), b.getCurrentDate(), b.getLastUpdate(),
                b.isSmart(), b.getCurrentTemp(), b.getCurrentHum(), b.getTimeZone(), b.getPassword());
    }

    public BoardDAO(String id, boolean active, long duration,
                    long hourToStart, boolean[] rotation, int state, int currentState, long currentDate, long lastUpdate,
                    boolean smart, float currentTemp, float currentHum, String timeZone, String password) {
        super();
        this.id = id;
        this.active = active;
        this.duration = duration;
        this.hourToStart = hourToStart;
        this.rotation = rotation;
        this.state = state;
        this.currentState = currentState;
        this.currentDate = currentDate;
        this.lastUpdate = lastUpdate;
        this.smart = smart;
        this.currentTemp = currentTemp;
        this.currentHum = currentHum;
        this.timeZone = timeZone;
        this.password = password;

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

    public long getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }

    public boolean isSmart() {
        return smart;
    }

    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    public float getCurrentHum() {
        return currentHum;
    }

    public void setCurrentHum(float currentHum) {
        this.currentHum = currentHum;
    }

    public float getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(float currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Board toBoard() {
        return new Board(id, active, duration,
                hourToStart, rotation, state, currentState, currentDate, lastUpdate,
                smart, currentTemp, currentHum, timeZone, password);
    }

    @Override
    public String toString() {
        return "BoardDAO[_rid=" + _rid + ", _ts=" + _ts + ", id=" + id + ", active=" + active +
                ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + ", state=" + state
                + " , currentState=" + currentState + " , currentDate=" + currentDate + " , lastUpdate=" + lastUpdate
                + " , smart=" + smart + " , currentTemp=" + currentTemp + " , currentHum=" + currentHum
                + " , timeZone=" + timeZone + " , password=" + password +"]";
    }

}
