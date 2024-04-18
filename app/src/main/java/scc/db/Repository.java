package main.java.scc.db;

import java.util.List;

public interface Repository<T> {

    Object delete(T entity);

    T create(T entity);

    T put(T entity);

    T get(String id);

    List<T> list();

    List<T> listByPartition(String partitionKey);

}
