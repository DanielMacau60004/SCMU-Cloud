package main.java.scmu.db.memory;

import main.java.scmu.db.Repository;
import org.apache.commons.lang3.NotImplementedException;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MemoryRepository<T> implements Repository<T> {

    protected final Map<String, T> storage;

    public abstract String getId(T entity);


    public MemoryRepository() {
        storage = new HashMap<>();
    }


    public Object delete(T entity) {
        T removedEntity = storage.remove(getId(entity));

        if (removedEntity == null)
            throw new NotFoundException();

        return removedEntity;
    }

    public T create(T entity) {
        if (storage.containsKey(getId(entity)))
            throw new NotFoundException();

        storage.put(getId(entity), entity);
        return entity;
    }

    public T put(T entity) {
        if (!storage.containsKey(getId(entity)))
            throw new NotFoundException();

        return storage.put(getId(entity), entity);
    }

    public T get(String id) {
        T entity = storage.get(id);
        if (entity == null)
            throw new NotFoundException();

        return entity;
    }

    public List<T> list() {
        return storage.values().stream().toList();
    }

    public List<T> listByPartition(String partitionKey) {
        throw new NotImplementedException();
    }

}
