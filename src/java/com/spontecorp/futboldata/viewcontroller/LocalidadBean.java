/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.PaisJpaController;
import com.spontecorp.futboldata.jpacontroller.extensions.PaisJpaExt;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
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
    private DataModel items = null;

    private final PaisJpaController controllerPais;
    private final transient EntityManagerFactory emf = Util.getEmf();
    private final PaisJpaExt paisJpaExt;
    
    /**
     * Creates a new instance of LocalidadBean
     */
    public LocalidadBean() {
        controllerPais = new PaisJpaController(emf);
        paisJpaExt = new PaisJpaExt(emf);
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public DataModel getItems() {

        if (items == null) {
            items = new ListDataModel(controllerPais.findPaisEntities());
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

    public void setItems(DataModel items) {
        this.items = items;
    }

    public SelectItem[] getPaisAvailable() {
        return Util.getSelectItems(paisJpaExt.paisxNombre());
    }

        public Pais getSelected() {
        if (pais == null) {
            pais = new Pais();
        }
        return pais;
    }
    public String returnAdminPage() {
        return "adminPage";
    }
    
        public String prepareCreate() {
        pais = new Pais();
        return "createLocalidad";
    }
        
        public String prepareEdit() {
        pais = (Pais) getItems().getRowData();
        return "editLocalidad";
    }
}

