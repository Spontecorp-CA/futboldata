/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoEnGrupo;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.entity.TemporadaCategoria;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoEnGrupoFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoInLigaFacade;
import com.spontecorp.futboldata.jpacontroller.FaseFacade;
import com.spontecorp.futboldata.jpacontroller.GrupoFacade;
import com.spontecorp.futboldata.jpacontroller.JornadaFacade;
import com.spontecorp.futboldata.jpacontroller.LlaveFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoFacade;
import com.spontecorp.futboldata.jpacontroller.StatusPartidoFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaCategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("configBean")
@SessionScoped
public class ConfigBean implements Serializable {

    private Temporada temporada;
    private Fase fase;
    private Llave llave;
    private Grupo grupo;
    private Jornada jornada;
    private Partido partido;
    private int faseTipo;
    private Competicion liga;

    private boolean temporadaActiva;
    private boolean faseActiva;
    private boolean grupoActiva;
    private boolean jornadaActiva;
    private boolean llaveActiva;
    private boolean partidoActivo;

    private List<Temporada> temporadaList;

    private List<Fase> fases = null;
    private List<Fase> filteredFase;
    private List<Grupo> grupos;
    private List<Grupo> filteredGrupos;
    private List<Temporada> filteredTemporada;
    private List<Categoria> categoriaSource;
    private List<Categoria> categoriaTarget;
    private List<Jornada> jornadas;
    private List<Llave> llaves;
    private List<Llave> filteredLlaves;
    private List<Partido> partidos;
    private List<Partido> filteredPartidos;
    private List<Jornada> filteredJornada;
    private List<Equipo> equipos;
    private List<Equipo> equiposEli;
    private List<EquipoEnGrupo> equipoEnGrupo;
    private ArrayList<TemporadaCategoria> listTemporadaCategoria;
    private DualListModel<Categoria> categorias;

    private final StatusPartidoFacade statusPartidoFacade;
    private final TemporadaFacade temporadaFacade;
    private final FaseFacade faseFacade;
    private final LlaveFacade llaveFacade;
    private final GrupoFacade grupoFacade;
    private final JornadaFacade jornadaFacade;
    private final PartidoFacade partidoFacade;
    private final CategoriaFacade categoriaFacade;
    private TemporadaCategoria temporadaCategoria;
    private final EquipoInLigaFacade equipoInLigaFacade;
    private final TemporadaCategoriaFacade temporadaCategoriaFacade;
    private final EquipoEnGrupoFacade equipoEnGrupoFacade;

    private static final Logger logger = LoggerFactory.getLogger(ConfigBean.class);

    public ConfigBean() {
        this.temporadaFacade = new TemporadaFacade();
        this.faseFacade = new FaseFacade();
        this.llaveFacade = new LlaveFacade();
        this.jornadaFacade = new JornadaFacade();
        this.partidoFacade = new PartidoFacade();
        this.categoriaFacade = new CategoriaFacade();
        this.equipoEnGrupoFacade =  new EquipoEnGrupoFacade();
        this.temporadaCategoriaFacade = new TemporadaCategoriaFacade();
        this.grupoFacade = new GrupoFacade();
        this.equipoInLigaFacade = new EquipoInLigaFacade();
        this.statusPartidoFacade = new StatusPartidoFacade();

        liga = new Competicion();
        categoriaSource = new ArrayList<Categoria>();
        categoriaTarget = new ArrayList<Categoria>();
        categorias = null;
        inicializeMenu();
        faseTipo = -1;
    }

    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public List<Equipo> getEquiposEli() {
        return equiposEli;
    }

    public void setEquiposEli(List<Equipo> equiposEli) {
        this.equiposEli = equiposEli;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
        for (Equipo equi : equipos) {
            logger.debug("Equipo en la lista:" + equi.getNombre());
        }
    }

    public int getFaseTipo() {
        return faseTipo;
    }

