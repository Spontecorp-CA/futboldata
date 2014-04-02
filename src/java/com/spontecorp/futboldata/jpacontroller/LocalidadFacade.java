/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Localidad;
import java.io.Serializable;

/**
 *
 * @author sponte03
 */
public class LocalidadFacade extends AbstractFacade<Localidad> implements Serializable{

    public LocalidadFacade(Class<Localidad> entityClass) {
        super(entityClass);
    }
    
}
