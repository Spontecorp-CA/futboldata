/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Posicion;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class PosicionFacade extends AbstractFacade<Posicion>{

    private static final Logger logger = LoggerFactory.getLogger(PosicionFacade.class);
    
    public PosicionFacade() {
        super(Posicion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Object findPosicion(String nombre) {
        EntityManager em = getEntityManager();
        Posicion competicion = null;
        try {
            Query query = em.createNamedQuery("Posicion.findByNombre", Posicion.class);
            query.setParameter("nombre", nombre);
            competicion = (Posicion) query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Problema al buscar posicion", e.getMessage());
            throw new NoResultException();
        } finally {
            em.close();
        }
        return competicion;
    }
    
}
