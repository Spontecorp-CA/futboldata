/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoHasJugador;
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
public class EquipoHasJugadorFacade extends AbstractFacade<EquipoHasJugador> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(EquipoHasJugadorFacade.class);
    private final int ACTIVO = 1;

    public EquipoHasJugadorFacade() {
        super(EquipoHasJugador.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<EquipoHasJugador> getListEquipoHasJugador(Equipo equipo) {
        List<EquipoHasJugador> equipoHasJugador = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e FROM EquipoHasJugador e WHERE  e.equipoId = :equipo "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, EquipoHasJugador.class);
            q.setParameter("equipo", equipo);
            q.setParameter("status", ACTIVO);
            equipoHasJugador = (List<EquipoHasJugador>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoHasJugador: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return equipoHasJugador;
    }

    public EquipoHasJugador getEquipoHasJugador(Equipo equipo, Jugador jugador) {
        EquipoHasJugador equipoHasJugador = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT e FROM EquipoHasJugador e WHERE  e.equipoId = :equipo "
                    + "AND e.jugadorId = :jugador AND e.status =:status";
            Query q = em.createQuery(query, EquipoHasJugador.class);
            q.setParameter("equipo", equipo);
            q.setParameter("jugador", jugador);
            q.setParameter("status", ACTIVO);
            equipoHasJugador = (EquipoHasJugador) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoHasJugador: " + e.getLocalizedMessage());

        } finally {
            em.close();
        }
        return equipoHasJugador;
    }

    public void persist(Object object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("Error en EquipoHasJugador Facade ", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
