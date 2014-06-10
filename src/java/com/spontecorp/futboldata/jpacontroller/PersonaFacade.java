/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.RedSocial;
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
public class PersonaFacade extends AbstractFacade<Persona> implements Serializable{
    private static final RedSocialFacade controllerRedSocial = new RedSocialFacade();
    private static final Logger logger = LoggerFactory.getLogger(PersonaFacade.class);

    public PersonaFacade() {
        super(Persona.class);
        
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

        public List<RedSocial> findListTelefonoxDireaccion(Persona persona) {
        return controllerRedSocial.findRedSocialxPersona(persona);
    }
    public Persona findPersona(String nombre) {
        EntityManager em = getEntityManager();
        Persona persona = null;
        try {
            Query q = em.createNamedQuery("Persona.findByNombre", Persona.class);
            q.setParameter("nombre", nombre);
            persona = (Persona) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Persona: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return persona;
    }
    
        public Persona findPersonaxDocumentoId(String documentoIdentidad) {
        EntityManager em = getEntityManager();
        Persona persona = null;
        try {
            Query q = em.createNamedQuery("Persona.findByDocumentoIdentidad", Persona.class);
            q.setParameter("documentoIdentidad", documentoIdentidad);
            persona = (Persona) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando Persona: " + e.getLocalizedMessage(),e.getMessage());
        } finally {
            em.close();
        }
        return persona;
    }
}
