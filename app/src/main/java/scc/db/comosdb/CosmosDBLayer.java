package main.java.scc.db.comosdb;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import main.java.scc.db.*;
import main.java.scc.utils.Properties;

public class CosmosDBLayer implements DBLayerRepository {
    //TODO SETUP THE KEYS MANUALLY
    private static final String CONNECTION_URL = System.getenv(Properties.COSMOSDB_URL);
    private static final String DB_KEY = System.getenv(Properties.COSMOSDB_KEY);
    private static final String DB_NAME = System.getenv(Properties.COSMOSDB_DATABASE);


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

    private final CosmosDBUserRepository users;
    private final CosmosDBHouseRepository houses;
    private final CosmosDBRentalRepository rentals;
    private final CosmosDBQuestionRepository questions;
    private final CosmosDBPromotionRepository promotion;

    public CosmosDBLayer(CosmosClient client) {
        users = new CosmosDBUserRepository(client, DB_NAME);
        houses = new CosmosDBHouseRepository(client, DB_NAME);
        rentals = new CosmosDBRentalRepository(client, DB_NAME);
        questions = new CosmosDBQuestionRepository(client, DB_NAME);
        promotion = new CosmosDBPromotionRepository(client,DB_NAME);
    }

    public UserRepository getUsersRepository() {
        return users;
    }

    public HouseRepository getHousesRepository() {
        return houses;
    }

    public RentalRepository getRentalsRepository() {
        return rentals;
    }

    public QuestionRepository getQuestionsRepository() {
        return questions;
    }

    public PromotionRepository getPromotionRepository() {
        return promotion;
    }

    public void close() {
        users.close();
        houses.close();
        rentals.close();
        questions.close();
        promotion.close();

    }
}
