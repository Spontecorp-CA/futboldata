/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Convocatoria;
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
public class convocatoriaFacade extends AbstractFacade<Convocatoria> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(convocatoriaFacade.class);
    private final int ACTIVO = 1;

    public convocatoriaFacade() {
        super(Convocatoria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<Convocatoria> getListConvocatoria(Equipo equipo) {
        List<Convocatoria> convocatoria = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e FROM Convocatoria e WHERE  e.equipoId = :equipo "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, Convocatoria.class);
            q.setParameter("equipo", equipo);
            q.setParameter("status", ACTIVO);
            convocatoria = (List<Convocatoria>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando Convocatoria: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return convocatoria;
    }

    public Convocatoria getConvocatoria(Equipo equipo, Jugador jugador) {
        Convocatoria convocatoria = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT e FROM Convocatoria e WHERE  e.equipoId = :equipo "
                    + "AND e.jugadorId = :jugador AND e.status =:status";
            Query q = em.createQuery(query, Convocatoria.class);
            q.setParameter("equipo", equipo);
            q.setParameter("jugador", jugador);
            q.setParameter("status", ACTIVO);
            convocatoria = (Convocatoria) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Convocatoria: " + e.getLocalizedMessage());

        } finally {
            em.close();
        }
        return convocatoria;
    }

    public void persist(Object object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("Error en Convocatoria Facade ", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
