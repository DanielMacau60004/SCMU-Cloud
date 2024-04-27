package main.java.scmu.srv;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import main.java.scmu.data.Data;
import main.java.scmu.services.DataService;

import java.util.List;

@Path("/data")
public class DataResource {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Data create(Data data) {
        try {
            return DataService.create(data);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Data> update(@PathParam("id") String id, @QueryParam("start") long start,
                             @QueryParam("end") long end) {
        try {
            return DataService.list(id, start, end);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

}
