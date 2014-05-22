/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Temporada;
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
public class TemporadaFacade extends AbstractFacade<Temporada>{

    private static final Logger logger = LoggerFactory.getLogger(TemporadaFacade.class);
    
    public TemporadaFacade() {
        super(Temporada.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public Temporada findTemporada(String nombre) {
        EntityManager em = getEntityManager();
        Temporada temporada = null;
        try {
            Query query = em.createNamedQuery("Temporada.findByNombre", Temporada.class);
            query.setParameter("nombre", nombre);
            temporada = (Temporada) query.getSingleResult();
        } catch (NoResultException e) {
           logger.debug("Problema al buscar temporada",e.getMessage());
        } finally {
            em.close();
        }
        return temporada;
    }
}
