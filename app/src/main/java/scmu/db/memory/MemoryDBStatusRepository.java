package main.java.scmu.db.memory;

import main.java.scmu.data.DataDAO;
import main.java.scmu.data.StatusDAO;
import main.java.scmu.db.DataRepository;
import main.java.scmu.db.StatusRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MemoryDBStatusRepository extends MemoryRepository<StatusDAO> implements StatusRepository {

    @Override
    public String getId(StatusDAO entity) {
        return entity.getId() + entity.getT();
    }

    @Override
    public void addBulk(List<StatusDAO> status) {
        status.forEach(s -> storage.put(getId(s), s));
    }

    @Override
    public void removeBulk(List<StatusDAO> data) {
        data.forEach(d -> storage.remove(getId(d), d));
    }

    @Override
    public List<StatusDAO> listByIntervalLocation(String id, long start, long end) {
        return storage.values().stream().filter(s -> s.getId().equals(id) && s.getT() >= start && s.getT() <= end)
                .collect(Collectors.toList());
    }


}
