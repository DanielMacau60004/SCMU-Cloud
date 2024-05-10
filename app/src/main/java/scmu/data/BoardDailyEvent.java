package main.java.scmu.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class BoardDailyEvent {

    @JsonProperty("start")
    private long start;
    @JsonProperty("end")
    private long end;

    @JsonProperty("executionTime")
    private long executionTime;

    @JsonProperty("pausedTime")
    private long pausedTime;

    @JsonProperty("avgTemp")
    private float avgTemp;

    @JsonProperty("avgHum")
    private float avgHum;

    @JsonProperty("timeLine")
    private List<BoardTimeLine> timeLine;

    //@JsonProperty("data")
    private List<Data> data;

    //@JsonProperty("status")
    private List<Status> status;

    public BoardDailyEvent() {
    }

    public BoardDailyEvent(List<Data> data, List<Status> status) {
        this.data = data;
        this.status = status;

        timeLine = new LinkedList<>();

        calculateData(data);
        calculateStatus(status);
    }

    private void calculateData(List<Data> data) {
        //Initialize data
        float totalTemp = 0;
        float totalHum = 0;

        for (Data d : data) {
            totalTemp += d.getTemp();
            totalHum += d.getHum();
        }

        if (data.size() > 0) {
            avgTemp = totalTemp / data.size();
            avgHum = totalHum / data.size();
        }
    }

    private void calculateStatus(List<Status> status) {

        Status prvStatus = null;
        for (Status s : status) {

            if (s.getStatus() == 0) {//Running
                if (start == 0) start = s.getT();
                end = s.getT();
            }

            if (prvStatus != null) {
                long duration = s.getT() - prvStatus.getT();

                if (prvStatus.getStatus() == 0) {
                    executionTime += duration;
                    timeLine.add(new BoardTimeLine(prvStatus.getT(), s.getT(), prvStatus.getStatus()));
                } else if (prvStatus.getStatus() == 1) {
                    pausedTime += duration;
                    timeLine.add(new BoardTimeLine(prvStatus.getT(), s.getT(), prvStatus.getStatus()));
                }
            }

            prvStatus = s;
        }
    }

    @JsonProperty("asEvent")
    public boolean asEvent() {
        return !timeLine.isEmpty();
    }

}
