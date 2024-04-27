package main.java.scmu.db.comosdb;

import java.util.List;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.*;

import main.java.scmu.db.Repository;

import javax.ws.rs.NotFoundException;

public abstract class CosmosRepository<T> implements Repository<T> {

    private static final int ERROR_CODE = 300;
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

        return response.getItem();
    }

    public T create(T entity) {
        init();

        CosmosItemResponse<T> response = container.createItem(entity, getPartitionKey(entity), new CosmosItemRequestOptions());

        if (response.getStatusCode() >= ERROR_CODE)
            throw new NotFoundException();

        return response.getItem();
    }

    public T put(T entity) {
        init();

        CosmosItemResponse<T> response = container.replaceItem(entity, getId(entity), getPartitionKey(entity), new CosmosItemRequestOptions());

        if (response.getStatusCode() >= ERROR_CODE)
            throw new NotFoundException();

        return response.getItem();
    }

    public T get(String id) {
        init();

        T obj = container
                .queryItems(GET_QUERY.formatted(containerName, containerName, id), new CosmosQueryRequestOptions(), clazz)
                .stream().findFirst().orElse(null);

        if (obj == null)
            throw new NotFoundException();

        return obj;
    }

    public List<T> list() {
        init();

        return container.queryItems(SELECT_QUERY.formatted(containerName), new CosmosQueryRequestOptions(), clazz).stream().toList();
    }

    public List<T> listByPartition(String partitionKey) {
        init();
        return container.readAllItems(new PartitionKey(partitionKey), new CosmosQueryRequestOptions(), clazz).stream().toList();
    }

    public void close() {
        client.close();
    }
}
