/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "equipo_in_liga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoInLiga.findAll", query = "SELECT e FROM EquipoInLiga e"),
    @NamedQuery(name = "EquipoInLiga.findById", query = "SELECT e FROM EquipoInLiga e WHERE e.id = :id")})
public class EquipoInLiga implements Serializable {
    @Column(name = "status")
    private Integer status;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "competicion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Competicion competicionId;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoId;

    public EquipoInLiga() {
    }

    public EquipoInLiga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Competicion getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(Competicion competicionId) {
        this.competicionId = competicionId;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
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
        if (!(object instanceof EquipoInLiga)) {
            return false;
        }
        EquipoInLiga other = (EquipoInLiga) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.EquipoInLiga[ id=" + id + " ]";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
