package main.java.scmu.data;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.stream.Collectors;

public class BoardInfo {

    @JsonProperty("board")
    private Board board;

    @JsonProperty("events")
    private List<BoardDailyEvent> events;

    public BoardInfo() {}

    public BoardInfo(Board board, List<Data> data, List<Status> status, long start, long end) {
        this.board = board;
        this.events = new LinkedList<>();

        final long DAY = 24 * 60 * 60;
        Map<Long, List<Data>> groupedData = data.stream()
                .collect(Collectors.groupingBy(d -> d.getT() / DAY, TreeMap::new, Collectors.toList()));
        Map<Long, List<Status>> groupedStatus = status.stream()
                .collect(Collectors.groupingBy(d -> d.getT() / DAY, TreeMap::new, Collectors.toList()));

        for (long i = start / DAY; i <= end / DAY; i++) {
            List<Data> currentData = groupedData.get(i);
            if (currentData == null)
                currentData = new LinkedList<>();

            List<Status> currentStatus = groupedStatus.get(i);
            if (currentStatus == null)
                currentStatus = new LinkedList<>();

            events.add(new BoardDailyEvent(currentData, currentStatus));
        }

    }

}




