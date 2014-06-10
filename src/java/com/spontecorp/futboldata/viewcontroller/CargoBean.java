/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Cargo;
import com.spontecorp.futboldata.jpacontroller.CargoFacade;
import com.spontecorp.futboldata.utilities.Util;
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
@Named("cargoBean")
@SessionScoped
public class CargoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cargo selected;
    private Cargo temporal;
    private List<Cargo> items;
    private List<Cargo> filteredCargos = null;

    private final CargoFacade cargoFacade;
    private static final Logger logger = LoggerFactory.getLogger(CargoBean.class);

    public CargoBean() {
        cargoFacade = new CargoFacade();
    }

    public Cargo getSelected() {
        return selected;
    }

    public void setSelected(Cargo selected) {
        this.selected = selected;
    }

    public Cargo getTemporal() {
        return temporal;
    }

    public void setTemporal(Cargo temporal) {
        this.temporal = temporal;
    }

    public List<Cargo> getFilteredCargos() {
        return filteredCargos;
    }

    public void setFilteredCargos(List<Cargo> filteredCargoes) {
        this.filteredCargos = filteredCargoes;
    }

    public List<Cargo> getItems() {
        if (items == null) {
            items = cargoFacade.findAll();
        }
        return items;
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public Cargo prepareCreate() {
        selected = new Cargo();
        return selected;
    }

    public void prepareEdit() {
        temporal = (Cargo) cargoFacade.findCargo(selected.getNombre());
        temporal.setNombre(selected.getNombre());
        temporal.setStatus(selected.getStatus());
    }

    public void create() {
        temporal = (Cargo) cargoFacade.findCargo(selected.getNombre());
        if (temporal == null) {
            persist(Util.PersistAction.CREATE, "Posición creada con éxito");
            items = null;
        } else {
            temporal.setNombre(selected.getNombre());
            temporal.setStatus(selected.getStatus());
            if (!existeNombreCargo(selected.getNombre())) {
                persist(Util.PersistAction.CREATE, "Posición creada con éxito");
                items = null;
            } else {
                Util.addErrorMessage("Posición ya existente, coloque un nombre diferente");
                items = null;
            }
        }
    }

    public void edit() {
        
            cargoFacade.edit(selected);
            Util.addSuccessMessage("Se edito exitosamente el Cargo");
            items = null;
        
    }

    private boolean existeNombreCargo(String nombre) {
        boolean result = true;
        try {
            Cargo pos = (Cargo) cargoFacade.findCargo(nombre);
            if (pos != null && !pos.getNombre().equals(temporal.getNombre())) {
                result = false;
            }
        } catch (Exception e) {
            result = true;
        }
        return result;
    }

    private void persist(Util.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction == Util.PersistAction.CREATE) {
                    cargoFacade.create(selected);
                } else if (persistAction == Util.PersistAction.UPDATE) {
                    cargoFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
            } catch (Exception e) {
                logger.error("Error creando o editando la posición: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar la posición");
            }
        }
    }

    public Cargo getCargo(java.lang.Integer id) {
        return cargoFacade.find(id);
    }

    @FacesConverter(forClass = Cargo.class)
    public static class CargoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CargoBean controller = (CargoBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cargoBean");
            return controller.getCargo(getKey(value));
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
            if (object instanceof Cargo) {
                Cargo o = (Cargo) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cargo.class.getName()});
                return null;
            }
        }

    }
}
