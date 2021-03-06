/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
    @NamedQuery(name = "Direccion.findById", query = "SELECT d FROM Direccion d WHERE d.id = :id"),
    @NamedQuery(name = "Direccion.findByPaginaWeb", query = "SELECT d FROM Direccion d WHERE d.paginaWeb = :paginaWeb"),
    @NamedQuery(name = "Direccion.findByLatitud", query = "SELECT d FROM Direccion d WHERE d.latitud = :latitud"),
    @NamedQuery(name = "Direccion.findByLongitud", query = "SELECT d FROM Direccion d WHERE d.longitud = :longitud")})
public class Direccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 80)
    @Column(name = "pagina_web")
    private String paginaWeb;
    @Column(name = "latitud")
    private Integer latitud;
    @Column(name = "longitud")
    private Integer longitud;
    @OneToMany(mappedBy = "direccionId")
    private Collection<Asociacion> asociacionCollection;
    @OneToMany(mappedBy = "direccionId")
    private Collection<Club> clubCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direccionId")
    private Collection<Telefono> telefonoCollection;
    @OneToMany(mappedBy = "direccionId")
    private Collection<Persona> personaCollection;
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    @ManyToOne
    private Ciudad ciudadId;
    @OneToMany(mappedBy = "direccionId")
    private Collection<Cancha> canchaCollection;
    @OneToMany(mappedBy = "direccionId")
    private Collection<Competicion> competicionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direccionId")
    private Collection<Email> emailCollection;

    public Direccion() {
    }

    public Direccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public Integer getLatitud() {
        return latitud;
    }

    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    @XmlTransient
    public Collection<Asociacion> getAsociacionCollection() {
        return asociacionCollection;
    }

    public void setAsociacionCollection(Collection<Asociacion> asociacionCollection) {
        this.asociacionCollection = asociacionCollection;
    }

    @XmlTransient
    public Collection<Club> getClubCollection() {
        return clubCollection;
    }

    public void setClubCollection(Collection<Club> clubCollection) {
        this.clubCollection = clubCollection;
    }

    @XmlTransient
    public Collection<Telefono> getTelefonoCollection() {
        return telefonoCollection;
    }

    public void setTelefonoCollection(Collection<Telefono> telefonoCollection) {
        this.telefonoCollection = telefonoCollection;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    public Ciudad getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Ciudad ciudadId) {
        this.ciudadId = ciudadId;
    }

    @XmlTransient
    public Collection<Cancha> getCanchaCollection() {
        return canchaCollection;
    }

    public void setCanchaCollection(Collection<Cancha> canchaCollection) {
        this.canchaCollection = canchaCollection;
    }

    @XmlTransient
    public Collection<Competicion> getCompeticionCollection() {
        return competicionCollection;
    }

    public void setCompeticionCollection(Collection<Competicion> competicionCollection) {
        this.competicionCollection = competicionCollection;
    }

    @XmlTransient
    public Collection<Email> getEmailCollection() {
        return emailCollection;
    }

    public void setEmailCollection(Collection<Email> emailCollection) {
        this.emailCollection = emailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return direccion;
    }
    
}
