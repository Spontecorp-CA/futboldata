/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;



import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.jpacontroller.FaseFacade;
import com.spontecorp.futboldata.utilities.Util;
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
@Named("faseBean")
@SessionScoped
public class FaseBean implements Serializable {


    private Fase fase;
    private Temporada temporada;
    private List<Fase> fases = null;
    private List<Fase> filteredFase;




    private static final Logger logger = LoggerFactory.getLogger(FaseBean.class);

    private final FaseFacade controllerFase;


    public FaseBean() {
        controllerFase = new FaseFacade();

    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        fases = null;
        this.temporada = temporada;
    }

    
    public List<Fase> getFases() {
        if (fases == null) {
            fases = new ArrayList<Fase>(controllerFase.findFasexTemporada(temporada));
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

    public Fase getSelected() {
        if (fase == null) {
            fase = new Fase();
        }
        return fase;
    }

    public void setSelected(Fase fase) {
        this.fase = fase;

    }

    public void prepareCreate() {
        fase = new Fase();




        initializeEmbeddableKey();
    }

    protected void setEmbeddableKeys() {

    }

    protected void initializeEmbeddableKey() {

        setEmbeddableKeys();
    }

    public void recreateModel() {
        fase = null;
        fases = null;

    }

    public void prepareEdit() {
 

    }



    public void create() {
        try {
            if (controllerFase.findFase(fase.getNombre()) != null) {
                Util.addErrorMessage("El fase ya se encuentra Registrado " );
                
               
            }
            fase.setTemporadaId(temporada);
            controllerFase.create(fase);
             Util.addSuccessMessage("Se creo exitosamente el Fase");
            recreateModel();

        } catch (Exception e) {
            logger.debug("Error al crear Fase :", e);
        }
    }

    public void edit() {
        logger.debug("Esta editando un Fase");

        

        controllerFase.edit(fase);

        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el Fase");
    }

    public Fase getFase() {
        if (fase == null) {
            fase = new Fase();
            initializeEmbeddableKey();
        }
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public String gotoFasePage() {
        return "/admin/liga/fase/list?faces-redirect=true";
    }

    public String gotoConfig() {
        recreateModel();

        return "/admin/liga/fases/config?faces-redirect=true";
    }

}
