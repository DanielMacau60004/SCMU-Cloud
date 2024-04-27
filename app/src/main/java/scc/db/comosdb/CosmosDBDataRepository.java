package main.java.scc.db.comosdb;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import main.java.scc.data.DataDAO;
import main.java.scc.db.DataRepository;

import java.util.List;

public class CosmosDBDataRepository extends CosmosRepository<DataDAO> implements DataRepository {

    private static final String LIST_QUERY = "SELECT * FROM data WHERE data.id =\"%s\" and data.t >= %d and data.t <= %d";

    public CosmosDBDataRepository(CosmosClient client, String databaseName) {
        super(client, databaseName, "data", DataDAO.class);
    }

    @Override
    public String getId(DataDAO entity) {
        return entity.getId();
    }

    @Override
    public PartitionKey getPartitionKey(DataDAO entity) {
        return new PartitionKey(entity.getId());
    }

    @Override
    public List<DataDAO> listByIntervalLocation(String id, long start, long end) {
        init();

        return container.queryItems(
                LIST_QUERY.formatted(id, start, end),
                new CosmosQueryRequestOptions(), DataDAO.class).stream().toList();
    }

}
