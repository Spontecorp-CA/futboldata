/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Cargo;
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
 * @author sponte07
 */
public class CargoFacade extends AbstractFacade<Cargo> implements Serializable {
    
    private final Logger logger;
    public CargoFacade() {
        super(Cargo.class);        
       logger = LoggerFactory.getLogger(CargoFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Cargo findCargo(String nombre,int organizacion) {
        EntityManager em = getEntityManager();
        Cargo cargo = null;
        try {
            String query =  "SELECT c FROM Cargo c "
                    + "WHERE c.organizacionId =:organizacion "
                    + "AND c.nombre =:nombre";
            Query q = em.createQuery(query, Cargo.class);
            q.setParameter("nombre", nombre);
            q.setParameter("organizacion", organizacion);
            cargo = (Cargo) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return cargo;
    }

    public List<Cargo> findAll(int organizacion) {
        List<Cargo> cargos = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT c FROM Cargo c WHERE c.organizacionId =:organizacion";
                Query query = em.createQuery(q, Cargo.class);
                query.setParameter("organizacion", organizacion);
                cargos = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar cargo", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            cargos = findAll();
        }
        return cargos;
    }
}
