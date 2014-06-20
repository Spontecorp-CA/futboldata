/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.entity.TipoRedSocial;
import com.spontecorp.futboldata.jpacontroller.ArbitroFacade;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
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
import javax.persistence.EntityManagerFactory;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("arbitroBean")
@SessionScoped
public class ArbitroBean implements Serializable {

    private Arbitro arbitro;
    private Pais pais;
    private SelectItem[] ciudades;
    private List<Arbitro> items;
    private Direccion direccion;
    private Persona persona;
    private String cuenta;
    private RedSocial redSocial;
    private TipoRedSocial tipoRedSocial;
    private Asociacion asociacion;
    private Email email;
    private List<RedSocial> redes;
    private List<RedSocial> redesEliminar;
    private List<Email> emails;
    private final transient EntityManagerFactory emf = Util.getEmf();

    private final ArbitroFacade controllerArbitro;
    private final AsociacionFacade controllerAsociacion;
    private final PaisFacade controllerPais;
    private final CiudadFacade controllerCiudad;
    private final RedSocialFacade controllerRedSocial;
    private final TipoRedSocialFacade tipoRedSocialController;

    private static final Logger logger = LoggerFactory.getLogger(ArbitroBean.class);

    public ArbitroBean() {
        controllerArbitro = new ArbitroFacade();
        controllerAsociacion = new AsociacionFacade();
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        controllerRedSocial = new RedSocialFacade();
        tipoRedSocialController = new TipoRedSocialFacade();
    }

    public Arbitro getSelected() {
        if (arbitro == null) {
            arbitro = new Arbitro();
            direccion = new Direccion();
            persona = new Persona();
            persona.setDireccionId(direccion);
            arbitro.setPersonaId(persona);
            ciudades = null;
            redes = new ArrayList<RedSocial>();
            redesEliminar = new ArrayList<RedSocial>();
        }
        return arbitro;
    }

    public void prepareCreate() {
        arbitro = new Arbitro();
        initializeEmbeddableKey();
    }

    protected void initializeEmbeddableKey() {
        redSocial = new RedSocial();
        direccion = new Direccion();
        persona = new Persona();
        persona.setFoto("vacio");
        redes = new ArrayList<RedSocial>();
        redesEliminar = new ArrayList<RedSocial>();
        direccion = new Direccion();
        ciudades = null;
        pais = null;
        asociacion = new Asociacion();
        setEmbeddableKeys();
    }

    protected void setEmbeddableKeys() {
        persona.setDireccionId(direccion);
        arbitro.setPersonaId(persona);
        arbitro.setAsociacionId(asociacion);

    }

    public void prepareEdit() {
        redes = getRedSocials(arbitro.getPersonaId());
        asociacion = arbitro.getAsociacionId();
        if (arbitro.getPersonaId().getDireccionId().getCiudadId() != null) {
            pais = arbitro.getPersonaId().getDireccionId().getCiudadId().getPaisId();
            ciudadesAvalaible();
        }

    }

    public SelectItem[] getAsociacionesAvalaible() {
        return Util.getSelectItems(controllerAsociacion.findAll());
    }

    public SelectItem[] getPaisesAvalaible() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadesAvalaible() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
//        for (SelectItem city : ciudades) {
//            logger.debug(city.getLabel());
//        }
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public SelectItem[] getRedSocialAvailable() {
        return Util.getSelectItems(tipoRedSocialController.findAll());
    }

    public void cargarRedSocial() {
        redSocial.setTipoRedSocialId(tipoRedSocial);
        redSocial.setPersonaId(persona);
        redes.add(redSocial);
        redSocial = new RedSocial();
    }

    public void cargarEmail() {

        emails.add(email);
        email = new Email();

    }

    public List<RedSocial> getRedesSocial() {
        return controllerRedSocial.findRedSocialxPersona(persona);
    }

    public List<Arbitro> getItems() {
        if (items == null) {
            items = controllerArbitro.findAll();
        }
        return items;
    }

    public void create() {
        try {
//            if (controllerArbitro.findArbitroByDomentoId(persona.getDocumentoIdentidad()) != null) {
//                Util.addErrorMessage("El arbitro ya se encuentra Registrado por el Documento de "
//                        + "identificacion");
//
//            } else {

                persona.setRedSocialCollection(redes);
                persona.setDireccionId(direccion);
                arbitro.setPersonaId(persona);
                arbitro.setAsociacionId(asociacion);
                logger.debug("Esta Creando  un Arbitro");
                controllerArbitro.create(arbitro);
                recreateModel();
                Util.addSuccessMessage("Se creo exitosamente el Jugador");
//
//            }

        } catch (Exception e) {
            logger.debug("Error al crear Arbitro: ", e);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        long lDateTime = new Date().getTime();
        System.out.println("Date() - Time in milliseconds: " + lDateTime);
        String nombreArchivo = "arbitro" + lDateTime;
        Util.subirArchivo(event, "arbitro/", nombreArchivo);
        arbitro.getPersonaId().setFoto(nombreArchivo);

    }

    public List<RedSocial> getRedSocials(Persona persona) {
        redes = controllerRedSocial.findRedSocialxPersona(persona);
        return redes;
    }

    public void edit() {
        for (RedSocial red : redes) {
            red.setPersonaId(arbitro.getPersonaId());
        }
        arbitro.getPersonaId().setRedSocialCollection(redes);
        logger.debug("Esta editando un Arbitro");
        arbitro.setAsociacionId(asociacion);
        controllerArbitro.edit(arbitro);
        for (RedSocial redEliminar : redesEliminar) {
            controllerRedSocial.remove(redEliminar);
        }

        Util.addSuccessMessage("Se editó exitosamente el Arbitro");
        recreateModel();
    }

    public void recreateModel() {
        redSocial = null;
        pais = null;
        arbitro = null;
        items = null;
        persona = null;
        asociacion = null;
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

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public RedSocial getRedSocial() {
        if (redSocial == null) {
            redSocial = new RedSocial();
        }
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public TipoRedSocial getTipoRedSocial() {
        return tipoRedSocial;
    }

    public void setTipoRedSocial(TipoRedSocial tipoRedSocial) {
        this.tipoRedSocial = tipoRedSocial;
    }

    public Email getEmail() {
        if (email == null) {
            email = new Email();
        }
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public List<RedSocial> getRedes() {
        return redes;
    }

    public void setRedes(List<RedSocial> redes) {
        this.redes = redes;
    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "arbitro/";
        return host;
    }

}
