package mytube.entity;

import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rce2
 */
@Entity
@Table(name = "content")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = Content.FIND_ALL, query = "select g from Content g")
public class Content {
    public static final String FIND_ALL = "findAllContents";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    protected String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    @NotNull
    protected Long serverID;
    
    @NotNull
    protected String description;

    @NotNull
    protected String user;

    public Long getServerID() {
        return serverID;
    }

    public void setServerID(Long serverID) {
        this.serverID = serverID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    
    @NotNull
    protected Boolean state;
    
    @NotNull
    protected ArrayList<String> tags;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
    
    @Override
    public String toString() {
        return new StringBuilder("Content [")
                .append(id).append(", ")
                .append(fileName).append(", ")
                .append(serverID).append(", ")
                .append(user).append(", ")
                .append(description).append(", ")
                .append(state).append(", ")
                .append(tags).append("]").toString();
    }

    public JsonObject toJson() {
        
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        JsonArrayBuilder plnArrBld = Json.createArrayBuilder();
        for( String s:this.tags){
             plnArrBld.add(s);
        }
        
        JsonArray arr = plnArrBld.build();
        jsonBuilder.add("id", this.id);
        jsonBuilder.add("fileName", this.fileName);
        jsonBuilder.add("serverID", this.serverID);
        jsonBuilder.add("user", this.user);
        jsonBuilder.add("description", this.description);
        jsonBuilder.add("state", this.state);
        jsonBuilder.add("tags", arr);
        return jsonBuilder.build();
        
     
    }
}
