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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "tipo_red_social")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRedSocial.findAll", query = "SELECT t FROM TipoRedSocial t"),
    @NamedQuery(name = "TipoRedSocial.findById", query = "SELECT t FROM TipoRedSocial t WHERE t.id = :id"),
    @NamedQuery(name = "TipoRedSocial.findByNombre", query = "SELECT t FROM TipoRedSocial t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoRedSocial.findByUrl", query = "SELECT t FROM TipoRedSocial t WHERE t.url = :url")})
public class TipoRedSocial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "url")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoRedSocialId")
    private Collection<RedSocial> redSocialCollection;

    public TipoRedSocial() {
    }

    public TipoRedSocial(Integer id) {
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
    public Collection<RedSocial> getRedSocialCollection() {
        return redSocialCollection;
    }

    public void setRedSocialCollection(Collection<RedSocial> redSocialCollection) {
        this.redSocialCollection = redSocialCollection;
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
        if (!(object instanceof TipoRedSocial)) {
            return false;
        }
        TipoRedSocial other = (TipoRedSocial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.TipoRedSocial[ id=" + id + " ]";
    }
    
}
