/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class ArbitroFacade extends AbstractFacade<Arbitro> implements Serializable {

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

    public Arbitro findArbitroByPersona(Persona p) {
        EntityManager em = getEntityManager();
        Arbitro arbitro = null;
        try {
            String query = "SELECT * FROM Jugador j WHERE j.personaId = :persona";
            Query q = em.createQuery(query, Arbitro.class);
            q.setParameter("persona", p);
            arbitro = (Arbitro) q.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Error al buscar jugador fingJugador ", e.getMessage());
        } finally {
            em.close();
        }
        return arbitro;
    }

    public Arbitro findArbitroByDomentoId(String domentoId) {
        Persona persona = controllerPersona.findPersonaxDocumentoId(domentoId);
        if (persona == null) {
            return null;
        }
        return findArbitroByPersona(persona);
    }

    public List<Arbitro> findAll(int organizacion) {
        List<Arbitro> arbitros = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT a FROM Arbitro a WHERE a.organizacionId =:organizacion";
                Query query = em.createQuery(q, Arbitro.class);
                query.setParameter("organizacion", organizacion);
                arbitros = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar arbitro", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            arbitros = findAll();
        }
        return arbitros;
    }

}
