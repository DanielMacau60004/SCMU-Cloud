package main.java.scc.srv;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import main.java.scc.cache.RedisCache;
import main.java.scc.data.User;
import main.java.scc.services.UserService;
import main.java.scc.utils.Properties;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user) {
        try {
            return UserService.create(user);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@CookieParam("scc:session") Cookie session, @PathParam("id") String id, User user) {
        try {
            return UserService.update(session, id, user);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("id") String id) {
        try {
            return UserService.get(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object delete(@CookieParam("scc:session") Cookie session, @PathParam("id") String id) {
        try {
            return UserService.delete(session, id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        try {
            return UserService.list();
        } catch (Exception e) {
            throw new ServiceUnavailableException();
        }

    }


    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> test() {
        Map<String,String> str = new HashMap<>();

        try (Jedis jedis = RedisCache.getCachePool().getResource()) {
            jedis.set("key", "a");
            str.put("REDIS_ERROR", "NONE");
        } catch (Exception e) {
            str.put("REDIS_ERROR", e.getMessage());
        }


        str.put("REDIS", System.getenv(Properties.REDIS));
        str.put("COSMOSDB_DATABASE", System.getenv(Properties.COSMOSDB_DATABASE));
        str.put("COSMOSDB_KEY", System.getenv(Properties.COSMOSDB_KEY));
        str.put("COSMOSDB_URL", System.getenv(Properties.COSMOSDB_URL));
        str.put("COSMOSDB_DB_CONNECTION", System.getenv(Properties.COSMOSDB_DB_CONNECTION));

        return str;
    }

}
