package main.java.scc.data;

import java.util.Collections;

public class Board {

    private String id;
    private boolean active;
    private long duration;
    private long hourToStart;
    private boolean[] rotation;

    public Board() {
    }

    public Board(String id, boolean active, long duration,
                 long hourToStart, boolean[] rotation) {
        super();
        this.id = id;
        this.active = active;
        this.duration = duration;
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
        return "BoardConfig[id=" + id + ", active=" + active + ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + "]";
    }

}
