/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Posicion;
import com.spontecorp.futboldata.jpacontroller.PosicionFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("posicionBean")
@SessionScoped
public class PosicionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Posicion selected;
    private List<Posicion> items;
    private List<Posicion> filteredPosiciones = null;

    private final PosicionFacade posicionFacade;
    private static final Logger logger = LoggerFactory.getLogger(PosicionBean.class);

    public PosicionBean() {
        posicionFacade = new PosicionFacade();
    }

    public Posicion getSelected() {
        return selected;
    }

    public void setSelected(Posicion selected) {
        this.selected = selected;
    }

    public List<Posicion> getFilteredPosiciones() {
        return filteredPosiciones;
    }

    public void setFilteredPosiciones(List<Posicion> filteredPosiciones) {
        this.filteredPosiciones = filteredPosiciones;
    }

    public List<Posicion> getItems() {
        if (items == null) {
            items = posicionFacade.findAll();
        }
        return items;
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public Posicion prepareCreate() {
        selected = new Posicion();
        return selected;
    }

    public void prepareEdit() {
    }

    public void create() {
        if (!existeNombrePosicionCrear(selected.getNombre())) {
            persist(Util.PersistAction.CREATE, "Posición creada con éxito");
            if (!Util.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            Util.addErrorMessage("Posición ya existente, coloque un nombre diferente");
        }
    }

    public void edit() {
       persist(Util.PersistAction.UPDATE, "Posición editada con éxito");
    }
    
    private boolean existeNombrePosicionCrear(String nombre){
        boolean result = false;
        Posicion pos = (Posicion) posicionFacade.findPosicion(nombre);
        if (pos != null){
            result = true;
        }
        return result;
    }

    private void persist(Util.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction == Util.PersistAction.CREATE) {
                    posicionFacade.create(selected);
                } else if (persistAction == Util.PersistAction.UPDATE) {
                    posicionFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
                items = null;
            } catch (Exception e) {
                logger.error("Error creando o editando la posición: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar la posición");
            }
        }
    }
    
    public Posicion getPosicion(java.lang.Integer id) {
        return posicionFacade.find(id);
    }
    
    @FacesConverter(forClass = Posicion.class)
    public static class PosicionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PosicionBean controller = (PosicionBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "posicionBean");
            return controller.getPosicion(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
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
            if (object instanceof Posicion) {
                Posicion o = (Posicion) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Posicion.class.getName()});
                return null;
            }
        }

    }
}
