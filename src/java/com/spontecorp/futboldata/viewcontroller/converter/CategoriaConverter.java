package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter{

    private CategoriaFacade controller = null;
    
    private CategoriaFacade getController(){
        if(controller == null){
            controller = new CategoriaFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findCategoria(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof Categoria){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Categoria.class.getName());
        }
        
    }
    
}
