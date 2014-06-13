/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.StatusPartido;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class StatusPartidoFacade extends AbstractFacade<StatusPartido> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(StatusPartidoFacade.class);

    public StatusPartidoFacade() {
        super(StatusPartido.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public StatusPartido findStatusPartido(String nombre) {
        EntityManager em = getEntityManager();
        StatusPartido statusPartido = null;
        try {
            Query q = em.createNamedQuery("StatusPartido.findByNombre", StatusPartido.class);
            q.setParameter("nombre", nombre);
            statusPartido = (StatusPartido) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return statusPartido;
    }

    public StatusPartido findStatusPartidoXValue(int value) {
        EntityManager em = getEntityManager();
        StatusPartido statusPartido = null;
        try {
            Query q = em.createNamedQuery("StatusPartido.findByValue", StatusPartido.class);
            q.setParameter("value", value);
            statusPartido = (StatusPartido) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return statusPartido;
    }
}
