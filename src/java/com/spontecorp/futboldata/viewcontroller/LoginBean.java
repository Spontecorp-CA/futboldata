/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Perfil;
import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.utilities.SecurePassword;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private User current;
    private boolean isAdmin;
    private boolean isSupervisor;
    private boolean isEditor;
    private boolean isConsultor;

    private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);
    private static final long serialVersionUID = 1L;

    public LoginBean() {
        resetPermissions();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getCurrent() {
        return current;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public boolean isIsSupervisor() {
        return isSupervisor;
    }

    public boolean isIsEditor() {
        return isEditor;
    }

    public boolean isIsConsultor() {
        return isConsultor;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String login() {
        char[] pswChar = password.toCharArray();
        FacesMessage msg = null;
        String result = "";
        current = SecurePassword.authenticate(username, pswChar);
        HttpSession session = Util.getSession();
        if (current != null) {
            session.setAttribute("username", username);
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", username);
            Perfil perfil = current.getPerfilId();
            switch (perfil.getId()) {
                case Util.ADMINISTRATOR:
                    isAdmin = true;
                    setAdminPermissions();
                    result = "admin/adminPage?faces-redirect=true";
                    break;
                case Util.SUPERVISOR:
                    isSupervisor = true;
                    setSuperPermissions();
                    result = "admin/adminPage?faces-redirect=true";
                    break;
                case Util.EDITOR:
                    isEditor = true;
                    setEditPermissions();
                    result = "admin/adminPage?faces-redirect=true";
                    break;
                case Util.CONSULTOR:
                    isConsultor = true;
                    result = "admin/adminPage?faces-redirect=true";
                    break;
            }
        } else {
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", "");
            session.setAttribute("username", "");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Ingreso", "Credenciales no válidas");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return result;
    }

    public String logout() {
        HttpSession session = Util.getSession();
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(username);
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        session.removeAttribute(username);
        session.invalidate();
        current = null;
        resetPermissions();
        return "/index?faces-redirect=true";
    }

    private void resetPermissions() {
        isAdmin = false;
        isConsultor = false;
        isEditor = false;
        isSupervisor = false;
    }
    
    private void setAdminPermissions(){
        isAdmin = true;
        isConsultor = true;
        isEditor = true;
        isSupervisor = true;
    }
    
    private void setSuperPermissions(){
        isConsultor = true;
        isEditor = true;
        isSupervisor = true;
    }
    
    private void setEditPermissions(){
        isConsultor = true;
        isEditor = true;
    }
}
