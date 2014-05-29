/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.utilities.Util;
import javax.persistence.EntityManager;

/**
 *
 * @author jgcastillo
 */
public class JornadaFacade extends AbstractFacade<Jornada>{

    public JornadaFacade() {
        super(Jornada.class);
    }

    
    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
}
