/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Competicion;
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
public class CompeticionFacade extends AbstractFacade<Competicion>{

    private static final Logger logger = LoggerFactory.getLogger(CompeticionFacade.class);
    
    public CompeticionFacade() {
        super(Competicion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public Competicion findCompeticion(String nombre) {
        EntityManager em = getEntityManager();
        Competicion competicion = null;
        try {
            Query query = em.createNamedQuery("Competicion.findByNombre", Competicion.class);
            query.setParameter("nombre", nombre);
            competicion = (Competicion) query.getSingleResult();
        } catch (NoResultException e) {
           logger.debug("Problema al buscar competicion",e.getMessage());
        } finally {
            em.close();
        }
        return competicion;
    }
}