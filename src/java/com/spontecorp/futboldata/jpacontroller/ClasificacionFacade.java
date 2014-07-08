/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class ClasificacionFacade extends AbstractFacade<Clasificacion> {

    private static final Logger logger = LoggerFactory.getLogger(ClasificacionFacade.class);

    public ClasificacionFacade() {
        super(Clasificacion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Clasificacion findClasificacion(Partido partido,Equipo equipo) {
        EntityManager em = getEntityManager();
        Clasificacion clasificacion = null;
        try {
            String query = null;
            Query q = null;
            query = " SELECT c FROM Clasificacion  c WHERE  c.partidoId = :partido "
                    + "AND c.equipoId =:equipo ";
            q = em.createQuery(query, Clasificacion.class);
            q.setParameter("partido", partido); 
            q.setParameter("equipo", equipo);
            clasificacion = (Clasificacion) q.getSingleResult();


        } catch (Exception e) {
            logger.debug("No encontro clasificacions x jornada", e.getCause());
        } finally {
            em.close();

        }
        return clasificacion;
    }


}
