/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Categoria;
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
public class CategoriaFacade extends AbstractFacade<Categoria> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaFacade.class);

    public CategoriaFacade() {
        super(Categoria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Categoria findCategoria(String nombre, int organizacion) {
        EntityManager em = getEntityManager();
        Categoria categoria = null;
        try {
            String q = "SELECT c FROM Categoria c WHERE c.organizacionId =:organizacion "
                    + "AND c.nombre =:nombre";
            Query query = em.createQuery(q, Categoria.class);
            query.setParameter("nombre", nombre);
            query.setParameter("organizacion", organizacion);
            categoria = (Categoria) query.getSingleResult();
        } catch (NoResultException e) {
//            logger.debug("Error al buscar Categoria ",e.getMessage());
        } finally {
            em.close();
        }
        return categoria;
    }

    public List<Categoria> findAll(int organizacion) {
        List<Categoria> categorias = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT c FROM Categoria c WHERE c.organizacionId =:organizacion";
                Query query = em.createQuery(q, Categoria.class);
                query.setParameter("organizacion", organizacion);
                categorias = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar categoria", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            categorias = findAll();
        }
        return categorias;
    }
}
