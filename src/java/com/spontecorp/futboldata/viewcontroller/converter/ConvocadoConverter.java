package com.spontecorp.futboldata.viewcontroller.converter;

import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.jpacontroller.ConvocadoFacade;
import static com.spontecorp.futboldata.utilities.Util.logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgcastillo
 */
@FacesConverter(forClass = Convocado.class)
    public class ConvocadoConverter implements Converter {
        ConvocadoFacade controller = new ConvocadoFacade();
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0 || getKey(value)==null) {
                return null;
            }
            return controller.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
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
            if (object instanceof Convocado) {
                Convocado o = (Convocado) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Convocado.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Convocado.class.getName()});
                return null;
            }
        }

    }