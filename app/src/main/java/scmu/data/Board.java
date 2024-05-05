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

    @Nullable
    private List<Data> data;

    @Nullable
    private List<Status> status;

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

    @Nullable
    public List<Data> getData() {
        return data;
    }

    @Nullable
    public List<Status> getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Board[id=" + id + ", active=" + active + ", duration=" + duration + ", hourToStart=" + hourToStart +
                ", rotation=" + Collections.singletonList(rotation) + ", data=" + data + "+ , status=" + status + "]";
    }

}
