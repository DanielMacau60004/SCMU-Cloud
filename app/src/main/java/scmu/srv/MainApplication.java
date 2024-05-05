package main.java.scmu.srv;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.core.Application;
import main.java.scmu.db.DBLayerRepository;
import main.java.scmu.db.comosdb.CosmosDBLayer;

public class MainApplication extends Application {

    public static final DBLayerRepository DB_LAYER = CosmosDBLayer.getInstance();

    private final Set<Object> singletons = new HashSet<Object>();
    private final Set<Class<?>> resources = new HashSet<Class<?>>();

    public MainApplication() {
        singletons.add(new BoardResource());
        singletons.add(new DataResource());
        singletons.add(new StatusResource());
        singletons.add(new UserResource());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return resources;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
