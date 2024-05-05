package main.java.scmu.db;


public interface DBLayerRepository {


    BoardRepository getBoardsRepository();

    DataRepository getDataRepository();

    StatusRepository getStatusRepository();

    UserRepository getUserRepository();

}
