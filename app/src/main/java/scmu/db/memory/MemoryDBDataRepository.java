package main.java.scmu.db.memory;

import main.java.scmu.data.DataDAO;
import main.java.scmu.db.DataRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MemoryDBDataRepository extends MemoryRepository<DataDAO> implements DataRepository {

    @Override
    public String getId(DataDAO entity) {
        return entity.getId() + entity.getT();
    }

    @Override
    public List<DataDAO> listByIntervalLocation(String id, long start, long end) {
        return storage.values().stream().filter(d -> d.getId().equals(id) && d.getT() >= start && d.getT() <= end)
                .collect(Collectors.toList());
    }

}
