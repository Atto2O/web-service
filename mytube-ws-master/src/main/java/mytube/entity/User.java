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
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("name", this.name)
                .add("password", this.password)
                .add("subscriptions", String.valueOf(this.subscriptions)
                )
                .build();
    }
}
