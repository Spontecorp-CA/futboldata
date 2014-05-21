/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Jugador;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.entity.TipoRedSocial;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacade;
import com.spontecorp.futboldata.jpacontroller.JugadorFacade;
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
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("jugadorBean")
@SessionScoped
public class JugadorBean implements Serializable {

    private Jugador jugador;
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
    private List<Jugador> filteredJugador;

    private final JugadorFacade controllerJugador;

    private final PaisFacade controllerPais;
    private final CiudadFacade controllerCiudad;
    private final RedSocialFacade controllerRedSocial;
    private final TipoRedSocialFacade tipoRedSocialController;

    private static final Logger logger = LoggerFactory.getLogger(Jugador.class);

    public JugadorBean() {
        controllerJugador = new JugadorFacade();
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        controllerRedSocial = new RedSocialFacade();
        tipoRedSocialController = new TipoRedSocialFacade();
    }

    public Jugador getSelected() {
        if (jugador == null) {
            jugador = new Jugador();
            direccion = new Direccion();
            persona = new Persona();
            persona.setDireccionId(direccion);
            jugador.setPersonaId(persona);

            ciudades = null;
            redes = new ArrayList<RedSocial>();
        }
        return jugador;
    }

    public List<Jugador> getFilteredJugador() {
        return filteredJugador;
    }

    public void setFilteredJugador(List<Jugador> filteredJugador) {
        this.filteredJugador = filteredJugador;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
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

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerJugador.findAll());
        }
        return items;
    }

    public void prepareCreate() {
////        redSocial = new RedSocial();
//
////        direccion = new Direccion();
////        persona = new Persona();
//        persona.setFoto("vacio");
//        persona.setDireccionId(direccion);
//        jugador.setPersonaId(persona);
//        ciudades = null;
//        redes = new ArrayList<RedSocial>();
         jugador = new Jugador();
        initializeEmbeddableKey();
//        return "list?faces-redirect=true";
    }

    protected void setEmbeddableKeys() {
        persona.setDireccionId(direccion);
        jugador.setPersonaId(persona);

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

    public SelectItem[] getRedSocialAvailable() {
        return Util.getSelectItems(tipoRedSocialController.findAll());
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
        jugador = null;
        items = null;
        persona = null;
    }

    public void prepareEdit() {

        redes = getRedSocials(jugador.getPersonaId());
        pais = jugador.getPersonaId().getDireccionId().getCiudadId().getPaisId();
        ciudadesAvalaible();
    }

    public void ciudadesAvailable(Pais pais) {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public void create() {
        try {
            if (controllerJugador.findJugadorxDomentoId(persona.getDocumentoIdentidad()) != null) {
                Util.addErrorMessage("El jugador ya se encuentra Registrado por el Documenta de "
                        + "identificacion");

            } else {

                persona.setRedSocialCollection(redes);
                persona.setDireccionId(direccion);
                jugador.setPersonaId(persona);
                logger.debug("Esta Creando  un Jugador");
                controllerJugador.create(jugador);
                recreateModel();
                Util.addSuccessMessage("Se creo exitosamente el Jugador");

            }

        } catch (Exception e) {
            logger.debug("Error al crear Jugador :", e.getMessage());
        }
    }

    public void edit() {

        for (RedSocial red : redes) {
            red.setPersonaId(jugador.getPersonaId());
        }
        jugador.getPersonaId().setRedSocialCollection(redes);
        logger.debug("Esta editando un Jugador");
        controllerJugador.edit(jugador);
        for (RedSocial redEliminar : redesEliminar) {
            controllerRedSocial.remove(redEliminar);
        }
        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el Jugador");
    }

    public Jugador getJugador() {
        if (jugador == null) {
            jugador = new Jugador();
            initializeEmbeddableKey();
        }
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

//    public Direccion getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(Direccion direccion) {
//        this.direccion = direccion;
//    }

//    public Persona getPersona() {
//        if (persona == null) {
//            persona = new Persona();
//        }
//        return persona;
//    }
//
//    public void setPersona(Persona persona) {
//        this.persona = persona;
//    }

    public RedSocial getRedSocial() {
        if (redSocial == null) {
            redSocial = new RedSocial();
        }
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    public TipoRedSocial getTipoRedSocial() {
        return tipoRedSocial;
    }

    public void setTipoRedSocial(TipoRedSocial tipoRedSocial) {
        this.tipoRedSocial = tipoRedSocial;
    }

    public List<RedSocial> getRedSocials(Persona persona) {
        redes = controllerRedSocial.findRedSocialxPersona(persona);
        return redes;
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
        jugador.getPersonaId().setFoto(nombreArchivo);

    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "jugador/";
        return host;
    }
}
