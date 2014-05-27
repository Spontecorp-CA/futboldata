/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.entity.TemporadaCategoria;
import com.spontecorp.futboldata.utilities.Util;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class TemporadaCategoriaFacade extends AbstractFacade<TemporadaCategoria> {

    private final Logger logger = LoggerFactory.getLogger(TemporadaCategoriaFacade.class);

    public TemporadaCategoriaFacade() {
        super(TemporadaCategoria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<Categoria> getCategorias(Temporada temporada) {
        EntityManager em = getEntityManager();
        List<Categoria> categorias = null;
        try {
            String query = "SELECT t.categoriaId FROM TemporadaCategoria t WHERE t.temporadaId = :temporada";
            Query q = em.createQuery(query, Categoria.class);
            q.setParameter("temporada", temporada);
            categorias = (List<Categoria>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error al buscar las Categorias de TemporadaCategoria", e);
        } finally {
            em.close();
        }
        return categorias;
    }

    public TemporadaCategoria findTemporadaCategoria(Temporada temporada,Categoria categoria) {
        EntityManager em = getEntityManager();
        TemporadaCategoria temporadaCategoria = null;
        try {
            String query = "SELECT t FROM TemporadaCategoria t WHERE t.categoriaId = :categoria"
                    + " AND t.temporadaId = :temporada";
            Query q = em.createQuery(query, TemporadaCategoria.class);
            q.setParameter("categoria", categoria);
            q.setParameter("temporada", temporada);
            temporadaCategoria = (TemporadaCategoria) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error al buscar el TemporadaCategoria finTemporadaCategoria", e);
        } finally {
            em.close();
        }
        return temporadaCategoria;
    }

    public TemporadaCategoria findTemporadaCategoria(String temporadaCategoria) {
        EntityManager em = getEntityManager();
        TemporadaCategoria mail = null;
        try {
            String query = "SELECT t FROM TemporadaCategoria t WHERE t.categoriaId.nombre = :temporadaCategoria";
            Query q = em.createQuery(query, TemporadaCategoria.class);
            q.setParameter("temporadaCategoria", temporadaCategoria);
            mail = (TemporadaCategoria) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error al buscar el TemporadaCategoria finTemporadaCategoria", e);
        } finally {
            em.close();
        }
        return mail;
    }
}
