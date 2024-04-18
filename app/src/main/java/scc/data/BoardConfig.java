package main.java.scc.data;

import java.util.Collections;

public class BoardConfig {

    private String id;
    private boolean active;
    private long nextTime;
    private long currentTime;
    private long duration;
    private long hourToStart;
    private boolean[] rotation;

    public BoardConfig() {
    }

    public BoardConfig(String id, boolean active, long nextTime, long currentTime, long duration,
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

    public String getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
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


    @Override
    public String toString() {
        return "BoardConfig[id=" + id + ", active=" + active + ", nextTime=" + nextTime
                + ", currentTime=" + currentTime + ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + "]";
    }

}
