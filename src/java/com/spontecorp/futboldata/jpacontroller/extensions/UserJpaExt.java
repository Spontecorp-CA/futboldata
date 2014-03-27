package com.spontecorp.futboldata.jpacontroller.extensions;


import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.jpacontroller.UserJpaController;
import com.spontecorp.futboldata.utilities.Constantes;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class UserJpaExt extends UserJpaController{
    private EntityManagerFactory emf;
    
    private static final Logger logger = LoggerFactory.getLogger(UserJpaExt.class);

    public UserJpaExt(EntityManagerFactory emf) {
        super(emf);
        this.emf = emf;
    }

    public User findUsuario(String user) {
        EntityManager em = emf.createEntityManager();
        User usuario = null;
        try {
            Query query = em.createNamedQuery("User.findByUsuario", User.class);
            query.setParameter("usuario", user);
            usuario = (User) query.getSingleResult();
        } catch(NoResultException e){
            // Do nothing
        }finally {
            em.close();
            return usuario;            
        }
    }
    
    public List<User> findActiveUsers(){
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("User.findByStatus", User.class);
            query.setParameter("status", Constantes.ACTIVO);
            return (List<User>) query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
