/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Pais;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class CiudadFacade extends AbstractFacade<Ciudad> {

    private static Logger logger = LoggerFactory.getLogger(CiudadFacade.class);
    public CiudadFacade(Class<Ciudad> entityClass) {
        super(entityClass);
    }

    public Ciudad findCiudadxPais(String nombre,Pais pais) {
        Ciudad ciudad = null;
        try {
            EntityManager em = getEntityManager();
            String query = "SELECT c FROM Ciudad c WHERE c.ciudad = :ciudad AND c.paisId = :pais";
            Query q = em.createQuery(query, Ciudad.class);
            q.setParameter("ciudad", nombre);
            q.setParameter("pais", pais);
            ciudad = (Ciudad) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando ciudad: " + e.getLocalizedMessage(), e);
        }
        return ciudad;
    }
}
