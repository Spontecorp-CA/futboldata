/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.ClubFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.faces.model.SelectItem;
import javax.inject.Named;
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

    private Equipo selected;
    private Club club;
    private Categoria categoria;
    private List<Equipo> items = null;
    private List<Equipo> filteredEquipos;
    private SelectItem[] clubOptions;    
 
    private final CategoriaFacade controllerCategoria;
    private final ClubFacade controllerClub;
    private final EquipoFacade controllerEquipo;
    
    private static final Logger logger = LoggerFactory.getLogger(EquipoBean.class);

    public EquipoBean() {
        controllerEquipo = new EquipoFacade();
        controllerCategoria = new CategoriaFacade();
        controllerClub = new ClubFacade();
    }

    public Equipo getSelected() {
        return selected;
    }

    public void setSelected(Equipo selected) {
        this.selected = selected;
    }

    public SelectItem[] getClubOptions() {
        if(clubOptions == null){
            clubOptions = createClubOptions();
        }
        return clubOptions;
    }

    protected void setEmbeddableKeys() {
        club = selected.getClubId();
        categoria = selected.getCategoriaId();
    }

    protected void initializeEmbeddableKey() {
        
    }

    public List<Equipo> getItems(){
        if(items == null){
            items = controllerEquipo.findAll();
        }
        return items;
    }

    public List<Equipo> getFilteredEquipos() {
        return filteredEquipos;
    }

    public void setFilteredEquipos(List<Equipo> filteredEquipos) {
        this.filteredEquipos = filteredEquipos;
    }

    public void recreateModel() {
        items = null;
        selected = null;
    }

    public String gotoEquiposPage() {
        return "/admin/equipo/equipo/list";
    }

    public Equipo prepareCreate() {
        selected = new Equipo();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareEdit() {
        setEmbeddableKeys();
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

    public void create() {
        try {
            if ((selected.getId() != null) && (controllerEquipo.find(selected.getId()) != null)) {
                Util.addErrorMessage("Equipo ya existente, coloque otro");
                //return null;
            } else {
                controllerEquipo.create(selected);
                Util.addSuccessMessage("Equipo creado con éxito");
                recreateModel();
                //return prepareCreate();
            }
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage(), e);
            Util.addErrorMessage(e, "Error al crear el Equipo");
            //return null;
        }
    }

    public void edit() {
        try {
            if (controllerEquipo.find(selected.getId()) == null) {    
                Util.addErrorMessage("Equipo no existente, hay un error");
                //return null;
            } else {
                controllerEquipo.edit(selected);
                Util.addSuccessMessage("Equipo editado con éxito");
                //return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar el Equipo");
            //return null;
        }
    }

    private SelectItem[] createClubOptions() {
        List<Club> listClubes = controllerClub.findAll();
        SelectItem[] options = new SelectItem[listClubes.size() + 1];
        int i = 1;
        for (Club club : listClubes) {
            options[i] = new SelectItem(club.getNombre(), club.getNombre());
            i++;
        }
        
        return options;
    }
    
}
