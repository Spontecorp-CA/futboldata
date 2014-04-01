/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Pais;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jgcastillo
 */
public class PaisFacade extends AbstractFacade<Pais> {

    public PaisFacade(Class<Pais> entityClass) {
        super(entityClass);
    }

    public List<Pais> listaPaisxNombre() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT u FROM Pais u ORDER BY u.nombre");
        return (List<Pais>) q.getResultList();
    }

    public Pais findPais(String nombre) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Pais.findByNombre", Pais.class);
        q.setParameter("nombre", nombre);
        return (Pais) q.getSingleResult();
    }
}
