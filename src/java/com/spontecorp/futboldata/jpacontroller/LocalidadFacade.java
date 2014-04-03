/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Localidad;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class LocalidadFacade extends AbstractFacade<Localidad> implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(Localidad.class);

    public LocalidadFacade(Class<Localidad> entityClass) {
        super(entityClass);
    }

    public Localidad findLocalidadxCiudad(String nombre, Ciudad ciudad) {
        Localidad localidad = null;
        try {
            EntityManager em = getEntityManager();
            String query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre AND l.ciudadId = :ciudad";
            Query q = em.createQuery(query, Localidad.class);
            q.setParameter("nombre", nombre);
            q.setParameter("ciudad", ciudad);
            localidad = (Localidad) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Localidad: " + e.getLocalizedMessage(), e);
        }
        return localidad;
    }

    public Localidad findLocalidad(String nombre) {
        try {
            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("Localidad.findByNombre", Localidad.class);
            q.setParameter("nombre", nombre);
            return (Localidad) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando en Localidad: " + e.getLocalizedMessage(), e);
        }
        return null;

    }

}
