/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Localidad;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.LocalidadFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
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
    private Localidad localidad;
    private transient DataModel items = null;
    private transient DataModel itemsCiudad = null;
    private transient DataModel itemsLocalidad = null;

    private SelectItem[] ciudades;
    private final CiudadFacade controllerCiudad;
    private final PaisFacade controllerPais;
    private final LocalidadFacade controllerLocalidad;
    private final transient EntityManagerFactory emf = Util.getEmf();

    /**
     * Creates a new instance of LocalidadBean
     */
    public LocalidadBean() {
        controllerPais = new PaisFacade(Pais.class);
        controllerCiudad = new CiudadFacade(Ciudad.class);
        controllerLocalidad = new LocalidadFacade(Localidad.class);
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
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
            itemsCiudad = new ListDataModel(controllerCiudad.findCiudadxPais(pais));
        }
        return itemsCiudad;
    }

    public DataModel getItemsPais() {

        if (items == null) {
            items = new ListDataModel(controllerPais.findAll());
        }
        return items;

    }

    public DataModel getItemsLocalidad() {

        if (items == null) {
            items = new ListDataModel(controllerLocalidad.findAll());
        }
        return items;

    }

    public String gotoLocalidadPage() {
        recreateModel();
        return "/admin/localidad/localidades/list.xhtml";
    }

    private void recreateModel() {
        items = null;
    }

    private void recreateModelCiudad() {
        itemsCiudad = null;
    }

    private void recreateModelLocalidad() {
        items = null;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public void setItemsCiudad(DataModel items) {
        this.itemsCiudad = items;
    }

    public void setItemsLocalidad(DataModel items) {
        this.items = items;
    }

    public SelectItem[] getPaisAvailable() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadAvailable() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public void setCiudades(SelectItem[] ciudades) {
        this.ciudades = ciudades;
    }

    public SelectItem[] getLocalidadAvailable() {
        return Util.getSelectItems(controllerLocalidad.findAll());
    }

    public Localidad getSelectedLocalidad() {
        if (localidad == null) {
            localidad = new Localidad();
        }
        return localidad;
    }

    public String create() {
        try {
            if (controllerLocalidad.findLocalidadxCiudad(localidad.getNombre(), localidad.getCiudadId()) != null) {
                Util.addErrorMessage("Localidad ya existente, coloque otra");
                return null;
            } else {

                controllerLocalidad.create(localidad);
                Util.addSuccessMessage("Localidad creada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la localidad");
            return null;
        }
    }
    public String edit(){
        try {
            if(controllerLocalidad.find(localidad.getId()) == null ){
                Util.addErrorMessage("Localidad no existe");
                return prepareList();
            } else {
            
                controllerLocalidad.edit(localidad);
                Util.addSuccessMessage("Lo editado con éxito");
                
                return prepareList();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la ciudad");
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
        recreateModelLocalidad();
        return "/admin/localidad/localidades/list.xhtml";
    }

    public String returnAdminPage() {
        return "/admin/adminPage.xhtml";
    }

    public String prepareCreate() {
        localidad = new Localidad();
        return "/admin/localidad/localidades/create.xhtml";
    }

    public String prepareEdit() {
        localidad = (Localidad) getItemsLocalidad().getRowData();
        return "/admin/localidad/localidades/edit.xhtml";
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
