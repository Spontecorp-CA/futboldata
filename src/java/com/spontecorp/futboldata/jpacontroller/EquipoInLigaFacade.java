/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoInLiga;
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
public class EquipoInLigaFacade extends AbstractFacade<EquipoInLiga> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(EquipoInLigaFacade.class);
    private final int ACTIVO = 1;

    public EquipoInLigaFacade() {
        super(EquipoInLiga.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<EquipoInLiga> getListEquipoInLiga(Equipo equipo) {
        List<EquipoInLiga> equipoHasJugador = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e FROM EquipoInLiga e WHERE  e.equipoId = :equipo "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, EquipoInLiga.class);
            q.setParameter("equipo", equipo);
            q.setParameter("status", ACTIVO);
            equipoHasJugador = (List<EquipoInLiga>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoInLiga: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return equipoHasJugador;
    }

    public EquipoInLiga getEquipoInLiga(Equipo equipo, Jugador jugador) {
        EquipoInLiga equipoHasJugador = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT e FROM EquipoInLiga e WHERE  e.equipoId = :equipo "
                    + "AND e.jugadorId = :jugador AND e.status =:status";
            Query q = em.createQuery(query, EquipoInLiga.class);
            q.setParameter("equipo", equipo);
            q.setParameter("jugador", jugador);
            q.setParameter("status", ACTIVO);
            equipoHasJugador = (EquipoInLiga) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando EquipoInLiga: " + e.getLocalizedMessage());

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
            logger.debug("Error en EquipoInLiga Facade ", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
