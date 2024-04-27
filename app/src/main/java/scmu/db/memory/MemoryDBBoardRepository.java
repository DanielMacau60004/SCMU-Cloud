package main.java.scmu.db.memory;

import main.java.scmu.data.BoardDAO;
import main.java.scmu.db.BoardRepository;

public class MemoryDBBoardRepository extends MemoryRepository<BoardDAO> implements BoardRepository  {
    @Override
    public String getId(BoardDAO entity) {
        return entity.getId();
    }
}
