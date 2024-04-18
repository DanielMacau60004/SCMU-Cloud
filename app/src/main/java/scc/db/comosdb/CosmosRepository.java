package main.java.scc.db.comosdb;

import java.util.List;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.*;

import main.java.scc.cache.Cache;
import main.java.scc.db.Repository;

import javax.ws.rs.NotFoundException;

public abstract class CosmosRepository<T> implements Repository<T> {

    private static final int ERROR_CODE = 300;

    private static final String CACHE_GET_KEY = "Get-{%s}-{%s}";
    private static final long CACHE_GET_TIME = 30;
    private static final String CACHE_LIST_KEY = "List-{%s}";
    private static final long CACHE_LIST_TIME = 60;

    private static final String CACHE_PARTITION_LIST_KEY = "List-{%s}-{%s}";
    private static final long CACHE_PARTITION_LIST_TIME = 60;
    private static final String GET_QUERY = "SELECT * FROM %s WHERE %s.id=\"%s\"";

    private static final String SELECT_QUERY = "SELECT * FROM %s";

    private final CosmosClient client;
    protected CosmosDatabase db;
    protected CosmosContainer container;
    private final String databaseName;
    private final String containerName;

    private final Class<T> clazz;

    public abstract String getId(T entity);
    public abstract PartitionKey getPartitionKey(T entity);

    public CosmosRepository(CosmosClient client, String databaseName, String containerName, Class<T> clazz) {
        this.client = client;
        this.databaseName = databaseName;
        this.containerName = containerName;
        this.clazz = clazz;
    }

    protected synchronized void init() {
        if (db != null)
            return;
        db = client.getDatabase(databaseName);
        container = db.getContainer(containerName);
    }

    public Object delete(T entity) {
        init();

        CosmosItemResponse<Object> response = container.deleteItem(getId(entity), getPartitionKey(entity), new CosmosItemRequestOptions());

        if (response.getStatusCode() >= ERROR_CODE)
            throw new NotFoundException();

        Cache.del(CACHE_GET_KEY.formatted(containerName, getId(entity)));
        return response.getItem();
    }

    public T create(T entity) {
        init();

        CosmosItemResponse<T> response = container.createItem(entity, getPartitionKey(entity), new CosmosItemRequestOptions());

        if (response.getStatusCode() >= ERROR_CODE)
            throw new NotFoundException();

        Cache.set(CACHE_GET_KEY.formatted(containerName, getId(entity)), entity, CACHE_GET_TIME);
        return response.getItem();
    }

    public T put(T entity) {
        init();

        CosmosItemResponse<T> response = container.replaceItem(entity, getId(entity), getPartitionKey(entity), new CosmosItemRequestOptions());

        if (response.getStatusCode() >= ERROR_CODE)
            throw new NotFoundException();

        Cache.set(CACHE_GET_KEY.formatted(containerName, getId(entity)), entity, CACHE_GET_TIME);
        return response.getItem();
    }

    public T get(String id) {
        init();

        T obj = Cache.get(CACHE_GET_KEY.formatted(containerName, id), clazz, CACHE_GET_TIME, () -> container
                .queryItems(GET_QUERY.formatted(containerName, containerName, id), new CosmosQueryRequestOptions(), clazz)
                .stream().findFirst().orElse(null));

        if (obj == null)
            throw new NotFoundException();

        return obj;
    }

    public List<T> list() {
        init();

        return Cache.lrange(CACHE_LIST_KEY.formatted(containerName), 0, -1, clazz, CACHE_LIST_TIME, () -> container
                .queryItems(SELECT_QUERY.formatted(containerName), new CosmosQueryRequestOptions(), clazz).stream().toList());
    }

    public List<T> listByPartition(String partitionKey) {
        init();
        return Cache.lrange(CACHE_PARTITION_LIST_KEY.formatted(containerName, partitionKey), 0, -1, clazz, CACHE_PARTITION_LIST_TIME, () ->
                container.readAllItems(new PartitionKey(partitionKey), new CosmosQueryRequestOptions(), clazz).stream().toList());
    }

    public void close() {
        client.close();
    }
}
