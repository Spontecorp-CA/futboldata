package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.jpacontroller.TelefonoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Telefono.class)
public class TelefonoConverter implements Converter{

    private TelefonoFacade controller = null;
    
    private TelefonoFacade getController(){
        if(controller == null){
            controller = new TelefonoFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findTelefono(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof Telefono){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Telefono.class.getName());
        }
        
    }
    
}
