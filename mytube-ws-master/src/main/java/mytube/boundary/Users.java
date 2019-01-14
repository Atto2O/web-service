package mytube.boundary;

import mytube.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;


@Stateless
public class Users {
    @PersistenceContext(name = "mytube")
    EntityManager em;

    public List<User> findAll() {
        return this.em.createNamedQuery(User.FIND_ALL).getResultList();
    }
    
    public User findById(Long id){
        return this.em.find(User.class, id);
    }

    public User findByUsername(String name) {
        Query query = this.em.createQuery("select u from User u where u.name = :name");
        query.setParameter("name", name);
        if(query.getResultList().size()!=0){
         return (User)query.getResultList().get(0);   
        }else{
            return null;
        }
        
    }

    public Long create(User user) {
        this.em.persist(user);
        em.flush();
        return user.getId();
    }
    
    public void modify(User user){
        this.em.merge(user);
    }
    public void remove(Long id) {
        User user = findById(id);
        this.em.remove(user);
    }
}
