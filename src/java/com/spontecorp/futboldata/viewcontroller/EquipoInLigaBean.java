/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoInLiga;
import com.spontecorp.futboldata.entity.Jugador;
import com.spontecorp.futboldata.jpacontroller.EquipoFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoInLigaFacade;
import com.spontecorp.futboldata.jpacontroller.JugadorFacade;
import com.spontecorp.futboldata.utilities.Util;
import static com.spontecorp.futboldata.utilities.Util.ACTIVO;
import static com.spontecorp.futboldata.utilities.Util.INACTIVO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("equipoHasJugadorBean")
@SessionScoped
public class EquipoInLigaBean implements Serializable {

    private List<EquipoInLiga> items = null;
    private List<Jugador> itemsJugador = null;
    private List<Equipo> itemsEquipo = null;
    private List<Equipo> filtereditemsEquipo = null;
    private List<Equipo> filteredEquipo;
    private List<EquipoInLiga> filteredEquipoInLiga;
    private List<Jugador> filteredJugador;

    private final EquipoInLigaFacade controllerEquipoInLiga;
    private final EquipoFacade controllerEquipo;
    private final JugadorFacade controllerJugador;

    private static final Logger logger = LoggerFactory.getLogger(EquipoInLigaBean.class);
    private Jugador jugador;
    private Equipo equipo;
    private EquipoInLiga equipoHasJugador;

    public EquipoInLigaBean() {
        controllerEquipoInLiga = new EquipoInLigaFacade();
        controllerEquipo = new EquipoFacade();
        controllerJugador = new JugadorFacade();

    }

    public EquipoInLiga getEquipoInLiga() {
        if (equipoHasJugador == null) {
            equipoHasJugador = new EquipoInLiga();
            initializeEmbeddableKey();
            setEmbeddableKeys();
        }
        return equipoHasJugador;
    }

    public void setEquipoInLiga(EquipoInLiga equipoHasJugador) {
        this.equipoHasJugador = equipoHasJugador;
    }
    
    public List<Jugador> getFilteredJugador() {
        return filteredJugador;
    }

    public void setFilteredJugador(List<Jugador> filteredJugador) {
        this.filteredJugador = filteredJugador;
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

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<EquipoInLiga> getItems() {
        if (items == null) {
            items = controllerEquipoInLiga.getListEquipoInLiga(this.equipo);
        }
        return items;
    }

    public List<Jugador> getItemJugador() {
        if (itemsJugador == null) {
            itemsJugador = controllerJugador.findAll();
        }
        return itemsJugador;
    }

    public List<Equipo> getItemEquipo() {
        if (itemsEquipo == null) {
            itemsEquipo = controllerEquipo.findAll();
        }
        return itemsEquipo;
    }

    public String prepareCreate() {
        equipoHasJugador = new EquipoInLiga();
        recreateModel();
        return "/admin/equipo/equipohasjugador/listjugador?faces-redirect=true";
    }

    protected void setEmbeddableKeys() {

        //      equipoHasJugador.setPersonaId(jugador);
    }

    protected void initializeEmbeddableKey() {
        jugador = new Jugador();
    }

    public void recreateModel() {
        jugador = new Jugador();
        equipoHasJugador = new EquipoInLiga();
        items = null;

    }

    public void prepareEdit() {
        equipoHasJugador = new EquipoInLiga();
    }

    public String create() {
      
        if (controllerEquipoInLiga.getEquipoInLiga(equipo, jugador) == null) {
            
           try {
            equipoHasJugador.setEquipoId(this.equipo);
            logger.debug("Nombre del jugador " + jugador.getPersonaId().getNombre());
            equipoHasJugador.setEquipoId(this.equipo);
            logger.debug("Nombre del Equipo " + equipo.getNombre());
            logger.debug("Esta Creando  un EquipoInLiga");
            equipoHasJugador.setStatus(ACTIVO);
            controllerEquipoInLiga.create(equipoHasJugador);
            recreateModel();
            Util.addSuccessMessage("Se Agrego Exitosamente el Jugador");
        } catch (Exception e) {
            logger.debug("Error al crear EquipoInLiga :", e);
        }
        
        }else{
            Util.addErrorMessage("El Jugador ya esta en el equipo, Seleccion otro");         
        }                      
        return prepareCreate();
    }

    public String edit() {

        logger.debug("Esta editando un EquipoInLiga");
        controllerEquipoInLiga.edit(equipoHasJugador);

        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el EquipoInLiga");
        return prepareCreate();
    }
   public String delete() {
       try {
           equipoHasJugador.setStatus(INACTIVO);
           controllerEquipoInLiga.edit(equipoHasJugador);
           equipoHasJugador = null;
                  Util.addSuccessMessage("Se elimino exitosamente");
       } catch (Exception e) {
           logger.debug("Error al eliminar la entidad EquipoInLiga : ",e.getMessage());
           Util.addErrorMessage("Error al eliminar al Jugador del Equipo");
       }

       return prepareCreate();
   }
    public String getHostImagen() {
        String host = Util.getHostImagen() + "jugador/";
        return host;
    }

    public String gotoEquipoInLigaPage() {
        recreateModel();
        equipo = new Equipo();
        return "/admin/equipo/equipohasjugador/listequipo?faces-redirect=true";
    }
}
