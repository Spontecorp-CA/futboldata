/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Perfil;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class PerfilFacade extends AbstractFacade<Perfil> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(PerfilFacade.class);
    
    public PerfilFacade() {
        super(Perfil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public Perfil findPerfil(String nombre) {
        EntityManager em = getEntityManager();
        Perfil perfil = null;        
        try {
            Query q = em.createNamedQuery("Perfil.findByNombre", Perfil.class);
            q.setParameter("nombre", nombre);
            perfil = (Perfil) q.getSingleResult();
        } catch (NoResultException e) {
        } finally {
            em.close();
        }
        return perfil;
    }
    
}
