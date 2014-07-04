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
public class RedSocialFacade extends AbstractFacade<RedSocial> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(RedSocialFacade.class);

    public RedSocialFacade() {
        super(RedSocial.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<RedSocial> findRedSocialxPersona(Persona persona) {
        EntityManager em = getEntityManager();
        List<RedSocial> redes = null;
        try {
            String query = "SELECT rs FROM RedSocial rs WHERE rs.personaId =:persona";
            Query q = em.createQuery(query, RedSocial.class);
            q.setParameter("persona", persona);
            redes = q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrado en buscar RedSocial: " + e.getLocalizedMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return redes;
    }

    public RedSocial findRedSocial(String usuario) {
        RedSocial reds = null;
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("RedSocial.findByUsuario", RedSocial.class);
            q.setParameter("usuario", usuario);
            reds = (RedSocial) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrado en buscar RedSocial: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return reds;
    }

    public RedSocial findRedSocial(RedSocial redsocial) {
        EntityManager em = getEntityManager();
        RedSocial redes = null;

        try {
            String query = "SELECT rs FROM RedSocial rs WHERE rs.usuario =:usuario "
                    + "AND rs.tipoRedSocialId =:tipoRedSocial";
            Query q = em.createQuery(query, RedSocial.class);
            q.setParameter("usuario", redsocial.getUsuario());
            q.setParameter("tipoRedSocial", redsocial.getTipoRedSocialId());
            redes = (RedSocial)q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrado en buscar RedSocial: " + e.getLocalizedMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return redes;
    }
}
