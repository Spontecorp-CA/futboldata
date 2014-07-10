/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.ClasificacionGrupo;
import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.entity.Convocatoria;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoHasJugador;
import com.spontecorp.futboldata.entity.Evento;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.PartidoArbitro;
import com.spontecorp.futboldata.entity.PartidoEvento;
import com.spontecorp.futboldata.entity.Staff;
import com.spontecorp.futboldata.entity.TipoEvento;
import com.spontecorp.futboldata.jpacontroller.ClasificacionFacade;
import com.spontecorp.futboldata.jpacontroller.ClasificacionGrupoFacade;
import com.spontecorp.futboldata.jpacontroller.ConvocadoFacade;
import com.spontecorp.futboldata.jpacontroller.ConvocatoriasFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoHasJugadorFacade;
import com.spontecorp.futboldata.jpacontroller.EventoFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoArbitroFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoEventoFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoFacade;
import com.spontecorp.futboldata.jpacontroller.StaffFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
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
    private Staff staff;
    private Equipo equipo;
    private Partido partido;
    private Convocado convocado;
    private Convocado convocadoLocal;
    private Arbitro arbitro;
    private Convocatoria convocatoriaVisitante;
    private Convocatoria convocatoriaLocal;
    private PartidoEvento eventoSelected;
    private PartidoArbitro partidoArbitro;
    private EquipoHasJugador equipoHasJugador;
    private PartidoEvento partidoEvento;
    private TipoEvento tipoEvento;
    private ClasificacionGrupo clasificacionGrupo;
    private final PartidoFacade partidoFacade;
    private final ConvocatoriasFacade convocatoriaFacade;
    private final ConvocadoFacade convocadoFacade;
    private final EquipoHasJugadorFacade equipoHasJugadorFacade;
    private final PartidoArbitroFacade partidoArbitroFacade;
    private final StaffFacade staffFacade;
    private final PartidoEventoFacade partidoEventoFacade;
    private final ClasificacionFacade clasificacionFacade;
    private final ClasificacionGrupoFacade clasificacionGrupoFacade;
    private final EventoFacade eventoFacade;
    private List<Evento> comboEvento;
    private List<Staff> staffs;
    private List<Convocado> convocados;
    private List<EquipoHasJugador> jugadorEquipoLocal;
    private List<Convocado> convocadoEquipoLocal;
    private List<EquipoHasJugador> jugadorEquipoVisitante;
    private List<Convocado> convocadoEquipoVisitante;
    private List<PartidoArbitro> partidoArbitros;
    private List<PartidoEvento> eventos;
    private static final Logger logger = LoggerFactory.getLogger(ResultadoBean.class);
    private Clasificacion clasificacion;

    /**
     * Creates a new instance of ResultadoBean
     */
    public ResultadoBean() {
        partidoFacade = new PartidoFacade();
        convocatoriaFacade = new ConvocatoriasFacade();
        convocadoFacade = new ConvocadoFacade();
        equipoHasJugadorFacade = new EquipoHasJugadorFacade();
        partidoArbitroFacade = new PartidoArbitroFacade();
        staffFacade = new StaffFacade();
        partidoEventoFacade = new PartidoEventoFacade();
        clasificacionFacade = new ClasificacionFacade();
        clasificacionGrupoFacade = new ClasificacionGrupoFacade();
        eventoFacade = new EventoFacade();
    }

    /**
     * ************codigo de jugadores Convocados ********* *
     * ************************************************
     */
    public void preEditConvocado(Convocatoria convocatoria) {
        convocado = new Convocado();
        convocado.setJugadorId(equipoHasJugador.getJugadorId());
        convocado.setConvocatoriaId(convocatoria);
        convocado.setCamiseta(equipoHasJugador.getJugadorId().getCamiseta());
        convocado.setPosicionId(equipoHasJugador.getJugadorId().getPosicionId());
    }

    public void editConvocado() {
        if (convocado.getConvocatoriaId() == convocatoriaLocal) {
            editConvocado(convocatoriaLocal, convocadoEquipoLocal);
        } else {
            editConvocado(convocatoriaVisitante, convocadoEquipoVisitante);
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

    public List<Convocado> getConvocados() {
        if (convocados == null) {
            if (this.equipo == this.partido.getEquipoLocalId()) {
                convocados = convocadoEquipoLocal;
            } else if (this.equipo == this.partido.getEquipoVisitanteId()) {
                convocados = convocadoEquipoVisitante;
            }
        }

        return convocados;
    }

    public void setConvocados(List<Convocado> convocados) {
        this.convocados = convocados;
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

    /**
     * ******************************************
     * Codigo para la asignacion de Arbitros**
     *
     */
    public void preEditArbitro() {
        partidoArbitro = new PartidoArbitro();
        partidoArbitro.setPartidoId(partido);
        partidoArbitro.setArbitroId(arbitro);

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

    public void removeArbitro() {
        partidoArbitros.remove(partidoArbitro);
        partidoArbitroFacade.remove(partidoArbitro);
        Util.addSuccessMessage("Se elimino exitosamente");

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

    public Partido getPartido() {
        return partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    /**
     * *********************partido *************************
     */
    public void guardar() {
        partidoFacade.edit(partido);
        if (partido.getStatusPartidoId().getValue() != 0 && partido.getGolesEquipoVisitante() != null
                && partido.getGolesEquipoLocal() != null) {
            createClasificacionLocal();
            createClasificacionVisitante();
            clasificacionGrupoFacade.actulizar(createClasificiacionGrupo(partido.getEquipoLocalId()));
            clasificacionGrupoFacade.actulizar(createClasificiacionGrupo(partido.getEquipoVisitanteId()));
        }
        Util.addSuccessMessage("Se edito con exito");
    }

    private void createClasificacionLocal() {
        if (partido.getJornadaId() != null) {

            if (clasificacionFacade.findClasificacion(partido, partido.getEquipoLocalId()) == null) {
                clasificacion = new Clasificacion();
                clasificacion.setJornadaId(partido.getJornadaId());
                clasificacion.setEquipoId(partido.getEquipoLocalId());
                clasificacion.setIsLocal(1);
                clasificacion.setGolesFavor(partido.getGolesEquipoLocal());
                clasificacion.setGolesContra(partido.getGolesEquipoVisitante());
                clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
                calcularPuntos(clasificacion);
                clasificacion.setPartidoId(partido);
                clasificacion.setClasificacionGrupoId(createClasificiacionGrupo(partido.getEquipoLocalId()));
                clasificacionFacade.edit(clasificacion);

            } else {
                clasificacion = clasificacionFacade.findClasificacion(partido, partido.getEquipoLocalId());
                clasificacion.setIsLocal(1);
                clasificacion.setGolesFavor(partido.getGolesEquipoLocal());
                clasificacion.setGolesContra(partido.getGolesEquipoVisitante());
                clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
                calcularPuntos(clasificacion);
                clasificacionFacade.edit(clasificacion);
            }

        }
    }

    private void createClasificacionVisitante() {
        if (partido.getJornadaId() != null) {

            if (clasificacionFacade.findClasificacion(partido, partido.getEquipoVisitanteId()) == null) {
                clasificacion = new Clasificacion();
                clasificacion.setJornadaId(partido.getJornadaId());
                clasificacion.setEquipoId(partido.getEquipoVisitanteId());
                clasificacion.setIsLocal(0);
                clasificacion.setGolesFavor(partido.getGolesEquipoVisitante());
                clasificacion.setGolesContra(partido.getGolesEquipoLocal());
                clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
                calcularPuntos(clasificacion);
                clasificacion.setPartidoId(partido);
                clasificacion.setClasificacionGrupoId(createClasificiacionGrupo(partido.getEquipoVisitanteId()));
                clasificacionFacade.edit(clasificacion);
            } else {
                clasificacion = clasificacionFacade.findClasificacion(partido, partido.getEquipoVisitanteId());
                clasificacion.setIsLocal(0);
                clasificacion.setGolesFavor(partido.getGolesEquipoVisitante());
                clasificacion.setGolesContra(partido.getGolesEquipoLocal());
                clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
                calcularPuntos(clasificacion);
                clasificacionFacade.edit(clasificacion);
            }

        }
    }

    private ClasificacionGrupo createClasificiacionGrupo(Equipo equipo) {
        if (clasificacionGrupoFacade.findClasificacion(partido.getJornadaId().getGrupoId(), equipo) == null) {
            clasificacionGrupo = new ClasificacionGrupo();
            clasificacionGrupo.setStatus(0);
            clasificacionGrupo.setEquipoId(equipo);
            clasificacionGrupo.setGrupoId(partido.getJornadaId().getGrupoId());

        } else {
            clasificacionGrupo = clasificacionGrupoFacade.findClasificacion(partido.getJornadaId().getGrupoId(), equipo);
            clasificacionGrupo.setStatus(0);
        }
        return clasificacionGrupo;
    }

    private void calcularPuntos(Clasificacion clasificacion) {
        if (clasificacion.getDiferencia() == 0) {
            clasificacion.setPuntos(1);
            clasificacion.setJGanados(0);
            clasificacion.setJPerdidos(0);
            clasificacion.setJEmpatados(1);
            clasificacion.setJJugados(1);
        } else if (clasificacion.getDiferencia() < 0) {
            clasificacion.setPuntos(0);
            clasificacion.setJGanados(0);
            clasificacion.setJPerdidos(1);
            clasificacion.setJEmpatados(0);
            clasificacion.setJJugados(1);
        } else {
            clasificacion.setPuntos(3);
            clasificacion.setJGanados(1);
            clasificacion.setJPerdidos(0);
            clasificacion.setJEmpatados(0);
            clasificacion.setJJugados(1);

        }
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
        eventos = null;
        convocatoriaLocal = null;
        convocatoriaVisitante = null;
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

    /*-------------------------codigo de evento ------------------------*/
    public PartidoEvento getPartidoEvento() {
        if (partidoEvento == null) {
            partidoEvento = new PartidoEvento();
        }
        return partidoEvento;
    }

    public void setPartidoEvento(PartidoEvento partidoEvento) {
        this.partidoEvento = partidoEvento;
    }

    public List<PartidoEvento> getEventos() {
        if (eventos == null) {
            eventos = partidoEventoFacade.findPartidoEventoxPartido(partido);
        }
        return eventos;
    }

    public void setEventos(List<PartidoEvento> eventos) {
        this.eventos = eventos;
    }

    public void createEvento() {
        if (convocado != null) {
            partidoEvento.setJugador1Id(convocado.getJugadorId());
        }

        partidoEvento.setPartidoId(partido);
        partidoEvento.setStaffId(staff);
        partidoEventoFacade.edit(partidoEvento);
        eventos.add(partidoEvento);
        recreateModelEvento();
        Util.addSuccessMessage("Se creo el evento");

    }

    public void recreateModelEvento() {
        partidoEvento = new PartidoEvento();
        staff = null;
        convocado = null;
        equipo = null;
        tipoEvento = null;
        comboEvento = null;
    }

    public void equipoSelected() {
        try {
            if (this.partido.getEquipoLocalId().equals(equipo)) {
                convocados = convocadoEquipoLocal;
                staffs = staffFacade.findStaffListByEquipo(equipo);

            } else if (this.partido.getEquipoVisitanteId().equals(equipo)) {
                convocados = convocadoEquipoVisitante;
                staffs = staffFacade.findStaffListByEquipo(equipo);
            } else {
                logger.debug("No entro en ningun If");
            }
        } catch (Exception e) {
            logger.debug("no se encontro el Equipo");
        }

    }

    public void deleteEvento() {
        partidoEventoFacade.remove(eventoSelected);
        eventos.remove(eventoSelected);
        Util.addSuccessMessage("Se elimino el evento con éxito");
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public List<Evento> getComboEvento() {
        if (comboEvento == null) {
            comboEvento = eventoFacade.findAll();
        }
        return comboEvento;
    }

    public void setComboEvento(List<Evento> comboEvento) {
        this.comboEvento = comboEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public PartidoEvento getEventoSelected() {
        return eventoSelected;
    }

    public void setEventoSelected(PartidoEvento eventoSelected) {
        this.eventoSelected = eventoSelected;
    }

    public void TipoEventoSelected(ValueChangeEvent changeEvent) {
        if (tipoEvento == null) {
            comboEvento = eventoFacade.findAll();
        } else {
            comboEvento = eventoFacade.findEvento(tipoEvento);
        }
    }
}
