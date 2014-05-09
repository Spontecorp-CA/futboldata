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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "patrocinante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patrocinante.findAll", query = "SELECT p FROM Patrocinante p"),
    @NamedQuery(name = "Patrocinante.findById", query = "SELECT p FROM Patrocinante p WHERE p.id = :id"),
    @NamedQuery(name = "Patrocinante.findByNombre", query = "SELECT p FROM Patrocinante p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Patrocinante.findByIsMarcaEquipacion", query = "SELECT p FROM Patrocinante p WHERE p.isMarcaEquipacion = :isMarcaEquipacion")})
public class Patrocinante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "is_marca_equipacion")
    private Integer isMarcaEquipacion;
    @OneToMany(mappedBy = "patrocinanteId")
    private Collection<Contrato> contratoCollection;
    @OneToMany(mappedBy = "patrocinanteId")
    private Collection<Imagen> imagenCollection;

    public Patrocinante() {
    }

    public Patrocinante(Integer id) {
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

    public Integer getIsMarcaEquipacion() {
        return isMarcaEquipacion;
    }

    public void setIsMarcaEquipacion(Integer isMarcaEquipacion) {
        this.isMarcaEquipacion = isMarcaEquipacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patrocinante)) {
            return false;
        }
        Patrocinante other = (Patrocinante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Patrocinante[ id=" + id + " ]";
    }
    
}
