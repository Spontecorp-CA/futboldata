/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class ClubFacade extends AbstractFacade<Club>{

    private static final Logger logger = LoggerFactory.getLogger(ClubFacade.class);
    
    public ClubFacade() {
        super(Club.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public Club findClub(String nombre) {
        EntityManager em = getEntityManager();
        Club club = null;
        try {
            Query query = em.createNamedQuery("Club.findByNombre", Club.class);
            query.setParameter("nombre", nombre);
            club = (Club) query.getSingleResult();
        } catch (NoResultException e) {
           logger.debug("Problema al buscar club",e.getMessage());
        } finally {
            em.close();
        }
        return club;
    }
}
