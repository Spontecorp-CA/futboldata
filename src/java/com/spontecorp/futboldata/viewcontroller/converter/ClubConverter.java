package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.jpacontroller.ClubFacade;
import com.spontecorp.futboldata.utilities.Util;
import com.spontecorp.futboldata.viewcontroller.LoginBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Club.class)
public class ClubConverter implements Converter {

    private ClubFacade controller = null;
    private LoginBean bean;

    private ClubFacade getController() {
        if (controller == null) {
            controller = new ClubFacade();
        }
        return controller;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        bean = (LoginBean) Util.findBean("loginBean");
        return getController().findClub(value, bean.getIdOrganizacion());
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Club) {
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Club.class.getName());
        }

    }

}
