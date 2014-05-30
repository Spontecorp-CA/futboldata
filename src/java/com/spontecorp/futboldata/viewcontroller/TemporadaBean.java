/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.entity.TemporadaCategoria;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaCategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("temporadaBean")
@SessionScoped
public class TemporadaBean implements Serializable {
    private int index ;
    private TemporadaCategoria temporadaCategoria;
    private Temporada temporada;
    private Competicion liga;
    private List<Temporada> temporadas = null;
    private List<Temporada> filteredTemporada;
    private List<Categoria> categoriaSourceAlone;
    private List<Categoria> categoriaSource;
    private List<Categoria> categoriaTarget;
    private List<TemporadaCategoria> listTemporadaCategoria;

    private DualListModel<Categoria> categorias;

    private static final Logger logger = LoggerFactory.getLogger(TemporadaBean.class);
    private final CompeticionFacade controllerCompeticion;
    private final CategoriaFacade controllerCategoria;
    private final TemporadaFacade controllerTemporada;
    private final TemporadaCategoriaFacade controllerTemporadaCategoria;

    public TemporadaBean() {
        index = 0;
        controllerTemporada = new TemporadaFacade();
        controllerCompeticion = new CompeticionFacade();
        controllerCategoria = new CategoriaFacade();
        controllerTemporadaCategoria = new TemporadaCategoriaFacade();
        liga = new Competicion();
        categoriaSource = new ArrayList<Categoria>();
        categoriaTarget = new ArrayList<Categoria>();

        categorias = null;

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Temporada> getTemporadas() {
        if (temporadas == null) {
            temporadas = new ArrayList<Temporada>(controllerTemporada.findTemporadaxLiga(liga));
        }

        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
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
        categoriaSource = controllerCategoria.findAll();

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

    public void recreateModel() {
        temporada = null;
        temporadas = null;
        temporadaCategoria = null;

        categorias = null;
        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();
        categoriaSource = new ArrayList<Categoria>();
        categoriaTarget = new ArrayList<Categoria>();
    }

    public void prepareEdit() {
        categoriaSource = controllerCategoria.findAll();
        categoriaTarget = controllerTemporadaCategoria.getCategorias(temporada);
        categoriaSource.removeAll(categoriaTarget);
        categorias = null;
        listTemporadaCategoria = new ArrayList<TemporadaCategoria>();

    }

    public void ciudadesAvailable(Pais pais) {

    }

    public void create() {
        try {
            if (controllerTemporada.findTemporada(temporada.getNombre()) != null) {
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
                controllerTemporada.create(temporada);
                Util.addSuccessMessage("Se creo exitosamente el Temporada");
            }

            recreateModel();

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
        
        for (Categoria cat : getSource){
            TemporadaCategoria temCat = controllerTemporadaCategoria.findTemporadaCategoria(temporada, cat);
            controllerTemporadaCategoria.remove(temCat);            
        }
        
        for (Categoria cat : getTarget) {
            temporadaCategoria = new TemporadaCategoria();
            temporadaCategoria.setCategoriaId(cat);
            temporadaCategoria.setTemporadaId(temporada);
            listTemporadaCategoria.add(temporadaCategoria);

        }
        temporada.setTemporadaCategoriaCollection(listTemporadaCategoria);
        controllerTemporada.edit(temporada);

        recreateModel();
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

    public SelectItem[] getCompenticionAvalaible() {
        return Util.getSelectItems(controllerCompeticion.findAll());
    }

    public String gotoConfig() {
        recreateModel();

        return "/admin/liga/temporadas/config?faces-redirect=true";
    }

}
