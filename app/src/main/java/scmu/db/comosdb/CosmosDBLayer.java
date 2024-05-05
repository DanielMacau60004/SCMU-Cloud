package main.java.scmu.db.comosdb;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import main.java.scmu.db.*;

public class CosmosDBLayer implements DBLayerRepository {
    private static final String CONNECTION_URL = "https://scmu-db.documents.azure.com:443/";
    private static final String DB_KEY = "S1f35gjDDIWJiAmeVn0AjOe3Wnnrw2BVnEe50U6EcXitZgQJMjCjrjHCfDRXwuge6iCdgR8co3RMACDbAgpk2g==";
    private static final String DB_NAME = "scmu";


    private static CosmosDBLayer instance;

    public static synchronized CosmosDBLayer getInstance() {
        if (instance != null)
            return instance;

        CosmosClient client = new CosmosClientBuilder()
                .endpoint(CONNECTION_URL)
                .key(DB_KEY)
                // .directMode()
                .gatewayMode()
                // replace by .directMode() for better performance
                .consistencyLevel(ConsistencyLevel.SESSION)
                .connectionSharingAcrossClientsEnabled(true)
                .contentResponseOnWriteEnabled(true)
                .buildClient();
        instance = new CosmosDBLayer(client);
        return instance;

    }

    private final CosmosDBBoardRepository boards;
    private final CosmosDBDataRepository data;
    private final CosmosDBStatusRepository status;
    private final CosmosDBUserRepository users;

    public CosmosDBLayer(CosmosClient client) {
        boards = new CosmosDBBoardRepository(client, DB_NAME);
        data = new CosmosDBDataRepository(client, DB_NAME);
        status = new CosmosDBStatusRepository(client, DB_NAME);
        users = new CosmosDBUserRepository(client, DB_NAME);
    }

    public BoardRepository getBoardsRepository() {
        return boards;
    }

    public DataRepository getDataRepository() {
        return data;
    }

    public StatusRepository getStatusRepository() {
        return status;
    }

    public UserRepository getUserRepository() {
        return users;
    }

    public void close() {
        boards.close();
        data.close();
        status.close();
        users.close();
    }
}
