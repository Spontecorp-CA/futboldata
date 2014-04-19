/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Localidad;
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
public class LocalidadFacade extends AbstractFacade<Localidad> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(LocalidadFacade.class);

    public LocalidadFacade() {
        super(Localidad.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Localidad findLocalidadxCiudad(String nombre, Ciudad ciudad) {
        Localidad localidad = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre AND l.ciudadId = :ciudad";
            Query q = em.createQuery(query, Localidad.class);
            q.setParameter("nombre", nombre);
            q.setParameter("ciudad", ciudad);
            localidad = (Localidad) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Localidad: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return localidad;
    }

    public Localidad findLocalidad(String nombre) {
        Localidad localidad = null;
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Localidad.findByNombre", Localidad.class);
            q.setParameter("nombre", nombre);
            localidad = (Localidad) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando en Localidad: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return localidad;

    }

}
