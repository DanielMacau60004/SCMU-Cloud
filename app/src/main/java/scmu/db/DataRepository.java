package main.java.scmu.db;

import main.java.scmu.data.DataDAO;

import java.util.List;


public interface DataRepository extends Repository<DataDAO> {


    List<DataDAO> listByIntervalLocation(String id, long start, long end);
}