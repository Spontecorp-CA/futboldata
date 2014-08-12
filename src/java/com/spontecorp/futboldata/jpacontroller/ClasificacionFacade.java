/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.ClasificacionGrupo;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Partido;
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
public class ClasificacionFacade extends AbstractFacade<Clasificacion> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ClasificacionFacade.class);

    public ClasificacionFacade() {
        super(Clasificacion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Clasificacion findClasificacion(Partido partido, Equipo equipo) {
        EntityManager em = getEntityManager();
        Clasificacion clasificacion = null;
        try {
            String query = "SELECT c FROM Clasificacion  c "
                    + "WHERE c.partidoId = :partido "
                    + "AND c.equipoId =:equipo ";
            Query q = em.createQuery(query, Clasificacion.class);
            q.setParameter("partido", partido);
            q.setParameter("equipo", equipo);
            clasificacion = (Clasificacion) q.getSingleResult();

        } catch (Exception e) {
            logger.debug("No encontro clasificaciones x jornada", e);
        } finally {
            em.close();
        }
        return clasificacion;
    }
    
    public List<Clasificacion> findClasificacion(Partido partido){
        EntityManager em = getEntityManager();
        List<Clasificacion> clasificacion = null;
        try {
            String query = "SELECT c FROM Clasificacion  c "
                    + "WHERE c.partidoId = :partido ";
            Query q = em.createQuery(query, Clasificacion.class);
            q.setParameter("partido", partido);
            clasificacion = q.getResultList();

        } catch (Exception e) {
            logger.debug("No encontró clasificaciones para el partido " + partido.getEquipoLocalId().getNombre()
                    + " - " + partido.getEquipoVisitanteId().getNombre(), e);
        } finally {
            em.close();
        }
        return clasificacion;
    }

    public List<Clasificacion> findClasificaciones(ClasificacionGrupo cg) {
        EntityManager em = getEntityManager();
        List<Clasificacion> clasificaciones = null;
        try {
            String query = null;
            Query q = null;
            query = "SELECT c FROM Clasificacion  c WHERE c.clasificacionGrupoId =:clasificacionGrupo";
            q = em.createQuery(query, Clasificacion.class);
            q.setParameter("clasificacionGrupo", cg);
            clasificaciones = q.getResultList();

        } catch (Exception e) {
            logger.debug("No encontro clasificaciones", e.getCause());
        } finally {
            em.close();

        }
        return clasificaciones;
    }

}
