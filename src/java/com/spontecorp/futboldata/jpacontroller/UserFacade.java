/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.utilities.Constantes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jgcastillo
 */
public class UserFacade extends AbstractFacade<User>{

    public UserFacade(Class<User> entityClass) {
        super(entityClass);
    }
 
    public User findUsuario(String user) {
        EntityManager em = getEntityManager();
        User usuario = null;
        try {
            Query query = em.createNamedQuery("User.findByUsuario", User.class);
            query.setParameter("usuario", user);
            usuario = (User) query.getSingleResult();
        } catch (NoResultException e) {
            // Do nothing
        } finally {
            em.close();
        }
        return usuario;
    }

    public List<User> findActiveUsers() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("User.findByStatus", User.class);
            query.setParameter("status", Constantes.ACTIVO);
            return (List<User>) query.getResultList();
        } finally {
            em.close();
        }
    }
}
