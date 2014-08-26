/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Cancha;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.jpacontroller.CanchaFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacade;
import com.spontecorp.futboldata.jpacontroller.EmailFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.TelefonoFacade;
import com.spontecorp.futboldata.utilities.Util;
import com.spontecorp.futboldata.utilities.Util.PersistAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named("canchaBean")
@SessionScoped
public class CanchaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private double lat;
    private double lng;

    private List<Cancha> items = null;
    private List<Cancha> filteredCanchas = null;
    private Cancha selected;

    private Pais pais;
    private Direccion direccion;
    private Telefono telefono;
    private Email email;
    private List<Email> emails;
    private List<Telefono> telefonos;
    private List<Email> emailEliminados;
    private List<Telefono> telefonoEliminados;
    private SelectItem[] ciudades;
    private boolean coordenadasAvailable;

    private final CanchaFacade canchaFacade;
    private final DireccionFacade direccionFacade;
    private final EmailFacade emailFacade;
    private final TelefonoFacade telefonoFacade;
    private final CiudadFacade ciudadFacade;
    private final PaisFacade paisFacade;
    private MapModel emptyModel;

    private static final Logger logger = LoggerFactory.getLogger(CanchaBean.class);

    public CanchaBean() {
        this.canchaFacade = new CanchaFacade();
        this.direccionFacade = new DireccionFacade();
        this.emailFacade = new EmailFacade();
        this.telefonoFacade = new TelefonoFacade();
        this.ciudadFacade = new CiudadFacade();
        this.paisFacade = new PaisFacade();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    
    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public Cancha getSelected() {
        return selected;
    }

    public void setSelected(Cancha selected) {
        this.selected = selected;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public List<Cancha> getItems() {
        if (items == null) {
            items = canchaFacade.findAll();
        }
        return items;
    }

    public List<Cancha> getFilteredCanchas() {
        return filteredCanchas;
    }

    public void setFilteredCanchas(List<Cancha> filteredCanchas) {
        this.filteredCanchas = filteredCanchas;
    }

    private List<Telefono> getTelefonos(Direccion direccion) {
        telefonos = direccionFacade.findListTelefonoxDireccion(direccion);
        return telefonos;
    }

    private List<Email> getEmails(Direccion direccion) {
        emails = direccionFacade.findListEmailxDireaccion(direccion);
        return emails;
    }

    public boolean isCoordenadasAvailable() {

        coordenadasAvailable = false;
        if (selected != null) {
            if ((selected.getCoordenadaLat() != null && selected.getCoordenadaLong() != null)
                    && (!selected.getCoordenadaLat().isEmpty() && !selected.getCoordenadaLong().isEmpty())) {
                coordenadasAvailable = true;
            }
        }
        return coordenadasAvailable;
    }

    public void cargarTelefono() {
        telefonos.add(telefono);
        telefono = new Telefono();
    }

    public void cargarEmail() {
        emails.add(email);
        email = new Email();
    }

    public SelectItem[] getPaisAvailable() {
        return Util.getSelectItems(paisFacade.listaPaisxNombre());
    }

    public void ciudadAvailable() {
        ciudades = Util.getSelectItems(ciudadFacade.findCiudadxPais(pais));
    }

    private void ciudadAvailable(Pais pais) {
        ciudades = Util.getSelectItems(ciudadFacade.findCiudadxPais(pais));
    }

    public String returnAdminPage() {
        return "adminPage";
    }

    public Cancha prepareCreate() {
        selected = new Cancha();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareEdit() {
        emailEliminados = new ArrayList<Email>();
        telefonoEliminados = new ArrayList<Telefono>();
        pais = selected.getDireccionId().getCiudadId().getPaisId();
        direccion = selected.getDireccionId();
        telefonos = getTelefonos(selected.getDireccionId());
        telefono = new Telefono();
        emails = getEmails(selected.getDireccionId());
        email = new Email();
        ciudadAvailable(pais);
    }

    public void create() {
        if (!existeNombreCancha(selected.getNombre())) {
            persist(PersistAction.CREATE, "Cancha creada con éxito");
            if (!Util.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            Util.addErrorMessage("Cancha ya existente, coloque un nombre diferente");
        }
    }

    private boolean existeNombreCancha(String nombre) {
        boolean result = true;
        if (canchaFacade.findCancha(nombre) == null) {
            result = false;
        }
        return result;
    }

    public void edit() {
        persist(PersistAction.UPDATE, "Cancha actualizada con éxito");
        if (!Util.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    protected void setEmbeddableKeys(PersistAction persistAction) {
        if (persistAction == PersistAction.CREATE) {

            for (Email item : emails) {
                item.setDireccionId(direccion);
            }
            for (Telefono item : telefonos) {
                item.setDireccionId(direccion);
            }
            direccion.setEmailCollection(emails);
            direccion.setTelefonoCollection(telefonos);

            selected.setDireccionId(direccion);

        } else if (persistAction == PersistAction.UPDATE) {

            for (Telefono item : telefonos) {
                if (telefonoFacade.findTelefono(item.getTelefono()) == null) {
                    item.setDireccionId(selected.getDireccionId());
                }
            }

            for (Email item : emails) {
                if (emailFacade.findEmail(item.getEmail()) == null) {
                    item.setDireccionId(selected.getDireccionId());
                }
            }
            direccion.setTelefonoCollection(telefonos);
            direccion.setEmailCollection(emails);
        }
    }

    protected void initializeEmbeddableKey() {
        direccion = new Direccion();
        telefono = new Telefono();
        email = new Email();
        telefonos = new ArrayList<Telefono>();
        emails = new ArrayList<Email>();
        direccion.setTelefonoCollection(telefonos);
        direccion.setEmailCollection(emails);
        selected.setDireccionId(direccion);
    }

    public void eliminarTelefono(Telefono telefono) {
        logger.debug("El número telefono: " + telefono.getTelefono(), AsociacionBean.class);
        if (telefonos.remove(telefono)) {
            telefonoEliminados.add(telefono);
            for (Telefono tlf : telefonoEliminados) {
                logger.debug("Va a eliminar a: " + tlf.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    public void eliminarEmail(Email email) {
        if (emails.remove(email)) {
            emailEliminados.add(email);
            for (Email eml : emailEliminados) {
                logger.debug("Va a eliminar a: " + eml.toString());
            }
        } else {
            logger.debug("No lo agrego a la lista de eliminar Telefono");
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                setEmbeddableKeys(persistAction);
                if (persistAction == PersistAction.CREATE) {
                    canchaFacade.create(selected);
                } else if (persistAction == PersistAction.UPDATE) {
                    canchaFacade.edit(selected);
                }
                Util.addSuccessMessage(successMessage);
                selected = null;
            } catch (Exception e) {
                logger.error("Error creando o editando la cancha: " + e.getMessage(), e);
                Util.addErrorMessage(e, "Error al crear ó editar la cancha");
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        long lDateTime = new Date().getTime();
        System.out.println("Date() - Time in milliseconds: " + lDateTime);
        String nombreArchivo = "cancha" + lDateTime;
        Util.subirArchivo(event, "cancha/", nombreArchivo);
        selected.setFoto(nombreArchivo);
    }

    public String getHostImagen() {
        String host = Util.getHostImagen() + "cancha/";
        return host;
    }

    public Cancha getCancha(java.lang.Integer id) {
        return canchaFacade.find(id);
    }

    public List<Cancha> getItemsAvailableSelectMany() {
        return canchaFacade.findAll();
    }

    public List<Cancha> getItemsAvailableSelectOne() {
        return canchaFacade.findAll();
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);

        Util.addSuccessMessage("Marker Added" + "Lat:" + lat + ", Lng:" + lng);
    
    }

    @FacesConverter(forClass = Cancha.class)
    public static class CanchaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CanchaBean controller = (CanchaBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "canchaBean");
            return controller.getCancha(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cancha) {
                Cancha o = (Cancha) object;
                return getStringKey(o.getId());
            } else {
                logger.error("object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cancha.class.getName()});
                //java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cancha.class.getName()});
                return null;
            }
        }

    } 
}
