/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.PartidoEvento;
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
public class PartidoEventoFacade extends AbstractFacade<PartidoEvento> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(PartidoEventoFacade.class);

    public PartidoEventoFacade() {
        super(PartidoEvento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public PartidoEvento findPartidoEvento(String nombre) {
        EntityManager em = getEntityManager();
        PartidoEvento partidoEvento = null;
        try {
            Query query = em.createNamedQuery("PartidoEvento.findByNombre", PartidoEvento.class);
            query.setParameter("nombre", nombre);
            partidoEvento = (PartidoEvento) query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Problema al buscar partidoEvento", e.getMessage());
        } finally {
            em.close();
        }
        return partidoEvento;
    }

    public List<PartidoEvento> findPartidoEventoxPartido(Partido partido) {
        EntityManager em = getEntityManager();
        List<PartidoEvento> partidoEventos = null;

        try {
            String q = "SELECT p FROM PartidoEvento p WHERE p.partidoId = :partido";
            Query query = em.createQuery(q, PartidoEvento.class);
            query.setParameter("partido", partido);
            partidoEventos = query.getResultList();
            return partidoEventos;

        } catch (Exception e) {
            logger.debug("Problemas al buscar la partidoEventoxPartido: ", e.getMessage());
        } finally {
            em.close();
        }
        return partidoEventos;
    }
    
        public PartidoEvento findPartidoEventoxTemporada(String nombre,Temporada temporada) {
        EntityManager em = getEntityManager();
        PartidoEvento partidoEvento = null;

        try {
            String q = "SELECT f FROM PartidoEvento f WHERE f.temporadaId = :temporada "
                    + "AND f.nombre =:nombre";
            Query query = em.createQuery(q, PartidoEvento.class);
            query.setParameter("temporada", temporada);
            query.setParameter("nombre", nombre);
            partidoEvento = (PartidoEvento) query.getSingleResult();
            return partidoEvento;

        } catch (Exception e) {
            logger.debug("Problemas al buscar la partidoEventoxTemporada: ", e.getMessage());
        } finally {
            em.close();
        }
        return partidoEvento;
    }
}
