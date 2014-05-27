package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.TemporadaCategoria;
import com.spontecorp.futboldata.jpacontroller.TemporadaCategoriaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = TemporadaCategoria.class,value = "temporadaCategoriaConverter")
public class TemporadaCategoriaConverter implements Converter{

    private TemporadaCategoriaFacade controller = null;
    
    private TemporadaCategoriaFacade getController(){
        if(controller == null){
            controller = new TemporadaCategoriaFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findTemporadaCategoria(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof TemporadaCategoria){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + TemporadaCategoria.class.getName());
        }
        
    }
    
}
