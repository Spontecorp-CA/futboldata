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
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
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
    private DataModel items;
    private Direccion direccion;
    private Persona persona;
    private String cuenta;
    private RedSocial redSocial;
    private TipoRedSocial tipoRedSocial;
    private Asociacion asociacion;
    private Email email;
    private List<RedSocial> redes;
    private List<Email> emails;
    private final transient EntityManagerFactory emf = Util.getEmf();

    private final ArbitroFacade controllerArbitro;
    private final AsociacionFacade controllerAsociacion;
    private final PaisFacade controllerPais;
    private final CiudadFacade controllerCiudad;
    private final RedSocialFacade controllerRedSocial;
    private final TipoRedSocialFacade tipoRedSocialController;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioBean.class);

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
        direccion = new Direccion();
        persona = new Persona();
        asociacion = new Asociacion();
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

    public void cargarEmail() {
            
        emails.add(email);
        email = new Email();

    }

    public List<RedSocial> getRedesSocial() {
        return controllerRedSocial.findRedSocialxPersona(persona);
    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerArbitro.findAll());
        }
        return items;
    }

    public String create() {        
        persona.setRedSocialCollection(redes);
        direccion.setEmailCollection(emails);
        persona.setDireccionId(direccion);
        arbitro.setPersonaId(persona);
        arbitro.setAsociacionId(asociacion);
        controllerArbitro.create(arbitro);
        return prepareCreate();
    }

    public List<RedSocial> getRedSocials(Persona persona) {
        redes = controllerRedSocial.findRedSocialxPersona(persona);
        return redes;
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
        if(email==null){
        email= new Email();
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

}
