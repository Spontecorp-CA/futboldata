/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.TipoEvento;
import com.spontecorp.futboldata.jpacontroller.TipoEventoFacade;
import com.spontecorp.futboldata.utilities.Util;
import com.spontecorp.futboldata.utilities.Util.PersistAction;
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
@Named("tipoEventoBean")
@SessionScoped
public class TipoEventoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoEvento selected;
    private List<TipoEvento> items;
    private List<TipoEvento> filteredEvento = null;
       

    private final TipoEventoFacade tipoEventoFacade;

    private static final Logger logger = LoggerFactory.getLogger(TipoEventoBean.class);
    private final LoginBean bean;

    public TipoEventoBean() {
        tipoEventoFacade = new TipoEventoFacade();
        bean = (LoginBean) Util.findBean("loginBean");    

    }

    public TipoEvento getSelected() {
        return selected;
    }

    public void setSelected(TipoEvento selected) {
        this.selected = selected;
    }

    public List<TipoEvento> getFilteredEvento() {
        return filteredEvento;
    }

    public void setFilteredEvento(List<TipoEvento> filteredEvento) {
        this.filteredEvento = filteredEvento;
    }

    public List<TipoEvento> getItems() {
            items = tipoEventoFacade.findAll(bean.getIdOrganizacion());
        return items;
    }

    public List<TipoEvento> getItemsT(char tipoQueAplica) {
            items = tipoEventoFacade.findAll(bean.getIdOrganizacion(),tipoQueAplica);       
        return items;
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public TipoEvento prepareCreate() {
        selected = new TipoEvento();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareEdit() {
    }

    public void create() {
        if (!existeNombreStatus(selected.getNombre())) {
            selected.setStatus(Util.ACTIVO);
            selected.setOrganizacionId(bean.getIdOrganizacion());
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
            if (tipoEventoFacade.findEvento(nombre, bean.getIdOrganizacion()) == null) {
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
                    tipoEventoFacade.create(selected);
                } else if (persistAction == PersistAction.UPDATE) {
                    tipoEventoFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
            } catch (Exception e) {
                logger.error("Error creando o editando el Status de Partido: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar el Status de Partido");
            }
        }
    }

    public TipoEvento getEvento(java.lang.Integer id) {
        return tipoEventoFacade.find(id);
    }

    public List<TipoEvento> getItemsAvailableSelectMany() {
        return tipoEventoFacade.findAll(bean.getIdOrganizacion());
    }

    public List<TipoEvento> getItemsAvailableSelectOne() {
        return tipoEventoFacade.findAll(bean.getIdOrganizacion());
    }

    @FacesConverter(forClass = TipoEvento.class)
    public static class EventoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoEventoBean controller = (TipoEventoBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoEventoBean");
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
            if (object instanceof TipoEvento) {
                TipoEvento o = (TipoEvento) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoEvento.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoEvento.class.getName()});
                return null;
            }
        }

    }
}
