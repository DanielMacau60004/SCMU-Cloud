package main.java.scc.srv;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.core.Application;
import main.java.scc.db.DBLayerRepository;
import main.java.scc.db.comosdb.CosmosDBLayer;

public class MainApplication extends Application {

    public static final DBLayerRepository DB_LAYER = CosmosDBLayer.getInstance();


    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> resources = new HashSet<Class<?>>();

    public MainApplication() {
        singletons.add(new BoardResource());
        singletons.add(new DataResource());
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
