/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

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
public class TipoEventoFacade extends AbstractFacade<TipoEvento> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(TipoEventoFacade.class);

    public TipoEventoFacade() {
        super(TipoEvento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public TipoEvento findEvento(String nombre, int organizacion) {
        EntityManager em = getEntityManager();
        TipoEvento tipoEvento = null;
        try {
            String query = "SELECT t FROM TipoEvento t "
                    + "WHERE t.nombre =:nombre ";
            if (organizacion != 1) {
                query = query + "AND t.organizacionId =:organizacion";
            }
            Query q = em.createQuery(query, TipoEvento.class);
            q.setParameter("nombre", nombre);
            if (organizacion != 1) {
                q.setParameter("organizacion", organizacion);
            }
            tipoEvento = (TipoEvento) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return tipoEvento;
    }

    public TipoEvento findEventoXValue(int value) {
        EntityManager em = getEntityManager();
        TipoEvento tipoEvento = null;
        try {
            Query q = em.createNamedQuery("TipoEvento.findByValue", TipoEvento.class);
            q.setParameter("value", value);
            tipoEvento = (TipoEvento) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return tipoEvento;
    }

    public List<TipoEvento> findAll(int organizacion) {
        List<TipoEvento> tipoEventos = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT t FROM TipoEvento t WHERE t.organizacionId =:organizacion";
                Query query = em.createQuery(q, TipoEvento.class);
                query.setParameter("organizacion", organizacion);
                tipoEventos = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar tipoEvento", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            tipoEventos = findAll();
        }
        return tipoEventos;
    }

    public List<TipoEvento> findAll(int organizacion, char tipoQueAplica) {
        List<TipoEvento> tipoEventos = null;
        EntityManager em = getEntityManager();
            try {
                String q = "SELECT t FROM TipoEvento t "
                        + "WHERE t.organizacionId =:organizacion "
                        + "AND t.tipoQueAplica =:tipoQueAplica";
                Query query = em.createQuery(q, TipoEvento.class);
                query.setParameter("organizacion", organizacion);
                query.setParameter("tipoQueAplica", tipoQueAplica);
                tipoEventos = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar tipoEvento", e.getMessage());
            } finally {
                em.close();
            }

        return tipoEventos;
    }
}
