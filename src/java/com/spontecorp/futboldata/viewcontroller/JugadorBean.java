/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Jugador;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.entity.TipoRedSocial;
import com.spontecorp.futboldata.jpacontroller.JugadorFacade;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
import com.spontecorp.futboldata.jpacontroller.TipoRedSocialFacade;
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
    private RedSocial redSocial;
    private TipoRedSocial tipoRedSocial;
    private List<RedSocial> redes;

    private final JugadorFacade controllerJugador;
    private final AsociacionFacade controllerAsociacion;
    private final PaisFacade controllerPais;
    private final CiudadFacade controllerCiudad;
    private final RedSocialFacade controllerRedSocial;
    private final TipoRedSocialFacade tipoRedSocialController;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);
    private DireccionFacade controllerDireccion;

    public JugadorBean() {
        controllerJugador = new JugadorFacade();
        controllerAsociacion = new AsociacionFacade();
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        controllerRedSocial = new RedSocialFacade();
        controllerDireccion = new DireccionFacade();
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

    public String prepareCreate() {
        redSocial = new RedSocial();
        jugador = new Jugador();
        direccion = new Direccion();
        persona = new Persona();
        persona.setDireccionId(direccion);
        jugador.setPersonaId(persona);
        ciudades = null;
        redes = new ArrayList<RedSocial>();
        return "create";
    }

    public SelectItem[] getAsociacionesAvalaible() {
        return Util.getSelectItems(controllerAsociacion.findAll());
    }

    public SelectItem[] getPaisesAvalaible() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadesAvalaible() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
        for (SelectItem city : ciudades) {
            logger.debug(city.getLabel());
        }
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
        items = null;
    }

    public String create() {

        persona.setRedSocialCollection(redes);
        persona.setDireccionId(direccion);
        jugador.setPersonaId(persona);
        controllerJugador.create(jugador);
        recreateModel();
        return prepareCreate();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
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

    public List<Telefono> getTelefonos(Direccion direccion) {
        List<Telefono> lista = controllerDireccion.findListTelefonoxDireaccion(direccion);
        return lista;
    }
}
