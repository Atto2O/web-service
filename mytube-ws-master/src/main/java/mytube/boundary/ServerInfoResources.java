package mytube.boundary;


import java.util.ArrayList;
import java.util.Iterator;
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
    
    @POST
    @Path("/byID")
    public JsonArray findByServerInfoId( JsonObject id) {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<ServerInfo> all = new ArrayList<ServerInfo>();
        
        
        ArrayList<Integer> list1 = new ArrayList<Integer>();     
        JsonArray jsonArray = (JsonArray) id.get("list");
        
        if (jsonArray != null) { 
           int len = jsonArray.size();
           for (int i=0;i<len;i++){ 
            list1.add(Integer.parseInt(jsonArray.get(i).toString()));
           } 
        }      
        
        for (Iterator<Integer> i = list1.iterator(); i.hasNext();) {
            Integer item = i.next();
            if(this.servers.findByServerInfoId(item).size()!=0){
                ServerInfo obj = this.servers.findByServerInfoId(item).get(0);
                all.add(obj);
            }
            
            
        }
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
    }
    
    @PUT
    @Path("modify")
    public long modify(@Valid ServerInfo serverInfo) {
        if(this.servers.findByServerInfoId(serverInfo.getId()).size()==0){
            this.servers.modify(serverInfo);
            return 1;
        }else{
         this.servers.modify(serverInfo);
         return 0;
        }
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        this.servers.remove(id);
        return Response.ok().build();
    }
}
