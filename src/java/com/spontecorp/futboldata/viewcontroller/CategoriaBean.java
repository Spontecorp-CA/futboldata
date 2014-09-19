/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
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
@Named("categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Categoria> items = null;
    private List<Categoria> filteredCategorias = null;
    private Categoria selected;

    private final CategoriaFacade categoriaFacade;

    private static final Logger logger = LoggerFactory.getLogger(CategoriaBean.class);
    private final LoginBean bean;

    public CategoriaBean() {
        this.categoriaFacade = new CategoriaFacade();
        bean = (LoginBean) Util.findBean("loginBean");
    }

    public Categoria getSelected() {
        return selected;
    }

    public void setSelected(Categoria selected) {
        this.selected = selected;
    }

    public List<Categoria> getItems() {
        if (items == null) {
            items = categoriaFacade.findAll(bean.getIdOrganizacion());
        }
        return items;
    }

    public List<Categoria> getFilteredCategorias() {
        return filteredCategorias;
    }

    public void setFilteredCategorias(List<Categoria> filteredCategorias) {
        this.filteredCategorias = filteredCategorias;
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public Categoria prepareCreate() {
        selected = new Categoria();
        initializeEmbeddableKey();
        selected.setOrganizacionId(bean.getIdOrganizacion());
        return selected;
    }

    public void prepareEdit() {
        // No hace nada
    }

    public void create() {
        if (!existeNombreCategoria(selected.getNombre())) {
            persist(PersistAction.CREATE, "Categoria creada con éxito");
            if (!Util.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            Util.addErrorMessage("Categoria ya existente, coloque un nombre diferente");
        }
    }

    private boolean existeNombreCategoria(String nombre) {
        boolean result = true;
        try {
            if (categoriaFacade.findCategoria(nombre,bean.getIdOrganizacion()) == null) {
                result = false;
            }
        } catch (NoResultException e) {
            result = false;
        }

        return result;
    }

    public void edit() {
        persist(PersistAction.UPDATE, "Categoria actualizada con éxito");
        if (!Util.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    protected void setEmbeddableKeys(PersistAction persistAction) {
        // no hace nada
    }

    protected void initializeEmbeddableKey() {
        // no hace nada
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                setEmbeddableKeys(persistAction);
                if (persistAction == PersistAction.CREATE) {
                    categoriaFacade.create(selected);
                } else if (persistAction == PersistAction.UPDATE) {
                    categoriaFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
            } catch (Exception e) {
                logger.error("Error creando o editando la liga: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar la liga");
            }
        }
    }

    public Categoria getCategoria(java.lang.Integer id) {
        return categoriaFacade.find(id);
    }

    public List<Categoria> getItemsAvailableSelectMany() {
        return categoriaFacade.findAll(bean.getIdOrganizacion());
    }

    public List<Categoria> getItemsAvailableSelectOne() {
        return categoriaFacade.findAll(bean.getIdOrganizacion());
    }

    @FacesConverter(forClass = Categoria.class)
    public static class CategoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriaBean controller = (CategoriaBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriaBean");
            return controller.getCategoria(getKey(value));
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
            if (object instanceof Categoria) {
                Categoria o = (Categoria) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Categoria.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Categoria.class.getName()});
                return null;
            }
        }

    }
}
