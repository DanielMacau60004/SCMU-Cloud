package main.java.scmu.db.memory;

import main.java.scmu.db.BoardRepository;
import main.java.scmu.db.DBLayerRepository;
import main.java.scmu.db.DataRepository;

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

    public MemoryDBLayer() {
        boards = new MemoryDBBoardRepository();
        data = new MemoryDBDataRepository();
    }


    @Override
    public BoardRepository getBoardsRepository() {
        return boards;
    }

    @Override
    public DataRepository getDataRepository() {
        return data;
    }
}
