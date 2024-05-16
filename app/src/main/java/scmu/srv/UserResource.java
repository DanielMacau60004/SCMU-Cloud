package main.java.scmu.srv;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import main.java.scmu.data.Board;
import main.java.scmu.data.User;
import main.java.scmu.services.BoardService;
import main.java.scmu.services.UserService;

import java.util.List;

@Path("/users")
public class UserResource {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User getOrCreate(User user) {
        try {
            return UserService.getOrCreate(user);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@PathParam("id") String id, User user) {
        try {
            return UserService.update(id, user);
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
    public Object delete(@PathParam("id") String id) {
        try {
            return UserService.delete(id);
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


}
