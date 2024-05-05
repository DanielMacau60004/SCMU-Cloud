package main.java.scmu.srv;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import main.java.scmu.data.Data;
import main.java.scmu.data.Status;
import main.java.scmu.services.DataService;
import main.java.scmu.services.StatusService;

import java.util.List;

@Path("/status")
public class StatusResource {

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> update(@PathParam("id") String id, @QueryParam("start") long start,
                               @QueryParam("end") long end) {
        try {
            return StatusService.list(id, start, end);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

}
