/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class AsociacionFacade extends AbstractFacade<Asociacion> implements Serializable{
   
    private static final Logger logger = LoggerFactory.getLogger(AsociacionFacade.class);

    public AsociacionFacade() {
        super(Asociacion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Asociacion findAsociacion(String nombre) {
        EntityManager em = getEntityManager();
        Asociacion asociacion = null;
        try {
            Query q = em.createNamedQuery("Asociacion.findByNombre", Asociacion.class);
            q.setParameter("nombre", nombre);
            asociacion = (Asociacion) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando la Asociacion: " + e.getLocalizedMessage(), e);
        } finally{
            em.close();
        }
        return asociacion;

    }
    
        public List<Asociacion> findAll(int organizacion) {
        List<Asociacion> asociacions = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT a FROM Asociacion a WHERE a.organizacionId =:organizacion";
                Query query = em.createQuery(q, Asociacion.class);
                query.setParameter("organizacion", organizacion);
                asociacions = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar asociacion", e.getMessage());
            } finally {
                em.close();
            }
        } else {
               asociacions = findAll();
        }
        return asociacions;
    }

}
