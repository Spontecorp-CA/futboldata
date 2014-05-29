/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;

/**
 *
 * @author jgcastillo
 */
public class LlaveFacade extends AbstractFacade<Llave>{

    public LlaveFacade() {
        super(Llave.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
}
