/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "temporada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temporada.findAll", query = "SELECT t FROM Temporada t"),
    @NamedQuery(name = "Temporada.findById", query = "SELECT t FROM Temporada t WHERE t.id = :id"),
    @NamedQuery(name = "Temporada.findByNombre", query = "SELECT t FROM Temporada t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Temporada.findByFechaInicio", query = "SELECT t FROM Temporada t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Temporada.findByFechaFin", query = "SELECT t FROM Temporada t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "Temporada.findByStatus", query = "SELECT t FROM Temporada t WHERE t.status = :status")})
public class Temporada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temporadaId")
    private Collection<Fase> faseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temporada")
    private Collection<TemporadaCategoria> temporadaCategoriaCollection;
    @JoinColumn(name = "competicion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Competicion competicionId;

    public Temporada() {
    }

    public Temporada(Integer id) {
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Fase> getFaseCollection() {
        return faseCollection;
    }

    public void setFaseCollection(Collection<Fase> faseCollection) {
        this.faseCollection = faseCollection;
    }

    @XmlTransient
    public Collection<TemporadaCategoria> getTemporadaCategoriaCollection() {
        return temporadaCategoriaCollection;
    }

    public void setTemporadaCategoriaCollection(Collection<TemporadaCategoria> temporadaCategoriaCollection) {
        this.temporadaCategoriaCollection = temporadaCategoriaCollection;
    }

    public Competicion getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(Competicion competicionId) {
        this.competicionId = competicionId;
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
        if (!(object instanceof Temporada)) {
            return false;
        }
        Temporada other = (Temporada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Temporada[ id=" + id + " ]";
    }
    
}
