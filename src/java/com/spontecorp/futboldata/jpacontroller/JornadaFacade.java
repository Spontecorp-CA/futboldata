/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.utilities.Util;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class JornadaFacade extends AbstractFacade<Jornada> {

    public JornadaFacade() {
        super(Jornada.class);
    }

    private static final Logger logger = LoggerFactory.getLogger(JornadaFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<Jornada> findJornadasxGrupo(Grupo grupo) {
        EntityManager em = getEntityManager();
        List<Jornada> jornadas = null;
        try {
            String query = "SELECT j FROM Jornada j WHERE j.grupoId = :grupo";
            Query q = em.createQuery(query);
            q.setParameter("grupo", grupo);
            jornadas = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando las Jornada de un grupo", e.getMessage());
        }

        return jornadas;
    }

    public Jornada findJornadaxGrupo(Grupo grupo,String nombre) {
        EntityManager em = getEntityManager();
        Jornada jornada = null;
        try {
            String query = "SELECT j FROM Jornada j WHERE j.grupoId = :grupo "
                    + "AND j.nombre = :nombre";
            Query q = em.createQuery(query, Jornada.class);
            q.setParameter("grupo", grupo);
            q.setParameter("nombre", nombre);
            jornada = (Jornada) q.getSingleResult();
        } catch (Exception e) {
            logger.error("Error recuperando los grupos de una fase", e);
        }

        return jornada;
    }

}
