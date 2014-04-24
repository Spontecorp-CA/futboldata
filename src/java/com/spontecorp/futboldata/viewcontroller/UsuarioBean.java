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
    private boolean changingPassword = false;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);

    public UsuarioBean() {
        //emf = Util.getEmf();
        controllerUser = new UserFacade();
        controllerPerfil = new PerfilFacade();
        //contextPath = FacesContext.getCurrentInstance().getExternalContext().getContextName();
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public User getSelected() {
//        if (user == null) {
//            user = new User();
//        }
        return selected;
    }
    
    public void setSelected(User selected){
        this.selected = selected;
    }

    public boolean isChangingPassword() {
        return changingPassword;
    }

//    public DataModel getItems() {
//        if (items == null) {
//            //items = new ListDataModel(controllerUser.findUserEntities());
//            items = new ListDataModel(controllerUser.findAll());
//        }
//        return items;
//    }
    
    public List<User> getItems(){
        if(items == null){
            items = controllerUser.findAll();
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

//    public String prepareCreate() {
//        user = new User();
//        return "create";
//    }
    
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
                items = null;
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear el usuario");
            //return null;
        }
    }
    
//    public String prepareEdit() {
//        user = (User) getItems().getRowData();
//        return "edit";
//    }

    public void edit(){
        try {
            if(controllerUser.findUsuario(selected.getUsuario()) == null ){
                Util.addErrorMessage("Usuario no existente");
                //return prepareList();
            } else {
                if(changingPassword){
                    selected.setPassword(SecurePassword.encript(selected.getPassword()));
                }
                controllerUser.edit(selected);
                Util.addSuccessMessage("Usuario editado con éxito");
                changingPassword = false;
                //return prepareList();
                items = null;
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar el usuario");
            //return null;
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
