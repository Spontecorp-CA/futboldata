/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.utilities.Util;
import java.util.List;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class DireccionFacade extends AbstractFacade<Direccion>{

    private final TelefonoFacade controllerTelefono = new TelefonoFacade();
    private final EmailFacade controllerEmail = new EmailFacade();
    private static final Logger logger = LoggerFactory.getLogger(DireccionFacade.class);

    public DireccionFacade() {
        super(Direccion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }
    
    public List<Telefono> findListTelefonoxDireaccion(Direccion direccion) {
        return controllerTelefono.findListTelefonoxDireaccion(direccion);
    }

    public List<Email> findListEmailxDireaccion(Direccion direccion) {
        return controllerEmail.findListEmailxDireaccion(direccion);
    }

}
