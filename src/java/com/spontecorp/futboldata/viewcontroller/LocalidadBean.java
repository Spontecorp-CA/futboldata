/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.PaisJpaController;
import com.spontecorp.futboldata.jpacontroller.extensions.PaisJpaExt;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sponte03
 */
@ManagedBean(name = "localidadBean")
@SessionScoped
public class LocalidadBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pais pais;
    private Ciudad ciudad;
    private DataModel items = null;
    private DataModel itemsCiudad = null;

    private final CiudadFacade controllerCiudad;
    private final PaisFacade controllerPais;
    private final transient EntityManagerFactory emf = Util.getEmf();

    /**
     * Creates a new instance of LocalidadBean
     */
    public LocalidadBean() {
        controllerPais = new PaisFacade(Pais.class);
        controllerCiudad = new CiudadFacade(Ciudad.class);
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public DataModel getItemsCiudad() {

        if (itemsCiudad == null) {
            itemsCiudad = new ListDataModel(controllerCiudad.findAll());
        }
        return itemsCiudad;

    }

    public DataModel getItems() {

        if (items == null) {
            items = new ListDataModel(controllerPais.findAll());
        }
        return items;

    }

    public String gotoAdminPage() {
        recreateModel();
        return "listLocalidad";
    }

    private void recreateModel() {
        items = null;
    }

    private void recreateModelCiudad() {
        itemsCiudad = null;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }
        public void setItemsCiudad(DataModel items) {
        this.itemsCiudad = items;
    }

    public SelectItem[] getPaisAvailable() {
        return Util.getSelectItems(controllerPais.paisxNombre());
    }

    public Pais getSelectedPais() {
        if (pais == null) {
            pais = new Pais();
        }
        return pais;
    }
    
        public String create() {
        try {
            if (controllerCiudad.find(ciudad.getCiudad())==null) {
                Util.addErrorMessage("Ciudad ya existente, coloque otro");
                return null;
            } else {
                ciudad.setPaisId(pais);
                controllerCiudad.create(ciudad);
                Util.addSuccessMessage("Ciudad creada con éxito");
               return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la ciudad");
            return null;
        }
    }
        public Ciudad getSelectedCiudad() {
        if (ciudad == null) {
            ciudad = new Ciudad();
        }
        return ciudad;
    }

        public String prepareList() {
        recreateModelCiudad();
        return "listLocalidad";
    }    
    public String returnAdminPage() {
        return "adminPage";
    }

    public String prepareCreate() {
        ciudad = new Ciudad();
        pais = new Pais();
        return "createCiudad";
    }

    public String prepareEdit() {
        pais = (Pais) getItems().getRowData();
        return "editLocalidad";
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
