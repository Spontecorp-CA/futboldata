/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.ClubFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacade;
import com.spontecorp.futboldata.jpacontroller.EmailFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.TelefonoFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("equipoBean")
@SessionScoped
public class EquipoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Equipo equipo;
    private DataModel items = null;

    private static final Logger logger = LoggerFactory.getLogger(EquipoBean.class);

    private CategoriaFacade controllerCategoria;
    private ClubFacade controllerClub;
    private final EquipoFacade controllerEquipo;

    public EquipoBean() {
        controllerEquipo = new EquipoFacade();
        controllerCategoria = new CategoriaFacade();
        controllerClub = new ClubFacade();

    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Equipo getSelected() {
        if (equipo == null) {
            equipo = new Equipo();

        }
        return equipo;
    }

    protected void setEmbeddableKeys() {

    }

    protected void initializeEmbeddableKey() {

    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerEquipo.findAll());
        }
        return items;
    }

    public void recreateModel() {
        items = null;
        equipo = null;
    }

    public String gotoEquiposPage() {
        return "list";
    }

    public String prepareCreate() {

        equipo = new Equipo();
        initializeEmbeddableKey();

        return "create";
    }

    public String prepareEdit() {
        initializeEmbeddableKey();
        equipo = (Equipo) getItems().getRowData();

        return "edit";
    }

    public String prepareList() {
        return "list";
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public SelectItem[] getClubAvailable() {
        return Util.getSelectItems(controllerClub.findAll());
    }

    public SelectItem[] getCategoriaAvailable() {
        return Util.getSelectItems(controllerCategoria.findAll());
    }

    public String create() {
        try {
            if (controllerEquipo.findEquipo(equipo.getNombre()) != null) {
                Util.addErrorMessage("Equipo ya existente, coloque otro");
                return null;
            } else {

                controllerEquipo.create(equipo);
                Util.addSuccessMessage("Categoría creada con éxito");
                recreateModel();
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la categoría");
            return null;
        }
    }

    public String edit() {
        try {
            if (controllerEquipo.findEquipo(equipo.getNombre()) == null) {
                Util.addErrorMessage("Equipo no existente, hay un error");
                return null;
            } else {

                controllerEquipo.edit(equipo);
                Util.addSuccessMessage("Categoría editada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la categoría");
            return null;
        }
    }

}
