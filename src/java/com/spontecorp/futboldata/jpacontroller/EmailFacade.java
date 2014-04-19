/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.utilities.Util;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class EmailFacade extends AbstractFacade<Email> {

    private final Logger logger = LoggerFactory.getLogger(EmailFacade.class);

    public EmailFacade() {
        super(Email.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Email findEmail(String email) {
        EntityManager em = getEntityManager();
        Email mail = null;
        try {
            Query q = em.createNamedQuery("Email.findByEmail", Email.class);
            q.setParameter("email", email);
            mail = (Email) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error al buscar el Email", e.getMessage());
        } finally {
            em.close();
        }
        return mail;
    }

    public List<Email> findListEmailxDireaccion(Direccion direccion) {
        List<Email> emailList = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT e FROM Email e WHERE  e.direccionId = :direccion";
            Query q = em.createQuery(query, Email.class);
            q.setParameter("direccion", direccion);
            emailList = (List<Email>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando los Correos: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return emailList;
    }

}
