/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Cargo;
import com.spontecorp.futboldata.jpacontroller.CargoFacade;
import com.spontecorp.futboldata.utilities.Util;
import com.spontecorp.futboldata.viewcontroller.LoginBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sponte07
 */
@FacesConverter(forClass = Cargo.class)
public class CargoConverter implements Converter {

    private CargoFacade controller;

    private CargoFacade getController() {
        if (controller == null) {
            controller = new CargoFacade();
        }
        return controller;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LoginBean bean = (LoginBean) Util.findBean("loginBean");
        if (value == null || value.length() == 0) {
            return null;
        }
        return getController().findCargo(value,bean.getIdOrganizacion());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Cargo) {
            return value.toString();
        } else {
            throw new IllegalArgumentException("El objecto " + value + " es de tipo " + value.getClass().getName() + "; se espera: " + Cargo.class.getName());
        }
    }

}
