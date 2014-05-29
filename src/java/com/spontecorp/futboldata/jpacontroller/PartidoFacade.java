/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;

/**
 *
 * @author jgcastillo
 */
public class PartidoFacade extends AbstractFacade<Partido>{

    public PartidoFacade() {
        super(Partido.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
}
