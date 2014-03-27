/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller.extensions;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.jpacontroller.CategoriaJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class CategoriaJpaExt extends CategoriaJpaController{
    
    private EntityManagerFactory emf;
    private static final Logger logger = LoggerFactory.getLogger(CategoriaJpaExt.class);

    public CategoriaJpaExt(EntityManagerFactory emf) {
        super(emf);
        this.emf = emf;
    }
    
    public Categoria findCategoria(String nombre){
        EntityManager em = emf.createEntityManager();
        Categoria categoria = null;
        try {
            Query query = em.createNamedQuery("Categoria.findByNombre", Categoria.class);
            query.setParameter("nombre", nombre);
            categoria = (Categoria) query.getSingleResult();
        } catch (NoResultException e) {
            // Do nothing
        } finally {
            em.close();
        }
        return categoria;
    }
}
