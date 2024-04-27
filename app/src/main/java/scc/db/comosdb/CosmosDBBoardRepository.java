package main.java.scc.db.comosdb;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.models.PartitionKey;
import main.java.scc.data.BoardDAO;
import main.java.scc.db.BoardRepository;

public class CosmosDBBoardRepository extends CosmosRepository<BoardDAO> implements BoardRepository {

    public CosmosDBBoardRepository(CosmosClient client, String databaseName) {
        super(client, databaseName, "board", BoardDAO.class);
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
