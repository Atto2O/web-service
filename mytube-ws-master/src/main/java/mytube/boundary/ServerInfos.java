/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytube.boundary;


import mytube.entity.ServerInfo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;

public class ServerInfos {
    @PersistenceContext(name = "mytube")
    EntityManager em;

    public List<ServerInfo> findAll() {
        return this.em.createNamedQuery(ServerInfo.FIND_ALL).getResultList();
    }
    
    public ServerInfo findById(Long id){
        return this.em.find(ServerInfo.class, id);
    }

    public List<ServerInfo> findByServerInfoId(long id) {
        Query query = this.em.createQuery("select u from ServerInfo u where u.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public long create(ServerInfo serverInfo) {
        this.em.persist(serverInfo);
        em.flush();
        return serverInfo.getId();
    }
    
    public void modify(ServerInfo serverInfo) {
        this.em.merge(serverInfo);
    }

    public void remove(Long id) {
        ServerInfo user = findById(id);
        this.em.remove(user);
    }
}
