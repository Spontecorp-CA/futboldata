/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
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
public class ArbitroBean implements Serializable{

    private Arbitro arbitro;
    private Pais pais;
    private String telefono;
    private String celular;
    private String email;

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

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }
    
    public Arbitro getSelected(){
        if(arbitro == null){
            arbitro = new Arbitro();
        }
        return arbitro;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String prepareCreate() {
        arbitro = new Arbitro();
        return "create";
    }
    
    public SelectItem[] getAsociacionesAvalaible(){
        return Util.getSelectItems(asociacionController.findAll());
    }
    
    public SelectItem[] getPaisesAvalaible(){
        return Util.getSelectItems(paisController.listaPaisxNombre());
    }
    
    public SelectItem[] getCiudadesAvalaible(){
        return Util.getSelectItems(ciudadController.findCiudadxPais(pais));
    }
    
    public SelectItem[] getRedSocialAvailable(){
        return Util.getSelectItems(redSocialController.findAll());
    }
}
