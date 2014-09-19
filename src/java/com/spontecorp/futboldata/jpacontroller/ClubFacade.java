/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class ClubFacade extends AbstractFacade<Club> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ClubFacade.class);

    public ClubFacade() {
        super(Club.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Club findClub(String nombre ,int organizacion) {
        EntityManager em = getEntityManager();
        Club club = null;
        try {
            String q = "SELECT c FROM Club c WHERE c.organizacionId =:organizacion "
                    + "AND c.nombre =:nombre";
            Query query = em.createQuery(q, Club.class);
            query.setParameter("nombre", nombre);
            query.setParameter("organizacion", organizacion);
            club = (Club) query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Problema al buscar club", e.getMessage());
        } finally {
            em.close();
        }
        return club;
    }

    public List<Club> findAll(int organizacion) {
        List<Club> clubs = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT c FROM Club c WHERE c.organizacionId =:organizacion";
                Query query = em.createQuery(q, Club.class);
                query.setParameter("organizacion", organizacion);
                clubs = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar club", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            clubs = findAll();
        }
        return clubs;
    }
}
