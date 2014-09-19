/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Evento;
import com.spontecorp.futboldata.entity.TipoEvento;
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
 * @author jgcastillo
 */
public class EventoFacade extends AbstractFacade<Evento> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EventoFacade.class);

    public EventoFacade() {
        super(Evento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Evento findEvento(String nombre, int organizacion) {
        EntityManager em = getEntityManager();
        Evento evento = null;
        try {
            String query = "SELECT e FROM Evento e "
                    + "WHERE e.nombre =:nombre ";
            if (organizacion != 1) {
                query = query + "AND e.organizacionId =:organizacion";
            }

            Query q = em.createQuery(query, Evento.class);
            q.setParameter("nombre", nombre);
            if (organizacion != 1) {
                q.setParameter("organizacion", organizacion);
            }
            evento = (Evento) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return evento;
    }

    public List<Evento> findEvento(TipoEvento evento) {
        EntityManager em = getEntityManager();
        List<Evento> eventos = null;
        try {
            String query = "SELECT e FROM Evento e WHERE e.tipoEventoId =:evento AND e.status =:status";
            Query q = em.createQuery(query, Evento.class);
            q.setParameter("evento", evento);
            q.setParameter("status", 1);
            eventos = q.getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return eventos;
    }

    public Evento findEventoXValue(int value) {
        EntityManager em = getEntityManager();
        Evento evento = null;
        try {
            Query q = em.createNamedQuery("Evento.findByValue", Evento.class);
            q.setParameter("value", value);
            evento = (Evento) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return evento;
    }

    public List<Evento> findAll(int organizacion) {
        List<Evento> eventos = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT e FROM Evento e WHERE e.organizacionId =:organizacion";
                Query query = em.createQuery(q, Evento.class);
                query.setParameter("organizacion", organizacion);
                eventos = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar evento", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            eventos = findAll();
        }
        return eventos;
    }
}
