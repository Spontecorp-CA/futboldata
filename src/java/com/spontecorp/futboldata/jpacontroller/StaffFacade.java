/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.Staff;
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
 * @author sponte07
 */
public class StaffFacade extends AbstractFacade<Staff> implements Serializable {

    private static final PersonaFacade controllerPersona = new PersonaFacade();
    private static final Logger logger = LoggerFactory.getLogger(StaffFacade.class);

    public StaffFacade() {
        super(Staff.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Staff findStaffByPersona(Persona p) {
        EntityManager em = getEntityManager();
        Staff staff = null;
        try {
            String query = "SELECT j FROM Staff j WHERE j.personaId = :persona";
            Query q = em.createQuery(query, Staff.class);
            q.setParameter("persona", p);
            staff = (Staff) q.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Error al buscar staff findStaff ", e.getMessage());
        } finally {
            em.close();
        }
        return staff;
    }

    public Staff findStaffxDomentoId(String domentoId) {
        Persona persona = controllerPersona.findPersonaxDocumentoId(domentoId);
        if (persona == null) {
            return null;
        }
        return findStaffByPersona(persona);
    }

    public List<Staff> findStaffListByClub(Club club) {
        List<Staff> staffList = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT j FROM Staff j WHERE j.clubId = :club AND j.status= 1";
            Query q = em.createQuery(query);
            q.setParameter("club", club);
            staffList = q.getResultList();
        } catch (NoResultException e) {
            logger.debug("Error al buscar lista de staff por club findStaffListByClub ", e.getMessage());
        } catch (Exception e) {
            logger.debug("Ha ocurrido un error: " + e);
        } finally {
            em.close();
        }
        return staffList;
    }

    public List<Staff> findStaffListByEquipo(Equipo equipo) {
        List<Staff> staffList = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT j FROM Staff j WHERE j.equipoId = :equipo AND j.status= 1";
            Query q = em.createQuery(query);
            q.setParameter("equipo", equipo);
            staffList = q.getResultList();
        } catch (NoResultException e) {
            logger.debug("Error al buscar lista de staff por Equipo findStaffListByEquipo ", e.getMessage());
        } catch (Exception e) {
            logger.debug("Ha ocurrido un error: " + e);
        } finally {
            em.close();
        }
        return staffList;
    }

    public List<Staff> findStaffListByAsociacion(Asociacion asociacion) {
        List<Staff> staffList = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT S FROM Staff s WHERE s.asociacionId = :asociacion AND s.status= 1";
            Query q = em.createQuery(query);
            q.setParameter("asociacion", asociacion);
            staffList = q.getResultList();
        } catch (NoResultException e) {
            logger.debug("Error al buscar lista de staff por staff findStaffListBystaff", e.getMessage());
        } catch (Exception e) {
            logger.debug("Ha ocurrido un error: " + e);
        } finally {
            em.close();
        }
        return staffList;
    }

    public List<Staff> findStaffListByCompeticion(Competicion competicion) {
        List<Staff> staffList = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT j FROM Staff j WHERE j.competicionId = :competicion AND j.status= 1";
            Query q = em.createQuery(query);
            q.setParameter("competicion", competicion);
            staffList = q.getResultList();
        } catch (NoResultException e) {
            logger.debug("Error al buscar lista de staff por club findStaffListBycompeticion ", e.getMessage());
        } catch (Exception e) {
            logger.debug("Ha ocurrido un error: " + e);
        } finally {
            em.close();
        }
        return staffList;
    }

    public List<Persona> findDistinctStaffList(int organizacion) {
        List<Persona> staffList = null;
        EntityManager em = getEntityManager();
        try {
            String query = "select distinct p from Persona p, Staff s where s.personaId = p "
                    + "AND s.status = 1 AND s.personaId.status = 1";
            if (organizacion != 1) {
                query = query + "AND s.organizacionId =:organizacion";
            }
            Query q = em.createQuery(query);
            if (organizacion != 1) {
                q.setParameter("organizacion", organizacion);
            }

            staffList = q.getResultList();
//            if (!staffList.isEmpty()) {
//                for (int i = 0; i < staffList.size(); i++) {
//                    for (int h = 1; h < staffList.size(); h++) {
//
//                    }
//                }
//            }
        } catch (NoResultException e) {
            logger.debug("Error al buscar lista de staff por club findStaffListByClub ", e.getMessage());
        } catch (Exception e) {
            logger.debug("Ha ocurrido un error: " + e);
        } finally {
            em.close();
        }
        return staffList;
    }

    public List<Staff> findAll(int organizacion) {
        List<Staff> staffs = null;
        EntityManager em = getEntityManager();
        if (organizacion != 1) {

            try {
                String q = "SELECT s FROM Staff s WHERE s.organizacionId =:organizacion";
                Query query = em.createQuery(q, Staff.class);
                query.setParameter("organizacion", organizacion);
                staffs = query.getResultList();
            } catch (NoResultException e) {
                logger.debug("Problema al buscar staff", e.getMessage());
            } finally {
                em.close();
            }
        } else {
            staffs = findAll();
        }
        return staffs;
    }
}
