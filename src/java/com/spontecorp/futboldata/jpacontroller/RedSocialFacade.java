/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.RedSocial;
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
public class RedSocialFacade extends AbstractFacade<RedSocial> {

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
            String query = "SELECT * FROM RedSocial rs WHERE re.personalId =: persona";
            Query q = em.createQuery(query, RedSocial.class);
            q.setParameter("persona", persona);
            redes = q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return redes;
    }

}
