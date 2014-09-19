/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Organizacion;
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
 * @author jgcastillo
 */
public class OrganizacionFacade extends AbstractFacade<Organizacion> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(OrganizacionFacade.class);

    public OrganizacionFacade() {
        super(Organizacion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Organizacion findEvento(String nombre) {
        EntityManager em = getEntityManager();
        Organizacion organizacion = null;
        try {
            String query = "SELECT t FROM Organizacion t "
                    + "WHERE t.nombre =:nombre  ";
            Query q = em.createQuery(query, Organizacion.class);
            q.setParameter("nombre", nombre);
            organizacion = (Organizacion) q.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }
        return organizacion;
    }



    public List<Organizacion> findAll(int organizacion) {
        List<Organizacion> organizacions = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT t FROM Organizacion t WHERE t.organizacionId =:organizacion";
                Query query = em.createQuery(q, Organizacion.class);
                query.setParameter("organizacion", organizacion);
                organizacions = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar organizacion", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            organizacions = findAll();
        }
        return organizacions;
    }
}
