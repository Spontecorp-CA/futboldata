/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoInLiga;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoInLigaFacade;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.utilities.Util;
import static com.spontecorp.futboldata.utilities.Util.ACTIVO;
import static com.spontecorp.futboldata.utilities.Util.INACTIVO;
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
@Named("equipoInLigaBean")
@SessionScoped
public class EquipoInLigaBean implements Serializable {

    private List<EquipoInLiga> items = null;
    private List<Competicion> itemsCompeticion = null;
    private List<Competicion> itemsLiga = null;
    private List<Equipo> itemsEquipo = null;
    private List<Equipo> filtereditemsEquipo = null;
    private List<Equipo> filteredEquipo;
    private List<EquipoInLiga> filteredEquipoInLiga;
    private List<Competicion> filteredCompeticion;

    private final EquipoInLigaFacade controllerEquipoInLiga;
    private final EquipoFacade controllerEquipo;
    private final CompeticionFacade controllerCompeticion;
    private final CompeticionFacade controllerLiga;

    private static final Logger logger = LoggerFactory.getLogger(EquipoInLigaBean.class);
    private Competicion liga;
    private Equipo equipo;
    private EquipoInLiga equipoInLiga;
    private List<Competicion> filteredLigas;

    public EquipoInLigaBean() {
        controllerEquipoInLiga = new EquipoInLigaFacade();
        controllerEquipo = new EquipoFacade();
        controllerCompeticion = new CompeticionFacade();
        controllerLiga = new CompeticionFacade();
    }
    
    

    public EquipoInLiga getEquipoInLiga() {
        if (equipoInLiga == null) {
            equipoInLiga = new EquipoInLiga();
            initializeEmbeddableKey();
            setEmbeddableKeys();
        }
        return equipoInLiga;
    }

    public void setEquipoInLiga(EquipoInLiga equipoInLiga) {
        this.equipoInLiga = equipoInLiga;
    }
    
    
    
        public List<Competicion> getItemsLiga() {
        if (itemsLiga == null) {
            itemsLiga = controllerLiga.findAll();
        }
        return itemsLiga;
    }

    public List<Equipo> getItemsEquipo() {
        if (itemsEquipo == null){
            itemsEquipo = controllerEquipo.findAll();
        }
        return itemsEquipo;
    }

    public void setItemsEquipo(List<Equipo> itemsEquipo) {
        this.itemsEquipo = itemsEquipo;
    }
   
    public List<Competicion> getFilteredLigas() {
        return filteredLigas;
    }

    public void setFilteredLigas(List<Competicion> filteredLigas) {
        this.filteredLigas = filteredLigas;
    }
    public List<Competicion> getFilteredCompeticion() {
        return filteredCompeticion;
    }

    public void setFilteredCompeticion(List<Competicion> filteredCompeticion) {
        this.filteredCompeticion = filteredCompeticion;
    }

    public List<EquipoInLiga> getFilteredEquipoInLiga() {
        return filteredEquipoInLiga;
    }

    public void setFilteredEquipoInLiga(List<EquipoInLiga> filteredEquipoInLiga) {
        this.filteredEquipoInLiga = filteredEquipoInLiga;
    }

    public List<Equipo> getFilteredEquipo() {
        return filteredEquipo;
    }

    public void setFilteredEquipo(List<Equipo> filteredEquipo) {
        this.filteredEquipo = filteredEquipo;
    }

    public List<Equipo> getFiltereditemsEquipo() {
        return filtereditemsEquipo;
    }

    public void setFiltereditemsEquipo(List<Equipo> filtereditemsEquipo) {
        this.filtereditemsEquipo = filtereditemsEquipo;
    }

    public Competicion getCompeticion() {
        return liga;
    }

    public void setCompeticion(Competicion liga) {
        this.liga = liga;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<EquipoInLiga> getItems() {
        if (items == null) {
            items = controllerEquipoInLiga.getListEquipoInLiga(this.liga);
        }
        return items;
    }

    public List<Competicion> getItemCompeticion() {
        if (itemsCompeticion == null) {
            itemsCompeticion = controllerCompeticion.findAll();
        }
        return itemsCompeticion;
    }

    public String prepareCreate() {
        equipoInLiga = new EquipoInLiga();
        recreateModel();
        return "/admin/equipo/equipohasliga/listliga?faces-redirect=true";
    }

    protected void setEmbeddableKeys() {

        //      equipoInLiga.setPersonaId(liga);
    }

    protected void initializeEmbeddableKey() {
        liga = new Competicion();
    }

    public void recreateModel() {
        equipoInLiga = new EquipoInLiga();
        items = null;

    }

    public void prepareEdit() {
        equipoInLiga = new EquipoInLiga();
    }

    public String create() {
      
        if (controllerEquipoInLiga.getEquipoInLiga(equipo, liga) == null) {
            
           try {
            equipoInLiga.setEquipoId(this.equipo);
            logger.debug("Nombre del liga ");
            equipoInLiga.setCompeticionId(liga);
            logger.debug("Nombre del Equipo " + equipo.getNombre());
            logger.debug("Esta Creando  un EquipoInLiga");
            equipoInLiga.setStatus(ACTIVO);
            controllerEquipoInLiga.create(equipoInLiga);
            recreateModel();
            Util.addSuccessMessage("Se Agrego Exitosamente el Competicion");
        } catch (Exception e) {
            logger.debug("Error al crear EquipoInLiga :", e);
        }
        
        }else{
            Util.addErrorMessage("El Competicion ya esta en el equipo, Seleccion otro");         
        }                      
        return prepareCreate();
    }

    public String edit() {

        logger.debug("Esta editando un EquipoInLiga");
        controllerEquipoInLiga.edit(equipoInLiga);

        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el EquipoInLiga");
        return prepareCreate();
    }
   public String delete() {
       try {
           logger.debug(equipoInLiga.getEquipoId().getNombre());
           equipoInLiga.setStatus(INACTIVO);
           controllerEquipoInLiga.edit(equipoInLiga);
           equipoInLiga = null;
                  Util.addSuccessMessage("Se elimino exitosamente");
       } catch (Exception e) {
           logger.debug("Error al eliminar la entidad EquipoInLiga : ",e.getMessage());
           Util.addErrorMessage("Error al eliminar al Competicion del Equipo");
       }

       return prepareCreate();
   }
    public String getHostImagen() {
        String host = Util.getHostImagen() + "equipo/";
        return host;
    }

    public String gotoEquipoInLigaPage(Competicion liga) {
        recreateModel();
        this.liga = liga;
        equipo = new Equipo();
        return "/admin/liga/equipoinliga/listequipo?faces-redirect=true";
    }
    
    
    public String gotoLigaPage() {
        recreateModel();
        return "/admin/liga/equipoinliga/list?faces-redirect=true";
    }
}
