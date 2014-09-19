/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class UserFacade extends AbstractFacade<User> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(UserFacade.class);

    public UserFacade() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<User> findUser(int organizacion) {
        if (organizacion == 1) {
            return findAll();
        } else {
            List<User> users = null;
            EntityManager em = getEntityManager();
            String query = "SELECT u FROM User u "
                    + "WHERE  u.organizacionId.id = :organizacion";
            Query q = em.createQuery(query, User.class);
            q.setParameter("organizacion", organizacion);
            users = q.getResultList();
            return users;
        }
    }

    public User findUsuario(String user) {
        EntityManager em = getEntityManager();
        User usuario = null;
        try {
            Query query = em.createNamedQuery("User.findByUsuario", User.class);
            query.setParameter("usuario", user);
            usuario = (User) query.getSingleResult();
        } catch (Exception e) {
            // Do nothing
            logger.debug("Lanzó la exception: ", e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return usuario;
    }

    public List<User> findActiveUsers() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("User.findByStatus", User.class);
            query.setParameter("status", Util.ACTIVO);
            return (List<User>) query.getResultList();
        } finally {
            em.close();
        }
    }

}
