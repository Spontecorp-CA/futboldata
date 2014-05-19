/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("ligaBean")
@SessionScoped
public class LigaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private List<Competicion> items = null;
    private List<Competicion> filteredLigas = null;
    private Competicion selected;
    
    private final CompeticionFacade ligaController; 

    private static final Logger logger = LoggerFactory.getLogger(CategoriaBean.class);

    public LigaBean() {
        this.ligaController = new CompeticionFacade();
    }

    public Competicion getSelected() {
        return selected;
    }

    public void setSelected(Competicion selected) {
        this.selected = selected;
    }

    public List<Competicion> getItems() {
        if(items == null){
            items = ligaController.findAll();
        }
        return items;
    }

    public List<Competicion> getFilteredLigas() {
        return filteredLigas;
    }

    public void setFilteredLigas(List<Competicion> filteredLigas) {
        this.filteredLigas = filteredLigas;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public String returnAdminPage() {
        return "adminPage";
    }
    
    public Competicion prepareCreate() {
        selected = new Competicion();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void prepareEdit(){
        setEmbeddableKeys();
    }
    
    public void create() {
        persist(PersistAction.CREATE, "Liga creada con éxito");
        if (!Util.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, "Liga actualiizada con éxito");
    }
    
    private void persist(PersistAction persistAction, String successMessage){
        if(selected != null){
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE){
                    ligaController.create(selected);
                } else if (persistAction == PersistAction.UPDATE){
                    ligaController.edit(selected);
                }
            } catch (Exception e) {
                logger.error("Error creando o editando la liga: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar la liga");
            }
        }
    }
    
    public Competicion getCompeticion(java.lang.Integer id) {
        return ligaController.find(id);
    }

    public List<Competicion> getItemsAvailableSelectMany() {
        return ligaController.findAll();
    }

    public List<Competicion> getItemsAvailableSelectOne() {
        return ligaController.findAll();
    }
    
    @FacesConverter(forClass = Competicion.class)
    public static class CompeticionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LigaBean controller = (LigaBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ligaBean");
            return controller.getCompeticion(getKey(value));
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
            if (object instanceof Competicion) {
                Competicion o = (Competicion) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Competicion.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Competicion.class.getName()});
                return null;
            }
        }

    }
}
