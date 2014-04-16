/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class EmailFacade extends AbstractFacade<Email> implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(Email.class);

    public EmailFacade(Class<Email> entityClass) {
        super(entityClass);
    }

    public Email findEmail(String email) {
        try {
            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("Email.findByEmail", Email.class);
            q.setParameter("email", email);
            return (Email) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error al buscar el Email", e.getMessage());
            return null;
        }

    }

    public List<Email> findListEmailxDireaccion(Direccion direccion) {
        List<Email> email = null;
        try {
            EntityManager emTelefono = getEntityManager();
            String query = "SELECT e FROM Email e WHERE  e.direccionId = :direccion";
            Query q = emTelefono.createQuery(query, Email.class);
            q.setParameter("direccion", direccion);
            email = (List<Email>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando los Correos: " + e.getLocalizedMessage());
        }
        return email;
    }

}
