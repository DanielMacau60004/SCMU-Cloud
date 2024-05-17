package main.java.scmu.data;

import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private String id;
    private String pwd;

    private boolean active;
    private long duration;
    private long hourToStart;
    private boolean[] rotation;
    private int state;
    private int currentState;
    private long lastUpdate;

    @Nullable
    private List<Data> data;

    @Nullable
    private List<Status> status;

    public Board() {
    }

    public Board(String id, String pwd, boolean active, long duration,
                 long hourToStart, boolean[] rotation, int state,
                 int currentState, long lastUpdate) {
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

        this.data = new ArrayList<>();
        this.status = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
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

    @Override
    public String toString() {
        return "Board[id=" + id + ", pwd=" + pwd + ", active=" + active + ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + ", state=" + state + ", data=" +
                data + "+ , status=" + status + " , currentState=" + currentState + " , lastUpdate=" + lastUpdate + "]";
    }

}
