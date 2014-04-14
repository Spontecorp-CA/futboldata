/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.entity.Ciudad;
import com.spontecorp.futboldata.entity.Direccion;
import com.spontecorp.futboldata.entity.Email;
import com.spontecorp.futboldata.entity.Pais;
import com.spontecorp.futboldata.entity.Telefono;
import com.spontecorp.futboldata.jpacontroller.AsociacionFacade;
import com.spontecorp.futboldata.jpacontroller.CiudadFacade;
import com.spontecorp.futboldata.jpacontroller.DireccionFacede;
import com.spontecorp.futboldata.jpacontroller.EmailFacade;
import com.spontecorp.futboldata.jpacontroller.PaisFacade;
import com.spontecorp.futboldata.jpacontroller.TelefonoFacade;
import com.spontecorp.futboldata.utilities.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sponte03
 */
@ManagedBean(name = "asociacionBean")
@SessionScoped
public class AsociacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Asociacion asociacion = null;
    private Pais pais;
    private Direccion direccion;
    private Telefono telefono;
    private Email email;

    private List<Email> emails = null;
    private List<Telefono> telefonos = null;
    private transient DataModel itemsTelefono = null;
    private transient DataModel itemsAsociacion = null;
    private SelectItem[] ciudades;

    private final AsociacionFacade controllerAsociacion;
    private final transient EntityManagerFactory emf = Util.getEmf();
    private final DireccionFacede controllerDireccion;
    private final TelefonoFacade controllerTelefono;
    private final EmailFacade controllerEmail;

    private final CiudadFacade controllerCiudad;
    private final PaisFacade controllerPais;

    /**
     * Creates a new instance of LocalidadBean
     */
    public AsociacionBean() {
        controllerPais = new PaisFacade(Pais.class);
        controllerCiudad = new CiudadFacade(Ciudad.class);
        controllerEmail = new EmailFacade(Email.class);
        controllerTelefono = new TelefonoFacade(Telefono.class);
        controllerAsociacion = new AsociacionFacade(Asociacion.class);
        controllerDireccion = new DireccionFacede(Direccion.class);
    }

    public String edit() {
        try {
            if (controllerAsociacion.find(asociacion.getId()) == null) {
                Util.addErrorMessage("Asociacion no existente");
                return prepareList();
            } else {
                telefonos = (List<Telefono>) asociacion.getDireccionId().getTelefonoCollection();
                for (Telefono telefonoEditar : telefonos) {
                    controllerTelefono.edit(telefonoEditar);
                }

                emails = (List<Email>) asociacion.getDireccionId().getEmailCollection();
                for (Email emailEditar : emails) {
                    if (controllerEmail.findEmail(emailEditar.getEmail()) != null){
                    controllerEmail.edit(emailEditar);
                }
                    else {
                       
                        emailEditar.setDireccionId(asociacion.getDireccionId());
                        controllerEmail.create(emailEditar);
                        
                    }   
                }
                controllerDireccion.edit(asociacion.getDireccionId());
                controllerAsociacion.edit(asociacion);
                Util.addSuccessMessage("Asociacion editado con éxito");

                return prepareList();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la asociacion");
            return null;
        }
    }

    public SelectItem[] getPaisAvailable() {
        return Util.getSelectItems(controllerPais.listaPaisxNombre());
    }

    public void ciudadAvailable() {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public void ciudadAvailable(Pais pais) {
        ciudades = Util.getSelectItems(controllerCiudad.findCiudadxPais(pais));
    }

    public SelectItem[] getCiudades() {
        return ciudades;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public DataModel getItemsAsociacion() {

        if (itemsAsociacion == null) {
            itemsAsociacion = new ListDataModel(controllerAsociacion.findAll());
        }
        return itemsAsociacion;

    }

    public List<Telefono> getTelefonos(Direccion direccion) {
        System.out.print("Esta es la direaccion que trata de buscar " + direccion.getId().toString() + "***");
        telefonos = controllerDireccion.findListTelefonoxDireaccion(direccion);
        return telefonos;

    }

    public List<Email> getEmails(Direccion direccion) {
        System.out.print("Esta es la direaccion que trata de buscar " + direccion.getId().toString() + "***");
        emails = controllerDireccion.findListEmailxDireaccion(direccion);
        return emails;

    }

    public String gotoAsociacionPage() {
        recreateModel();
        return "/admin/asociacion/asociacion/list.xhtml";
    }

    private void recreateModel() {
        itemsTelefono = null;
    }

    private void recreateModelAsociacion() {
        itemsAsociacion = null;
    }

    public void setItemsAsociacion(DataModel items) {
        this.itemsAsociacion = items;
    }

    public String create() {
        try {
            if (controllerAsociacion.findAsociacion(asociacion.getNombre()) != null) {
                Util.addErrorMessage("Asociacion ya existente, coloque otra");
                return null;
            } else {

                controllerDireccion.create(direccion);
                for (Email item : emails) {
                    item.setDireccionId(direccion);
                    controllerEmail.create(item);
                }
                for (Telefono item2 : telefonos) {
                    item2.setDireccionId(direccion);
                    controllerTelefono.create(item2);
                }
                direccion.setEmailCollection(emails);
                direccion.setTelefonoCollection(telefonos);

                asociacion.setDireccionId(direccion);

                controllerAsociacion.create(asociacion);
                Util.addSuccessMessage("Asociacion creada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la asociacion");
            return null;
        }
    }

    public Asociacion getSelectedAsociacion() {
        if (asociacion == null) {
            asociacion = new Asociacion();
        }
        return asociacion;
    }

    public void setSelectedAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;

    }

    public String prepareList() {
        recreateModelAsociacion();
        return "/admin/asociacion/asociacion/list.xhtml";
    }

    public String returnAdminPage() {
        return "/admin/adminPage";
    }

    public String prepareCreate() {
        asociacion = new Asociacion();
        direccion = new Direccion();
        telefono = new Telefono();
        telefonos = new ArrayList<>();
        emails = new ArrayList<>();
        email = new Email();
        return "/admin/asociacion/asociacion/create.xhtml";
    }

    public void cargarTelefono() {
        telefonos.add(telefono);
        telefono = new Telefono();

    }

    public void cargarEmail() {
        emails.add(email);
        email = new Email();

    }

    
    public void cargarTelefonoEdit() {
        asociacion.getDireccionId().getTelefonoCollection().add(telefono);
        telefono = new Telefono();

    }

    public void cargarEmailEdit() {
        asociacion.getDireccionId().getEmailCollection().add(email);
        email = new Email();
        telefono = new Telefono();

    }

    public String prepareEdit() {
          email = new Email();
        telefono = new Telefono();
        asociacion = (Asociacion) getItemsAsociacion().getRowData();
        return "/admin/asociacion/asociacion/edit.xhtml";
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {

        this.telefono = new Telefono();

        this.telefono = telefono;
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public DataModel getItemsTelefono() {
        return itemsTelefono;
    }

    public void setItemsTelefono(DataModel itemsTelefono) {
        this.itemsTelefono = itemsTelefono;
    }

    public void cargarItemTelefono() {
        telefono = (Telefono) getItemsTelefono().getRowData();
    }

}
