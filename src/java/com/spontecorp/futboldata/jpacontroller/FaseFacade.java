/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Temporada;
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
public class FaseFacade extends AbstractFacade<Fase> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(FaseFacade.class);

    public FaseFacade() {
        super(Fase.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Fase findFase(String nombre) {
        EntityManager em = getEntityManager();
        Fase fase = null;
        try {
            Query query = em.createNamedQuery("Fase.findByNombre", Fase.class);
            query.setParameter("nombre", nombre);
            fase = (Fase) query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Problema al buscar fase", e.getMessage());
        } finally {
            em.close();
        }
        return fase;
    }

    public List<Fase> findFasexTemporada(Temporada temporada) {
        EntityManager em = getEntityManager();
        List<Fase> fases = null;

        try {
            String q = "SELECT f FROM Fase f WHERE f.temporadaId = :temporada";
            Query query = em.createQuery(q, Fase.class);
            query.setParameter("temporada", temporada);
            fases = query.getResultList();
            return fases;

        } catch (Exception e) {
            logger.debug("Problemas al buscar la fasexTemporada: ", e);
        } finally {
            em.close();
        }
        return fases;
    }
}
