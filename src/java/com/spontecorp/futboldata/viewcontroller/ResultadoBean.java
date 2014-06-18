/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.entity.Convocatoria;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.jpacontroller.PartidoFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sponte03
 */
@Named("resultadoBean")
@SessionScoped
public class ResultadoBean implements Serializable{
private static final long serialVersionUID = 1L;  

private Partido partido;
private final PartidoFacade partidoFacade;
private Convocado convocado;
private Convocatoria convocatoria;

    



/**
     * Creates a new instance of ResultadoBean
     */
               
        
    public ResultadoBean() {
        partidoFacade = new PartidoFacade();
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
    public void guardar(){
        partidoFacade.edit(partido);
        Util.addSuccessMessage("Se edito con exito");

        
    }
    public String gotoResultadoPage(Partido partido){
        this.partido = partido;
        return "/admin/liga/temporadas/resultado/detallepartido??faces-redirect=true";
    }
}
