/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
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
public class GrupoFacade extends AbstractFacade<Grupo>{

    private static final Logger logger = LoggerFactory.getLogger(GrupoFacade.class);
    
    public GrupoFacade() {
        super(Grupo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public List<Grupo> findGruposXFase(Fase fase){
        EntityManager em = getEntityManager();
        List<Grupo> grupos = null;
        try {
            String query = "SELECT g FROM Grupo g WHERE g.faseId = :fase";
            Query q = em.createQuery(query);
            q.setParameter("fase", fase);
            grupos = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando los grupos de una fase", e.getMessage());
        }
        
        return grupos;
    }
    
        public Grupo findGrupoXFase(Fase fase,String nombre){
        EntityManager em = getEntityManager();
        Grupo grup = null;
        try {
            String query = "SELECT g FROM Grupo g WHERE g.faseId = :fase "
                    + "AND g.nombre = :grupo";
            Query q = em.createQuery(query,Grupo.class);
            q.setParameter("fase", fase);
            q.setParameter("grupo", nombre);
            grup = (Grupo) q.getSingleResult();
        } catch (Exception e) {
            logger.error("Error recuperando los grupos de una fase", e.getMessage());
        }
        
        return grup;
    }
}
