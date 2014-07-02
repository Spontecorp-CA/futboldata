/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
public class TelefonoFacade extends AbstractFacade<Telefono> implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(TelefonoFacade.class);

    public TelefonoFacade() {
        super(Telefono.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public Telefono findTelefono(String telefono) {
        Telefono telef = null;
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Telefono.findByTelefono", Telefono.class);
            q.setParameter("telefono", telefono);
            telef = (Telefono) q.getSingleResult();
        } catch (Exception e) {
            logger.debug("Error encontrado en buscar Telefono: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return telef;
    }

    public List<Telefono> findListTelefonoxDireaccion(Direccion direccion) {
        List<Telefono> telefonos = null;
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT t FROM Telefono t WHERE  t.direccionId = :direccion";
            Query q = em.createQuery(query, Telefono.class);
            q.setParameter("direccion", direccion);
            telefonos = (List<Telefono>) q.getResultList();
        } catch (Exception e) {
            logger.debug("Error encontrando los telefonos: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
        return telefonos;
    }

}
