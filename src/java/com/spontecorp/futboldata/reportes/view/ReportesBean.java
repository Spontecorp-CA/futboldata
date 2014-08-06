/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.reportes.view;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Clasifica;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.FaseFacade;
import com.spontecorp.futboldata.jpacontroller.GrupoFacade;
import com.spontecorp.futboldata.jpacontroller.JornadaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaCategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.reportes.ReportesDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
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
    private Categoria categoria;
    private List<Clasifica> clasificacion;
    private final ReportesDAO dao;

    private List<Competicion> ligas;
    private List<Temporada> temporadaList;
    private List<Fase> fases;
    private List<Grupo> grupos;
    private List<Jornada> jornadas;
    private List<Categoria> categorias;
    private List<Equipo> equipos;
    
    private final CompeticionFacade ligaController;
    private final TemporadaFacade temporadaFacade;
    private final FaseFacade faseFacade;
    private final GrupoFacade grupoFacade;
    private final JornadaFacade jornadaFacade;
    private final TemporadaCategoriaFacade temporadaCategoriaFacade;
    
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
        this.temporadaCategoriaFacade = new TemporadaCategoriaFacade();
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Clasifica> getClasificacion() {
        return clasificacion;
    }
    
    public void makeClasificaXGrupo(){
        if(categoria!= null){
                   clasificacion = dao.clasificaXGrupoAndJornada(jornada, grupo, categoria); 
        }else{
            clasificacion = dao.clasificaXGrupoAndClub(jornada, grupo, categoria);
        }

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

    public void grupoSelected(){
        recreateModelCategoria();
    }
    
    public void categoriaSelected(){
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
    
    public List<Categoria> getCategorias(){
        if(categorias == null){
            categorias = temporadaCategoriaFacade.getCategorias(temporada);
        }
        return categorias;
    }
    
    public void recreateModelTemporada() {
        temporada = null;
        temporadaList = null;
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
        recreateModelCategoria();
    }
    
    private void recreateModelCategoria(){
        categoria = null;
        categorias = null;
        recreateModelJornada();
    }
    
    private void recreateModelJornada() {
        jornada = null;
        jornadas = null;
    }
}
