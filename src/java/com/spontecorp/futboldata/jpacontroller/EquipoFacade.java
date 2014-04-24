/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class EquipoFacade extends AbstractFacade<Equipo> {
   
    private static final Logger logger = LoggerFactory.getLogger(EquipoFacade.class);

    public EquipoFacade() {
        super(Equipo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Equipo findEquipo(String nombre) {
        EntityManager em = getEntityManager();
        Equipo equipo = null;
        try {
            Query q = em.createNamedQuery("Equipo.findByNombre", Equipo.class);
            q.setParameter("nombre", nombre);
            equipo = (Equipo) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando la Equipo: " + e.getLocalizedMessage(), e);
        } finally{
            em.close();
        }
        return equipo;
    }
}
