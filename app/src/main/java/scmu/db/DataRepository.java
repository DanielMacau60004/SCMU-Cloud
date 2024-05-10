package main.java.scmu.db;

import main.java.scmu.data.Data;
import main.java.scmu.data.DataDAO;

import java.util.List;


public interface DataRepository extends Repository<DataDAO> {


    void addBulk(List<DataDAO> data);
    void removeBulk(List<DataDAO> data);
    List<DataDAO> listByIntervalLocation(String id, long start, long end);
}
