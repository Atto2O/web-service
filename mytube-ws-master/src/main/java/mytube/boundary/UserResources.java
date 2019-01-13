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
import mytube.entity.User;


@Stateless
@Path("users")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserResources {

    @Inject
    Users users;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("all")
    public JsonArray findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<User> all = this.users.findAll();
        all.stream()
                .map(m -> m.toJson()
                )
                .forEach(list::add);
        return list.build();
    }

    @GET
    @Path("/name")
    public JsonObject findByUsername(@QueryParam("name") String name) {
        JsonArrayBuilder list = Json.createArrayBuilder();
        User all = this.users.findByUsername(name);
       
        return all.toJson();
    }

    @POST
    @Path("new")
    public long save(@Valid User user) {
        if(this.users.findByUsername(user.getName())!=null){
            return 0;
        }else{
            return this.users.create(user);
        //return Response.ok().build();
        }
        
    }
    
    @PUT
    @Path("modify")
    public Response modify(@Valid User user) {
        //this.users.remove(user.getId());
        
        //this.users.create(user);
        /*if(!this.users.findByUsername(user.getName()).isEmpty()){     
            this.users.modify(user);
        }*/
        this.users.modify(user);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        this.users.remove(id);
        return Response.ok().build();
    }
}
