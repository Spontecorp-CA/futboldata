package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.TipoArbitro;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.TipoArbitroFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = TipoArbitro.class)
public class TipoArbitroConverter implements Converter{

    private TipoArbitroFacade controller = null;
    
    private TipoArbitroFacade getController(){
        if(controller == null){
            controller = new TipoArbitroFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findTipoArbitro(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof TipoArbitro){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + TipoArbitro.class.getName());
        }
        
    }
    
}
