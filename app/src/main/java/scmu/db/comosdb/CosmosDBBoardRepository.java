package main.java.scmu.db.comosdb;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.models.PartitionKey;
import main.java.scmu.data.BoardDAO;
import main.java.scmu.db.BoardRepository;

public class CosmosDBBoardRepository extends CosmosRepository<BoardDAO> implements BoardRepository {

    public CosmosDBBoardRepository(CosmosClient client, String databaseName) {
        super(client, databaseName, "boards", BoardDAO.class);
    }

    @Override
    public String getId(BoardDAO entity) {
        return entity.getId();
    }

    @Override
    public PartitionKey getPartitionKey(BoardDAO entity) {
        return new PartitionKey(entity.getId());
    }

}
