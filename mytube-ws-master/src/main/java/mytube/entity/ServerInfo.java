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
@Table(name = "ServerInfo")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = ServerInfo.FIND_ALL, query = "select g from ServerInfo g")

public class ServerInfo {
    public static final String FIND_ALL = "findAllServerInfo";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    protected Integer port;

    @NotNull
    protected String ip;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String toString() {
        return new StringBuilder("ServerInfo [")
                .append(id).append(", ")
                .append(port).append(", ")
                .append(ip).append("]").toString();
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("port", this.port)
                .add("ip", this.ip)          
                .build();
    }
    
}
