package main.java.scc.db.comosdb;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import main.java.scc.db.*;

public class CosmosDBLayer implements DBLayerRepository {
    //TODO SETUP THE KEYS MANUALLY
    private static final String CONNECTION_URL = "URL";
    private static final String DB_KEY = "KEY";
    private static final String DB_NAME = "NAME";


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

    public CosmosDBLayer(CosmosClient client) {
        boards = new CosmosDBBoardRepository(client, DB_NAME);
        data = new CosmosDBDataRepository(client, DB_NAME);
    }

    public BoardRepository getBoardsRepository() {
        return boards;
    }

    public DataRepository getDataRepository() {
        return data;
    }

    public void close() {
        boards.close();
        data.close();
    }
}
