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
@Table(name = "grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findById", query = "SELECT g FROM Grupo g WHERE g.id = :id"),
    @NamedQuery(name = "Grupo.findByNombre", query = "SELECT g FROM Grupo g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "Grupo.findByStatus", query = "SELECT g FROM Grupo g WHERE g.status = :status")})
public class Grupo implements Serializable {
    
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoId")
    private Collection<Jornada> jornadaCollection;
    @JoinColumn(name = "fase_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fase faseId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoId")
    private Collection<ClasificacionGrupo> clasificacionGrupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoId")
    private Collection<EquipoEnGrupo> equipoEnGrupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoId")
    private Collection<Clasificacion> clasificacionCollection;
    
    private static final long serialVersionUID = 1L;

    public Grupo() {
    }

    public Grupo(Integer id) {
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

    @XmlTransient
    public Collection<Jornada> getJornadaCollection() {
        return jornadaCollection;
    }

    public void setJornadaCollection(Collection<Jornada> jornadaCollection) {
        this.jornadaCollection = jornadaCollection;
    }

    public Fase getFaseId() {
        return faseId;
    }

    public void setFaseId(Fase faseId) {
        this.faseId = faseId;
    }

    public Collection<Clasificacion> getClasificacionCollection() {
        return clasificacionCollection;
    }

    public void setClasificacionCollection(Collection<Clasificacion> clasificacionCollection) {
        this.clasificacionCollection = clasificacionCollection;
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
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @XmlTransient
    public Collection<EquipoEnGrupo> getEquipoEnGrupoCollection() {
        return equipoEnGrupoCollection;
    }

    public void setEquipoEnGrupoCollection(Collection<EquipoEnGrupo> equipoEnGrupoCollection) {
        this.equipoEnGrupoCollection = equipoEnGrupoCollection;
    }

    @XmlTransient
    public Collection<ClasificacionGrupo> getClasificacionGrupoCollection() {
        return clasificacionGrupoCollection;
    }

    public void setClasificacionGrupoCollection(Collection<ClasificacionGrupo> clasificacionGrupoCollection) {
        this.clasificacionGrupoCollection = clasificacionGrupoCollection;
    }
    
}
