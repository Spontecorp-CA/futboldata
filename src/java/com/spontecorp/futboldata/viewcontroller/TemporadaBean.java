/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
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
    private DataModel items = null;
    private List<Temporada> filteredTemporada;

    private final TemporadaFacade controllerTemporada;
    

    private static final Logger logger = LoggerFactory.getLogger(Temporada.class);
    private CompeticionFacade controllerCompeticion;

    public TemporadaBean() {
        controllerTemporada = new TemporadaFacade();
        controllerCompeticion = new CompeticionFacade();
        liga= new Competicion();
        liga.setNombre("No tiene nada !!");
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

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerTemporada.findAll());
        }
        return items;
    }

    public void prepareCreate() {
        temporada = new Temporada();
        initializeEmbeddableKey();
    }

    protected void setEmbeddableKeys() {

    }

    protected void initializeEmbeddableKey() {
        setEmbeddableKeys();
    }

    public void recreateModel() {
        temporada = null;
        items = null;
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

}
