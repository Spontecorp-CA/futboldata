/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.entity.Convocatoria;
import com.spontecorp.futboldata.entity.EquipoHasJugador;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.PartidoArbitro;
import com.spontecorp.futboldata.jpacontroller.ConvocadoFacade;
import com.spontecorp.futboldata.jpacontroller.ConvocatoriasFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoHasJugadorFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoArbitroFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sponte03
 */
@Named("resultadoBean")
@SessionScoped
public class ResultadoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int indexTab;
    private Partido partido;
    private Convocado convocado;
    private Convocado convocadoLocal;
    private Arbitro arbitro;
    private Convocatoria convocatoriaVisitante;
    private Convocatoria convocatoriaLocal;
    private PartidoArbitro partidoArbitro;
    private EquipoHasJugador equipoHasJugador;
    private final PartidoFacade partidoFacade;
    private final ConvocatoriasFacade convocatoriaFacade;
    private final ConvocadoFacade convocadoFacade;
    private final EquipoHasJugadorFacade equipoHasJugadorFacade;
    private final PartidoArbitroFacade partidoArbitroFacade;

    private List<EquipoHasJugador> jugadorEquipoLocal;
    private List<Convocado> convocadoEquipoLocal;
    private List<EquipoHasJugador> jugadorEquipoVisitante;
    private List<Convocado> convocadoEquipoVisitante;
    private List<PartidoArbitro> partidoArbitros;

    private static final Logger logger = LoggerFactory.getLogger(ResultadoBean.class);

    /**
     * Creates a new instance of ResultadoBean
     */
    public ResultadoBean() {
        partidoFacade = new PartidoFacade();
        convocatoriaFacade = new ConvocatoriasFacade();
        convocadoFacade = new ConvocadoFacade();
        equipoHasJugadorFacade = new EquipoHasJugadorFacade();
        partidoArbitroFacade = new PartidoArbitroFacade();
    }

    public void preEditConvocado(Convocatoria convocatoria) {
        convocado = new Convocado();
        convocado.setJugadorId(equipoHasJugador.getJugadorId());
        convocado.setConvocatoriaId(convocatoria);
        convocado.setCamiseta(equipoHasJugador.getJugadorId().getCamiseta());
        convocado.setPosicionId(equipoHasJugador.getJugadorId().getPosicionId());

    }

    public void preEditArbitro() {
        partidoArbitro = new PartidoArbitro();
        partidoArbitro.setPartidoId(partido);
        partidoArbitro.setArbitroId(arbitro);

    }

    public void editConvocado() {
        if (convocado.getConvocatoriaId() == convocatoriaLocal) {
            editConvocado(convocatoriaLocal, convocadoEquipoLocal);
        } else {
            editConvocado(convocatoriaVisitante, convocadoEquipoVisitante);
        }
        recreateModel();
    }

    public void editArbitro() {
        if (partidoArbitroFacade.getPartidoArbitro(arbitro, partido) == null) {
            partidoArbitroFacade.edit(partidoArbitro);
            partidoArbitros.add(partidoArbitro);
            Util.addSuccessMessage("Se agrego el Arbitro");
        } else {
            Util.addErrorMessage("Ya se encuentra agregado el Arbitro");
        }
        recreateModel();
    }

    public void removeConvocado(Convocado convocado) {
        if (convocado.getConvocatoriaId() == convocatoriaLocal) {
            convocadoFacade.remove(convocado);

            Util.addErrorMessage("Se elimino exitosamente");
        } else {
            convocadoEquipoVisitante.remove(convocado);
            convocadoFacade.remove(convocado);
            Util.addErrorMessage("Se elimino exitosamente");
        }
        recreateModel();
    }

    public void removeArbitro() {
        partidoArbitros.remove(partidoArbitro);
        partidoArbitroFacade.remove(partidoArbitro);
        Util.addSuccessMessage("Se elimino exitosamente");

    }

    public void editConvocado(Convocatoria convocatoria, List<Convocado> convocados) {
        try {
            if (convocadoFacade.getConvocado(convocado.getJugadorId(), convocatoria) == null) {
                convocadoFacade.edit(convocado);
                Util.addSuccessMessage("Se agrego el convocado con exito");
                convocados.add(convocado);
            } else {
                Util.addErrorMessage("Ya se agrego el Jugador a la Convocatoria");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al agregar Convocado");
            logger.error("Error al agregar Convocado" + e.toString());
        }
    }

    public Convocado getConvocadoLocal() {
        return convocadoLocal;
    }

    public void setConvocadoLocal(Convocado convocadoLocal) {
        this.convocadoLocal = convocadoLocal;
    }

    public List<PartidoArbitro> getPartidoArbitros() {
        if (partidoArbitros == null) {
            partidoArbitros = partidoArbitroFacade.getListPartidoArbitro(partido);
        }
        return partidoArbitros;
    }

    public PartidoArbitro getPartidoArbitro() {
        if (partidoArbitro == null) {
            partidoArbitro = new PartidoArbitro();
        }
        return partidoArbitro;
    }

    public void setPartidoArbitro(PartidoArbitro partidoArbitro) {
        this.partidoArbitro = partidoArbitro;
    }

    public void setPartidoArbitros(List<PartidoArbitro> partidoArbitros) {
        this.partidoArbitros = partidoArbitros;
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
                convocatoriaVisitante.setEquipoId(partido.getEquipoVisitanteId());
                convocatoriaVisitante.setPartidoId(partido);
                convocatoriaFacade.edit(convocatoriaVisitante);
            }

        }
        return convocatoriaVisitante;
    }

    public Convocado getConvocado() {
        if (convocado == null) {
            convocado = new Convocado();
        }
        return convocado;
    }

    public void setConvocado(Convocado convocado) {
        logger.debug("Convocado  modificandose " + convocado);
        this.convocado = convocado;
    }

    public List<EquipoHasJugador> getJugadorEquipoLocal() {
        if (jugadorEquipoLocal == null) {
            this.jugadorEquipoLocal = equipoHasJugadorFacade.getListEquipoHasJugador(partido.getEquipoLocalId());
        }
        return jugadorEquipoLocal;
    }

    public void setJugadorEquipoLocal(List<EquipoHasJugador> jugadorEquipoLocal) {
        this.jugadorEquipoLocal = jugadorEquipoLocal;
    }

    public List<Convocado> getConvocadoEquipoLocal() {
        if (convocadoEquipoLocal == null) {
            convocadoEquipoLocal = convocadoFacade.getListConvocado(convocatoriaLocal);
        }
        return convocadoEquipoLocal;
    }

    public void setConvocadoEquipoLocal(List<Convocado> convocadoEquipoLocal) {
        this.convocadoEquipoLocal = convocadoEquipoLocal;
    }

    public List<EquipoHasJugador> getJugadorEquipoVisitante() {
        if (jugadorEquipoVisitante == null) {
            this.jugadorEquipoVisitante = equipoHasJugadorFacade.getListEquipoHasJugador(partido.getEquipoVisitanteId());
        }
        return jugadorEquipoVisitante;
    }

    public void setJugadorEquipoVisitante(List<EquipoHasJugador> jugadorEquipoVisitante) {
        this.jugadorEquipoVisitante = jugadorEquipoVisitante;
    }

    public List<Convocado> getConvocadoEquipoVisitante() {
        if (convocadoEquipoVisitante == null) {
            convocadoEquipoVisitante = convocadoFacade.getListConvocado(convocatoriaVisitante);
        }
        return convocadoEquipoVisitante;
    }

    public void setConvocadoEquipoVisitante(List<Convocado> convocadoEquipoVisitante) {
        this.convocadoEquipoVisitante = convocadoEquipoVisitante;
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

    public int getIndexTab() {
        return indexTab;
    }

    public void setIndexTab(int indexTab) {
        this.indexTab = indexTab;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public void guardar() {
        partidoFacade.edit(partido);
        Util.addSuccessMessage("Se edito con exito");

    }

    public String gotoResultadoPage(Partido partido) {
        this.partido = partido;

        recreateModel();
        convocatoriaLocal = getConvocatoriaLocal();
        convocatoriaVisitante = getConvocatoriaVisitante();
        convocadoEquipoVisitante = getConvocadoEquipoVisitante();
        convocadoEquipoLocal = getConvocadoEquipoLocal();

        indexTab = 0;
        return "/admin/liga/temporadas/resultado/detallepartido??faces-redirect=true";
    }

    public void recreateModel() {
        partidoArbitros = null;
        jugadorEquipoLocal = null;
        convocadoEquipoLocal = null;
        jugadorEquipoVisitante = null;
        convocadoEquipoVisitante = null;

    }

    public void onTabChange(TabChangeEvent event) {
        try {
            if (event != null) {
                if (event.getTab().getId().equals("tab1")) {
                    indexTab = 0;
                }
                if (event.getTab().getId().equals("tab2")) {
                    indexTab = 1;
                }
                if (event.getTab().getId().equals("tab3")) {
                    indexTab = 2;
                }
                if (event.getTab().getId().equals("tab4")) {
                    indexTab = 3;
                }
                if (event.getTab().getId().equals("tab5")) {
                    indexTab = 4;
                }
            }

        } catch (NullPointerException e) {
            logger.error("El evento era Null", e.getMessage());
        }

    }
}
