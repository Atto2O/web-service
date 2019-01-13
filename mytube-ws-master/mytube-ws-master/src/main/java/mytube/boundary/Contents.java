package mytube.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mytube.entity.Content;
import mytube.entity.ServerInfo;
import mytube.boundary.ContentsPack;

/**
 *
 * @author rce2
 */
@Stateless
public class Contents {
    @PersistenceContext(name = "mytube")
    EntityManager em;
    
    public List<Content> findAll() {
        return this.em.createNamedQuery(Content.FIND_ALL).getResultList();
    }
    
    public Content findById(Long id){
        return this.em.find(Content.class, id);
    }

    public List<Content> findByLocation(String location) {
        Query query = this.em.createQuery("select c from Content c where c.location = :location");
        query.setParameter("location", location);
        return query.getResultList();
    }

    /*
    public List<Content> findContentLike(String word) {
        Query query = this.em.createQuery("select c from Content c where c.description = :word");
        query.setParameter("word", word);
        //Fer que es consulti amb un like
        return query.getResultList();
    }*/
    
    public List<Content> findContentLike(String word) {
        Query query = this.em.createQuery("select c from Content c where "            
                + "c.fileName LIKE :word  or "
                + "c.user LIKE %:word% or "
                + "c.description LIKE %:word% "
        );//+ "c.tags LIKE :word ");
        
        query.setParameter("word", word);
        return query.getResultList();
    }
    
    public long create(Content content) {
        this.em.persist(content);
        em.flush();
        return content.getId();
    }

    public void modify(Content content) {
        //this.em.refresh(serverInfo);
        //em.flush();
        //return serverInfo.getId();
        this.em.merge(content);
    }
    
    public void remove(Long id) {
        Content content = findById(id);
        this.em.remove(content);
    }
}
