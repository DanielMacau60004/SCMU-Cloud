package main.java.scmu.db;

import main.java.scmu.data.DataDAO;
import main.java.scmu.data.Status;
import main.java.scmu.data.StatusDAO;

import java.util.List;


public interface StatusRepository extends Repository<StatusDAO> {


    void addBulk(List<StatusDAO> data);
    void removeBulk(List<StatusDAO> data);
    List<StatusDAO> listByIntervalLocation(String id, long start, long end);

}
