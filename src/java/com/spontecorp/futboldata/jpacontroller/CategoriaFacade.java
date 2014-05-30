/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Categoria;
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
public class CategoriaFacade extends AbstractFacade<Categoria> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(CategoriaFacade.class);
    
    public CategoriaFacade() {
        super(Categoria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public Categoria findCategoria(String nombre) {
        EntityManager em = getEntityManager();
        Categoria categoria = null;
        try {
            Query query = em.createNamedQuery("Categoria.findByNombre", Categoria.class);
            query.setParameter("nombre", nombre);
            categoria = (Categoria) query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Error al buscar Club ",e);
        } finally {
            em.close();
        }
        return categoria;
    }
}
