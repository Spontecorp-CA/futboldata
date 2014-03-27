/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller.extensions;

import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.PaisJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author sponte03
 */
public class PaisJpaExt extends PaisJpaController {

    public PaisJpaExt(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Pais> paisxNombre() {
        EntityManager em = getEntityManager();

        Query q = em.createQuery("SELECT u FROM Pais u ORDER BY u.nombre");
    
         
            return (List<Pais>) q.getResultList();

    }
}
