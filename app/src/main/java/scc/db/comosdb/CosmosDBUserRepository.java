package main.java.scc.db.comosdb;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.models.PartitionKey;
import main.java.scc.data.UserDAO;
import main.java.scc.db.UserRepository;

public class CosmosDBUserRepository extends CosmosRepository<UserDAO> implements UserRepository {

    public static final String ADMIN_ADDRESS = "00000000-0000-0000-0000-000000000000";

    public CosmosDBUserRepository(CosmosClient client, String databaseName) {
        super(client, databaseName, "users", UserDAO.class);
        addDefaultUser();
    }

    private void addDefaultUser() {
        //Add a default user
        init();
        container.upsertItem(new UserDAO(ADMIN_ADDRESS, "admin", "pwd", null));
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
