package mytube.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import mytube.entity.Content;

/**
 *
 * @author rce2
 */
@Stateless
@Path("contents")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ContentResources {
    @Inject
    Contents contents;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("all")
    public JsonArray findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Content> all = this.contents.findAll();
        all.stream()
                .map(m -> m.toJson()
                )
                .forEach(list::add);
        return list.build();
    }

    @GET
    @Path("/")
    public JsonArray findByLocation(@QueryParam("location") String location) {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Content> all = this.contents.findByLocation(location);
        all.stream()
                .map(m -> m.toJson()
                )
                .forEach(list::add);
        return list.build();
    }
    
    @GET
    @Path("/descriptionLike")
    public JsonArray findContentLike(@QueryParam("word") String word) {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Content> all = this.contents.findContentLike(word);
        all.stream()
                .map(m -> m.toJson()
                )
                .forEach(list::add);
        return list.build();
    }

    @POST
    public Response save(@Valid Content content) {
        this.contents.create(content);
        return Response.ok().build();
    }
    
    @PUT
    public Response modify(@Valid Content content) {
        this.contents.create(content);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        this.contents.remove(id);
        return Response.ok().build();
    }
}
