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
import com.spontecorp.futboldata.jpacontroller.PersonaFacade;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
import com.spontecorp.futboldata.jpacontroller.StaffFacade;
import com.spontecorp.futboldata.jpacontroller.TipoRedSocialFacade;
import com.spontecorp.futboldata.utilities.Util;
import static com.spontecorp.futboldata.utilities.Util.INACTIVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
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

    private Direccion direccion;
    private Persona persona;
    private String cuenta;
    private Email email;
    private RedSocial redSocial;
    private TipoRedSocial tipoRedSocial;
    private List<Persona> items;

    private List<RedSocial> redes;
    private List<RedSocial> redesEliminar;
    private static final Logger logger = LoggerFactory.getLogger(StaffBean.class);
    private final PersonaFacade controllerPersona;

    public StaffBean() {
        controllerStaff = new StaffFacade();
        controllerAsociacion = new AsociacionFacade();
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        controllerRedSocial = new RedSocialFacade();
        tipoRedSocialController = new TipoRedSocialFacade();
        controllerCargo = new CargoFacade();
        controllerPersona = new PersonaFacade();
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

    public void delete() {
        staff.setStatus(INACTIVO);
        controllerStaff.edit(staff);
        staff = null;
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
        persona = null;
        redSocial = null;
        pais = null;
        staff = null;
        items = null;

    }

    public void prepareEdit() {
        redes = getRedSocials(persona);
        if (persona.getDireccionId().getCiudadId() != null) {
            pais = persona.getDireccionId().getCiudadId().getPaisId();
            ciudadesAvalaible();
        } else {
            pais = new Pais();
        }
    }

    public List<RedSocial> getRedSocials(Persona persona) {
        redes = controllerRedSocial.findRedSocialxPersona(persona);
        return redes;
    }

    public void create() {
        try {
            logger.debug("El documento de identidad trae: " + persona.getDocumentoIdentidad());
            if (persona.getDocumentoIdentidad() == null || persona.getDocumentoIdentidad().equals(" ")
                    || persona.getDocumentoIdentidad().equals("")) {
                persona.setRedSocialCollection(redes);
                persona.setDireccionId(direccion);
                staff.setPersonaId(persona);
                controllerStaff.create(staff);
                recreateModel();
                Util.addSuccessMessage("Staff creado satisfactoriamente");
            } else {
                if (controllerStaff.findStaffxDomentoId(persona.getDocumentoIdentidad()) != null) {
                    Util.addErrorMessage("El Staff ya se encuentra Registrado por el Documenta de "
                            + "identificación");
                } else {
                    persona.setRedSocialCollection(redes);
                    persona.setDireccionId(direccion);
                    staff.setPersonaId(persona);
                    controllerStaff.create(staff);
                    recreateModel();
                    Util.addSuccessMessage("Staff creado satisfactoriamente");
                }
            }

        } catch (Exception e) {
            logger.error("Error al crear Staff: ", e);
        }
    }

    public void prepareCreate() {
        redSocial = new RedSocial();
        staff = new Staff();
        direccion = new Direccion();
        persona = new Persona();
        persona.setDireccionId(direccion);
        staff.setPersonaId(persona);
        ciudades = null;
        redes = new ArrayList<RedSocial>();
        pais = null;
//        return "list?faces-redirect=true";
    }

    public void edit() {
        for (RedSocial red : redes) {
            red.setPersonaId(persona);
        }
        persona.setRedSocialCollection(redes);
        logger.debug("Esta editando un Staff");
        controllerPersona.edit(persona);
        for (RedSocial redEliminar : redesEliminar) {
            controllerRedSocial.remove(redEliminar);
        }
        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el Staff");
//        return prepareCreate();
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
            direccion = new Direccion();
            persona.setDireccionId(direccion);
            ciudades = null;
            redes = new ArrayList<RedSocial>();
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
        String nombreArchivo = "staff" + lDateTime;
        Util.subirArchivo(event, "staff/", nombreArchivo);
        persona.setFoto(nombreArchivo);
        staff.getPersonaId().setFoto(nombreArchivo);

    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "staff/";
        return host;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Persona> getItems() {
        if (items == null) {
            items = controllerStaff.findDistinctStaffList();
        }
        return items;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
