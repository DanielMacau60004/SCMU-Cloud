package main.java.scmu.services;

import main.java.scmu.data.*;
import main.java.scmu.db.DataRepository;
import main.java.scmu.db.StatusRepository;
import main.java.scmu.srv.MainApplication;

import java.util.List;
import java.util.stream.Collectors;

public class StatusService {

    public static void addBulk(BoardDAO board, List<Status> status) {
        if(status == null || status.isEmpty())
            return;

        StatusRepository statusDB = MainApplication.DB_LAYER.getStatusRepository();
        statusDB.addBulk(status.stream().map(s -> new StatusDAO(board, s)).collect(Collectors.toList()));
    }

    public static List<Status> list(String id, long start, long end) {
        StatusRepository statusDB = MainApplication.DB_LAYER.getStatusRepository();
        return statusDB.listByIntervalLocation(id, start, end).stream().map(StatusDAO::toStatus).toList();
    }

}
