/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.jpacontroller.extensions;

import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.jpacontroller.UserFacade;

/**
 *
 * @author jgcastillo
 */
public class UserFacadeExt extends UserFacade{

    public UserFacadeExt(Class<User> entityClass) {
        super(entityClass);
    }
        
}
