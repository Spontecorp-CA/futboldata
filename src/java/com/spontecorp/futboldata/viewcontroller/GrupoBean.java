/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.jpacontroller.GrupoFacade;
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
@Named("grupoBean")
@SessionScoped
public class GrupoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Grupo selected;
    private List<Grupo> items = null;
    private List<Grupo> filteredGrupos = null;
    private Fase fase;
    
    private final GrupoFacade grupoFacade;
    private static final Logger logger = LoggerFactory.getLogger(LigaBean.class);

    public GrupoBean() {
        this.grupoFacade = new GrupoFacade();
    }

    public Grupo getSelected() {
        return selected;
    }

    public void setSelected(Grupo selected) {
        this.selected = selected;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        recreateFase();
        this.fase = fase;
    }

    public List<Grupo> getItems() {
        if(items == null){
           items = getGruposXFase();       
        }
        return items;
    }

    private List<Grupo> getGruposXFase(){
        return grupoFacade.findGruposXFase(fase);
    }
    
    private void recreateFase(){
        this.fase = null;
    }
    
    public List<Grupo> getFilteredGrupos() {
        return filteredGrupos;
    }

    public void setFilteredGrupos(List<Grupo> filteredGrupos) {
        this.filteredGrupos = filteredGrupos;
    }

    public Grupo prepareCreate(){
        selected = new Grupo();
        
        return selected;
    }
    
    public void prepareEdit(){
    
    }
}
