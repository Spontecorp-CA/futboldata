/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.jpacontroller.FaseFacade;
import com.spontecorp.futboldata.jpacontroller.GrupoFacade;
import com.spontecorp.futboldata.jpacontroller.JornadaFacade;
import com.spontecorp.futboldata.jpacontroller.LlaveFacade;
import com.spontecorp.futboldata.jpacontroller.PartidoFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("configBean")
@SessionScoped
public class ConfigBean implements Serializable{
    
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

    public ConfigBean() {
        this.temporadaFacade = new TemporadaFacade();
        this.faseFacade = new FaseFacade();
        this.llaveFacade = new LlaveFacade();
        this.jornadaFacade = new JornadaFacade();
        this.partidoFacade = new PartidoFacade();
        
        inicializeMenu();
    }

    public boolean isTemporadaActiva() {
        return temporadaActiva;
    }

    public void setTemporadaActiva(boolean temporadaActiva) {
        this.temporadaActiva = temporadaActiva;
    }
    
    public void activateTemporadaList(){
        setTemporadaActiva(true);
    }
    
    private void inicializeMenu(){
        setTemporadaActiva(false);
    }
}
