/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Competicion;
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
public class CompeticionFacade extends AbstractFacade<Competicion> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CompeticionFacade.class);

    public CompeticionFacade() {
        super(Competicion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Competicion findCompeticion(String nombre, int organizacion) throws NoResultException {
        EntityManager em = getEntityManager();
        Competicion competicion = null;

        try {
            String q = "SELECT c FROM Competicion c WHERE c.nombre =:nombre ";
            if (organizacion != 1) {
                q = q + "AND c.organizacionId =:organizacion";
            }
            Query query = em.createQuery(q, Competicion.class);
            query.setParameter("nombre", nombre);
            if (organizacion != 1) {
                query.setParameter("organizacion", organizacion);
            }

            competicion = (Competicion) query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Problema al buscar competicion", e.getMessage());
            throw new NoResultException();
        } finally {
            em.close();
        }
        return competicion;
    }

    public List<Competicion> findAll(int organizacion) {
        List<Competicion> competicions = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT c FROM Competicion c WHERE c.organizacionId =:organizacion";
                Query query = em.createQuery(q, Competicion.class);
                query.setParameter("organizacion", organizacion);
                competicions = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar competicion", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            competicions = findAll();
        }
        return competicions;
    }
}
