package main.java.scmu.db.comosdb;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.models.PartitionKey;
import main.java.scmu.data.BoardDAO;
import main.java.scmu.data.User;
import main.java.scmu.data.UserDAO;
import main.java.scmu.db.BoardRepository;
import main.java.scmu.db.UserRepository;

public class CosmosDBUserRepository extends CosmosRepository<UserDAO> implements UserRepository {

    public CosmosDBUserRepository(CosmosClient client, String databaseName) {
        super(client, databaseName, "users", UserDAO.class);
    }

    @Override
    public String getId(UserDAO entity) {
        return entity.getId();
    }

    @Override
    public PartitionKey getPartitionKey(UserDAO entity) {
        return new PartitionKey(entity.getId());
    }

}
