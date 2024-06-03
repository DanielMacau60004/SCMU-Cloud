package main.java.scmu.data;

import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

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

    @Nullable
    private List<Data> data;

    @Nullable
    private List<Status> status;

    public Board() {
    }

    public Board(String id, boolean active, long duration,
                 long hourToStart, boolean[] rotation, int state,
                 int currentState, long currentDate, long lastUpdate,
                 boolean smart, float currentTemp, float currentHum,
                 String timeZone, String password) {
        super();
        this.id = id;
        this.active = active;
        this.duration = duration;
        this.hourToStart = hourToStart;
        this.rotation = rotation;
        this.state = state;
        this.currentState = currentState;
        this.currentDate = currentDate;
        this.smart = smart;
        this.currentTemp = currentTemp;
        this.currentHum = currentHum;
        this.timeZone = timeZone;
        this.password = password;

        this.lastUpdate = lastUpdate;

        this.data = new ArrayList<>();
        this.status = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @Nullable
    public List<Data> getData() {
        return data;
    }

    @Nullable
    public List<Status> getStatus() {
        return status;
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

    @Override
    public String toString() {
        return "Board[id=" + id + ", active=" + active + ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + ", state=" + state + ", data=" +
                data + "+ , status=" + status + " , currentState=" + currentState + " , currentDate=" + currentDate + " , lastUpdate=" + lastUpdate
                + " , smart=" + smart + " , currentTemp=" + currentTemp + " , currentHum=" + currentHum +
                " , timeZone=" + timeZone + " , password=" + password +"]";
    }

}
