package main.java.scmu.srv;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import main.java.scmu.data.Board;
import main.java.scmu.data.BoardInfo;
import main.java.scmu.data.Data;
import main.java.scmu.services.BoardService;
import main.java.scmu.services.DataService;

import java.util.List;

@Path("/boards")
public class BoardResource {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Board register(Board board) {
        try {
            return BoardService.register(board);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/{id}/arduino")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Board updateArduino(@PathParam("id") String id, Board board) {
        try {
            return BoardService.updateArduino(id, board);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/{id}/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Board updateUser(@PathParam("id") String id, Board board) {
        try {
            return BoardService.updateUser(id, board);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/{id}/request")
    @Produces(MediaType.APPLICATION_JSON)
    public Board request(@PathParam("id") String id, @QueryParam("request") int request) {
        try {
            return BoardService.request(id, request);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Board get(@PathParam("id") String id) {
        try {
            return BoardService.get(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/{id}/pwd")
    @Produces(MediaType.APPLICATION_JSON)
    public Board getPassword(@PathParam("id") String id, @QueryParam("pwd") String pwd) {
        try {
            return BoardService.get(id, pwd);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object delete(@PathParam("id") String id) {
        try {
            return BoardService.delete(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/{id}/reset")
    @Produces(MediaType.APPLICATION_JSON)
    public Board reset(@PathParam("id") String id) {
        try {
            return BoardService.reset(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/{id}/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BoardInfo update(@PathParam("id") String id, @QueryParam("days") int days) {
        try {
            return BoardService.boardInfo(id, days);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Board> list() {
        try {
            return BoardService.list();
        } catch (Exception e) {
            throw new ServiceUnavailableException();
        }

    }


}
