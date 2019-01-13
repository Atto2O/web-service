/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytube.boundary;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author user
 */
public class ContentsPack {

    private Long id;
    private Long serverID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerID() {
        return serverID;
    }

    public void setServerID(Long serverID) {
        this.serverID = serverID;
    }
    
    public ContentsPack(Long id, Long serverId) {
        this.id = id;
        this.serverID = serverId;
    }
   
  @Override
    public String toString() {
        return new StringBuilder("ContentsPack [")
                .append(id).append(", ")
                .append(serverID).append("]").toString();
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("serverID", this.serverID)
                .build();
    }
}
