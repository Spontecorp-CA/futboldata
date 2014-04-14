/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import static com.spontecorp.futboldata.entity.Categoria_.nombre;
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
public class ArbitroFacade extends AbstractFacade<Arbitro> implements Serializable {

    private final PersonaFacade controllerPersona = new PersonaFacade(Persona.class);
    private static final Logger logger = LoggerFactory.getLogger(Arbitro.class);
    public ArbitroFacade(Class<Arbitro> entityClass) {
        super(entityClass);
    }
   
            public Arbitro findArbitroxPersona(Persona persona) {
        try {

            EntityManager em = getEntityManager();
            String query = "SELECT a FROM Arbitro a where a.personaId = :persona";
            Query q = em.createNamedQuery(query, Arbitro.class);
            q.setParameter("nombre", nombre);
            return (Arbitro) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Persona en Arbitro: " + e.getLocalizedMessage(), e);

        }
        return null;

    }
            
    
    public  Arbitro findArbitro (String nombre){
        controllerPersona.findPersona(nombre);
        return null;
          
    }
            
}
