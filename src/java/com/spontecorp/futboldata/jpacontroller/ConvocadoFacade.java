/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.entity.Convocatoria;
import com.spontecorp.futboldata.entity.Equipo;
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
public class ConvocadoFacade extends AbstractFacade<Convocado> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(ConvocadoFacade.class);
    private final int ACTIVO = 1;

    public ConvocadoFacade() {
        super(Convocado.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<Convocado> getListConvocado(Equipo equipo) {
        List<Convocado> convocado = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT e FROM Convocado e WHERE  e.equipoId = :equipo "
                    + "AND e.status = :status";
            Query q = em.createQuery(query, Convocado.class);
            q.setParameter("equipo", equipo);
            q.setParameter("status", ACTIVO);
            convocado = (List<Convocado>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando Convocado: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return convocado;
    }

    public Convocado getConvocado(Jugador jugador, Convocatoria convocatoria) {
        Convocado convocado = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT c FROM Convocado c WHERE  c.jugadorId = :jugador "
                    + "AND c.convocatoriaId = :convocatoria ";
            Query q = em.createQuery(query, Convocado.class);
            q.setParameter("convocatoria", convocatoria);
            q.setParameter("jugador", jugador);
            convocado = (Convocado) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error al verificar el Convocado en la convocatoria " + e.getLocalizedMessage());

        } finally {
            em.close();
        }
        return convocado;
    }

    public void persist(Object object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("Error en Convocado Facade ", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
