/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Jugador;
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
 * @author Casper
 */
public class JugadorFacade extends AbstractFacade<Jugador> implements Serializable {

    private static final PersonaFacade controllerPersona = new PersonaFacade();
    private static final Logger logger = LoggerFactory.getLogger(JugadorFacade.class);

    public JugadorFacade() {
        super(Jugador.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Jugador findJugadorByPersona(Persona p) {
        EntityManager em = getEntityManager();
        Jugador jugador = null;
        try {
            String query = "SELECT * FROM Jugador j WHERE j.personaId = :persona";
            Query q = em.createQuery(query, Jugador.class);
            q.setParameter("persona", p);
            jugador = (Jugador) q.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Error al buscar jugador fingJugador ", e.getMessage());
        } finally {
            em.close();
        }
        return jugador;
    }

    public Jugador findJugadorxDomentoId(String domentoId) {
        Persona persona = controllerPersona.findPersonaxDocumentoId(domentoId);
        if (persona == null) {
            return null;
        }
        return findJugadorByPersona(persona);
    }

    public List<Jugador> findAll(int organizacion) {
        List<Jugador> jugadors = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT j FROM Jugador j WHERE j.organizacionId =:organizacion";
                Query query = em.createQuery(q, Jugador.class);
                query.setParameter("organizacion", organizacion);
                jugadors = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar jugador", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            jugadors = findAll();
        }
        return jugadors;
    }
}
