/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoEnGrupo;
import com.spontecorp.futboldata.entity.Jugador;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class EquipoEnGrupoFacade extends AbstractFacade<EquipoEnGrupo> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EquipoEnGrupoFacade.class);
    private final int ACTIVO = 1;

    public EquipoEnGrupoFacade() {
        super(EquipoEnGrupo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<EquipoEnGrupo> getListEquipoEnGrupo(Grupo liga) {
        List<EquipoEnGrupo> equipoEnGrupo = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e FROM EquipoEnGrupo e WHERE  e.competicionId = :liga "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, EquipoEnGrupo.class);
            q.setParameter("liga", liga);
            q.setParameter("status", ACTIVO);
            equipoEnGrupo = (List<EquipoEnGrupo>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoEnGrupo: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return equipoEnGrupo;
    }

    public List<Equipo> getEquipoEnGrupo(Grupo liga) {
        List<Equipo> equipoEnGrupo = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e.equipoId FROM EquipoEnGrupo e WHERE  e.competicionId = :liga "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, Equipo.class);
            q.setParameter("liga", liga);
            q.setParameter("status", ACTIVO);
            equipoEnGrupo = (List<Equipo>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoEnGrupo: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return equipoEnGrupo;
    }

    public EquipoEnGrupo getEquipoEnGrupo(Equipo equipo, Grupo liga) {
        EquipoEnGrupo equipoEnGrupo = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT e FROM EquipoEnGrupo e WHERE  e.equipoId = :equipo "
                    + "AND e.competicionId = :liga AND e.status =:status";
            Query q = em.createQuery(query, EquipoEnGrupo.class);
            q.setParameter("equipo", equipo);
            q.setParameter("liga", liga);
            q.setParameter("status", ACTIVO);
            equipoEnGrupo = (EquipoEnGrupo) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoEnGrupo: " + e.getLocalizedMessage());

        } finally {
            em.close();
        }
        return equipoEnGrupo;
    }

    public void persist(Object object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("Error en EquipoEnGrupo Facade ", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
