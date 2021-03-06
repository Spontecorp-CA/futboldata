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
 * @author jgcastillo
 */
@Entity
@Table(name = "status_partido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusPartido.findAll", query = "SELECT s FROM StatusPartido s"),
    @NamedQuery(name = "StatusPartido.findById", query = "SELECT s FROM StatusPartido s WHERE s.id = :id"),
    @NamedQuery(name = "StatusPartido.findByValue", query = "SELECT s FROM StatusPartido s WHERE s.value = :value"),
    @NamedQuery(name = "StatusPartido.findByNombre", query = "SELECT s FROM StatusPartido s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "StatusPartido.findByStatus", query = "SELECT s FROM StatusPartido s WHERE s.status = :status")})
public class StatusPartido implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusPartidoId")
    private Collection<Partido> partidoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "value")
    private Integer value;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    public StatusPartido() {
    }

    public StatusPartido(Integer id) {
        this.id = id;
    }

    public StatusPartido(Integer id, int status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof StatusPartido)) {
            return false;
        }
        StatusPartido other = (StatusPartido) object;
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
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }
    
}
