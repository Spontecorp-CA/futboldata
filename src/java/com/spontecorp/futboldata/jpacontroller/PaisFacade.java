/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

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
 * @author jgcastillo
 */
public class PaisFacade extends AbstractFacade<Pais> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(PaisFacade.class);
    
    public PaisFacade() {
        super(Pais.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<Pais> listaPaisxNombre() {
        EntityManager em = getEntityManager();
        List<Pais> paises = null;
        try {
            Query q = em.createQuery("SELECT u FROM Pais u ORDER BY u.nombre");
            paises = (List<Pais>) q.getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return paises;
    }

    public Pais findPais(String nombre) {
        EntityManager em = getEntityManager();
        Pais pais = null;
        try {
            Query q = em.createNamedQuery("Pais.findByNombre", Pais.class);
            q.setParameter("nombre", nombre);
            pais = (Pais) q.getSingleResult();
        } catch (Exception e) {
        } finally {
         em.close();
        }
        return pais;
    }
}
