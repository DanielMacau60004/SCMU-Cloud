package main.java.scmu.services;

import main.java.scmu.data.Board;
import main.java.scmu.data.BoardDAO;
import main.java.scmu.data.Data;
import main.java.scmu.data.DataDAO;
import main.java.scmu.db.DataRepository;
import main.java.scmu.srv.MainApplication;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class DataService {

    public static void addBulk(BoardDAO board, List<Data> data) {
        if(data == null || data.isEmpty())
            return;

        DataRepository dataDB = MainApplication.DB_LAYER.getDataRepository();
        dataDB.addBulk(data.stream().map(d -> new DataDAO(board, d)).collect(Collectors.toList()));
    }

    public static List<Data> list(String id, long start, long end) {
        DataRepository dataDB = MainApplication.DB_LAYER.getDataRepository();
        return dataDB.listByIntervalLocation(id, start, end).stream().map(DataDAO::toData).toList();
    }

}
