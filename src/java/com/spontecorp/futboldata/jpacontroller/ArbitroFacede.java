/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import java.io.Serializable;

/**
 *
 * @author sponte03
 */
public class ArbitroFacede extends AbstractFacade<Arbitro> implements  Serializable{

    public ArbitroFacede(Class<Arbitro> entityClass) {
        super(entityClass);
    }
    
}
