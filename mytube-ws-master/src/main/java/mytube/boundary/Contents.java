package mytube.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mytube.entity.Content;

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

    public List<Content> findContentLike(String word) {
        Query query = this.em.createQuery("select c from Content c where c.description = :word");
        query.setParameter("word", word);
        //Fer que es consulti amb un like
        return query.getResultList();
    }
    
    public void create(Content content) {
        this.em.persist(content);
    }

    public void remove(Long id) {
        Content content = findById(id);
        this.em.remove(content);
    }
}
