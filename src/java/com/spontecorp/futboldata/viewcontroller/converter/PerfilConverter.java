package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Perfil;
import com.spontecorp.futboldata.jpacontroller.extensions.PerfilJpaExt;
import com.spontecorp.futboldata.utilities.Util;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Perfil.class)
public class PerfilConverter implements Converter{

    private PerfilJpaExt controller = null;
    
    private PerfilJpaExt getController(){
        if(controller == null){
            controller = new PerfilJpaExt(Util.getEmf());
        }
        return controller;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }        
        return getController().findPerfil(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if(value instanceof Perfil){
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Perfil.class.getName());
        }
        
    }
    
}
