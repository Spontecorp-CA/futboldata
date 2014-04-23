/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.ClubFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacade;
import com.spontecorp.futboldata.jpacontroller.EmailFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.TelefonoFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

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
@Named("clubBean")
@SessionScoped
public class ClubBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Club club;
    private DataModel items = null;
    private Pais pais;
    private Direccion direccion;
    private Telefono telefono;
    private Email email;

    private SelectItem[] ciudades;
    private List<Email> emails = null;
    private List<Telefono> telefonos = null;
    private static final Logger logger = LoggerFactory.getLogger(ClubBean.class);

    private final CiudadFacade controllerCiudad;
    private final PaisFacade controllerPais;
    private final ClubFacade controllerClub;
    private final DireccionFacade controllerDireccion;
    private final TelefonoFacade controllerTelefono;
    private final EmailFacade controllerEmail;

    public ClubBean() {
        controllerClub = new ClubFacade();
        controllerCiudad = new CiudadFacade();
        controllerPais = new PaisFacade();
        controllerDireccion = new DireccionFacade();
        controllerTelefono = new TelefonoFacade();
        controllerEmail = new EmailFacade();
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public SelectItem[] getPaisAvailable() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadAvailable() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public void ciudadAvailable(Pais pais) {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public Club getSelected() {
        if (club == null) {
            club = new Club();
            direccion = new Direccion();
            club.setDireccionId(direccion);
        }
        return club;
    }
    
    protected void setEmbeddableKeys() {
        direccion.setTelefonoCollection(telefonos);
        direccion.setEmailCollection(emails);
        club.setDireccionId(direccion);
    }

    protected void initializeEmbeddableKey() {
        telefono = new Telefono();
        email = new Email();
        telefonos = new ArrayList<>();
        emails = new ArrayList<>();
        direccion = new Direccion();
    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerClub.findAll());
        }
        return items;
    }

    public void cargarTelefono() {
        telefonos.add(telefono);
        telefono = new Telefono();

    }

    public void cargarEmail() {
        emails.add(email);
        email = new Email();
    }

    public void recreateModel() {
        items = null;
        club = null;
    }

    public String gotoClubsPage() {
        return "list";
    }

    public String prepareCreate() {
//        telefono = new Telefono();
//        email = new Email();
//        telefonos = new ArrayList<>();
//        emails = new ArrayList<>();
//        direccion = new Direccion();
//        direccion.setTelefonoCollection(telefonos);
//        direccion.setEmailCollection(emails);
//        club = new Club();
//        club.setDireccionId(direccion);
        club = new Club();
        initializeEmbeddableKey();
        setEmbeddableKeys();
        return "create";
    }

    public String prepareEdit() {
        club = (Club) getItems().getRowData();
        return "edit";
    }

    public String prepareList() {
        return "list";
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public String create() {
        try {
            if (controllerClub.findClub(club.getNombre()) != null) {
                Util.addErrorMessage("Club ya existente, coloque otro");
                return null;
            } else {
                controllerDireccion.create(direccion);
                for(Telefono phone : telefonos){
                    phone.setDireccionId(direccion);
                    controllerTelefono.create(phone);
                }
                for(Email mail : emails){
                    mail.setDireccionId(direccion);
                    controllerEmail.create(mail);
                }       
                setEmbeddableKeys();
                club.setDireccionId(direccion);
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

    public String edit() {
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

}
