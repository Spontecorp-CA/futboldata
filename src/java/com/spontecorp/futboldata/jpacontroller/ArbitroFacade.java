/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Arbitro;

import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class ArbitroFacade extends AbstractFacade<Arbitro> {

    private final PersonaFacade controllerPersona;
    private static final Logger logger = LoggerFactory.getLogger(ArbitroFacade.class);

    public ArbitroFacade() {
        super(Arbitro.class);
        this.controllerPersona = new PersonaFacade();
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Arbitro findArbitroxPersona(Persona persona) {
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT a FROM Arbitro a where a.personaId = :persona";
            Query q = em.createNamedQuery(query, Arbitro.class);
            q.setParameter("nombre", persona);
            return (Arbitro) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Persona en Arbitro: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return null;

    }

    public Arbitro findArbitro(String nombre) {
        controllerPersona.findPersona(nombre);
        return null;
    }

}
