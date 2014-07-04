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
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
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
        if (clubOptions == null) {
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

    public List<Equipo> getItems() {
        if (items == null) {
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
        selected.setLogo("vacio");
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

//    public SelectItem[] getCategoriaAvailable() {
//        return Util.getSelectItems(controllerCategoria.findAll());
//    }
    
    public List<Categoria> getCategoriaAvailable(){
        return controllerCategoria.findAll();
    }

    public Equipo getEquipo(java.lang.Integer id) {
        return controllerEquipo.find(id);
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

    public void handleFileUpload(FileUploadEvent event) {

        long lDateTime = new Date().getTime();
        System.out.println("Date() - Time in milliseconds: " + lDateTime);
        String nombreArchivo = "equipo" + lDateTime;
        Util.subirArchivo(event, "equipo/", nombreArchivo);
        selected.setLogo(nombreArchivo);

    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "equipo/";
        return host;
    }

    @FacesConverter(forClass = Equipo.class,value = "equipo")
    public static class EquipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0 || getKey(value)== null) {
                return null;
            }
            EquipoBean controller = (EquipoBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "equipoBean");
            return controller.getEquipo(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            try {
                            key = Integer.valueOf(value);
            } catch (NumberFormatException e) {
                logger.error("No es compatible el String para trasformar el equipo");
            return null;
            }

            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Equipo) {
                Equipo o = (Equipo) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Equipo.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Equipo.class.getName()});
                return null;
            }
        }

    }
}
