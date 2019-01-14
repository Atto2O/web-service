package mytube.entity;

import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

@Entity
@Table(name = "user_mytube")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = User.FIND_ALL, query = "select g from User g")
public class User {

    public static final String FIND_ALL = "findAllUsers";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    protected String name;

    @NotNull
    protected String password;

    protected ArrayList<String> subscriptions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<String> Subscriptions) {
        this.subscriptions = Subscriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringBuilder("User [")
                .append(id).append(", ")
                .append(name).append(", ")
                .append(password).append(", ")
                .append(subscriptions).append("]").toString();
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        JsonArrayBuilder plnArrBld = Json.createArrayBuilder();
        for( String s:this.subscriptions){
             plnArrBld.add(s);
        }
        
        JsonArray arr = plnArrBld.build();
        jsonBuilder.add("id", this.id);
        jsonBuilder.add("name", this.name);
        jsonBuilder.add("password", this.password);
        jsonBuilder.add("subscriptions", arr);
        
        return jsonBuilder.build();

    }
}
