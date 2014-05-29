package com.spontecorp.futboldata.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Casper
 */
public class Util implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int ACTIVO = 1;
    public static final int INACTIVO = 0;
    public static final String PERSISTENCE_UNIT = "FutbolDataPU";

    public static final int ADMINISTRATOR = 1;
    public static final int SUPERVISOR = 2;
    public static final int EDITOR = 3;
    public static final int CONSULTOR = 4;
    public static final String rutaRelativa = "resources\\images\\";
    public static final Logger logger = LoggerFactory.getLogger(Util.class);
    
    // dirección de imagenes para máquinas de sponte03 y sponte07
    //public static final String STORAGE_ROOT = "C:/Program Files/Apache Software Foundation/Apache Tomcat 7.0.41/webapps/imagenes/";
    
    // dirección de imagenes para máquinas de sponte08 y producción
    public static final String STORAGE_ROOT = "C:/Servidores/apache-tomcat-7.0.41/webapps/imagenes/";
    private static String hostImagen;

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public static String getHostImagen() {
        HttpServletRequest request = getRequest();
        return request.getRequestURL().toString().replace(request.getRequestURI().substring(0), "") + "/imagenes/";
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
        return session.getAttribute("username").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }

    public static EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    public static SelectItem[] getSelectItems(List<?> list) {
        int size = list.size() + 1;
        SelectItem[] items = new SelectItem[size];
        items[0] = new SelectItem("", "---");
        int i = 1;
        for (Object obj : list) {
            items[i] = new SelectItem(obj, obj.toString());
            i++;
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        //FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void subirArchivo(FileUploadEvent event, String ruta, String nombreArchivo) {

        try {
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            File path = new File(STORAGE_ROOT + ruta);
            if (!path.exists()) {
                path.mkdirs();
            }

            File targetFolder = new File(path, nombreArchivo);
            logger.debug("Lugar donde guardo la imagen: " + targetFolder);
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(targetFolder);

            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            inputStream.close();

            out.flush();
            out.close();

        } catch (IOException e) {
            logger.debug("Error al cargar la imagen :", e);
        }

    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }
}
