/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.ArbitroFacade;
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
@ManagedBean(name = "arbitroBean")
@SessionScoped
public class ArbitroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pais pais;
    private Ciudad ciudad;
    private Arbitro arbitro;
    private transient DataModel items = null;
    private transient DataModel itemsCiudad = null;
    private transient DataModel itemsArbitro = null;

    private SelectItem[] ciudades;
    private final CiudadFacade controllerCiudad;
    private final PaisFacade controllerPais;
    private final ArbitroFacade controllerArbitro;
    private final transient EntityManagerFactory emf = Util.getEmf();

    /**
     * Creates a new instance of ArbitroBean
     */
    public ArbitroBean() {
        controllerPais = new PaisFacade(Pais.class);
        controllerCiudad = new CiudadFacade(Ciudad.class);
        controllerArbitro = new ArbitroFacade(Arbitro.class);
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
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

    public DataModel getItemsArbitro() {

        if (items == null) {
            items = new ListDataModel(controllerArbitro.findAll());
        }
        return items;

    }

    public String gotoArbitroPage() {
        recreateModel();
        return "/admin/arbitro/arbitroes/list.xhtml";
    }

    private void recreateModel() {
        items = null;
    }

    private void recreateModelCiudad() {
        itemsCiudad = null;
    }

    private void recreateModelArbitro() {
        items = null;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public void setItemsCiudad(DataModel items) {
        this.itemsCiudad = items;
    }

    public void setItemsArbitro(DataModel items) {
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

    public SelectItem[] getArbitroAvailable() {
        return Util.getSelectItems(controllerArbitro.findAll());
    }

    public Arbitro getSelectedArbitro() {
        if (arbitro == null) {
            arbitro = new Arbitro();
        }
        return arbitro;
    }

    public String create() {
        try {
            if (controllerArbitro.findArbitro(arbitro.getPersonaId().getNombre()) != null) {
                Util.addErrorMessage("Arbitro ya existente, coloque otra");
                return null;
            } else {

                controllerArbitro.create(arbitro);
                Util.addSuccessMessage("Arbitro creada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la arbitro");
            return null;
        }
    }
    public String edit(){
        try {
            if(controllerArbitro.find(arbitro.getId()) == null ){
                Util.addErrorMessage("Arbitro no existe");
                return prepareList();
            } else {
            
                controllerArbitro.edit(arbitro);
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
        recreateModelArbitro();
        return "/admin/arbitro/arbitroes/list.xhtml";
    }

    public String returnAdminPage() {
        return "/admin/adminPage.xhtml";
    }

    public String prepareCreate() {
        arbitro = new Arbitro();
        return "/admin/arbitro/arbitroes/create.xhtml";
    }

    public String prepareEdit() {
        arbitro = (Arbitro) getItemsArbitro().getRowData();
        return "/admin/arbitro/arbitroes/edit.xhtml";
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
