package mytube.boundary;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import mytube.entity.ServerInfo;



@Stateless
@Path("servers")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

public class ServerInfoResources {
    
    @Inject
    ServerInfos servers;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("all")
    public JsonArray findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<ServerInfo> all = this.servers.findAll();
        all.stream()
                .map(m -> m.toJson()
                )
                .forEach(list::add);
        return list.build();
    }

    @GET
    @Path("/")
    public JsonArray findByServerInfoId(@QueryParam("id") Long id) {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<ServerInfo> all = this.servers.findByServerInfoId(id);
        all.stream()
                .map(m -> m.toJson()
                )
                .forEach(list::add);
        return list.build();
    }

    @POST
    @Path("new")
    public Long save(@Valid ServerInfo serverInfo) {
        return this.servers.create(serverInfo);
        //return Response.ok().build();
    }
    
    @PUT
    @Path("modify")
    public long modify(@Valid ServerInfo serverInfo) {
        if(this.servers.findByServerInfoId(serverInfo.getId()).size()==0){
            return this.servers.create(serverInfo);
        }else{
        //this.servers.remove(serverInfo.getId());
         this.servers.modify(serverInfo);
         return 0;
        }
        //return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        this.servers.remove(id);
        return Response.ok().build();
    }
}
