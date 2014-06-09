/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.utilities.Util;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class LlaveFacade extends AbstractFacade<Llave>{

    public LlaveFacade() {
        super(Llave.class);
    }
    private static final Logger logger = LoggerFactory.getLogger(LlaveFacade.class);
    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
        public List<Llave> findLlavesXFase(Fase fase){
        EntityManager em = getEntityManager();
        List<Llave> llaves = null;
        try {
            String query = "SELECT l FROM Llave l WHERE l.faseId = :fase";
            Query q = em.createQuery(query);
            q.setParameter("fase", fase);
            llaves = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando los llaves de una fase", e.getMessage());
        }
        
        return llaves;
    }
    
        public Llave findLlaveXFase(Fase fase,String nombre){
        EntityManager em = getEntityManager();
        Llave llav = null;
        try {
            String query = "SELECT l FROM Llave l WHERE l.faseId = :fase "
                    + "AND l.nombre = :llave";
            Query q = em.createQuery(query,Llave.class);
            q.setParameter("fase", fase);
            q.setParameter("llave", nombre);
            llav = (Llave) q.getSingleResult();
        } catch (Exception e) {
            logger.error("Error recuperando los llaves de una fase", e);
        }
        
        return llav;
    }
    
}
