/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "red_social")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedSocial.findAll", query = "SELECT r FROM RedSocial r"),
    @NamedQuery(name = "RedSocial.findById", query = "SELECT r FROM RedSocial r WHERE r.id = :id"),
    @NamedQuery(name = "RedSocial.findByNombre", query = "SELECT r FROM RedSocial r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RedSocial.findByUrl", query = "SELECT r FROM RedSocial r WHERE r.url = :url")})
public class RedSocial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "url")
    private String url;
    @OneToMany(mappedBy = "redSocialId")
    private Collection<Asociacion> asociacionCollection;
    @OneToMany(mappedBy = "redSocialId")
    private Collection<Persona> personaCollection;

    public RedSocial() {
    }

    public RedSocial(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<Asociacion> getAsociacionCollection() {
        return asociacionCollection;
    }

    public void setAsociacionCollection(Collection<Asociacion> asociacionCollection) {
        this.asociacionCollection = asociacionCollection;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
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
        if (!(object instanceof RedSocial)) {
            return false;
        }
        RedSocial other = (RedSocial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.RedSocial[ id=" + id + " ]";
    }
    
}
