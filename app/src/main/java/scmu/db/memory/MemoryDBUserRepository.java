package main.java.scmu.db.memory;

import main.java.scmu.data.BoardDAO;
import main.java.scmu.data.UserDAO;
import main.java.scmu.db.BoardRepository;
import main.java.scmu.db.UserRepository;

public class MemoryDBUserRepository extends MemoryRepository<UserDAO> implements UserRepository {
    @Override
    public String getId(UserDAO entity) {
        return entity.getId();
    }
}
