/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class AsociacionFacade extends AbstractFacade<Asociacion> implements Serializable {
   
    private static final Logger logger = LoggerFactory.getLogger(Asociacion.class);

    public AsociacionFacade(Class<Asociacion> entityClass) {
        super(entityClass);
    }



    public Asociacion findAsociacion(String nombre) {
        try {

            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("Asociacion.findByNombre", Asociacion.class);
            q.setParameter("nombre", nombre);
            return (Asociacion) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando la Asociacion: " + e.getLocalizedMessage(), e);

        }
        return null;

    }
}
