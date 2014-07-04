/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Evento;
import com.spontecorp.futboldata.jpacontroller.EventoFacade;
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
@Named("eventoBean")
@SessionScoped
public class EventoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Evento selected;
    private List<Evento> items;
    private List<Evento> filteredEvento = null;
    
    private final EventoFacade eventoFacade;
            
    private static final Logger logger = LoggerFactory.getLogger(EventoBean.class);

    public EventoBean() {
        eventoFacade = new EventoFacade();
    }

    public Evento getSelected() {
        return selected;
    }

    public void setSelected(Evento selected) {
        this.selected = selected;
    }

    public List<Evento> getFilteredEvento() {
        return filteredEvento;
    }

    public void setFilteredEvento(List<Evento> filteredEvento) {
        this.filteredEvento = filteredEvento;
    }

    public List<Evento> getItems() {
        if(items == null){
            items = eventoFacade.findAll();
        }
        return items;
    }
    
    public String returnAdminPage() {
        return "adminPage";
    }

    public Evento prepareCreate() {
        selected = new Evento();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareEdit() {
    }

    public void create() {
        if (!existeNombreStatus(selected.getNombre())) {
            selected.setStatus(Util.ACTIVO);
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
            if (eventoFacade.findEvento(nombre) == null) {
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
                    eventoFacade.create(selected);
                } else if (persistAction == PersistAction.UPDATE) {
                    eventoFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
            } catch (Exception e) {
                logger.error("Error creando o editando el Status de Partido: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar el Status de Partido");
            }
        }
    }

    public Evento getEvento(java.lang.Integer id) {
        return eventoFacade.find(id);
    }

    public List<Evento> getItemsAvailableSelectMany() {
        return eventoFacade.findAll();
    }

    public List<Evento> getItemsAvailableSelectOne() {
        return eventoFacade.findAll();
    }


    @FacesConverter(forClass = Evento.class)
    public static class EventoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EventoBean controller = (EventoBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "eventoBean");
            return controller.getEvento(getKey(value));
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
            if (object instanceof Evento) {
                Evento o = (Evento) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Evento.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Evento.class.getName()});
                return null;
            }
        }

    }
}
