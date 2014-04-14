package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Perfil;
import com.spontecorp.futboldata.jpacontroller.EmailFacade;
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
@FacesConverter(forClass = Email.class)
public class EmailConverter implements Converter {

    private EmailFacade controller = null;

    private EmailFacade getController() {
        if (controller == null) {
            controller = new EmailFacade(Email.class);
        }
        return controller;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return getController().findEmail(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Email) {
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Email.class.getName());
        }

    }

}
