package main.java.scc.services;

import main.java.scc.data.Data;
import main.java.scc.data.DataDAO;
import main.java.scc.db.DataRepository;
import main.java.scc.srv.MainApplication;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class DataService {

    public static Data create(Data data) {
        //Check for nulls
        if (data.getId() == null)
            throw new NotFoundException();

        DataDAO dataDAO = new DataDAO(data);

        DataRepository dataDB = MainApplication.DB_LAYER.getDataRepository();
        return dataDB.create(dataDAO).toData();
    }

    public static List<Data> list(String id, long start, long end) {
        DataRepository dataDB = MainApplication.DB_LAYER.getDataRepository();
        return dataDB.listByIntervalLocation(id, start, end).stream().map(DataDAO::toData).toList();
    }

}
