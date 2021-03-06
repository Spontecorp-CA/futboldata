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
public class EquipoHasJugadorBean implements Serializable {

    private List<EquipoHasJugador> items = null;
    private List<Jugador> itemsJugador = null;
    private List<Equipo> itemsEquipo = null;
    private List<Equipo> filtereditemsEquipo = null;
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
    private LoginBean bean;
    public EquipoHasJugadorBean() {
        bean = (LoginBean) Util.findBean("loginBean");
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

    public List<EquipoHasJugador> getItems() {
        if (items == null) {
            items = controllerEquipoHasJugador.getListEquipoHasJugador(this.equipo);
        }
        return items;
    }

    public List<Jugador> getItemJugador() {
            itemsJugador = controllerJugador.findAll(bean.getIdOrganizacion());
        return itemsJugador;
    }

    public List<Equipo> getItemEquipo() {
        if (itemsEquipo == null) {
            itemsEquipo = controllerEquipo.findAll(bean.getIdOrganizacion());
        }
        return itemsEquipo;
    }

    public String prepareCreate() {
        equipoHasJugador = new EquipoHasJugador();
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
                equipoHasJugador.setStatus(ACTIVO);
                controllerEquipoHasJugador.create(equipoHasJugador);
                recreateModel();
                Util.addSuccessMessage("Se Agrego Exitosamente el Jugador");
            } catch (Exception e) {
                logger.debug("Error al crear EquipoHasJugador :", e);
            }

        } else {
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
            equipoHasJugador.setStatus(INACTIVO);
            controllerEquipoHasJugador.edit(equipoHasJugador);
            equipoHasJugador = null;
            Util.addSuccessMessage("Se elimino exitosamente");
        } catch (Exception e) {
            logger.debug("Error al eliminar la entidad EquipoHasJugador : ", e.getMessage());
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
