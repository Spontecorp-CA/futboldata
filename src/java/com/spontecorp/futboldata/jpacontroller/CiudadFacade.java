/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class CiudadFacade extends AbstractFacade<Ciudad> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(CiudadFacade.class);

    public CiudadFacade() {
        super(Ciudad.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public Ciudad findCiudadxPais(String nombre, Pais pais) {
        Ciudad ciudad = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT c FROM Ciudad c WHERE c.ciudad = :ciudad AND c.paisId = :pais";
            Query q = em.createQuery(query, Ciudad.class);
            q.setParameter("ciudad", nombre);
            q.setParameter("pais", pais);
            ciudad = (Ciudad) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando ciudad: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return ciudad;
    }

    public List<Ciudad> findCiudadxPais(Pais pais) {
        List<Ciudad> ciudades = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT c FROM Ciudad c WHERE  c.paisId = :pais";
            Query q = em.createQuery(query, Ciudad.class);
            q.setParameter("pais", pais);
            ciudades = (List<Ciudad>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando ciudad: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return ciudades;
    }

    public Ciudad findCiudad(String nombre) {
        EntityManager em = getEntityManager();
        Ciudad ciudad = null;
        try {
            Query q = em.createNamedQuery("Ciudad.findByCiudad", Ciudad.class);
            q.setParameter("ciudad", nombre);
            ciudad = (Ciudad) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando ciudad: " + e.getLocalizedMessage(), e);
        } finally{
            em.close();
        }
        return ciudad;

    }
        public void persist(Object object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("Error en Ciudad Facade ",e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
