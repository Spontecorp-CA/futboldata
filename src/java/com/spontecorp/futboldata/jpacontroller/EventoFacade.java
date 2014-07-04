/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Evento;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.persistence.EntityManager;
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

    public Evento findEvento(String nombre) {
        EntityManager em = getEntityManager();
        Evento evento = null;
        try {
            Query q = em.createNamedQuery("Evento.findByNombre", Evento.class);
            q.setParameter("nombre", nombre);
            evento = (Evento) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return evento;
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
}
