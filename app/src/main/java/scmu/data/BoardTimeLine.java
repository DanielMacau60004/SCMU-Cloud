package main.java.scmu.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoardTimeLine {

    @JsonProperty("start")
    private long start;

    @JsonProperty("end")
    private long end;

    @JsonProperty("duration")
    private long duration;

    @JsonProperty("state")
    private int state;

    public BoardTimeLine() {}
    public BoardTimeLine(long start, long end, int state) {
        this.start = start;
        this.end = end;
        this.state = state;

        duration = end - start;
    }

}
