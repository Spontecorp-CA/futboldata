package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Asociacion.class)
public class AsociacionConverter implements Converter{

    private AsociacionFacade controller = null;
    
    private AsociacionFacade getController(){
        if(controller == null){
            controller = new AsociacionFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findAsociacion(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof Asociacion){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Asociacion.class.getName());
        }
        
    }
    
}
