/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Jugador;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.Posicion;
import com.spontecorp.futboldata.entity.RedSocial;
import com.spontecorp.futboldata.entity.TipoRedSocial;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.JugadorFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.PosicionFacade;
import com.spontecorp.futboldata.jpacontroller.RedSocialFacade;
import com.spontecorp.futboldata.jpacontroller.TipoRedSocialFacade;
import com.spontecorp.futboldata.utilities.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("jugadorBean")
@SessionScoped
public class JugadorBean implements Serializable {

    private Jugador jugador;
    private Pais pais;
    private SelectItem[] ciudades;
    private List<Posicion> posiciones;
    private DataModel items = null;

    private Direccion direccion;
    private Persona persona;
    private String cuenta;
    private Email email;
    private RedSocial redSocial;
    private TipoRedSocial tipoRedSocial;
    private List<RedSocial> redes;
    private List<RedSocial> redesEliminar;
    private List<Jugador> filteredJugador;
    private List<Jugador> listaTemporal;

    private final JugadorFacade controllerJugador;

    private final PaisFacade controllerPais;
    private final CiudadFacade controllerCiudad;
    private final PosicionFacade posicionFacade;
    private final RedSocialFacade controllerRedSocial;
    private final TipoRedSocialFacade tipoRedSocialController;

    private static final Logger logger = LoggerFactory.getLogger(Jugador.class);
    private final LoginBean bean;
    private UploadedFile file;

    public JugadorBean() {
        controllerJugador = new JugadorFacade();
        controllerPais = new PaisFacade();
        controllerCiudad = new CiudadFacade();
        posicionFacade = new PosicionFacade();
        controllerRedSocial = new RedSocialFacade();
        tipoRedSocialController = new TipoRedSocialFacade();
        bean = (LoginBean) Util.findBean("loginBean");
    }

    public Jugador getSelected() {
        if (jugador == null) {
            jugador = new Jugador();
            direccion = new Direccion();
            persona = new Persona();
            persona.setDireccionId(direccion);
            jugador.setPersonaId(persona);

            ciudades = null;
            posiciones = null;
            redes = new ArrayList<RedSocial>();
        }
        return jugador;
    }

    public List<Jugador> getFilteredJugador() {
        return filteredJugador;
    }

