package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Localidad;
import com.spontecorp.futboldata.jpacontroller.LocalidadFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Localidad.class)
public class LocalidadConverter implements Converter{

    private LocalidadFacade controller = null;
    
    private LocalidadFacade getController(){
        if(controller == null){
            controller = new LocalidadFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findLocalidad(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof Localidad){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Ciudad.class.getName());
        }
        
    }
    
}
