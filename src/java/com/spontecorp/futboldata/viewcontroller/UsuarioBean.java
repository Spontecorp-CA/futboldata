/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.User;
import com.spontecorp.futboldata.jpacontroller.PerfilJpaController;
import com.spontecorp.futboldata.jpacontroller.extensions.PerfilJpaExt;
import com.spontecorp.futboldata.jpacontroller.extensions.UserJpaExt;
import com.spontecorp.futboldata.utilities.SecurePassword;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private DataModel items = null;

    private final UserJpaExt controllerUser;
    private final PerfilJpaController controllerPerfil;
    private final transient EntityManagerFactory emf = Util.getEmf();
    private final String contextPath;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);

    public UsuarioBean() {
        controllerUser = new UserJpaExt(emf);
        controllerPerfil = new PerfilJpaExt(emf);
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

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerUser.findUserEntities());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public String prepareList() {
        recreateModel();
        return "list";
    }

    public String gotoAdminPage() {
        recreateModel();
        return "list";
    }

    public String returnAdminPage() {
        return "adminPage";
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

    public SelectItem[] getPerfilesAvailable() {
        return Util.getSelectItems(controllerPerfil.findPerfilEntities());
    }

}
