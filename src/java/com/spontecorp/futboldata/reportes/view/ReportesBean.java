/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.reportes.view;

import com.spontecorp.futboldata.entity.Clasifica;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.entity.TemporadaCategoria;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.FaseFacade;
import com.spontecorp.futboldata.jpacontroller.GrupoFacade;
import com.spontecorp.futboldata.jpacontroller.JornadaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.reportes.ReportesDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named(value = "reportesBean")
@SessionScoped
public class ReportesBean implements Serializable{

    private Competicion liga;
    private Temporada temporada;
    private Fase fase;
    private Llave llave;
    private Jornada jornada;
    private Grupo grupo;
    private List<Clasifica> clasificacion;
    private final ReportesDAO dao;
//    @ManagedProperty(value = "#{configBean}")
//    private ConfigBean configBean;
    private TemporadaCategoria temporadaCategoria;
    
    private List<Competicion> ligas;
    private List<Temporada> temporadaList;
    private List<Fase> fases;
    private List<Grupo> grupos;
    private List<Jornada> jornadas;
    private List<Equipo> equipos;
    
    private final CompeticionFacade ligaController;
    private final TemporadaFacade temporadaFacade;
    private final FaseFacade faseFacade;
    private final GrupoFacade grupoFacade;
    private final JornadaFacade jornadaFacade;
    
    private static final Logger logger = LoggerFactory.getLogger(ReportesBean.class);
    
    /**
     * Creates a new instance of ReportesBean
     */
    public ReportesBean() {
        dao = new ReportesDAO();
        this.ligaController = new CompeticionFacade();
        this.temporadaFacade = new TemporadaFacade();
        this.faseFacade = new FaseFacade();
        this.grupoFacade = new GrupoFacade();
        this.jornadaFacade = new JornadaFacade();
    }

    public Competicion getLiga() {
        return liga;
    }

    public void setLiga(Competicion liga) {
        this.liga = liga;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Llave getLlave() {
        return llave;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Clasifica> getClasificacion() {
        return clasificacion;
    }

//    public ConfigBean getConfigBean() {
//        return configBean;
//    }
//
//    public void setConfigBean(ConfigBean configBean) {
//        this.configBean = configBean;
//    }
    
    public void makeClasificaXGrupo(){
        logger.debug("Llegó a hacer la clasificación");
        clasificacion = dao.clasificaXGrupoAndJornada(jornada, grupo);
        logger.debug("trajo una clasificación " + clasificacion);
    }
    
    public List<Competicion> getItems() {
        if (ligas == null) {
            ligas = ligaController.findAll();
        }
        return ligas;
    }
    public void ligaSelected() {
        recreateModelTemporada();
        temporadaList = null;
    }
    
    public void temporadaSelected() {
        recreateModelFase();
    }
    
    public void faseSelected() {
        recreateModelGrupo();
    }
    
//    public List<Partido> grupoSelected() {
//        recreateModelJornada();
//        getJornadas();
//        return partidos;
//    }
    
    public void grupoSelected(){
        recreateModelJornada();
    }
    
    public List<Temporada> getTemporadas() {
        if (temporadaList == null) {
            temporadaList = new ArrayList<Temporada>(temporadaFacade.findTemporadaxLiga(liga));
        }
        return temporadaList;
    }
    
    public List<Fase> getFases() {
        if (fases == null) {
            fases = new ArrayList<Fase>(faseFacade.findFasexTemporada(temporada));
        }
        return fases;
    }
    
    public List<Grupo> getGrupos() {
        if (grupos == null) {
            grupos = grupoFacade.findGruposXFase(fase);
        }
        return grupos;
    }
    
    public List<Jornada> getJornadas() {
        if (jornadas == null) {
            jornadas = jornadaFacade.findJornadasxGrupo(grupo);
        }
        return jornadas;
    }
    
    public void recreateModelTemporada() {
        temporada = null;
        temporadaList = null;
        temporadaCategoria = null;

//        categorias = null;
//        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();
//        categoriaSource = new ArrayList<Categoria>();
//        categoriaTarget = new ArrayList<Categoria>();
        recreateModelFase();
    }
    
    public void recreateModelFase() {
        fase = null;
        fases = null;
        recreateModelGrupo();
    }
    
    private void recreateModelGrupo() {
        grupo = null;
        grupos = null;
//        equipos = new ArrayList<Equipo>();
//        equipoEnGrupo = new ArrayList<EquipoEnGrupo>();
        recreateModelJornada();
    }
    
    private void recreateModelJornada() {
        jornada = null;
        jornadas = null;
//        partidos = new ArrayList<Partido>();
//        partido = null;
    }
}
