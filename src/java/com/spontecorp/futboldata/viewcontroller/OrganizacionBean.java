/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Organizacion;
import com.spontecorp.futboldata.jpacontroller.OrganizacionFacade;
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
@Named("organizacionBean")
@SessionScoped
public class OrganizacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Organizacion selected;
    private List<Organizacion> items;
    private List<Organizacion> filteredEvento = null;

    private final OrganizacionFacade organizacionFacade;

    private static final Logger logger = LoggerFactory.getLogger(OrganizacionBean.class);
    private final LoginBean bean;

    public OrganizacionBean() {
        organizacionFacade = new OrganizacionFacade();
        bean = (LoginBean) Util.findBean("loginBean");
    }

    public Organizacion getSelected() {
        return selected;
    }

    public void setSelected(Organizacion selected) {
        this.selected = selected;
    }

    public List<Organizacion> getFilteredEvento() {
        return filteredEvento;
    }

    public void setFilteredEvento(List<Organizacion> filteredEvento) {
        this.filteredEvento = filteredEvento;
    }

    public List<Organizacion> getItems() {
        if (items == null) {
            items = organizacionFacade.findAll(bean.getIdOrganizacion());
        }
        return items;
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public Organizacion prepareCreate() {
        selected = new Organizacion();
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
            if (organizacionFacade.findEvento(nombre) == null) {
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
                    organizacionFacade.create(selected);
                } else if (persistAction == PersistAction.UPDATE) {
                    organizacionFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
            } catch (Exception e) {
                logger.error("Error creando o editando el Status de Partido: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar el Status de Partido");
            }
        }
    }

    public Organizacion getEvento(java.lang.Integer id) {
        return organizacionFacade.find(id);
    }

    public List<Organizacion> getItemsAvailableSelectMany() {
        return organizacionFacade.findAll(bean.getIdOrganizacion());
    }

    public List<Organizacion> getItemsAvailableSelectOne() {
        return organizacionFacade.findAll(bean.getIdOrganizacion());
    }

    @FacesConverter(forClass = Organizacion.class)
    public static class EventoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrganizacionBean controller = (OrganizacionBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "organizacionBean");
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
            if (object instanceof Organizacion) {
                Organizacion o = (Organizacion) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Organizacion.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Organizacion.class.getName()});
                return null;
            }
        }

    }
}
