/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.jpacontroller.PerfilFacade;
import com.spontecorp.futboldata.jpacontroller.UserFacade;
import com.spontecorp.futboldata.utilities.SecurePassword;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

//    private User user;
//    private DataModel items = null;
    private List<User> items = null;
    private User selected;

    //private final UserJpaExt controllerUser;
    private final UserFacade controllerUser;
    private final PerfilFacade controllerPerfil;
    //private final transient EntityManagerFactory emf; 
    //private final String contextPath;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);

    public UsuarioBean() {
        controllerUser = new UserFacade();
        controllerPerfil = new PerfilFacade();
        //contextPath = FacesContext.getCurrentInstance().getExternalContext().getContextName();
    }

    public User getSelected() {
        return selected;
    }
    
    public void setSelected(User selected){
        this.selected = selected;
    }

    public List<User> getItems(){
        if(items == null){
            items = controllerUser.findAll();
        }
        return items;
    }

    private void recreateModel() {
        this.selected = null;
        items = null;
    }

    public String prepareList() {
        recreateModel();
        return "/admin/usuarios/list";
    }

    public String gotoAdminPage() {
        recreateModel();
        return "listUsuarios";
    }

    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public User prepareCreate(){
        selected = new User();
        return selected;
    }

    public void create() {
        try {
            if (controllerUser.findUsuario(selected.getUsuario()) != null) {
                Util.addErrorMessage("Usuario ya existente, coloque otro");
                //return null;
            } else {
                selected.setPassword(SecurePassword.encript(selected.getPassword()));
                controllerUser.create(selected);
                Util.addSuccessMessage("Usuario creado con éxito");
                //return prepareCreate();
                //items = null;
                recreateModel();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear el usuario");
            //return null;
        }
    }
    
    public void edit(){
        try {
            if(controllerUser.findUsuario(selected.getUsuario()) == null ){
                Util.addErrorMessage("Usuario no existente");
            } else {
                controllerUser.edit(selected);
                Util.addSuccessMessage("Usuario editado con éxito");
                //items = null;
                recreateModel();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar el usuario");
        }
    }
    
    public void changePassword(){
        try {
            if (controllerUser.findUsuario(selected.getUsuario()) == null) {
                Util.addErrorMessage("Usuario no existente");
            } else {
                selected.setPassword(SecurePassword.encript(selected.getPassword()));
                controllerUser.edit(selected);
                Util.addSuccessMessage("Password cambiado con éxito");
                //items = null;
                recreateModel();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error cambiando el password");
        }
    }
    
    public String preparePasswordChange(){
//        selected = (User) getItems().getRowData();
//        changingPassword = true;
        return "edit";
    }
            
    public SelectItem[] getPerfilesAvailable() {
        return Util.getSelectItems(controllerPerfil.findAll());
    }

}
