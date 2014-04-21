/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.jpacontroller.ClubFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("clubBean")
@SessionScoped
public class ClubBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Club club;
    private DataModel items = null;
    private final ClubFacade controllerClub;   
    
    private static final Logger logger = LoggerFactory.getLogger(ClubBean.class);

    public ClubBean() {
        controllerClub = new ClubFacade();
    }    

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    
    public Club getSelected(){
        if(club == null){
            club = new Club();
        }
        return club;
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerClub.findAll());
        }
        return items;
    }
    
    public void recreateModel(){
        items = null;
        club = null;
    }
    public String gotoClubsPage() {
        return "list";
    }
    
    public String prepareCreate(){
        club = new Club();
        return "create";
    }
    
    public String prepareEdit(){
        club = (Club) getItems().getRowData();
        return "edit";
    }
    
    public String prepareList() {
        return "list";
    }
    
    public String returnAdminPage() {
        return "adminPage";
    }
    
    public String create(){
        try {
            if(controllerClub.findClub(club.getNombre()) != null){
                Util.addErrorMessage("Club ya existente, coloque otro");
                return null;
            } else {
                controllerClub.create(club);
                Util.addSuccessMessage("Categoría creada con éxito");
                recreateModel();
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la categoría");
            return null;
        }
    }
    
    public String edit(){
        try {
            if (controllerClub.findClub(club.getNombre()) == null) {
                Util.addErrorMessage("Club no existente, hay un error");
                return null;
            } else {
                controllerClub.edit(club);
                Util.addSuccessMessage("Categoría editada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la categoría");
            return null;
        }
    }
    
//    public void persist(Object object) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }
}
