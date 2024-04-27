package main.java.scc.db;

import main.java.scc.data.DataDAO;

import java.util.List;


public interface DataRepository extends Repository<DataDAO> {


    List<DataDAO> listByIntervalLocation(String id, long start, long end);
}
