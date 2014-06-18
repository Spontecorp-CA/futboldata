/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Convocatoria;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Jugador;
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
 * @author sponte03
 */
public class ConvocatoriaFacade extends AbstractFacade<Convocatoria> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(ConvocatoriaFacade.class);
    private final int ACTIVO = 1;

    public ConvocatoriaFacade() {
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

    public Convocatoria getConvocatoria(Partido partido, Equipo equipo) {
        Convocatoria convocatoria = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT c FROM Convocatoria c WHERE  c.equipoId = :equipo "
                    + "AND c.partidoId = :partido ";
            Query q = em.createQuery(query, Convocatoria.class);
            q.setParameter("equipo", equipo);
            q.setParameter("partido", partido);

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
