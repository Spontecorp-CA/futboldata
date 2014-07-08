/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.ClasificacionGrupo;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class ClasificacionGrupoFacade extends AbstractFacade<ClasificacionGrupo> {

    private static final Logger logger = LoggerFactory.getLogger(ClasificacionGrupoFacade.class);

    public ClasificacionGrupoFacade() {
        super(ClasificacionGrupo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }


        public ClasificacionGrupo findClasificacion(Grupo grupo,Equipo equipo) {
        EntityManager em = getEntityManager();
        ClasificacionGrupo clasificacion = null;
        try {
            String query = null;
            Query q = null;
            query = " SELECT c FROM ClasificacionGrupo  c WHERE  c.grupoId = :grupo "
                    + "AND c.equipoId =:equipo ";
            q = em.createQuery(query, ClasificacionGrupo.class);
            q.setParameter("grupo", grupo); 
            q.setParameter("equipo", equipo);
            clasificacion = (ClasificacionGrupo) q.getSingleResult();


        } catch (Exception e) {
            logger.debug("No encontro clasificacions x grupo", e.getCause());
        } finally {
            em.close();

        }
        return clasificacion;
    }
}
