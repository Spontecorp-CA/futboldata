/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.ClasificacionGrupo;
import com.spontecorp.futboldata.entity.CompeticionHasJugador;
import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.entity.Convocatoria;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoHasJugador;
import com.spontecorp.futboldata.entity.Evento;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.PartidoArbitro;
import com.spontecorp.futboldata.entity.PartidoEvento;
import com.spontecorp.futboldata.entity.PartidoEventoEquipo;
import com.spontecorp.futboldata.entity.Staff;
import com.spontecorp.futboldata.entity.TipoEvento;
import com.spontecorp.futboldata.jpacontroller.ClasificacionFacade;
import com.spontecorp.futboldata.jpacontroller.ClasificacionGrupoFacade;
import com.spontecorp.futboldata.jpacontroller.CompeticionHasJugadorFacade;
import com.spontecorp.futboldata.jpacontroller.ConvocadoFacade;
import com.spontecorp.futboldata.jpacontroller.ConvocatoriasFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoEnGrupoFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoHasJugadorFacade;
import com.spontecorp.futboldata.jpacontroller.EventoFacade;
import com.spontecorp.futboldata.jpacontroller.JornadaFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoArbitroFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoEventoEquipoFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoEventoFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoFacade;
import com.spontecorp.futboldata.jpacontroller.StaffFacade;
import com.spontecorp.futboldata.reportes.template.ClasificacionesReport;
import com.spontecorp.futboldata.reportes.template.InformeArbitralReport;
import com.spontecorp.futboldata.reportes.template.InformeArbitralReportBlanco;
import com.spontecorp.futboldata.reportes.view.ReportesBean;
import com.spontecorp.futboldata.utilities.Util;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;
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
    private Convocado convocadoVisitante;
    private Arbitro arbitro;
    private Convocatoria convocatoriaVisitante;
    private Convocatoria convocatoriaLocal;
    private PartidoEvento eventoSelected;
    private PartidoArbitro partidoArbitro;
    private EquipoHasJugador equipoHasJugador;
    private PartidoEvento partidoEvento;
    private PartidoEventoEquipo eventoEquipo;
    private PartidoEventoEquipo equipoEvento;
    private TipoEvento tipoEvento;
    private TipoEvento tipoEventoE;
    private int tipoCantidadEvento;
    private ClasificacionGrupo clasificacionGrupo;
    private boolean capitan;

    private double cant_percent;

    private final PartidoFacade partidoFacade;
    private final ConvocatoriasFacade convocatoriaFacade;
    private final ConvocadoFacade convocadoFacade;
    private final EquipoHasJugadorFacade equipoHasJugadorFacade;
    private final PartidoArbitroFacade partidoArbitroFacade;
    private final StaffFacade staffFacade;
    private final PartidoEventoFacade partidoEventoFacade;
    private final ClasificacionFacade clasificacionFacade;
    private final ClasificacionGrupoFacade clasificacionGrupoFacade;
    private final EquipoEnGrupoFacade equipoEnGrupoFacade;
    private final EventoFacade eventoFacade;
    private final JornadaFacade jornadaFacade;
    private final PartidoEventoEquipoFacade eventoEquipoFacade;
    private final CompeticionHasJugadorFacade competicionHasJugadorFacade;

    private List<Evento> comboEvento;
    private List<Evento> comboEventoEquipo;
    //private List<> // reparar esto
    private List<Staff> staffs;
    private List<Convocado> convocados;
    private List<EquipoHasJugador> jugadorEquipoLocal;
    private List<Convocado> convocadoEquipoLocal;
    private List<EquipoHasJugador> jugadorEquipoVisitante;
    private List<Convocado> convocadoEquipoVisitante;
    private List<PartidoArbitro> partidoArbitros;
    private List<PartidoEvento> eventos;
    private List<PartidoEventoEquipo> eventosEquipo;
    private List<PartidoEvento> filteredEventos;
    private static final Logger logger = LoggerFactory.getLogger(ResultadoBean.class);
    private Clasificacion clasificacion;
    private LoginBean bean;

    public class TablaEventoEquipo {

        public Evento evento;
        public PartidoEventoEquipo equipoLocal;
        public PartidoEventoEquipo equipoVisitante;

        public Evento getEvento() {

            return evento;
        }

        public void setEvento(Evento evento) {
            this.evento = evento;
        }

        public PartidoEventoEquipo getEquipoLocal() {
            return equipoLocal;
        }

        public void setEquipoLocal(PartidoEventoEquipo equipoLocal) {
            this.equipoLocal = equipoLocal;
        }

        public PartidoEventoEquipo getEquipoVisitante() {
            return equipoVisitante;
        }

        public void setEquipoVisitante(PartidoEventoEquipo equipoVisitante) {
            this.equipoVisitante = equipoVisitante;
        }
    }
    private TablaEventoEquipo tablaEventoEquipo;
    private List<TablaEventoEquipo> tablaEventos;

    /**
     * Creates a new instance of ResultadoBean
     */
    public ResultadoBean() {
        partidoFacade = new PartidoFacade();
        convocatoriaFacade = new ConvocatoriasFacade();
        convocadoFacade = new ConvocadoFacade();
        eventoEquipoFacade = new PartidoEventoEquipoFacade();
        equipoHasJugadorFacade = new EquipoHasJugadorFacade();
        partidoArbitroFacade = new PartidoArbitroFacade();
        staffFacade = new StaffFacade();
        partidoEventoFacade = new PartidoEventoFacade();
        clasificacionFacade = new ClasificacionFacade();
        clasificacionGrupoFacade = new ClasificacionGrupoFacade();
        eventoFacade = new EventoFacade();
        equipoEnGrupoFacade = new EquipoEnGrupoFacade();
        jornadaFacade = new JornadaFacade();
        competicionHasJugadorFacade = new CompeticionHasJugadorFacade();
        bean = (LoginBean) Util.findBean("loginBean");
    }

    public double getCant_percent() {
        return cant_percent;
    }

    public void setCant_percent(double cant_percent) {
        this.cant_percent = cant_percent;
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
        if (capitan) {
            convocado.setCapitan(1);
        }
        if (convocado.getConvocatoriaId() == convocatoriaLocal) {
            editConvocado(convocatoriaLocal, convocadoEquipoLocal);
        } else {
            editConvocado(convocatoriaVisitante, convocadoEquipoVisitante);
        }
        recreateModel();
        convocatoriaLocal = getConvocatoriaLocal();
        convocatoriaVisitante = getConvocatoriaVisitante();
        convocadoEquipoVisitante = getConvocadoEquipoVisitante();
        convocadoEquipoLocal = getConvocadoEquipoLocal();

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
        convocatoriaLocal = getConvocatoriaLocal();
        convocatoriaVisitante = getConvocatoriaVisitante();
        convocadoEquipoVisitante = getConvocadoEquipoVisitante();
        convocadoEquipoLocal = getConvocadoEquipoLocal();
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
            Convocado convocadoTem = convocadoFacade.getConvocado(convocado.getJugadorId(), convocatoria);
            CompeticionHasJugador competicionHasJugador = competicionHasJugadorFacade.
                    getCompeticionHasJugador(convocado.getJugadorId(), Util.getCompeticion(partido));
            if (convocadoTem == null) {
                if (competicionHasJugador != null) {
                    convocado.setCompeticionHasJugadorId(competicionHasJugador);
                }
                convocadoFacade.edit(convocado);
                Util.addSuccessMessage("Se agrego el convocado con exito");
                convocados.add(convocado);
            } else {
                if (competicionHasJugador != null) {
                    convocadoTem.setCompeticionHasJugadorId(competicionHasJugador);
                }
                convocadoFacade.edit(convocadoTem);
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

    public boolean isCapitan() {
        return capitan;
    }

    public void setCapitan(boolean capitan) {
        this.capitan = capitan;
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

    public Convocado getConvocadoVisitante() {
        return convocadoVisitante;
    }

    public void setConvocadoVisitante(Convocado convocadoVisitante) {
        this.convocadoVisitante = convocadoVisitante;
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

    public PartidoEventoEquipo getEquipoEvento() {
        if (equipoEvento == null) {
            equipoEvento = new PartidoEventoEquipo();
            equipoEvento.setPorcentaje(0.0);
        }
        return equipoEvento;
    }

    public void setEquipoEvento(PartidoEventoEquipo equipoEvento) {
        this.equipoEvento = equipoEvento;
    }

    public int getTipoCantidadEvento() {
        return tipoCantidadEvento;
    }

    public void setTipoCantidadEvento(int tipoCantidadEvento) {
        this.tipoCantidadEvento = tipoCantidadEvento;
    }

    public List<PartidoEventoEquipo> getEventosEquipo() {
        if (eventosEquipo == null) {
            eventosEquipo = eventoEquipoFacade.findEventoEquipo(partido);
        }
        return eventosEquipo;
    }

    public void setEventosEquipo(List<PartidoEventoEquipo> eventosEquipo) {
        this.eventosEquipo = eventosEquipo;
    }

    /**
     * *********************partido *************************
     */
    public void guardar() {
        partidoFacade.edit(partido);
        if (partido.getStatusPartidoId().getValue() != 0
                && partido.getGolesEquipoVisitante() != null
                && partido.getGolesEquipoLocal() != null
                && partido.getLlaveId() == null) {
            createUpdateClasificacionLocal();
            createUpdateClasificacionVisitante();
        }
        Util.addSuccessMessage("Se guardaron los datos del partido con éxito");
    }

    private void createUpdateClasificacionLocal() {

        Grupo grupoDelPartido = partido.getJornadaId().getGrupoId();
        logger.debug("el grupo del partido es: " + grupoDelPartido.getNombre());
        Fase faseTemp = partido.getJornadaId().getGrupoId().getFaseId();
        logger.debug("la fase es: " + faseTemp.getNombre());
        Grupo grupoDelEquipo = equipoEnGrupoFacade.getGrupoxFasexEquipo(faseTemp, partido.getEquipoLocalId());
        logger.debug("EL grupo del equipo es: " + grupoDelEquipo);
        Jornada jornada;
        if (!grupoDelPartido.equals(grupoDelEquipo)) {
            // chequear que la jornada no exista!!!
            Jornada oldJornada = jornadaFacade.findJornadaxGrupo(grupoDelEquipo, partido.getJornadaId().getNumero());
            if (oldJornada == null) {
                jornada = new Jornada();
                jornada.setNumero(partido.getJornadaId().getNumero());
                jornada.setStatus(partido.getJornadaId().getStatus());
                jornada.setAlias(partido.getJornadaId().getAlias());
                jornada.setGrupoId(grupoDelEquipo);
                jornadaFacade.create(jornada);
            } else {
                jornada = oldJornada;
            }
        } else {
            jornada = partido.getJornadaId();
        }
        if (clasificacionFacade.findClasificacion(partido, partido.getEquipoLocalId()) == null) {
            clasificacion = new Clasificacion();
            logger.debug("la jornada a crear es: " + jornada);
            clasificacion.setJornadaId(jornada);
            clasificacion.setEquipoId(partido.getEquipoLocalId());
            clasificacion.setIsLocal(Util.LOCAL);
            clasificacion.setGolesFavor(partido.getGolesEquipoLocal());
            clasificacion.setGolesContra(partido.getGolesEquipoVisitante());
            clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
            calcularPuntos(clasificacion);
            clasificacion.setPartidoId(partido);
            clasificacion.setGrupoId(grupoDelEquipo);
            clasificacionFacade.create(clasificacion);

        } else {
            clasificacion = clasificacionFacade.findClasificacion(partido, partido.getEquipoLocalId());
            clasificacion.setIsLocal(Util.LOCAL);
            clasificacion.setGolesFavor(partido.getGolesEquipoLocal());
            clasificacion.setGolesContra(partido.getGolesEquipoVisitante());
            clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
            calcularPuntos(clasificacion);
            clasificacionFacade.edit(clasificacion);
        }

    }

    private void createUpdateClasificacionVisitante() {
        Grupo grupoDelPartido = partido.getJornadaId().getGrupoId();
        logger.debug("el grupo del partido es (visitante): " + grupoDelPartido.getNombre());
        Fase faseTemp = partido.getJornadaId().getGrupoId().getFaseId();
        logger.debug("la fase (visiatante) es: " + faseTemp.getNombre());
        Grupo grupoDelEquipo = equipoEnGrupoFacade.getGrupoxFasexEquipo(faseTemp, partido.getEquipoVisitanteId());
        logger.debug("EL grupo del equipo (visitante) es: " + grupoDelEquipo);
        Jornada jornada;
        if (!grupoDelPartido.equals(grupoDelEquipo)) {
            Jornada oldJornada = jornadaFacade.findJornadaxGrupo(grupoDelEquipo, partido.getJornadaId().getNumero());
            if (oldJornada == null) {
                jornada = new Jornada();
                jornada.setNumero(partido.getJornadaId().getNumero());
                jornada.setStatus(partido.getJornadaId().getStatus());
                jornada.setAlias(partido.getJornadaId().getAlias());
                jornada.setGrupoId(grupoDelEquipo);
                jornadaFacade.create(jornada);
            } else {
                jornada = oldJornada;
            }
        } else {
            jornada = partido.getJornadaId();
        }
        if (clasificacionFacade.findClasificacion(partido, partido.getEquipoVisitanteId()) == null) {
            clasificacion = new Clasificacion();
            logger.debug("la jornada (del visitante) a crear es: " + jornada);
            clasificacion.setJornadaId(jornada);
            clasificacion.setEquipoId(partido.getEquipoVisitanteId());
            clasificacion.setIsLocal(Util.VISITANTE);
            clasificacion.setGolesFavor(partido.getGolesEquipoVisitante());
            clasificacion.setGolesContra(partido.getGolesEquipoLocal());
            clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
            calcularPuntos(clasificacion);
            clasificacion.setPartidoId(partido);
            clasificacion.setGrupoId(grupoDelEquipo);
            clasificacionFacade.create(clasificacion);

        } else {
            clasificacion = clasificacionFacade.findClasificacion(partido, partido.getEquipoVisitanteId());
            clasificacion.setIsLocal(Util.VISITANTE);
            clasificacion.setGolesFavor(partido.getGolesEquipoVisitante());
            clasificacion.setGolesContra(partido.getGolesEquipoLocal());
            clasificacion.setDiferencia(clasificacion.getGolesFavor() - clasificacion.getGolesContra());
            calcularPuntos(clasificacion);
            clasificacionFacade.edit(clasificacion);
        }

    }

    private ClasificacionGrupo createClasificiacionGrupo(Equipo equipo) {

        Grupo grupo;
        grupo = equipoEnGrupoFacade.getGrupoxFasexEquipo(partido.getJornadaId().getGrupoId().getFaseId(), equipo);
        if (grupo == null) {
            logger.error("Debe ingresar el equipo " + equipo.getNombre() + " a un grupo");
        }
        if (clasificacionGrupoFacade.findClasificacion(grupo, equipo) == null) {
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
        clasificacion.setJJugados(1);
        if (clasificacion.getDiferencia() == 0) {
            clasificacion.setPuntos(Util.PUNTOS_EMPATE);
            clasificacion.setJGanados(0);
            clasificacion.setJPerdidos(0);
            clasificacion.setJEmpatados(1);

        } else if (clasificacion.getDiferencia() < 0) {
            clasificacion.setPuntos(Util.PUNTOS_PERDEDOR);
            clasificacion.setJGanados(0);
            clasificacion.setJPerdidos(1);
            clasificacion.setJEmpatados(0);
        } else {
            clasificacion.setPuntos(Util.PUNTOS_GANADOR);
            clasificacion.setJGanados(1);
            clasificacion.setJPerdidos(0);
            clasificacion.setJEmpatados(0);
        }
    }

    public String gotoResultadoPage(Partido partido) {
        this.partido = partido;
        if (partido.getAsistencia() == null) {
            partido.setAsistencia(0);
        }
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
        eventosEquipo = null;
        tablaEventos = null;

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
                if (event.getTab().getId().equals("tab6")) {
                    indexTab = 5;
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
            partidoEvento.setMinuto(0);
            partidoEvento.setCantidad(0);
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

    public List<PartidoEvento> getFilteredEventos() {
        return filteredEventos;
    }

    public void setFilteredEventos(List<PartidoEvento> filteredEventos) {
        this.filteredEventos = filteredEventos;
    }

    public void createEvento() {
        if (convocado != null) {
            partidoEvento.setConvocadoId(convocado);
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
        partidoEvento.setCantidad(0);
        partidoEvento.setMinuto(0);
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

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public List<Evento> getComboEvento() {
        if (comboEvento == null) {
            comboEvento = eventoFacade.findEvento(tipoEvento);
        }
        return comboEvento;
    }

    public void setComboEvento(List<Evento> comboEvento) {
        this.comboEvento = comboEvento;
    }

    public void setComboEventoEquipo(List<Evento> comboEventoEquipo) {
        this.comboEventoEquipo = comboEventoEquipo;
    }

    public PartidoEvento getEventoSelected() {
        return eventoSelected;
    }

    public void setEventoSelected(PartidoEvento eventoSelected) {
        this.eventoSelected = eventoSelected;
    }

    public void TipoEventoSelected(ValueChangeEvent changeEvent) {
        if (tipoEvento == null) {
            comboEvento = eventoFacade.findAll(bean.getIdOrganizacion());
        } else {
            comboEvento = eventoFacade.findEvento(tipoEvento);
        }
    }

    /**
     * *******Partido Evento Equipo*********
     */
    public void TipoEventoSelectedE(ValueChangeEvent changeEvent) {
        if (tipoEvento == null) {
            comboEvento = eventoFacade.findAll(bean.getIdOrganizacion());
        } else {
            comboEvento = eventoFacade.findEvento(tipoEventoE);
        }
    }

    public List<Evento> getComboEventoEquipo() {
        comboEventoEquipo = null;
        if (comboEventoEquipo == null) {
            comboEventoEquipo = eventoFacade.findEvento(tipoEventoE);
        }
        return comboEventoEquipo;
    }

    public TipoEvento getTipoEventoE() {
        return tipoEventoE;
    }

    public void setTipoEventoE(TipoEvento tipoEventoE) {
        this.tipoEventoE = tipoEventoE;
    }

    public PartidoEventoEquipo getEventoEquipo() {
        return eventoEquipo;
    }

    public void setEventoEquipo(PartidoEventoEquipo eventoEquipo) {
        this.eventoEquipo = eventoEquipo;
    }

    public TablaEventoEquipo getTablaEventoEquipo() {
        return tablaEventoEquipo;
    }

    public void setTablaEventoEquipo(TablaEventoEquipo tablaEventoEquipo) {
        this.tablaEventoEquipo = tablaEventoEquipo;
    }

    public List<TablaEventoEquipo> getTablaEventos() {
        if (tablaEventos == null) {
            tablaEventos = new ArrayList<TablaEventoEquipo>();
            List<PartidoEventoEquipo> listTemporalEventos = new ArrayList<PartidoEventoEquipo>();
            tablaEventoEquipo = new TablaEventoEquipo();
            for (PartidoEventoEquipo eventoExterno : findEventosPartido()) {
                for (PartidoEventoEquipo eventoInterno : findEventosPartido()) {
                    if (eventoExterno.getEventoId() == eventoInterno.getEventoId()
                            && !eventoExterno.equals(eventoInterno)) {
                        if (eventoExterno.getEquipoId().equals(partido.getEquipoLocalId())) {
                            tablaEventoEquipo = new TablaEventoEquipo();
                            tablaEventoEquipo.equipoLocal = eventoExterno;
                            tablaEventoEquipo.equipoVisitante = eventoInterno;
                            listTemporalEventos.add(eventoExterno);
                            listTemporalEventos.add(eventoInterno);
                            tablaEventoEquipo.evento = eventoExterno.getEventoId();
                        }
//                       else if ((eventoInterno.getEquipoId().equals(partido.getEquipoLocalId()))) {
//                            tablaEventoEquipo = new TablaEventoEquipo();
//                            tablaEventoEquipo.equipoLocal = eventoInterno;
//                            tablaEventoEquipo.equipoVisitante = eventoExterno;
//                            listTemporalEventos.add(eventoExterno);
//                            listTemporalEventos.add(eventoInterno);
//                            tablaEventoEquipo.evento = eventoExterno.getEventoId();
//                        }
                        if (!tablaEventos.contains(tablaEventoEquipo)) {
                            tablaEventos.add(tablaEventoEquipo);
                        }
                    }
                }
            }
            for (PartidoEventoEquipo partidoEventoEquipo : getEventosEquipo()) {
                if (!listTemporalEventos.contains(partidoEventoEquipo)) {
                    if (partido.getEquipoLocalId().equals(partidoEventoEquipo.getEquipoId())) {
                        tablaEventoEquipo = new TablaEventoEquipo();
                        tablaEventoEquipo.equipoLocal = partidoEventoEquipo;
                        tablaEventoEquipo.equipoVisitante = new PartidoEventoEquipo();
                        tablaEventoEquipo.evento = partidoEventoEquipo.getEventoId();
                        tablaEventos.add(tablaEventoEquipo);
                    } else {
                        tablaEventoEquipo = new TablaEventoEquipo();
                        tablaEventoEquipo.equipoVisitante = partidoEventoEquipo;
                        tablaEventoEquipo.equipoLocal = new PartidoEventoEquipo();
                        tablaEventoEquipo.evento = partidoEventoEquipo.getEventoId();
                        tablaEventos.add(tablaEventoEquipo);
                    }
                }

            }
        }
        return tablaEventos;
    }

    public void guardarEventoPartido(ActionEvent event) {

        if (eventoEquipoFacade.findEventoEquipo(partido, equipoEvento.getEquipoId(), equipoEvento.getEventoId()) == null) {
            equipoEvento.setPartidoId(partido);
            if (equipoEvento.getEventoId().getTipoValor() == 0) {
                equipoEvento.setCantidad((int) cant_percent);
            } else if (equipoEvento.getEventoId().getTipoValor() == 1) {
                equipoEvento.setPorcentaje(cant_percent / 100);
            }
            eventoEquipoFacade.create(equipoEvento);
            recreateModelEventoEquipo();
            Util.addSuccessMessage("Se creó con éxito el evento");
        } else {
            Util.addErrorMessage("Ya el Evento del Equipo esta Creado");
        }

    }

    public void eliminarEventoPartido() {
        if (tablaEventoEquipo.getEquipoLocal().getEventoId() != null) {
            eventoEquipoFacade.remove(tablaEventoEquipo.getEquipoLocal());
        }
        if (tablaEventoEquipo.getEquipoVisitante().getEventoId() != null) {
            eventoEquipoFacade.remove(tablaEventoEquipo.getEquipoVisitante());
        }
        recreateModelEventoEquipo();
        Util.addSuccessMessage("Se elimino el Evento con Exito");
    }

    public List<PartidoEventoEquipo> findEventosPartido() {
        if (eventosEquipo == null) {
            eventosEquipo = eventoEquipoFacade.findEventoEquipo(partido);
        }
        return eventosEquipo;
    }

    private void recreateModelEventoEquipo() {
        equipoEvento = null;
        equipo = null;
        tipoEventoE = null;
        comboEventoEquipo = null;
        eventosEquipo = null;
        tablaEventos = null;
    }

    public void createPDF(ActionEvent actionEvent) throws IOException {
        try {

            JasperReportBuilder builder;
            builder = InformeArbitralReport.crearReporte(partido, convocadoEquipoLocal,
                    convocadoEquipoVisitante, staffFacade.findStaffListByEquipo(partido.getEquipoLocalId()),
                    staffFacade.findStaffListByEquipo(partido.getEquipoVisitanteId()),
                    partidoArbitros, eventos);

            JasperPrint jasperPrint = builder.toJasperPrint();
            Util.exportarPDF(jasperPrint);

        } catch (DRException ex) {
            java.util.logging.Logger.getLogger(ReportesBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public void createPDFBlanco(ActionEvent actionEvent) throws IOException {
        try {

            JasperReportBuilder builder;
            builder = InformeArbitralReportBlanco.crearReporte(partido, jugadorEquipoLocal,
                    jugadorEquipoVisitante);

            JasperPrint jasperPrint = builder.toJasperPrint();
            Util.exportarPDF(jasperPrint);

        } catch (DRException ex) {
            java.util.logging.Logger.getLogger(ReportesBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }
}
