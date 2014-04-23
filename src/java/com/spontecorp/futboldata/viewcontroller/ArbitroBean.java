/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
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

    private Direccion direccion;
    private Persona persona;
    private String cuenta;
    private RedSocial redSocial;
    private List<RedSocial> redes;

    private final AsociacionFacade asociacionController;
    private final PaisFacade paisController;
    private final CiudadFacade ciudadController;
    private final RedSocialFacade redSocialController;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);

    public ArbitroBean() {
        asociacionController = new AsociacionFacade();
        paisController = new PaisFacade();
        ciudadController = new CiudadFacade();
        redSocialController = new RedSocialFacade();
    }

    public Arbitro getSelected() {
        if (arbitro == null) {
            arbitro = new Arbitro();

            direccion = new Direccion();
            persona = new Persona();
            persona.setDireccionId(direccion);
            arbitro.setPersonaId(persona);
            ciudades = null;
            redes = new ArrayList<>();
        }
        return arbitro;
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

    public String prepareCreate() {
        arbitro = new Arbitro();
        return "create";
    }

    public SelectItem[] getAsociacionesAvalaible() {
        return Util.getSelectItems(asociacionController.findAll());
    }

    public SelectItem[] getPaisesAvalaible() {
        return Util.getSelectItems(paisController.listaPaisxNombre());
    }

    public void ciudadesAvalaible() {
        ciudades = Util.getSelectItems(ciudadController.findCiudadxPais(pais));
        for (SelectItem city : ciudades) {
            logger.debug(city.getLabel());
        }
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public SelectItem[] getRedSocialAvailable() {
        return Util.getSelectItems(redSocialController.findAll());
    }
    
    public void cargarRedSocial(){
        redSocial = new RedSocial();
        redSocial.setPersonaId(persona);
        redSocial.setUrl(cuenta);
        
        redes.add(redSocial);
    }
    
    public List<RedSocial> getRedesSocial(){
        return redSocialController.findRedSocialxPersona(persona);
    }
    
}