    public void setFaseTipo(int faseTipo) {
        this.faseTipo = faseTipo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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

    public Partido getPartido() {
        if (partido == null) {
            partido = new Partido();
        }
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public boolean isTemporadaActiva() {
        return temporadaActiva;
    }

    public void setTemporadaActiva(boolean temporadaActiva) {
        this.temporadaActiva = temporadaActiva;
    }

    public void activateTemporadaList() {
        activateFaseList();
        temporadaActiva = true;
        faseActiva = false;
        temporada = null;
    }

    public void activateFaseList() {
        activateGrupoList();
        activateLlaveList();
        faseTipo = -1;
        fases = null;
        llave = null;
        grupoActiva = false;
        faseActiva = true;
        temporadaActiva = false;
        llaveActiva = false;
    }

    public void activateGrupoList() {
        activateJornadaList();
        grupo = null;
        grupos = null;
        faseActiva = false;
        grupoActiva = true;
        jornadas = null;
        jornadaActiva = false;
        partidoActivo = false;
    }

    public void activateJornadaList() {
        grupoActiva = false;
        jornadaActiva = true;
        partidoActivo = false;
        jornada = null;
    }

    public void activateLlaveList() {
        llave = null;
        faseActiva = false;
        llaveActiva = true;
        llaves = null;
        partidoActivo = false;

    }

    public void activatePartidosJornadaList() {
        partidos = getPartidos(jornada);
        jornadaActiva = false;
        partidoActivo = true;
    }

    public void activatePartidosLlaveList() {
        jornada = null;
        partidos = getPartidos(llave);
        partidos = null;
        llaveActiva = false;
        partidoActivo = true;
    }

    private void inicializeMenu() {
        setTemporadaActiva(false);
        setFaseActiva(false);
        setGrupoActiva(false);
        setJornadaActiva(false);
    }

    public boolean isFaseActiva() {
        return faseActiva;
    }

    public boolean isLlaveActiva() {
        return llaveActiva;
    }

    public void setLlaveActiva(boolean llaveActiva) {
        this.llaveActiva = llaveActiva;
    }

    public void setFaseActiva(boolean faseActiva) {
        this.faseActiva = faseActiva;
    }

    public boolean isGrupoActiva() {
        return grupoActiva;
    }

    public void setGrupoActiva(boolean grupoActiva) {
        this.grupoActiva = grupoActiva;
    }

    public boolean isJornadaActiva() {
        return jornadaActiva;
    }

    public void setJornadaActiva(boolean jornadaActiva) {
        this.jornadaActiva = jornadaActiva;
    }

    public boolean isPartidoActivo() {
        return partidoActivo;
    }

    /**
     * ******************************Codigo de la vista de
     * temporada****************
     */
    /**
     * *******************************Codigo de la vista de
     * temporada***************
     */
    public List<Temporada> getTemporadas() {
        if (temporadaList == null) {
            temporadaList = new ArrayList<Temporada>(temporadaFacade.findTemporadaxLiga(liga));
        }
        return temporadaList;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadaList = temporadas;
    }

    public DualListModel<Categoria> getCategoriasTemporada() {
        if (categorias == null) {
            categorias = new DualListModel<Categoria>(categoriaSource, categoriaTarget);
        }

        return categorias;
    }

    public void setCategoriasTemporada(DualListModel<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Competicion getLiga() {
        return liga;
    }

    public void setLiga(Competicion liga) {
        this.liga = liga;
    }

    public List<Temporada> getFilteredTemporada() {
        return filteredTemporada;
    }

    public void setFilteredTemporada(List<Temporada> filteredTemporada) {
        this.filteredTemporada = filteredTemporada;
    }

    public void prepareCreateTemporada() {
        temporada = new Temporada();
        categoriaSource = categoriaFacade.findAll();

        categorias = null;

        initializeEmbeddableKeyTemporada();
    }

    protected void setEmbeddableKeysTemporada() {
        temporada.setCompeticionId(liga);
    }

    protected void initializeEmbeddableKeyTemporada() {
        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();
        setEmbeddableKeysTemporada();
    }

    public void recreateModelTemporada() {
        temporada = null;
        temporadaList = null;
        temporadaCategoria = null;

        categorias = null;
        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();
        categoriaSource = new ArrayList<Categoria>();
        categoriaTarget = new ArrayList<Categoria>();
    }

    public void prepareEditTemporada() {
        categoriaSource = categoriaFacade.findAll();
        categoriaTarget = gestCategorias(temporada);
        categoriaSource.removeAll(categoriaTarget);
        categorias = null;
        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();

    }

    public List<Categoria> gestCategorias(Temporada temporada) {
        return temporadaCategoriaFacade.getCategorias(temporada);
    }

    public void createTemporada() {
        try {
            if (temporadaFacade.findTemporadaxLiga(temporada.getNombre(), liga) != null) {
                Util.addErrorMessage("El temporada ya se encuentra Registrado por el Documento de "
                        + "identificacion");

            } else {

                logger.debug("Esta Creando  un Temporada");
                for (Categoria cat : categorias.getTarget()) {
                    logger.debug("Lo que en CategoriaTarget" + cat.toString());
                    temporadaCategoria = new TemporadaCategoria();
                    temporadaCategoria.setCategoriaId(cat);
                    temporadaCategoria.setTemporadaId(temporada);
                    listTemporadaCategoria.add(temporadaCategoria);

                }

                temporada.setTemporadaCategoriaCollection(listTemporadaCategoria);
                temporadaFacade.create(temporada);
                Util.addSuccessMessage("Se creo exitosamente el Temporada");
            }

            recreateModelTemporada();

        } catch (Exception e) {
            logger.debug("Error al crear Temporada :", e);
        }
    }

    public void editTemporada() {
        logger.debug("Esta editando un Temporada");
        List<Categoria> getTarget = categorias.getTarget();
        List<Categoria> getSource = categorias.getSource();
        getTarget.removeAll(categoriaTarget);
        getSource.removeAll(categoriaSource);

        for (Categoria cat : getSource) {
            TemporadaCategoria temCat = temporadaCategoriaFacade.findTemporadaCategoria(temporada, cat);
            temporadaCategoriaFacade.remove(temCat);
        }

        for (Categoria cat : getTarget) {
            temporadaCategoria = new TemporadaCategoria();
            temporadaCategoria.setCategoriaId(cat);
            temporadaCategoria.setTemporadaId(temporada);
            listTemporadaCategoria.add(temporadaCategoria);

        }
        temporada.setTemporadaCategoriaCollection(listTemporadaCategoria);
        temporadaFacade.edit(temporada);

        recreateModelTemporada();
        Util.addSuccessMessage("Se edito exitosamente el Temporada");
    }

    public Temporada getTemporada() {
        if (temporada == null) {
            temporada = new Temporada();
            initializeEmbeddableKeyTemporada();
        }
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public String gotoTemporadaPage() {
        return "/admin/liga/temporada/list?faces-redirect=true";
    }

    public String gotoConfig() {
        activateTemporadaList();
        recreateModelTemporada();

        return "/admin/liga/temporadas/config?faces-redirect=true";
    }

    /**
     * ******************************Codigo de la vista
     * Fases************************
     */
    /**
     * ******************************Codigo de la vista Fases
     *
     ************************
     * @return
     */
    public List<Fase> getFases() {
        if (fases == null) {
            fases = new ArrayList<Fase>(faseFacade.findFasexTemporada(temporada));
        }

        return fases;
    }

    public void setFases(List<Fase> fases) {
        this.fases = fases;
    }

    public List<Fase> getFilteredFase() {
        return filteredFase;
    }

    public void setFilteredFase(List<Fase> filteredFase) {
        this.filteredFase = filteredFase;
    }

    public void prepareCreateFase() {
        fase = new Fase();
        initializeEmbeddableKeyFase();
    }

    protected void setEmbeddableKeysFase() {

    }

    protected void initializeEmbeddableKeyFase() {

        setEmbeddableKeysFase();
    }

    public void recreateModelFase() {
        fase = null;
        fases = null;

    }

    public void prepareEditFase() {

    }

    public void createFase() {
        try {
            if (faseFacade.findFasexTemporada(fase.getNombre(), temporada) != null) {
                Util.addErrorMessage("El fase ya se encuentra Registrado ");

            } else {
                fase.setTemporadaId(temporada);
                faseFacade.create(fase);
                Util.addSuccessMessage("Se creo exitosamente el Fase");
                recreateModelFase();
            }

        } catch (Exception e) {
            logger.debug("Error al crear Fase :", e);
        }
    }

    public void editFase() {
        logger.debug("Esta editando un Fase");
        faseFacade.edit(fase);
        recreateModelFase();
        Util.addSuccessMessage("Se edito exitosamente el Fase");
    }

    public Fase getFase() {
        if (fase == null) {
            fase = new Fase();
            initializeEmbeddableKeyFase();
        }
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public String gotoFasePage() {
        return "/admin/liga/fase/list?faces-redirect=true";
    }

    /**
     * ********************Grupo Bean
     *
     ***********************************
     * @return
     */
    public List<Grupo> getGrupos() {
        if (grupos == null) {
            grupos = getGruposXFase();
        }
        return grupos;
    }

    private List<Grupo> getGruposXFase() {
        return grupoFacade.findGruposXFase(fase);
    }

    private void recreateModelGrupo() {
        grupos = null;
        equipos = new ArrayList<Equipo>();
        equipoEnGrupo = new ArrayList<EquipoEnGrupo>();
    }

    public List<Grupo> getFilteredGrupos() {
        return filteredGrupos;
    }

    public void setFilteredGrupos(List<Grupo> filteredGrupos) {
        this.filteredGrupos = filteredGrupos;
    }

    public void prepareCreateGrupo() {
        equiposEli = new ArrayList<Equipo>();
        equipoEnGrupo = new ArrayList<EquipoEnGrupo>();
        equipos = new ArrayList<Equipo>();
        grupo = new Grupo();

    }

    public void prepareEditGrupo() {
        equiposEli = new ArrayList<Equipo>();
        equipos = new ArrayList<Equipo>();
        equipoEnGrupo = new ArrayList<EquipoEnGrupo>();
        for (EquipoEnGrupo equiG : grupo.getEquipoEnGrupoCollection()) {
            equipos.add(equiG.getEquipoId());
        }

    }

    public void editGrupo() {
        EquipoEnGrupo equipoEnGrupoTemp;
        logger.debug("Esta editando un Grupo");

        for (EquipoEnGrupo equiG : grupo.getEquipoEnGrupoCollection()) {
            equipoEnGrupoFacade.remove(equiG);
        }
        for (Equipo equi : equipos) {
            equipoEnGrupoTemp = new EquipoEnGrupo();
            equipoEnGrupoTemp.setEquipoId(equi);
            equipoEnGrupoTemp.setGrupoId(grupo);
            equipoEnGrupo.add(equipoEnGrupoTemp);
        }

        grupo.setEquipoEnGrupoCollection(equipoEnGrupo);
        grupoFacade.edit(grupo);
        recreateModelGrupo();
        Util.addSuccessMessage("Se edito exitosamente el Grupo");
    }

    public void createGrupo() {
        logger.debug("hola hola hola ");
        EquipoEnGrupo equipoEnGrupoTemp;
        try {
            if (grupoFacade.findGrupoXFase(fase, grupo.getNombre()) != null) {
                Util.addErrorMessage("El grupo ya se encuentra Registrado ");

            } else {
                for (Equipo equi : equipos) {
                    equipoEnGrupoTemp = new EquipoEnGrupo();
                    equipoEnGrupoTemp.setEquipoId(equi);
                    equipoEnGrupoTemp.setGrupoId(grupo);
                    equipoEnGrupo.add(equipoEnGrupoTemp);
                }
                grupo.setEquipoEnGrupoCollection(equipoEnGrupo);
                grupo.setFaseId(fase);
                grupoFacade.create(grupo);
                Util.addSuccessMessage("Se creo exitosamente el Fase");
                recreateModelGrupo();
            }

        } catch (Exception e) {
            logger.debug("Error al crear Fase :", e);
        }
    }

    /**
     * **************************Codigo de Jornada*****************************
     */
    /**
     * **************************Codigo de Jornada*****************************
     */
    public List<Jornada> getJornadas() {
        if (jornadas == null) {
            jornadas = getJornadaxGrupo();
        }
        return jornadas;
    }

    private List<Jornada> getJornadaxGrupo() {
        return jornadaFacade.findJornadasxGrupo(grupo);
    }

    private void recreateModelJornada() {
        jornadas = null;
    }

    public List<Jornada> getFilteredJornada() {
        return filteredJornada;
    }

    public void setFilteredJornadas(List<Jornada> filteredJornada) {
        this.filteredJornada = filteredJornada;
    }

    public void prepareCreateJornada() {
        jornada = new Jornada();

    }

    public void prepareEditJornada() {

    }

    public void editJornada() {
        logger.debug("Esta editando la Jornada");
        jornadaFacade.edit(jornada);
        recreateModelJornada();
        Util.addSuccessMessage("Se edito exitosamente la Jornada");
    }

    public void createJornada() {
        try {
            if (jornadaFacade.findJornadaxGrupo(grupo, jornada.getNumero()) != null) {
                Util.addErrorMessage("La jornada  ya se encuentra Registrado ");

            } else {
                jornada.setGrupoId(grupo);
                jornadaFacade.create(jornada);
                Util.addSuccessMessage("Se creo exitosamente la Jornada");
                recreateModelJornada();
            }

        } catch (Exception e) {
            logger.debug("Error al crear Jornada :", e);
        }
    }

    /**
     * ***********************Controlador Llave ***************************
     */
    /**
     * ***********************Controlador Llave ***************************
     */
    public List<Llave> getLlaves() {
        if (llaves == null) {
            llaves = getLlavesXFase();
        }
        return llaves;
    }

    private List<Llave> getLlavesXFase() {
        return llaveFacade.findLlavesXFase(fase);
    }

    private void recreateModelLlave() {
        llaves = null;
    }

    public List<Llave> getFilteredLlaves() {
        return filteredLlaves;
    }

    public void setFilteredLlaves(List<Llave> filteredLlaves) {
        this.filteredLlaves = filteredLlaves;
    }

    public void prepareCreateLlave() {
        llave = new Llave();

    }

    public void prepareEditLlave() {

    }

    public void editLlave() {
        logger.debug("Esta editando la Llave");
        llaveFacade.edit(llave);
        recreateModelGrupo();
        Util.addSuccessMessage("Se edito exitosamente la Llave");
    }

    public void createLlave() {
        try {
            if (llaveFacade.findLlaveXFase(fase, llave.getNombre()) != null) {
                Util.addErrorMessage("La llave ya se encuentra Registrado ");

            } else {
                llave.setFaseId(fase);
                llaveFacade.create(llave);
                Util.addSuccessMessage("Se creo exitosamente la Llave");
                recreateModelLlave();
            }

        } catch (Exception e) {
            logger.debug("Error al crear LLave :", e);
        }
    }

    /*
     * Manejo de partidos
     */
    public List<Partido> getPartidos() {
        partidos = null;
        if (jornada != null) {
            partidos = partidoFacade.findPartidos(jornada);
        } else if (llave != null) {
            partidos = partidoFacade.findPartidos(llave);
        } else {
            partidos = partidoFacade.findAll();
        }
        return partidos;
    }

    public List<Partido> getPartidos(Jornada jornada) {
        if (partidos == null) {
            partidos = partidoFacade.findPartidos(jornada);
        }
        return partidos;
    }

    public List<Partido> getPartidos(Llave llave) {
        if (partidos == null) {
            partidos = partidoFacade.findPartidos(llave);
        }
        return partidos;
    }

    private void recreateModelPartido() {
        partidos = null;
    }

    public List<Partido> getFilteredPartidos() {
        return filteredPartidos;
    }

    public void setFilteredPartidos(List<Partido> filteredPartidos) {
        this.filteredPartidos = filteredPartidos;
    }

    public void prepareCreatePartido() {
        partido = new Partido();
    }

    public void prepareEditPartido() {
    }
    
    public String prepareResultPartido(){
        return "detallepartido";
    }

    public void createPartido() {
        try {
            partido.setStatusPartidoId(statusPartidoFacade.findStatusPartidoXValue(0));
            if (jornada != null) {
                partido.setJornadaId(jornada);
            } else if (llave != null) {
                partido.setLlaveId(llave);
            }
            partidoFacade.create(partido);
            Util.addSuccessMessage("Partido creado con éxito");
            recreateModelPartido();
        } catch (Exception e) {
            Util.addErrorMessage("Se presento un erro al Crear el partido");
            logger.error(e.toString());
        }
    }

    public void editPartido() {
        try {
            partidoFacade.edit(partido);
            Util.addSuccessMessage("Partido editado con éxito");
        } catch (Exception e) {
            Util.addErrorMessage("Se presento un erro al Editar el partido");
            logger.error(e.toString());
        }
    }

    public List<Equipo> getEquipoInLiga() {
        return equipoInLigaFacade.getEquipoInLiga(liga);
    }

}
