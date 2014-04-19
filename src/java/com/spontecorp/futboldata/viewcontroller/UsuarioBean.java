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
import javax.enterprise.context.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
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

    private User user;
    private DataModel items = null;

    //private final UserJpaExt controllerUser;
    private final UserFacade controllerUser;
    private final PerfilFacade controllerPerfil;
    private final transient EntityManagerFactory emf; 
    private final String contextPath;
    private boolean changingPassword = false;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);

    public UsuarioBean() {
        emf = Util.getEmf();
        controllerUser = new UserFacade();
        controllerPerfil = new PerfilFacade();
        contextPath = FacesContext.getCurrentInstance().getExternalContext().getContextName();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSelected() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public boolean isChangingPassword() {
        return changingPassword;
    }

    public DataModel getItems() {
        if (items == null) {
            //items = new ListDataModel(controllerUser.findUserEntities());
            items = new ListDataModel(controllerUser.findAll());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public String prepareList() {
        recreateModel();
        changingPassword = false;
        return "/admin/usuarios/list";
    }

    public String gotoAdminPage() {
        recreateModel();
        return "listUsuarios";
    }

    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public String prepareCreate() {
        user = new User();
        return "create";
    }

    public String create() {
        try {
            if (controllerUser.findUsuario(user.getUsuario()) != null) {
                Util.addErrorMessage("Usuario ya existente, coloque otro");
                return null;
            } else {
                user.setPassword(SecurePassword.encript(user.getPassword()));
                controllerUser.create(user);
                Util.addSuccessMessage("Usuario creado con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear el usuario");
            return null;
        }
    }
    
    public String prepareEdit() {
        user = (User) getItems().getRowData();
        return "edit";
    }

    public String edit(){
        try {
            if(controllerUser.findUsuario(user.getUsuario()) == null ){
                Util.addErrorMessage("Usuario no existente");
                return prepareList();
            } else {
                if(changingPassword){
                    user.setPassword(SecurePassword.encript(user.getPassword()));
                }
                controllerUser.edit(user);
                Util.addSuccessMessage("Usuario editado con éxito");
                changingPassword = false;
                return prepareList();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar el usuario");
            return null;
        }
    }
    
    public String preparePasswordChange(){
        user = (User) getItems().getRowData();
        changingPassword = true;
        return "edit";
    }
            
    public SelectItem[] getPerfilesAvailable() {
        return Util.getSelectItems(controllerPerfil.findAll());
    }

}
