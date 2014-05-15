/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.EquipoHasJugador;
import com.spontecorp.futboldata.entity.Jugador;
import com.spontecorp.futboldata.jpacontroller.EquipoFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoHasJugadorFacade;
import com.spontecorp.futboldata.jpacontroller.JugadorFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import jdk.nashorn.internal.parser.TokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("equipoHasJugadorBean")
@SessionScoped
public class EquipoHasJugadorBean implements Serializable {

    private DataModel items = null;
    private DataModel itemsJugador = null;
    private DataModel itemsEquipo = null;
    private DataModel filtereditemsEquipo = null;
    private List<Equipo> filteredEquipo;
    private List<EquipoHasJugador> filteredEquipoHasJugador;
    private List<Jugador> filteredJugador;

    private final EquipoHasJugadorFacade controllerEquipoHasJugador;
    private final EquipoFacade controllerEquipo;
    private final JugadorFacade controllerJugador;

    private static final Logger logger = LoggerFactory.getLogger(EquipoHasJugadorBean.class);
    private Jugador jugador;
    private Equipo equipo;
    private EquipoHasJugador equipoHasJugador;

    public EquipoHasJugadorBean() {
        controllerEquipoHasJugador = new EquipoHasJugadorFacade();
        controllerEquipo = new EquipoFacade();
        controllerJugador = new JugadorFacade();

    }

    public EquipoHasJugador getEquipoHasJugador() {
        if (equipoHasJugador == null) {
            equipoHasJugador = new EquipoHasJugador();
            initializeEmbeddableKey();
            setEmbeddableKeys();
        }
        return equipoHasJugador;
    }

    public void setEquipoHasJugador(EquipoHasJugador equipoHasJugador) {
        this.equipoHasJugador = equipoHasJugador;
    }

    public void getSelect() {
        equipoHasJugador = new EquipoHasJugador();
        equipoHasJugador = (EquipoHasJugador) items.getRowData();
    }

    public void setSelect(EquipoHasJugador equipoHasJugador) {
        this.equipoHasJugador = equipoHasJugador;
    }

    public List<Jugador> getFilteredJugador() {
        return filteredJugador;
    }

    public void setFilteredJugador(List<Jugador> filteredJugador) {
        this.filteredJugador = filteredJugador;
    }

    public List<EquipoHasJugador> getFilteredEquipoHasJugador() {
        return filteredEquipoHasJugador;
    }

    public void setFilteredEquipoHasJugador(List<EquipoHasJugador> filteredEquipoHasJugador) {
        this.filteredEquipoHasJugador = filteredEquipoHasJugador;
    }

    public List<Equipo> getFilteredEquipo() {
        return filteredEquipo;
    }

    public void setFilteredEquipo(List<Equipo> filteredEquipo) {
        this.filteredEquipo = filteredEquipo;
    }

    public DataModel getFiltereditemsEquipo() {
        return filtereditemsEquipo;
    }

    public void setFiltereditemsEquipo(DataModel filtereditemsEquipo) {
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

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerEquipoHasJugador.getListEquipoHasJugador(this.equipo));
        }
        return items;
    }

    public DataModel getItemJugador() {
        if (itemsJugador == null) {
            itemsJugador = new ListDataModel(controllerJugador.findAll());
        }
        return itemsJugador;
    }

    public DataModel getItemEquipo() {
        if (itemsEquipo == null) {
            itemsEquipo = new ListDataModel(controllerEquipo.findAll());
        }
        return itemsEquipo;
    }

    public String prepareCreate() {
        equipoHasJugador = new EquipoHasJugador();
        recreateModel();
        return "listjugador?faces-redirect=true";
    }

    protected void setEmbeddableKeys() {

        //      equipoHasJugador.setPersonaId(jugador);
    }

    protected void initializeEmbeddableKey() {
        jugador = new Jugador();
    }

    public void recreateModel() {
        jugador = new Jugador();
        equipoHasJugador = new EquipoHasJugador();
        items = null;

    }

    public void prepareEdit() {
        equipoHasJugador = new EquipoHasJugador();
    }

    public String create() {
      
        if (controllerEquipoHasJugador.getEquipoHasJugador(equipo, jugador) == null) {
            
           try {
            equipoHasJugador.setJugadorId(this.jugador);
            logger.debug("Nombre del jugador " + jugador.getPersonaId().getNombre());
            equipoHasJugador.setEquipoId(this.equipo);
            logger.debug("Nombre del Equipo " + equipo.getNombre());
            logger.debug("Esta Creando  un EquipoHasJugador");
            controllerEquipoHasJugador.create(equipoHasJugador);
            recreateModel();
            Util.addSuccessMessage("Se Agrego Exitosamente el Jugador");
        } catch (Exception e) {
            logger.debug("Error al crear EquipoHasJugador :", e);
        }
        
        }else{
            Util.addErrorMessage("El Jugador ya esta en el equipo, Seleccion otro");         
        }                      
        return prepareCreate();
    }

    public String edit() {

        logger.debug("Esta editando un EquipoHasJugador");
        controllerEquipoHasJugador.edit(equipoHasJugador);

        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el EquipoHasJugador");
        return prepareCreate();
    }
   public String delete() {
       try {
                  controllerEquipoHasJugador.remove(equipoHasJugador);
                  Util.addSuccessMessage("Se elimino exitosamente");
       } catch (Exception e) {
           logger.debug("Error al eliminar la entidad EquipoHasJugador : ",e.getMessage());
           Util.addErrorMessage("Error al eliminar al Jugador del Equipo");
       }

       return prepareCreate();
   }
    public String getHostImagen() {
        String host = Util.getHostImagen() + "jugador/";
        return host;
    }

    public String gotoEquipoHasJugadorPage() {
        recreateModel();
        equipo = new Equipo();
        return "/admin/equipo/equipohasjugador/listequipo?faces-redirect=true";
    }
}
