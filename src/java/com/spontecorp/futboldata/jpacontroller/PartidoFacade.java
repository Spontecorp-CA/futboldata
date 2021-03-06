/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.Temporada;
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
 * @author jgcastillo
 */
public class PartidoFacade extends AbstractFacade<Partido> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(PartidoFacade.class);

    public PartidoFacade() {
        super(Partido.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<Partido> findPartidos(Competicion liga) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = null;
            Query q = null;
            query = " SELECT p FROM Partido  p WHERE  p.llaveId.faseId.temporadaId.competicionId = :liga "
                    + "ORDER BY p.fecha   DESC ";
            q = em.createQuery(query, Partido.class);
            q.setParameter("liga", liga);
            partidos = q.getResultList();

            query = " SELECT p FROM Partido p WHERE p.jornadaId.grupoId.faseId.temporadaId.competicionId = :liga "
                    + "ORDER BY p.fecha   DESC";
            q = em.createQuery(query, Partido.class);
            q.setParameter("liga", liga);
            partidos.addAll(q.getResultList());

        } catch (Exception e) {
            logger.debug("No encontro partidos x liga", e.getCause());
        } finally {
            em.close();

        }
        return partidos;
    }

    public List<Partido> findPartidos(Temporada temporada) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = null;
            Query q = null;
            query = "SELECT p FROM Partido p WHERE p.llaveId.faseId.temporadaId =:temporada "
                    + "ORDER BY p.fecha   DESC";
            q = em.createQuery(query, Partido.class);
            q.setParameter("temporada", temporada);
            partidos = q.getResultList();

            query = "SELECT p FROM Partido p WHERE  p.jornadaId.grupoId.faseId.temporadaId =:temporada "
                    + "ORDER BY p.fecha   DESC";
            q = em.createQuery(query, Partido.class);
            q.setParameter("temporada", temporada);
            partidos.addAll(q.getResultList());

        } catch (Exception e) {
            logger.debug("No encontro partidos x temporada", e.getCause());
        } finally {
            em.close();

        }
        return partidos;
    }

    public List<Partido> findPartidos(Fase fase) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = null;
            Query q = null;
            query = "SELECT p FROM Partido p WHERE p.llaveId.faseId =:fase "
                    + "ORDER BY p.fecha   DESC";
            q = em.createQuery(query, Partido.class);
            q.setParameter("fase", fase);
            partidos = q.getResultList();

            query = "SELECT p FROM Partido p WHERE p.jornadaId.grupoId.faseId =:fase "
                    + "ORDER BY p.fecha   DESC";
            q = em.createQuery(query, Partido.class);
            q.setParameter("fase", fase);
            partidos.addAll(q.getResultList());

        } catch (Exception e) {
            logger.debug("No encontro partidos x fase", e.getCause());
        } finally {
            em.close();

        }
        return partidos;
    }

    public List<Partido> findPartidos(Grupo grupo) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = null;
            Query q = null;
            query = "SELECT p FROM Partido p WHERE  p.jornadaId.grupoId =:grupo "
                    + "ORDER BY p.fecha   DESC";
            q = em.createQuery(query, Partido.class);
            q.setParameter("grupo", grupo);
            partidos = q.getResultList();

        } catch (Exception e) {
            logger.debug("No encontro partidos x grupo", e.getCause());
        } finally {
            em.close();

        }
        return partidos;
    }

    public List<Partido> findPartidos(Object obj) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = null;
            Query q = null;

            if (obj instanceof Jornada) {
                query = "SELECT p FROM Partido p WHERE p.jornadaId = :jornada "
                        + "ORDER BY p.fecha   DESC";
                q = em.createQuery(query);
                Jornada parJornada = (Jornada) obj;
                q.setParameter("jornada", parJornada);
            } else if (obj instanceof Llave) {
                query = "SELECT p FROM Partido p WHERE p.llaveId = :llave "
                        + "ORDER BY p.fecha   DESC";
                q = em.createQuery(query);
                Llave parLlave = (Llave) obj;
                q.setParameter("llave", parLlave);
            }
            partidos = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando los partidos de un grupo", e);
        }
        return partidos;
    }

    public List<Partido> findPartidos(Jornada jornada, Categoria categoria) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = "SELECT p FROM Partido p WHERE p.jornadaId = :jornada "
                    + "AND p.categoriaId = :categoria";
            Query q = em.createQuery(query);
            q.setParameter("jornada", jornada);
            q.setParameter("categoria", categoria);
            partidos = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando los partidos de una jornada y categoria", e);
        }
        return partidos;
    }

    public List<Partido> findPartidos(Grupo grupo, Categoria categoria) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = "SELECT p FROM Partido p WHERE p.jornadaId.grupoId = :grupo "
                    + "AND p.categoriaId = :categoria";
            Query q = em.createQuery(query);
            q.setParameter("grupo", grupo);
            q.setParameter("categoria", categoria);
            partidos = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando los partidos de un grupo y categoria", e);
        }
        return partidos;
    }

    public List<Partido> findPartidos(Llave llave, Categoria categoria) {
        EntityManager em = getEntityManager();
        List<Partido> partidos = null;
        try {
            String query = "SELECT p FROM Partido p WHERE p.llaveId =:llave "
                    + "AND p.categoriaId = :categoria";
            Query q = em.createQuery(query);
            q.setParameter("llave", llave);
            q.setParameter("categoria", categoria);
            partidos = q.getResultList();
        } catch (Exception e) {
            logger.error("Error recuperando los partidos de un grupo y categoria", e);
        }
        return partidos;
    }

    public List<Partido> findAll(int organizacion) {
        List<Partido> partidos = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT p FROM Partido p WHERE p.organizacionId =:organizacion";
                Query query = em.createQuery(q, Partido.class);
                query.setParameter("organizacion", organizacion);
                partidos = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar partidos", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            partidos = findAll();
        }
        return partidos;
    }
}
