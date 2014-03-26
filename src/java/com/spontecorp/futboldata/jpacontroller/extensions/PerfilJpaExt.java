package com.spontecorp.futboldata.jpacontroller.extensions;


import com.spontecorp.futboldata.entity.Perfil;
import com.spontecorp.futboldata.jpacontroller.PerfilJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author jgcastillo
 */
public class PerfilJpaExt extends PerfilJpaController{

    public PerfilJpaExt(EntityManagerFactory emf) {
        super(emf);
    }
    
    public Perfil findPerfil(String nombre){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Perfil.findByNombre", Perfil.class);
        q.setParameter("nombre", nombre);
        return (Perfil)q.getSingleResult();
    }
}
