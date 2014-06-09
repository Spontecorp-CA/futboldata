/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.utilities.Util;
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
public class TemporadaFacade extends AbstractFacade<Temporada> {

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
            logger.debug("Problema al buscar temporada", e.getMessage());
        } finally {
            em.close();
        }
        return temporada;
    }

    public List<Temporada> findTemporadaxLiga(Competicion competicion) {
        EntityManager em = getEntityManager();
        List<Temporada> temporadas = null;

        try {
            String q = "SELECT t FROM Temporada t WHERE t.competicionId = :competicion";
            Query query = em.createQuery(q, Temporada.class);
            query.setParameter("competicion", competicion);
            temporadas = query.getResultList();
            return temporadas;

        } catch (Exception e) {
            logger.debug("Problemas al buscar la temporadaxLiga: ", e);
        } finally {
            em.close();
        }
        return temporadas;
    }
    
        public Temporada findTemporadaxLiga(String nombre,Competicion competicion) {
        EntityManager em = getEntityManager();
        Temporada temporada = null;

        try {
            String q = "SELECT t FROM Temporada t WHERE t.competicionId = :competicion "
                    + "AND t.nombre = :nombre";
            Query query = em.createQuery(q, Temporada.class);
            query.setParameter("competicion", competicion);
            query.setParameter("nombre", nombre);
            temporada = (Temporada) query.getSingleResult();
            return temporada;

        } catch (Exception e) {
            logger.debug("Problemas al buscar la temporadaxLiga: ", e.getMessage());
        } finally {
            em.close();
        }
        return temporada;
    }
}
