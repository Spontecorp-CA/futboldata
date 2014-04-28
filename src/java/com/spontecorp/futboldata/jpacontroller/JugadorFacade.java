/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Jugador;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class JugadorFacade extends AbstractFacade<Jugador>{

    private static final Logger logger = LoggerFactory.getLogger(JugadorFacade.class);
    
    public JugadorFacade() {
        super(Jugador.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public Jugador findJugador(String nombre) {
        EntityManager em = getEntityManager();
        Jugador jugador = null;
        try {
            String query = "SELECT * FROM Jugador j WHERE j.personaId.nombre=:nombre";
            Query q = em.createQuery(query, Jugador.class);
            q.setParameter("nombre", nombre);
            jugador = (Jugador) q.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Error al buscar jugador fingJugador ",e.getMessage());
        } finally {
            em.close();
        }
        return jugador;
    }
}
