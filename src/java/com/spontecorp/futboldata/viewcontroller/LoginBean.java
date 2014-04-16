/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class LoginBean implements Serializable{
    
    private String username;
    private String password;
    private User current;
    
    private static final int ADMINISTRATOR = 1;
    private static final int GERENTE = 2;
    private static final int USUARIO = 3;
    
    private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

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
                case ADMINISTRATOR:
                    result = "admin/adminPage?faces-redirect=true";
                    break;
                case GERENTE:
                    result = "manager/managerPage?faces-redirect=true";
                    break;
                case USUARIO:
                    result = "usuario/userPage?faces-redirect=true";
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
        return "/index?faces-redirect=true";
    }
}
