/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Cancha;
import com.spontecorp.futboldata.utilities.Util;
import static com.spontecorp.futboldata.utilities.Util.logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jgcastillo
 */
public class CanchaFacade extends AbstractFacade<Cancha>{

    public CanchaFacade() {
        super(Cancha.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Object findCancha(String nombre) {
        EntityManager em = getEntityManager();
        Cancha cancha = null;
        try {
            Query query = em.createNamedQuery("Cancha.findByNombre", Cancha.class);
            query.setParameter("nombre", nombre);
            cancha = (Cancha) query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Problema al buscar cancha", e.getMessage());
        } finally {
            em.close();
        }
        return cancha;
    }
    
}
