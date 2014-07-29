/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.TipoRedSocial;
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
public class TipoRedSocialFacade extends AbstractFacade<TipoRedSocial> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(TipoRedSocialFacade.class);

    public TipoRedSocialFacade() {
        super(TipoRedSocial.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public TipoRedSocial findTipoRedSocial(String nombre) {
        EntityManager em = getEntityManager();
        TipoRedSocial tipoRedSocial = null;
        try {
            Query q = em.createNamedQuery("TipoRedSocial.findByNombre", TipoRedSocial.class);
            q.setParameter("nombre", nombre);
            tipoRedSocial = (TipoRedSocial) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Red Social: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return tipoRedSocial;

    }
}
