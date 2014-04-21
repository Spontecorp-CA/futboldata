/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.TipoArbitro;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.TipoArbitroFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sponte03
 */
@Named("TipoArbitroBean")
@SessionScoped
public class TipoArbitroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoArbitro tipoArbitro;
    private transient DataModel itemsTipoArbitro = null;
    private final TipoArbitroFacade controllerTipoArbitro;
    private final transient EntityManagerFactory emf = Util.getEmf();

    /**
     * Creates a new instance of LocalidadBean
     */
    public TipoArbitroBean() {
        controllerTipoArbitro = new TipoArbitroFacade();
    }

    public String edit() {
        try {
            if (controllerTipoArbitro.find(tipoArbitro.getId()) == null) {
                Util.addErrorMessage("TipoArbitro no existente");
                return prepareList();
            } else {

                controllerTipoArbitro.edit(tipoArbitro);
                Util.addSuccessMessage("TipoArbitro editado con éxito");

                return prepareList();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la tipoArbitro");
            return null;
        }
    }

    public TipoArbitro getTipoArbitro() {
        return tipoArbitro;
    }

    public void setTipoArbitro(TipoArbitro tipoArbitro) {
        this.tipoArbitro = tipoArbitro;
    }

    public DataModel getItemsTipoArbitro() {

        if (itemsTipoArbitro == null) {
            itemsTipoArbitro = new ListDataModel(controllerTipoArbitro.findAll());
        }
        return itemsTipoArbitro;

    }

    private void recreateModelTipoArbitro() {
        itemsTipoArbitro = null;
    }

    public void setItemsTipoArbitro(DataModel items) {
        this.itemsTipoArbitro = items;
    }

    public TipoArbitro getSelectedTipoArbitro() {
        if (tipoArbitro == null) {
            tipoArbitro = new TipoArbitro();
        }
        return tipoArbitro;
    }

    public void setSelectedTipoArbitro(TipoArbitro tipoArbitro) {
        this.tipoArbitro = tipoArbitro;
    }

    public String create() {
        try {
            if (controllerTipoArbitro.findTipoArbitro(tipoArbitro.getNombre()) != null) {
                Util.addErrorMessage("TipoArbitro ya existente, coloque otra");
                return null;
            } else {

                controllerTipoArbitro.create(tipoArbitro);
                Util.addSuccessMessage("TipoArbitro creada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la tipoArbitro");
            return null;
        }
    }

    public String prepareList() {
        recreateModelTipoArbitro();
        return "/admin/asociacion/arbitro/tipo_arbitro/list.xhtml";
    }

    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public String prepareCreate() {
        tipoArbitro = new TipoArbitro();
        return "/admin/asociacion/arbitro/tipo_arbitro/create.xhtml";
    }

    public String prepareEdit() {
        tipoArbitro = (TipoArbitro) getItemsTipoArbitro().getRowData();
        return "/admin/asociacion/arbitro/tipo_arbitro/edit.xhtml";
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
