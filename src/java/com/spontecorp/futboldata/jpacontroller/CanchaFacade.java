/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Cancha;
import com.spontecorp.futboldata.utilities.Util;
import static com.spontecorp.futboldata.utilities.Util.logger;
import com.sun.org.apache.xerces.internal.impl.dv.xs.IDDV;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jgcastillo
 */
public class CanchaFacade extends AbstractFacade<Cancha> implements Serializable {

    public CanchaFacade() {
        super(Cancha.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<Cancha> findAll(int organizacion) {
        List<Cancha> canchas = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT c FROM Cancha c WHERE c.organizacionId =:organizacion";
                Query query = em.createQuery(q, Cancha.class);
                query.setParameter("organizacion", organizacion);
                canchas = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar cancha", e.getMessage());
            } finally {
                em.close();
            }
        } else {
               canchas = findAll();
        }
        return canchas;
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
