/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.PartidoEventoEquipo;
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
public class PartidoEventoEquipoFacade extends AbstractFacade<PartidoEventoEquipo> implements Serializable{

    static final Logger logger =   LoggerFactory.getLogger(PartidoEventoEquipo.class);
    public PartidoEventoEquipoFacade() {
        super(PartidoEventoEquipo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public List<PartidoEventoEquipo> findEventoEquipo(Partido partido){
        EntityManager em = getEntityManager();
        List<PartidoEventoEquipo> partidoEventos = null;

        try {
            String q = "SELECT p FROM PartidoEventoEquipo p WHERE p.partidoId = :partido";
            Query query = em.createQuery(q, PartidoEventoEquipo.class);
            query.setParameter("partido", partido);
            partidoEventos = query.getResultList();
            return partidoEventos;

        } catch (Exception e) {
            logger.debug("Problemas al buscar la partidoEventoxPartido: ", e.getMessage());
        } finally {
            em.close();
        }
        return partidoEventos;
    
    }
}
