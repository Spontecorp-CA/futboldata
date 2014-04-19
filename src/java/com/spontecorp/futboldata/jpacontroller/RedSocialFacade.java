/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;
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
    
}
