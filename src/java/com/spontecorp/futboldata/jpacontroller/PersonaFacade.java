/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Persona;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class PersonaFacade extends AbstractFacade<Persona>implements Serializable  {

    private static final Logger logger = LoggerFactory.getLogger(Persona.class);
    public PersonaFacade(Class<Persona> entityClass) {
        super(entityClass);
    }
    
        public Persona findPersona(String nombre) {
        try {

            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("Persona.findByNombre", Persona.class);
            q.setParameter("nombre", nombre);
            return (Persona) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Persona: " + e.getLocalizedMessage(), e);

        }
        return null;

    }
}
