package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Competicion.class)
public class CompeticionConverter implements Converter{

    private CompeticionFacade controller = null;
    
    private CompeticionFacade getController(){
        if(controller == null){
            controller = new CompeticionFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findCompeticion(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof Competicion){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Competicion.class.getName());
        }
        
    }
    
}
