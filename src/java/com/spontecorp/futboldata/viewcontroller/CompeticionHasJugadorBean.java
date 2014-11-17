/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.CompeticionHasJugador;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.EquipoHasJugador;
import com.spontecorp.futboldata.entity.EquipoInLiga;
import com.spontecorp.futboldata.jpacontroller.CompeticionHasJugadorFacade;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoHasJugadorFacade;
import com.spontecorp.futboldata.jpacontroller.EquipoInLigaFacade;
import com.spontecorp.futboldata.utilities.Util;
import static com.spontecorp.futboldata.utilities.Util.ACTIVO;
import static com.spontecorp.futboldata.utilities.Util.INACTIVO;
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
@Named("competicionHasJugadorBean")
@SessionScoped
public class CompeticionHasJugadorBean implements Serializable {

    private List<CompeticionHasJugador> items = null;
    private List<Competicion> itemsCompeticion = null;
    private List<Competicion> itemsLiga = null;
    private List<CompeticionHasJugador> filteredCompeticionHasJugador;

    private final CompeticionHasJugadorFacade controllerCompeticionHasJugador;
    private final CompeticionFacade controllerCompeticion;
    private final CompeticionFacade controllerLiga;

    private static final Logger logger = LoggerFactory.getLogger(CompeticionHasJugadorBean.class);
    private Competicion liga;
    private Equipo equipo;
    private CompeticionHasJugador competicionHasJugador;
    private EquipoInLiga equipoInLiga;
    private Categoria categoria;
    private final LoginBean bean;
    private List<EquipoInLiga> equipoInLigas;
    private final EquipoInLigaFacade controllerEquipoInLiga;
    private final EquipoHasJugadorFacade controllerEquipoHasJugador;

    public CompeticionHasJugadorBean() {
        controllerCompeticionHasJugador = new CompeticionHasJugadorFacade();
        controllerEquipoInLiga = new EquipoInLigaFacade();
        controllerCompeticion = new CompeticionFacade();
        controllerLiga = new CompeticionFacade();
        controllerEquipoHasJugador = new EquipoHasJugadorFacade();
        bean = (LoginBean) Util.findBean("loginBean");
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            categoria = new Categoria();
        }
        this.categoria = categoria;
    }

    public EquipoInLiga getEquipoInLiga() {
        return equipoInLiga;
    }

    public void setEquipoInLiga(EquipoInLiga equipoInLiga) {
        this.equipoInLiga = equipoInLiga;
    }

    public CompeticionHasJugador getCompeticionHasJugador() {
        if (competicionHasJugador == null) {
            competicionHasJugador = new CompeticionHasJugador();
            setEmbeddableKeys();
        }
        return competicionHasJugador;
    }

    public void setCompeticionHasJugador(CompeticionHasJugador competicionHasJugador) {
        this.competicionHasJugador = competicionHasJugador;
    }

    public List<Competicion> getItemsLiga() {
        if (itemsLiga == null) {
            itemsLiga = controllerLiga.findAll(bean.getIdOrganizacion());
        }
        return itemsLiga;
    }

    public void setFilteredCompeticionHasJugador(List<CompeticionHasJugador> filteredCompeticionHasJugador) {
        this.filteredCompeticionHasJugador = filteredCompeticionHasJugador;
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
        if (equipo == null) {
            equipo = new Equipo();
        }
        this.equipo = equipo;
    }

    public List<CompeticionHasJugador> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public List<CompeticionHasJugador> getItems(Competicion liga) {
        if (items == null) {
            items = controllerCompeticionHasJugador.getListCompeticionHasJugador(liga);
        }
        return items;
    }

    public List<EquipoInLiga> getEquipoInLigas() {
        equipoInLigas = controllerEquipoInLiga.getEquipoInLigas(liga, categoria);
        return equipoInLigas;
    }

    public List<Competicion> getItemCompeticion() {
        if (itemsCompeticion == null) {
            itemsCompeticion = controllerCompeticion.findAll(bean.getIdOrganizacion());
        }
        return itemsCompeticion;
    }

    public String prepareCreate() {
        competicionHasJugador = new CompeticionHasJugador();
        recreateModel();
        return "/admin/equipo/equipohasliga/listliga?faces-redirect=true";
    }

    protected void setEmbeddableKeys() {

        //      competicionHasJugador.setPersonaId(liga);
    }

//    protected void initializeEmbeddableKey() {
//        liga = new Competicion();
//    }
    public void recreateModel() {
        competicionHasJugador = new CompeticionHasJugador();
        items = null;

    }

    public void prepareEdit() {
        competicionHasJugador = new CompeticionHasJugador();
    }

    public void cargarJugadores() {
        logger.debug("Llamo el metido");
        items = new ArrayList<>();
        if (equipo != null) {
            List<EquipoHasJugador> equipoHasJugadors
                    = controllerEquipoHasJugador.getListEquipoHasJugador(equipoInLiga.getEquipoId());
            for (EquipoHasJugador equipoHasJugador : equipoHasJugadors) {
                competicionHasJugador = controllerCompeticionHasJugador.getCompeticionHasJugador(equipoHasJugador.getJugadorId(), liga);
                if (competicionHasJugador == null) {
                    competicionHasJugador = new CompeticionHasJugador();
                    competicionHasJugador.setCompeticionId(liga);
                    competicionHasJugador.setJugadorId(equipoHasJugador.getJugadorId());
                    competicionHasJugador.setStatus(ACTIVO);
                }
                items.add(competicionHasJugador);

            }
        } else {
            Util.addErrorMessage("Seleccione el equipo.");
        }
        logger.debug("Tamaño de la lista " + items.size());
    }

    public void guardar() {

        for (CompeticionHasJugador item : items) {
            controllerCompeticionHasJugador.edit(item);
        }
        Util.addSuccessMessage("Guardado los Jugadores con Exito");

    }

    public String edit() {

        logger.debug("Esta editando un CompeticionHasJugador");
        controllerCompeticionHasJugador.edit(competicionHasJugador);

        recreateModel();
        Util.addSuccessMessage("Se edito exitosamente el CompeticionHasJugador");
        return prepareCreate();
    }

    public String delete() {
        try {
//            logger.debug(competicionHasJugador.getEquipoId().getNombre());
            competicionHasJugador.setStatus(INACTIVO);
            controllerCompeticionHasJugador.edit(competicionHasJugador);
            competicionHasJugador = null;
            Util.addSuccessMessage("Se elimino exitosamente");
        } catch (Exception e) {
            logger.debug("Error al eliminar la entidad CompeticionHasJugador : ", e.getMessage());
            Util.addErrorMessage("Error al eliminar al Competicion del Equipo");
        }

        return prepareCreate();
    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "equipo/";
        return host;
    }

    public String gotoCompeticionHasJugadorPage(Competicion liga) {
        recreateModel();
        this.liga = liga;
        equipo = new Equipo();
        return "/admin/liga/competicionhasjugador/list?faces-redirect=true";
    }

    public String gotoLigaPage() {
        recreateModel();
        return "/admin/liga/equipoinliga/list?faces-redirect=true";
    }
}
