package main.java.scc.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    public static final String BLOB_KEY = "BlobStoreConnection";
    public static final String COSMOSDB_KEY = "COSMOSDB_KEY";
    public static final String COSMOSDB_URL = "COSMOSDB_URL";
    public static final String COSMOSDB_DATABASE = "COSMOSDB_DATABASE";
    public static final String COSMOSDB_DB_CONNECTION = "AzureCosmosDBConnection";
    public static final String REDIS = "REDIS";
    public static final String REDIS_KEY = "REDIS_KEY";
    public static final String REDIS_URL = "REDIS_URL";
    public static final String MYSQL = "MYSQL";

    public static final String PROPS_FILE = "azurekeys.props";
    private static java.util.Properties props;

    public static synchronized java.util.Properties getProperties() {
        if (props == null) {
            props = new java.util.Properties();
            try {
                props.load(new FileInputStream(PROPS_FILE));
            } catch (IOException e) {
                // do nothing
            }
        }
        return props;
    }

}