    public void setFilteredJugador(List<Jugador> filteredJugador) {
        this.filteredJugador = filteredJugador;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public List<RedSocial> getRedes() {
        return redes;
    }

    public void setRedes(List<RedSocial> redes) {
        this.redes = redes;
    }

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerJugador.findAll(bean.getIdOrganizacion()));
        }
        return items;
    }

    public void prepareCreate() {

        jugador = new Jugador();
        initializeEmbeddableKey();
        jugador.setOrganizacionId(bean.getIdOrganizacion());

    }

    protected void setEmbeddableKeys() {
        persona.setDireccionId(direccion);
        jugador.setPersonaId(persona);

    }

    protected void initializeEmbeddableKey() {
        redSocial = new RedSocial();
        direccion = new Direccion();
        persona = new Persona();
        persona.setFoto("vacio");
        redes = new ArrayList<RedSocial>();
        redesEliminar = new ArrayList<RedSocial>();
        direccion = new Direccion();
        ciudades = null;
        posiciones = null;
        pais = null;
        setEmbeddableKeys();
    }

    public SelectItem[] getPaisesAvalaible() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadesAvalaible() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    private void posicionesAvailable() {
        posiciones = posicionFacade.findAll();
    }

    public List<Posicion> getPosiciones() {
        if (posiciones == null) {
            posicionesAvailable();
        }
        return posiciones;
    }

    public SelectItem[] getRedSocialAvailable() {
        return Util.getSelectItems(tipoRedSocialController.findAll());
    }

    public void cargarRedSocial() {
        redSocial.setTipoRedSocialId(tipoRedSocial);
        redSocial.setPersonaId(persona);
        redes.add(redSocial);
        redSocial = new RedSocial();
    }

    public List<RedSocial> getRedesSocial() {
        return controllerRedSocial.findRedSocialxPersona(persona);
    }

    public void recreateModel() {
        redSocial = null;
        pais = null;
        jugador = null;
        items = null;
        persona = null;
    }

    public void prepareEdit() {
        redes = getRedSocials(jugador.getPersonaId());
        pais = jugador.getPersonaId().getDireccionId().getCiudadId().getPaisId();
        ciudadesAvalaible();
    }

    public void ciudadesAvailable(Pais pais) {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public void create() {
        try {
            if (controllerJugador.findJugadorxDomentoId(persona.getDocumentoIdentidad()) != null) {
                Util.addErrorMessage("El jugador ya se encuentra Registrado por el Documento de "
                        + "identificacion");

            } else {

                persona.setRedSocialCollection(redes);
                persona.setDireccionId(direccion);
                jugador.setPersonaId(persona);
                logger.debug("Esta Creando  un Jugador");
                controllerJugador.create(jugador);
                recreateModel();
                Util.addSuccessMessage("Se creo exitosamente el Jugador");

            }

        } catch (Exception e) {
            logger.debug("Error al crear Jugador :", e.getMessage());
        }
    }

    public void edit() {

        for (RedSocial red : redes) {
            red.setPersonaId(jugador.getPersonaId());
        }
        jugador.getPersonaId().setRedSocialCollection(redes);
        logger.debug("Esta editando un Jugador");
        controllerJugador.edit(jugador);
        for (RedSocial redEliminar : redesEliminar) {
            controllerRedSocial.remove(redEliminar);
        }

        Util.addSuccessMessage("Se edito exitosamente el Jugador");
        recreateModel();
    }

    public Jugador getJugador() {
        if (jugador == null) {
            jugador = new Jugador();
            initializeEmbeddableKey();
        }
        return jugador;
    }

    public List<Jugador> getListaTemporal() {
        return listaTemporal;
    }

    public void setListaTemporal(List<Jugador> listaTemporal) {
        this.listaTemporal = listaTemporal;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public RedSocial getRedSocial() {
        if (redSocial == null) {
            redSocial = new RedSocial();
        }
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    public TipoRedSocial getTipoRedSocial() {
        return tipoRedSocial;
    }

    public void setTipoRedSocial(TipoRedSocial tipoRedSocial) {
        this.tipoRedSocial = tipoRedSocial;
    }

    public List<RedSocial> getRedSocials(Persona persona) {
        redes = controllerRedSocial.findRedSocialxPersona(persona);
        return redes;
    }

    public void eliminarRedSocial(RedSocial redsocial) {

        if (redes.remove(redsocial)) {
            redesEliminar.add(redsocial);
            for (RedSocial red : redesEliminar) {
                logger.debug("Va a eliminar a: " + red.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        long lDateTime = new Date().getTime();
        System.out.println("Date() - Time in milliseconds: " + lDateTime);
        String nombreArchivo = "jugador" + lDateTime;
        Util.subirArchivo(event, "jugador/", nombreArchivo);
        jugador.getPersonaId().setFoto(nombreArchivo);

    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "jugador/";
        return host;
    }

    public void leerArchivo(FileUploadEvent even) {
        Iterator<Row> rowIterator;
        listaTemporal = new ArrayList<>();
        UploadedFile file2 = even.getFile();
        if (file2 != null) {
            try {
                if (file2.getFileName().endsWith(".xls")) {
                    HSSFWorkbook workbook = new HSSFWorkbook(file2.getInputstream());
                    HSSFSheet sheet = workbook.getSheetAt(0);
                    rowIterator = sheet.iterator();
                } else {
                    XSSFWorkbook workbook = new XSSFWorkbook(file2.getInputstream());
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    rowIterator = sheet.iterator();
                }

//Create Workbook instance holding reference to .xlsx file
                //Get first/desired sheet from the workbook
                //Iterate through each rows one by one
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator();

                    Jugador jugadorTemp = new Jugador();
                    jugadorTemp.setPersonaId(new Persona());
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        //Check the cell type and format accordingly
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                System.out.print(cell.getNumericCellValue() + " ");
                                break;
                            case Cell.CELL_TYPE_STRING:
                                System.out.print(cell.getStringCellValue() + " Cell Index:" + cell.getColumnIndex()
                                        + " Row Index:" + cell.getRowIndex() + " ");
                                switch (cell.getColumnIndex()) {
                                    case 0:
                                        jugadorTemp.getPersonaId().setNombre(cell.getStringCellValue());
                                    case 1:
                                        jugadorTemp.getPersonaId().setApellido(cell.getStringCellValue());

                                }

                                break;
                        }
                    }
                    System.out.println("");
                    listaTemporal.add(jugadorTemp);
                }
            } catch (Exception e) {
                logger.debug("Error al leer archivo", e);
            }
        }

    }
}
