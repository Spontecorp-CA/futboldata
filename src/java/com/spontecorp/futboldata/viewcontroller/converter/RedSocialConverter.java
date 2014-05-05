package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = RedSocial.class)
public class RedSocialConverter implements Converter{

    private RedSocialFacade controller = null;
    
    private RedSocialFacade getController(){
        if(controller == null){
            controller = new RedSocialFacade();
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findRedSocial(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof RedSocial){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + RedSocial.class.getName());
        }
        
    }
    
}
