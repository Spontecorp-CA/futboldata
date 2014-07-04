package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Staff;
import com.spontecorp.futboldata.jpacontroller.StaffFacade;
import com.spontecorp.futboldata.viewcontroller.ConfigBean;
import static com.spontecorp.futboldata.utilities.Util.logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Staff.class)
public class StaffConverter implements Converter {

    StaffFacade controller = new StaffFacade();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || getKey(value)== null) {
            return null;
        }

        return controller.find(getKey(value));

    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = null;
        try {

            key = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            logger.error("Error al trasformar staff", e.getCause());
        }
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Staff) {
            Staff o = (Staff) object;
            return getStringKey(o.getId());
        } else {
            logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Staff.class.getName()});
            //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Staff.class.getName()});
            return null;
        }
    }

}
