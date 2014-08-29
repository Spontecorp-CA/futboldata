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

/**
 *
 * @author jgcastillo
 */
public class PartidoEventoEquipoFacade extends AbstractFacade<PartidoEventoEquipo> implements Serializable{

    public PartidoEventoEquipoFacade() {
        super(PartidoEventoEquipo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public List<PartidoEventoEquipo> findEventoEquipo(Partido partido){
        String query = "SELECT pee FROM PartidoEventoEquipo WHERE pee.partidoId = :partido";
        Query q = getEntityManager().createQuery(query, PartidoEventoEquipo.class);
        q.setParameter("partido", partido);
        return q.getResultList();
    }
}
