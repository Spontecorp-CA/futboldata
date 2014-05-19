/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.entity.Staff;
import com.spontecorp.futboldata.entity.TipoRedSocial;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CargoFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
import com.spontecorp.futboldata.jpacontroller.StaffFacade;
import com.spontecorp.futboldata.jpacontroller.TipoRedSocialFacade;
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
 * @author joseamromero
 */
@Named("staffBean")
@SessionScoped
public class StaffBean implements Serializable {

    private final StaffFacade controllerStaff;
    private final AsociacionFacade controllerAsociacion;
    private final PaisFacade controllerPais;
    private final CiudadFacade controllerCiudad;
    private final RedSocialFacade controllerRedSocial;
    private final TipoRedSocialFacade tipoRedSocialController;
    private final CargoFacade controllerCargo;

    private Staff staff;
    private Pais pais;
    private SelectItem[] ciudades;
    private DataModel items = null;

    private Direccion direccion;
    private Persona persona;
    private String cuenta;
    private Email email;
    private RedSocial redSocial;
    private TipoRedSocial tipoRedSocial;
    private List<RedSocial> redes;
    private List<RedSocial> redesEliminar;
    private static final Logger logger = LoggerFactory.getLogger(StaffBean.class);

    public StaffBean() {
        controllerStaff = new StaffFacade();
        controllerAsociacion = new AsociacionFacade();
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        controllerRedSocial = new RedSocialFacade();
        tipoRedSocialController = new TipoRedSocialFacade();
        controllerCargo = new CargoFacade();
    }

    public Staff getSelected() {
        if (staff == null) {
            staff = new Staff();
            direccion = new Direccion();
            persona = new Persona();
            persona.setDireccionId(direccion);
            staff.setPersonaId(persona);
            ciudades = null;
            redes = new ArrayList<RedSocial>();
        }
        return staff;
    }

    protected void setEmbeddableKeys() {
        persona.setDireccionId(direccion);
        staff.setPersonaId(persona);
    }

    protected void initializeEmbeddableKey() {
        redSocial = new RedSocial();
        direccion = new Direccion();
        persona = new Persona();
        redes = new ArrayList<RedSocial>();
        redesEliminar = new ArrayList<RedSocial>();
        direccion = new Direccion();
    }

    public SelectItem[] getAsociacionesAvalaible() {
        return Util.getSelectItems(controllerAsociacion.findAll());
    }

    public SelectItem[] getPaisesAvalaible() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public SelectItem[] getCargosAvalaible() {
        return Util.getSelectItems(controllerCargo.findAll());
    }

    public void ciudadesAvalaible() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public SelectItem[] getRedSocialAvailable() {
        return Util.getSelectItems(tipoRedSocialController.findAll());
    }

    public void ciudadesAvailable(Pais pais) {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public void cargarRedSocial() {
        redSocial.setTipoRedSocialId(tipoRedSocial);
        redSocial.setPersonaId(persona);
        redes.add(redSocial);
        redSocial = new RedSocial();
    }

    public List<RedSocial> getRedesSocial() {
        return controllerRedSocial.findRedSocialxPersona(persona);
    }

    public void recreateModel() {
        redSocial = null;
        pais = null;
        staff = null;
        items = null;
        persona = null;
    }

    public void prepareEdit() {

        redes = getRedSocials(staff.getPersonaId());
        pais = staff.getPersonaId().getDireccionId().getCiudadId().getPaisId();
        ciudadesAvalaible();
    }

    public List<RedSocial> getRedSocials(Persona persona) {
        redes = controllerRedSocial.findRedSocialxPersona(persona);
        return redes;
    }

    public String create() {
        try {
            if (controllerStaff.findStaffxDomentoId(persona.getDocumentoIdentidad()) != null) {
                Util.addErrorMessage("El Staff ya se encuentra Registrado por el Documenta de "
                        + "identificacion");
            } else {
                persona.setRedSocialCollection(redes);
                persona.setDireccionId(direccion);
                staff.setPersonaId(persona);
                logger.debug("Esta Creando  un Staff");
                controllerStaff.create(staff);
                recreateModel();
                Util.addSuccessMessage("Se creo exitosamente el Staff");
            }

        } catch (Exception e) {
            logger.debug("Error al crear Staff :", e.getMessage());
        }
        return prepareCreate();
    }

    public String prepareCreate() {
        redSocial = new RedSocial();
        staff = new Staff();
        direccion = new Direccion();
        persona = new Persona();
        persona.setDireccionId(direccion);
        staff.setPersonaId(persona);
        ciudades = null;
        redes = new ArrayList<RedSocial>();
        return "list?faces-redirect=true";
    }

    public String edit() {
        for (RedSocial red : redes) {
            red.setPersonaId(staff.getPersonaId());
        }
        staff.getPersonaId().setRedSocialCollection(redes);
        logger.debug("Esta editando un Staff");
        controllerStaff.edit(staff);
        for (RedSocial redEliminar : redesEliminar) {
            controllerRedSocial.remove(redEliminar);
        }
        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el Staff");
        return prepareCreate();
    }

    public Staff getStaff() {
        if (staff == null) {
            staff = new Staff();
            initializeEmbeddableKey();
            setEmbeddableKeys();
        }
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public RedSocial getRedSocial() {
        if (redSocial == null) {
            redSocial = new RedSocial();
        }
        return redSocial;
    }

    public void eliminarRedSocial(RedSocial redsocial) {

        if (redes.remove(redsocial)) {
            redesEliminar.add(redsocial);
            for (RedSocial red : redesEliminar) {
                logger.debug("Va a eliminar a: " + red.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        long lDateTime = new Date().getTime();
        System.out.println("Date() - Time in milliseconds: " + lDateTime);
        String nombreArchivo = "jugador" + lDateTime;
        Util.subirArchivo(event, "jugador/", nombreArchivo);
        staff.getPersonaId().setFoto(nombreArchivo);

    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "jugador/";
        return host;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerStaff.findAll());
        }
        return items;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public TipoRedSocial getTipoRedSocial() {
        return tipoRedSocial;
    }

    public void setTipoRedSocial(TipoRedSocial tipoRedSocial) {
        this.tipoRedSocial = tipoRedSocial;
    }

    public List<RedSocial> getRedes() {
        return redes;
    }

    public void setRedes(List<RedSocial> redes) {
        this.redes = redes;
    }

    public List<RedSocial> getRedesEliminar() {
        return redesEliminar;
    }

    public void setRedesEliminar(List<RedSocial> redesEliminar) {
        this.redesEliminar = redesEliminar;
    }

}