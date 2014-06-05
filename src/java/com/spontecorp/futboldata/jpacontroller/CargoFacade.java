/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Cargo;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author sponte07
 */
public class CargoFacade extends AbstractFacade<Cargo> implements Serializable{

    public CargoFacade() {
        super(Cargo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Cargo findCargo(String nombre) {
        EntityManager em = getEntityManager();
        Cargo cargo = null;
        try {
            Query q = em.createNamedQuery("Cargo.findByNombre", Cargo.class);
            q.setParameter("nombre", nombre);
            cargo = (Cargo) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return cargo;
    }
}
