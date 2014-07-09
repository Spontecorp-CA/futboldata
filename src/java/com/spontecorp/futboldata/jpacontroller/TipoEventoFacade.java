/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.TipoEvento;
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
public class TipoEventoFacade extends AbstractFacade<TipoEvento> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(TipoEventoFacade.class);

    public TipoEventoFacade() {
        super(TipoEvento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public TipoEvento findEvento(String nombre) {
        EntityManager em = getEntityManager();
        TipoEvento tipoEvento = null;
        try {
            Query q = em.createNamedQuery("TipoEvento.findByNombre", TipoEvento.class);
            q.setParameter("nombre", nombre);
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
}
