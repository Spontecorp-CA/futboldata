/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Persona;
import java.io.Serializable;

/**
 *
 * @author sponte03
 */
public class PersonaFacade extends AbstractFacade<Persona>implements Serializable  {

    public PersonaFacade(Class<Persona> entityClass) {
        super(entityClass);
    }
    
}
