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
import javax.persistence.JoinColumn;
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
 * @author jgcastillo
 */
@Entity
@Table(name = "asociacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asociacion.findAll", query = "SELECT a FROM Asociacion a"),
    @NamedQuery(name = "Asociacion.findById", query = "SELECT a FROM Asociacion a WHERE a.id = :id"),
    @NamedQuery(name = "Asociacion.findByNombre", query = "SELECT a FROM Asociacion a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Asociacion.findByStatus", query = "SELECT a FROM Asociacion a WHERE a.status = :status")})
public class Asociacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    @ManyToOne
    private Direccion direccionId;
    @OneToMany(mappedBy = "asociacionId")
    private Collection<Competicion> competicionCollection;
    @OneToMany(mappedBy = "asociacionId")
    private Collection<Arbitro> arbitroCollection;
    @OneToMany(mappedBy = "asociacionId")
    private Collection<Contrato> contratoCollection;
    @OneToMany(mappedBy = "asociacionId")
    private Collection<Imagen> imagenCollection;
    @OneToMany(mappedBy = "asociacionId")
    private Collection<Staff> staffCollection;
    @OneToMany(mappedBy = "asociacionId")
    private Collection<RedSocial> redSocialCollection;

    public Asociacion() {
    }

    public Asociacion(Integer id) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Direccion getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Direccion direccionId) {
        this.direccionId = direccionId;
    }

    @XmlTransient
    public Collection<Competicion> getCompeticionCollection() {
        return competicionCollection;
    }

    public void setCompeticionCollection(Collection<Competicion> competicionCollection) {
        this.competicionCollection = competicionCollection;
    }

    @XmlTransient
    public Collection<Arbitro> getArbitroCollection() {
        return arbitroCollection;
    }

    public void setArbitroCollection(Collection<Arbitro> arbitroCollection) {
        this.arbitroCollection = arbitroCollection;
    }

    @XmlTransient
    public Collection<Contrato> getContratoCollection() {
        return contratoCollection;
    }

    public void setContratoCollection(Collection<Contrato> contratoCollection) {
        this.contratoCollection = contratoCollection;
    }

    @XmlTransient
    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
    }

    @XmlTransient
    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
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
        if (!(object instanceof Asociacion)) {
            return false;
        }
        Asociacion other = (Asociacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
