/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.jpacontroller.CategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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

    private Temporada temporada;
    private Competicion liga;
    private List<Temporada> temporadas = null;
    private List<Temporada> filteredTemporada;
    private List<Categoria> categoriaSource;
    private List<Categoria> categoriaTarget;
    private DualListModel<Categoria> categorias;
    
    private static final Logger logger = LoggerFactory.getLogger(Temporada.class);
    private final CompeticionFacade controllerCompeticion;
    private final CategoriaFacade controllerCategoria;
    private final TemporadaFacade controllerTemporada;

    public TemporadaBean() {
        controllerTemporada = new TemporadaFacade();
        controllerCompeticion = new CompeticionFacade();
        controllerCategoria = new CategoriaFacade();
        liga= new Competicion();
        categoriaSource = controllerCategoria.findAll();
        categoriaTarget = new ArrayList<Categoria>();
        categorias = new DualListModel<Categoria>(categoriaSource, categoriaTarget);

    }

    public List<Temporada> getTemporadas() {
        if (temporadas ==null){
            temporadas = controllerTemporada.findTemporadaxLiga(liga);
            for (Temporada temporada1 : temporadas) {
                logger.debug("hola"+temporada1.getNombre());
            }
        }
            
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    
    public DualListModel<Categoria> getCategorias() {
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


    public void prepareCreate() {
        temporada = new Temporada();
        initializeEmbeddableKey();
    }

    protected void setEmbeddableKeys() {
        temporada.setCompeticionId(liga);
    }

    protected void initializeEmbeddableKey() {
        setEmbeddableKeys();
    }

    public void recreateModel() {
        temporada = null;
        temporadas = null;
    }

    public void prepareEdit() {

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
                controllerTemporada.create(temporada);
                recreateModel();
                Util.addSuccessMessage("Se creo exitosamente el Temporada");

            }

        } catch (Exception e) {
            logger.debug("Error al crear Temporada :", e.getMessage());
        }
    }

    public void edit() {
        logger.debug("Esta editando un Temporada");
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
    
    public String gotoConfig (){
        temporadas = null;            
        return "/admin/liga/temporadas/config?faces-redirect=true";
    }

}
