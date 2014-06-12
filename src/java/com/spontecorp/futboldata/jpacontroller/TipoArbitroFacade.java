/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.TipoArbitro;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class TipoArbitroFacade extends AbstractFacade<TipoArbitro> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(TipoArbitroFacade.class);

    public TipoArbitroFacade() {
        super(TipoArbitro.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public TipoArbitro findTipoArbitro(String nombre) {
        EntityManager em = getEntityManager();
        TipoArbitro tipoArbitro = null;
        try {
            Query q = em.createNamedQuery("TipoArbitro.findByNombre", TipoArbitro.class);
            q.setParameter("nombre", nombre);
            tipoArbitro = (TipoArbitro) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando ciudad: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return tipoArbitro;

    }
}
