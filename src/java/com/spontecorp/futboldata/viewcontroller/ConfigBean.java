/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.entity.TemporadaCategoria;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.FaseFacade;
import com.spontecorp.futboldata.jpacontroller.GrupoFacade;
import com.spontecorp.futboldata.jpacontroller.JornadaFacade;
import com.spontecorp.futboldata.jpacontroller.LlaveFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaCategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
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

    private boolean temporadaActiva;

    private List<Temporada> temporadaList;
    private List<Fase> faseList;
    private List<Llave> llaveLlist;
    private List<Grupo> grupoList;
    private List<Jornada> jornadaList;
    private List<Partido> partidoList;

    private final TemporadaFacade temporadaFacade;
    private final FaseFacade faseFacade;
    private final LlaveFacade llaveFacade;
    private GrupoFacade grupoFacade;
    private final JornadaFacade jornadaFacade;
    private final PartidoFacade partidoFacade;

    private static final Logger logger = LoggerFactory.getLogger(ConfigBean.class);
    private ArrayList<Categoria> categoriaSource;
    private ArrayList<Categoria> categoriaTarget;
    private Competicion liga;
    private DualListModel<Categoria> categorias;
    private List<Temporada> filteredTemporada;
    private final CategoriaFacade categoriaFacade;
    private ArrayList<TemporadaCategoria> listTemporadaCategoria;
    private TemporadaCategoria temporadaCategoria;
    private final TemporadaCategoriaFacade temporadaCategoriaFacade;


    public ConfigBean() {
        this.temporadaFacade = new TemporadaFacade();
        this.faseFacade = new FaseFacade();
        this.llaveFacade = new LlaveFacade();
        this.jornadaFacade = new JornadaFacade();
        this.partidoFacade = new PartidoFacade();
        this.categoriaFacade = new CategoriaFacade();
        this.temporadaCategoriaFacade = new TemporadaCategoriaFacade();
        liga = new Competicion();
        categoriaSource = new ArrayList<Categoria>();
        categoriaTarget = new ArrayList<Categoria>();

        categorias = null;

        inicializeMenu();
    }

    public boolean isTemporadaActiva() {
        return temporadaActiva;
    }

    public void setTemporadaActiva(boolean temporadaActiva) {
        this.temporadaActiva = temporadaActiva;
    }

    public void activateTemporadaList() {
        setTemporadaActiva(true);
    }

    private void inicializeMenu() {
        setTemporadaActiva(false);
    }

    /*Codigo de la vista de la temporada*/
    public List<Temporada> getTemporadas() {
        if (temporadaList == null) {
            temporadaList = new ArrayList<Temporada>(temporadaFacade.findTemporadaxLiga(liga));
        }
        return temporadaList;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadaList = temporadas;
    }

    public DualListModel<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = new DualListModel<Categoria>(categoriaSource, categoriaTarget);
        }

        return categorias;
    }

    public void setCategorias(DualListModel<Categoria> categorias) {
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

    public Temporada getSelected() {
        if (temporada == null) {
            temporada = new Temporada();
        }
        return temporada;
    }

    public void setSelected(Temporada temporada) {
        this.temporada = temporada;

    }

    public void prepareCreate() {
        temporada = new Temporada();
        categoriaSource = (ArrayList<Categoria>) categoriaFacade.findAll();

        categorias = null;

        initializeEmbeddableKey();
    }

    protected void setEmbeddableKeys() {
        temporada.setCompeticionId(liga);
    }

    protected void initializeEmbeddableKey() {
        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();
        setEmbeddableKeys();
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
        categoriaSource = (ArrayList<Categoria>) categoriaFacade.findAll();
        categoriaTarget = (ArrayList<Categoria>) temporadaCategoriaFacade.getCategorias(temporada);
        categoriaSource.removeAll(categoriaTarget);
        categorias = null;
        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();

    }



    public void createTemporada() {
        try {
            if (temporadaFacade.findTemporada(temporada.getNombre()) != null) {
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

    public void edit() {
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
            initializeEmbeddableKey();
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
        recreateModelTemporada();

        return "/admin/liga/temporadas/config?faces-redirect=true";
    }

}

