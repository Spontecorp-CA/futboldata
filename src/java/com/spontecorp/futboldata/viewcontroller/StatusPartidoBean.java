/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.StatusPartido;
import com.spontecorp.futboldata.jpacontroller.StatusPartidoFacade;
import com.spontecorp.futboldata.utilities.Util;
import com.spontecorp.futboldata.utilities.Util.PersistAction;
import java.io.Serializable;
import java.util.Collections;
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
@Named("statusPartidoBean")
@SessionScoped
public class StatusPartidoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private StatusPartido selected;
    private List<StatusPartido> items;
    private List<StatusPartido> filteredStatusPartido = null;
    
    private final StatusPartidoFacade statusPartidoFacade;
            
    private static final Logger logger = LoggerFactory.getLogger(StatusPartidoBean.class);

    public StatusPartidoBean() {
        statusPartidoFacade = new StatusPartidoFacade();
    }

    public StatusPartido getSelected() {
        return selected;
    }

    public void setSelected(StatusPartido selected) {
        this.selected = selected;
    }

    public List<StatusPartido> getFilteredStatusPartido() {
        return filteredStatusPartido;
    }

    public void setFilteredStatusPartido(List<StatusPartido> filteredStatusPartido) {
        this.filteredStatusPartido = filteredStatusPartido;
    }

    public List<StatusPartido> getItems() {
        if(items == null){
            items = statusPartidoFacade.findAll();
        }
        return items;
    }
    
    public String returnAdminPage() {
        return "adminPage";
    }

    public StatusPartido prepareCreate() {
        selected = new StatusPartido();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareEdit() {
    }

    public void create() {
        if (!existeNombreStatus(selected.getNombre())) {
            List<StatusPartido> statusEnBD = statusPartidoFacade.findAll();
            int lastValue = statusEnBD.get(statusEnBD.size() - 1).getValue();
            selected.setStatus(Util.ACTIVO);
            selected.setValue(lastValue + 1);
            persist(PersistAction.CREATE, "Status de Partido creado con éxito");
            if (!Util.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            Util.addErrorMessage("Status de Partido ya existente, coloque un nombre diferente");
        }
    }

    private boolean existeNombreStatus(String nombre) {
        boolean result = true;
        try {
            if (statusPartidoFacade.findStatusPartido(nombre) == null) {
                result = false;
            }
        } catch (NoResultException e) {
            result = false;
        }

        return result;
    }

    public void edit() {
        persist(PersistAction.UPDATE, "Status de Partido actualizado con éxito");
        if (!Util.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                setEmbeddableKeys();
                if (persistAction == PersistAction.CREATE) {
                    statusPartidoFacade.create(selected);
                } else if (persistAction == PersistAction.UPDATE) {
                    statusPartidoFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
            } catch (Exception e) {
                logger.error("Error creando o editando el Status de Partido: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar el Status de Partido");
            }
        }
    }

    public StatusPartido getStatusPartido(java.lang.Integer id) {
        return statusPartidoFacade.find(id);
    }

    public List<StatusPartido> getItemsAvailableSelectMany() {
        return statusPartidoFacade.findAll();
    }

    public List<StatusPartido> getItemsAvailableSelectOne() {
        return statusPartidoFacade.findAll();
    }


    @FacesConverter(forClass = StatusPartido.class)
    public static class StatusPartidoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            StatusPartidoBean controller = (StatusPartidoBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "statusPartidoBean");
            return controller.getStatusPartido(getKey(value));
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
            if (object instanceof StatusPartido) {
                StatusPartido o = (StatusPartido) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), StatusPartido.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), StatusPartido.class.getName()});
                return null;
            }
        }

    }
}
