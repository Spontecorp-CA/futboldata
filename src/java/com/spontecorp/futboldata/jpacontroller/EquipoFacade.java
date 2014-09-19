/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Equipo;
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
 * @author sponte03
 */
public class EquipoFacade extends AbstractFacade<Equipo> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EquipoFacade.class);

    public EquipoFacade() {
        super(Equipo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Equipo findEquipo(String nombre, Categoria categoria) {
        EntityManager em = getEntityManager();
        Equipo equipo = null;
        try {
            String query = "SELECT e FROM equipo e WHERE e.nombre = :nombre AND "
                    + "e.categoriaId = :categoria";
            Query q = em.createQuery(query, Equipo.class);
            q.setParameter("nombre", nombre);
            q.setParameter("categoria", categoria);
            equipo = (Equipo) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando la Equipo: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return equipo;
    }

    public Equipo findEquipo(Equipo equi, Categoria categoria) {
        EntityManager em = getEntityManager();
        Equipo equipo = null;
        try {
            String query = "SELECT e FROM equipo e WHERE e.id = :equipo AND "
                    + "e.categoriaId = :categoria";
            Query q = em.createQuery(query, Equipo.class);
            q.setParameter("equipo", equi);
            q.setParameter("categoria", categoria);
            equipo = (Equipo) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrando la Equipo: " + e.getLocalizedMessage(), e);
        } finally {
            em.close();
        }
        return equipo;
    }

    public List<Equipo> findAll(int organizacion) {
        List<Equipo> equipos = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT a FROM Equipo a WHERE a.organizacionId =:organizacion";
                Query query = em.createQuery(q, Equipo.class);
                query.setParameter("organizacion", organizacion);
                equipos = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar equipo", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            equipos = findAll();
        }
        return equipos;
    }

}
