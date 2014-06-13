/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Partido;
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
public class PartidoFacade extends AbstractFacade<Partido>{
    
    private static final Logger logger = LoggerFactory.getLogger(PartidoFacade.class);

    public PartidoFacade() {
        super(Partido.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public List<Partido> findPartidos(Object obj){
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = null;
            Query q = null;
            
            if(obj instanceof Jornada){
                query = "SELECT p FROM Partido p WHERE p.jornadaId = :jornada";
                q = em.createQuery(query);
                Jornada parJornada = (Jornada)obj;
                q.setParameter("jornada", parJornada);
            } else if(obj instanceof Llave){
                query = "SELECT p FROM Partido p WHERE p.llaveId = :llave";
                q = em.createQuery(query);
                Llave parLlave = (Llave) obj;
                q.setParameter("llave", parLlave);
            }            
            partidos = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando los partidos de un grupo", e);
        }
        return partidos;
    }
}
