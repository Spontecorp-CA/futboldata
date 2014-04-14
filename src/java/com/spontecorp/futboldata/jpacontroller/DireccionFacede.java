/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Telefono;
import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class DireccionFacede extends AbstractFacade<Direccion> implements Serializable {

    private final TelefonoFacade controllerTelefono = new TelefonoFacade(Telefono.class);

    private final  EmailFacade controllerEmail = new EmailFacade(Email.class);
    
    private static final Logger logger = LoggerFactory.getLogger(Direccion.class);

    public List<Telefono> findListTelefonoxDireaccion(Direccion direccion) {

        return controllerTelefono.findListTelefonoxDireaccion(direccion);
    }
    
    
    public List<Email> findListEmailxDireaccion(Direccion direccion) {

        return controllerEmail.findListEmailxDireaccion(direccion);
    }

    public DireccionFacede(Class<Direccion> entityClass) {
        super(entityClass);
    }

}
