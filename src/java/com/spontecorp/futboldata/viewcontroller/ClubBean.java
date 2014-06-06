/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Ciudad;
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
import com.spontecorp.futboldata.jpacontroller.StaffFacade;
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
    private Ciudad ciudad;
    private Direccion direccion;
    private Telefono telefono;
    private Email email;

    private SelectItem[] ciudades;
    private List<Email> emails = null;
    private List<Email> emailEliminar = null;
    private List<Telefono> telefonos = null;
    private List<Telefono> telefonoEliminar = null;

    private static final Logger logger = LoggerFactory.getLogger(ClubBean.class);

    private final CiudadFacade controllerCiudad;
    private final PaisFacade controllerPais;
    private final ClubFacade controllerClub;
    private final DireccionFacade controllerDireccion;
    private final TelefonoFacade controllerTelefono;
    private final StaffFacade controllerStaff;

    private final EmailFacade controllerEmail;

    public ClubBean() {
        controllerClub = new ClubFacade();
        controllerCiudad = new CiudadFacade();
        controllerPais = new PaisFacade();
        controllerDireccion = new DireccionFacade();
        controllerTelefono = new TelefonoFacade();
        controllerEmail = new EmailFacade();
        controllerStaff = new StaffFacade();
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
        if (club == null) {
            club = new Club();
            initializeEmbeddableKey();
            setEmbeddableKeys();
        }
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
            ciudad = new Ciudad();
            telefono = new Telefono();
            email = new Email();
            direccion.setCiudadId(ciudad);
            club.setDireccionId(direccion);

            ciudades = null;
            telefonos = null;
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
        telefonos = new ArrayList<Telefono>();
        emails = new ArrayList<Email>();
        telefonoEliminar = new ArrayList<Telefono>();
        emailEliminar = new ArrayList<Email>();
        direccion = new Direccion();
    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerClub.findAll());
        }
        return items;
    }

    public List<Club> getClubes() {
        return controllerClub.findAll();
    }

    public void cargarTelefono() {
        telefonos.add(telefono);
        telefono = new Telefono();

    }

    public void cargarEmail() {
        emails.add(email);
        email = new Email();
    }

    public void eliminarTelefono(Telefono telefono) {

        logger.debug("El numero telfono: " + telefono.getTelefono(), AsociacionBean.class);
        if (telefonos.remove(telefono)) {
            telefonoEliminar.add(telefono);
            for (Telefono tlf : telefonoEliminar) {
                logger.debug("Va a eliminar a: " + tlf.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public void eliminarEmail(Email email) {
        if (emails.remove(email)) {
            emailEliminar.add(email);
            for (Email eml : emailEliminar) {
                logger.debug("Va a eliminar a: " + eml.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public List<Telefono> getTelefonos(Direccion direccion) {
        telefonos = controllerDireccion.findListTelefonoxDireccion(direccion);
        return telefonos;
    }

    public List<Email> getEmails(Direccion direccion) {
        emails = controllerDireccion.findListEmailxDireaccion(direccion);
        return emails;
    }

    public void recreateModel() {
        items = null;
        club = null;
    }

    public Club prepareCreate() {
        club = new Club();
        initializeEmbeddableKey();
        setEmbeddableKeys();
        return club;
    }

    public void prepareEdit() {
        initializeEmbeddableKey();
        //club = (Club) getItems().getRowData();
        pais = club.getDireccionId().getCiudadId().getPaisId();
        telefonos = getTelefonos(club.getDireccionId());
        emails = getEmails(club.getDireccionId());
        ciudadAvailable(club.getDireccionId().getCiudadId().getPaisId());

        //return "edit";
    }

    public String prepareList() {
        return "list";
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public void create() {
        try {
            if (controllerClub.findClub(club.getNombre()) != null) {
                Util.addErrorMessage("Club ya existente, coloque otro");
                //return null;
            } else {
                club.setDireccionId(direccion);

                for (Telefono phone : telefonos) {
                    phone.setDireccionId(direccion);
                }

                for (Email mail : emails) {
                    mail.setDireccionId(direccion);
                }
                controllerClub.create(club);

                Util.addSuccessMessage("Club creado con éxito");
                recreateModel();
                //return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear el club");
            //return null;
        }
    }

    public void edit() {
        try {
            if (controllerClub.find(club.getId()) == null) {
                Util.addErrorMessage("Club no existente, hay un error");
                //return null;
            } else {
                for (Telefono telefonoEditar : telefonos) {
                    if (controllerTelefono.findTelefono(telefonoEditar.getTelefono()) != null) {
                        controllerTelefono.edit(telefonoEditar);
                    } else {
                        telefonoEditar.setDireccionId(club.getDireccionId());
                        controllerTelefono.edit(telefonoEditar);
                    }
                }

                for (Email emailEditar : emails) {
                    if (controllerEmail.findEmail(emailEditar.getEmail()) != null) {
                        controllerEmail.edit(emailEditar);
                    } else {
                        emailEditar.setDireccionId(club.getDireccionId());
                        controllerEmail.edit(emailEditar);
                    }
                }

                for (Email emailEli : emailEliminar) {
                    controllerEmail.remove(emailEli);
                }

                for (Telefono telefonoEli : telefonoEliminar) {
                    controllerTelefono.remove(telefonoEli);
                }

                controllerClub.edit(club);
                Util.addSuccessMessage("Club editado con éxito");
                //return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la categoría");
            //return null;
        }
    }


 

}
