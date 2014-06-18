/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.entity.Convocatoria;
import com.spontecorp.futboldata.entity.EquipoHasJugador;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.jpacontroller.ConvocatoriaFacade;
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
public class ResultadoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Partido partido;
    private Convocado convocado;
    private Convocatoria convocatoriaVisitante;
    private Convocatoria convocatoriaLocal;
    private EquipoHasJugador equipoHasJugador;
    private final PartidoFacade partidoFacade;
    private final ConvocatoriaFacade convocatoriaFacade;

    /**
     * Creates a new instance of ResultadoBean
     */
    public ResultadoBean() {
        partidoFacade = new PartidoFacade();
        convocatoriaFacade = new ConvocatoriaFacade();
    }

    public Convocatoria getConvocatoriaLocal() {
        if (convocatoriaLocal == null) {
            convocatoriaLocal = convocatoriaFacade.getConvocatoria(partido, partido.getEquipoLocalId());
            if (convocatoriaLocal == null) {
                convocatoriaLocal = new Convocatoria();
                convocatoriaLocal.setEquipoId(partido.getEquipoLocalId());
                convocatoriaLocal.setPartidoId(partido);
                convocatoriaFacade.edit(convocatoriaLocal);
            }

        }
        return convocatoriaLocal;
    }

    public Convocatoria getConvocatoriaVisitante() {
        if (convocatoriaVisitante == null) {
            convocatoriaVisitante = convocatoriaFacade.getConvocatoria(partido, partido.getEquipoVisitanteId());
            if (convocatoriaVisitante == null) {
                convocatoriaVisitante = new Convocatoria();
                convocatoriaVisitante.setEquipoId(partido.getEquipoLocalId());
                convocatoriaVisitante.setPartidoId(partido);
                convocatoriaFacade.edit(convocatoriaVisitante);
            }

        }
        return convocatoriaVisitante;
    }

    
    public EquipoHasJugador getEquipoHasJugador() {
        return equipoHasJugador;
    }

    public void setEquipoHasJugador(EquipoHasJugador equipoHasJugador) {
        this.equipoHasJugador = equipoHasJugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public void guardar() {
        partidoFacade.edit(partido);
        Util.addSuccessMessage("Se edito con exito");

    }

    public String gotoResultadoPage(Partido partido) {
        this.partido = partido;
        return "/admin/liga/temporadas/resultado/detallepartido??faces-redirect=true";
    }
}
