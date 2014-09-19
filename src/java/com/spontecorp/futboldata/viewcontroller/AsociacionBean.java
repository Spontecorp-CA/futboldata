/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.EmailFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.TelefonoFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("asociacionBean")
@SessionScoped
public class AsociacionBean implements Serializable {

    private Asociacion asociacion;
    private Pais pais;
    private SelectItem[] ciudades;
    private DataModel items = null;

    private Direccion direccion;
    private Email email;
    private List<Email> emails;
    private List<Email> emailsEliminar;
    private List<Telefono> telefonos;
    private List<Telefono> telefonosEliminar;
    private List<Asociacion> filteredAsociacion;

    private final AsociacionFacade controllerAsociacion;

    private final PaisFacade controllerPais;
    private final CiudadFacade controllerCiudad;
    private final EmailFacade controllerEmail;
    private final TelefonoFacade controllerTelefono;

    private static final Logger logger = LoggerFactory.getLogger(Asociacion.class);
    private Telefono telefono;
    private final LoginBean bean;

    public AsociacionBean() {
        controllerAsociacion = new AsociacionFacade();
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        controllerTelefono = new TelefonoFacade();
        controllerEmail = new EmailFacade();
        bean = (LoginBean) Util.findBean("loginBean");
    }

    public Asociacion getSelected() {
        if (asociacion == null) {
            asociacion = new Asociacion();
            direccion = new Direccion();
            asociacion.setDireccionId(direccion);
            ciudades = null;
        }
        return asociacion;
    }

    public void setSelected(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public List<Asociacion> getFilteredAsociacion() {
        return filteredAsociacion;
    }

    public void setFilteredAsociacion(List<Asociacion> filteredAsociacion) {
        this.filteredAsociacion = filteredAsociacion;
    }

    public void setEmail(Email email) {
        if (email == null) {
            email = new Email();
        }
        this.email = email;
    }

    public Email getEmail() {
        if (email == null) {
            email = new Email();
        }
        return email;
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

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Telefono getTelefono() {
        if (telefono == null) {
            telefono = new Telefono();
        }
        return telefono;
    }

    public List<Telefono> getTelefonos() {
        if (telefonos == null) {
            telefonos = new ArrayList<Telefono>();
        }
        return telefonos;
    }

    public List<Email> getEmails() {
        if (emails == null) {
            emails = new ArrayList<Email>();
        }
        return emails;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerAsociacion.findAll(bean.getIdOrganizacion()));
        }
        return items;
    }

    public void prepareCreate() {
        asociacion = null;

    }

    public List<Asociacion> getItemsAvailableSelectOne() {
        return controllerAsociacion.findAll(bean.getIdOrganizacion());
    }

    protected void setEmbeddableKeys() {

        asociacion.setDireccionId(direccion);

    }

    protected void initializeEmbeddableKey() {
        email = new Email();
        telefono = new Telefono();
        direccion = new Direccion();
        direccion = new Direccion();
        ciudades = null;
        pais = null;
        emails = new ArrayList<Email>();
        telefonos = new ArrayList<Telefono>();
        emailsEliminar = new ArrayList<Email>();
        telefonosEliminar = new ArrayList<Telefono>();
        setEmbeddableKeys();
    }

    public SelectItem[] getPaisesAvalaible() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadesAvalaible() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public void cargarEmail() {
        email.setDireccionId(direccion);
        emails.add(email);
        email = new Email();
    }

    public void cargarTelefono() {
        telefono.setDireccionId(direccion);
        telefonos.add(telefono);
        telefono = new Telefono();
    }

    public void recreateModel() {
        email = null;
        pais = null;
        asociacion = null;
        items = null;
        direccion = null;
    }

    public void prepareEdit() {
        emailsEliminar = new ArrayList<Email>();
        telefonosEliminar = new ArrayList<Telefono>();
        telefonos = getTelefonos(asociacion.getDireccionId());
        emails = getEmails(asociacion.getDireccionId());
        pais = asociacion.getDireccionId().getCiudadId().getPaisId();
        ciudadesAvalaible();

    }

    public void ciudadesAvailable(Pais pais) {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public void create() {
        try {
            if (controllerAsociacion.findAsociacion(asociacion.getNombre()) != null) {
                Util.addErrorMessage("El asociacion ya se encuentra ");

            } else {

                asociacion.setDireccionId(direccion);
                logger.debug("Esta Creando  un Asociacion");
                controllerAsociacion.create(asociacion);
                recreateModel();
                Util.addSuccessMessage("Se creo exitosamente el Asociacion");

            }

        } catch (Exception e) {
            logger.debug("Error al crear Asociacion :", e.getMessage());
        }
    }

    public void edit() {

        for (Email item : emails) {
            item.setDireccionId(asociacion.getDireccionId());
        }

        for (Telefono tel1 : telefonos) {
            tel1.setDireccionId(asociacion.getDireccionId());
        }
        asociacion.getDireccionId().setEmailCollection(emails);
        asociacion.getDireccionId().setTelefonoCollection(telefonos);

        logger.debug("Esta editando un Asociacion");
        controllerAsociacion.edit(asociacion);

        for (Email item2 : emailsEliminar) {
            controllerEmail.remove(item2);
        }
        for (Telefono tel : telefonosEliminar) {
            controllerTelefono.remove(tel);
        }

        Util.addSuccessMessage("Se edito exitosamente el Asociacion");
        recreateModel();
    }

    public Asociacion getAsociacion() {
        if (asociacion == null) {
            asociacion = new Asociacion();
            initializeEmbeddableKey();
            asociacion.setOrganizacionId(bean.getIdOrganizacion());
        }
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public List<Email> getEmails(Direccion direccion) {
        emails = controllerEmail.findListEmailxDireaccion(direccion);
        return emails;
    }

    public List<Telefono> getTelefonos(Direccion direccion) {
        telefonos = controllerTelefono.findListTelefonoxDireaccion(direccion);
        return telefonos;
    }

    public void eliminarEmail(Email email) {

        if (emails.remove(email)) {
            emailsEliminar.add(email);
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public void eliminarTelefono(Telefono telefono) {

        if (telefonos.remove(telefono)) {
            telefonosEliminar.add(telefono);

        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        long lDateTime = new Date().getTime();
        System.out.println("Date() - Time in milliseconds: " + lDateTime);
        String nombreArchivo = "asociacion" + lDateTime;
        Util.subirArchivo(event, "asociacion/", nombreArchivo);
        asociacion.setLogo(nombreArchivo);

    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "asociacion/";
        return host;
    }

}
