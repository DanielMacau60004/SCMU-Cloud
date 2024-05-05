package main.java.scmu.db.memory;

import main.java.scmu.db.*;

public class MemoryDBLayer implements DBLayerRepository {


    private static MemoryDBLayer instance;

    public static synchronized MemoryDBLayer getInstance() {
        if (instance != null)
            return instance;

        instance = new MemoryDBLayer();
        return instance;
    }


    private final MemoryDBBoardRepository boards;
    private final MemoryDBDataRepository data;
    private final MemoryDBStatusRepository status;
    private final MemoryDBUserRepository users;

    public MemoryDBLayer() {
        boards = new MemoryDBBoardRepository();
        data = new MemoryDBDataRepository();
        status = new MemoryDBStatusRepository();
        users = new MemoryDBUserRepository();
    }


    @Override
    public BoardRepository getBoardsRepository() {
        return boards;
    }

    @Override
    public DataRepository getDataRepository() {
        return data;
    }

    @Override
    public StatusRepository getStatusRepository() {
        return status;
    }

    @Override
    public UserRepository getUserRepository() {
        return users;
    }
}
