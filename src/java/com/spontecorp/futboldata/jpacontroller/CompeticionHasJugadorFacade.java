/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.CompeticionHasJugador;
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
public class CompeticionHasJugadorFacade extends AbstractFacade<CompeticionHasJugador> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CompeticionHasJugadorFacade.class);
    private final int ACTIVO = 1;

    public CompeticionHasJugadorFacade() {
        super(CompeticionHasJugador.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<CompeticionHasJugador> getListCompeticionHasJugador(Competicion liga) {
        List<CompeticionHasJugador> competicionHasJugador = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e FROM CompeticionHasJugador e WHERE  e.competicionId = :liga "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, CompeticionHasJugador.class);
            q.setParameter("liga", liga);
            q.setParameter("status", ACTIVO);
            competicionHasJugador = (List<CompeticionHasJugador>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando CompeticionHasJugador: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return competicionHasJugador;
    }

    public List<Equipo> getCompeticionHasJugador(Competicion liga) {
        List<Equipo> competicionHasJugador = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e.equipoId FROM CompeticionHasJugador e WHERE  e.competicionId = :liga "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, Equipo.class);
            q.setParameter("liga", liga);
            q.setParameter("status", ACTIVO);
            competicionHasJugador = (List<Equipo>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando CompeticionHasJugador: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return competicionHasJugador;
    }
    
        public List<Equipo> getCompeticionHasJugador(Competicion liga ,Categoria categoria) {
        List<Equipo> competicionHasJugador = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e.equipoId FROM CompeticionHasJugador e WHERE  e.competicionId = :liga "
                    + " AND e.equipoId.categoriaId =:categoria AND e.status = :status";
            Query q = em.createQuery(query, Equipo.class);
            q.setParameter("liga", liga);
            q.setParameter("categoria", categoria);
            q.setParameter("status", ACTIVO);
            competicionHasJugador = (List<Equipo>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando CompeticionHasJugador: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return competicionHasJugador;
    }

    public CompeticionHasJugador getCompeticionHasJugador(Jugador jugador, Competicion liga) {
        CompeticionHasJugador competicionHasJugador = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT c FROM CompeticionHasJugador c WHERE  c.jugadorId = :jugador "
                    + "AND c.competicionId = :liga AND c.status =:status";
            Query q = em.createQuery(query, CompeticionHasJugador.class);
            q.setParameter("jugador", jugador);
            q.setParameter("liga", liga);
            q.setParameter("status", ACTIVO);
            competicionHasJugador = (CompeticionHasJugador) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando CompeticionHasJugador: " + e.getLocalizedMessage());

        } finally {
            em.close();
        }
        return competicionHasJugador;
    }

    public CompeticionHasJugador getCompeticionHasJugador(String equipo, Competicion liga) {
        CompeticionHasJugador competicionHasJugador = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT e FROM CompeticionHasJugador e WHERE  e.equipoId.nombre = :equipo "
                    + "AND e.competicionId = :liga AND e.status =:status";
            Query q = em.createQuery(query, CompeticionHasJugador.class);
            q.setParameter("equipo", equipo);
            q.setParameter("liga", liga);
            q.setParameter("status", ACTIVO);
            competicionHasJugador = (CompeticionHasJugador) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando CompeticionHasJugador: " + e.getLocalizedMessage());

        } finally {
            em.close();
        }
        return competicionHasJugador;
    }

    public void persist(Object object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("Error en CompeticionHasJugador Facade ", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
