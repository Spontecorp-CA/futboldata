/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoEnGrupo;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
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

    public List<Equipo> getListEquipoEnGrupo(Grupo grupo) {
        List<Equipo> equipoEnGrupo = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e.equipoId FROM EquipoEnGrupo e WHERE  e.grupoId = :grupo ";
            Query q = em.createQuery(query, EquipoEnGrupo.class);
            q.setParameter("grupo",grupo);
            equipoEnGrupo = (List<Equipo>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoEnGrupo: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return equipoEnGrupo;
    }

    public Equipo getEquipoEnGrupo(Grupo grupo ,Equipo equipo) {
        Equipo equipoEnGrupo = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e.equipoId FROM EquipoEnGrupo e WHERE  e.grupoId = :grupo "
                    + "And e.equipoId =:equipo";
            Query q = em.createQuery(query, Equipo.class);
            q.setParameter("grupo", grupo);
            q.setParameter("equipo", equipo);
            equipoEnGrupo = (Equipo) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoEnGrupo: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return equipoEnGrupo;
    }
    
        public Grupo getGrupoxFasexEquipo(Fase fase , Equipo equipo) {
        Grupo grupo = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e.grupoId FROM EquipoEnGrupo e WHERE  e.grupoId.faseId = :fase "
                    + " AND e.equipoId =:equipo ";
            Query q = em.createQuery(query, Equipo.class);
            q.setParameter("fase", fase);
            q.setParameter("equipo", equipo);

            grupo = (Grupo) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoEnGrupo: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return grupo;
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
