package main.java.scmu.db.comosdb;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.models.*;
import main.java.scmu.data.DataDAO;
import main.java.scmu.data.StatusDAO;
import main.java.scmu.db.DataRepository;
import main.java.scmu.db.StatusRepository;

import java.util.ArrayList;
import java.util.List;

public class CosmosDBStatusRepository extends CosmosRepository<StatusDAO> implements StatusRepository {

    private static final String LIST_QUERY = "SELECT * FROM status WHERE status.board =\"%s\" and status.t >= %d and status.t <= %d";

    public CosmosDBStatusRepository(CosmosClient client, String databaseName) {
        super(client, databaseName, "status", StatusDAO.class);
    }

    @Override
    public String getId(StatusDAO entity) {
        return entity.getId();
    }

    @Override
    public PartitionKey getPartitionKey(StatusDAO entity) {
        return new PartitionKey(entity.getId());
    }

    @Override
    public void addBulk(List<StatusDAO> status) {
        init();
        List<CosmosItemOperation> bulkOperations = new ArrayList<>();
        status.forEach(s -> bulkOperations.add(CosmosBulkOperations.getCreateItemOperation(s, getPartitionKey(s))));
        container.executeBulkOperations(bulkOperations, new CosmosBulkExecutionOptions());
    }

    @Override
    public void removeBulk(List<StatusDAO> data) {
        init();
        List<CosmosItemOperation> bulkOperations = new ArrayList<>();
        data.forEach(d-> bulkOperations.add(CosmosBulkOperations.getDeleteItemOperation(getId(d), getPartitionKey(d))));
        container.executeBulkOperations(bulkOperations, new CosmosBulkExecutionOptions());
    }

    @Override
    public List<StatusDAO> listByIntervalLocation(String id, long start, long end) {
        init();
        return container.queryItems(
                LIST_QUERY.formatted(id, start, end),
                new CosmosQueryRequestOptions(), StatusDAO.class).stream().toList();
    }

}
