/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.PartidoArbitro;
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
public class PartidoArbitroFacade extends AbstractFacade<PartidoArbitro> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(PartidoArbitroFacade.class);

    public PartidoArbitroFacade() {
        super(PartidoArbitro.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<PartidoArbitro> getListPartidoArbitro(Partido partido) {
        List<PartidoArbitro> partidoArbitro = null;
        EntityManager em = getEntityManager();

        try {
            String query = "SELECT p FROM PartidoArbitro p WHERE  p.partidoId = :partido ";
            Query q = em.createQuery(query, PartidoArbitro.class);
            q.setParameter("equipo", partido);
            partidoArbitro = (List<PartidoArbitro>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando PartidoArbitro: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return partidoArbitro;
    }

    public PartidoArbitro getPartidoArbitro(Arbitro arbitro, Partido partido) {
        PartidoArbitro partidoArbitro = null;

        EntityManager em = getEntityManager();
        try {

            String query = "SELECT p FROM PartidoArbitro p WHERE  p.arbitroId = :arbitro "
                    + "AND p.partidoId = :partido ";
            Query q = em.createQuery(query, PartidoArbitro.class);
            q.setParameter("partido", partido);
            q.setParameter("arbitro", arbitro);
            partidoArbitro = (PartidoArbitro) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error al verificar el PartidoArbitro en la partido " + e.getLocalizedMessage());

        } finally {
            em.close();
        }
        return partidoArbitro;
    }

    public void persist(Object object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.debug("Error en PartidoArbitro Facade ", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
