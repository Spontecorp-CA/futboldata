package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.EquipoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Equipo.class)
public class EquipoConverter implements Converter{

    private EquipoFacade controller = null;
    
    private EquipoFacade getController(){
        if(controller == null){
            controller = new EquipoFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findEquipo(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof Equipo){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Equipo.class.getName());
        }
        
    }
    
}
