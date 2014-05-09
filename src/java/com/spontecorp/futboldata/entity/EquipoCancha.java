/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "equipo_cancha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoCancha.findAll", query = "SELECT e FROM EquipoCancha e"),
    @NamedQuery(name = "EquipoCancha.findById", query = "SELECT e FROM EquipoCancha e WHERE e.equipoCanchaPK.id = :id"),
    @NamedQuery(name = "EquipoCancha.findByEquipoId", query = "SELECT e FROM EquipoCancha e WHERE e.equipoCanchaPK.equipoId = :equipoId"),
    @NamedQuery(name = "EquipoCancha.findByCanchaId", query = "SELECT e FROM EquipoCancha e WHERE e.equipoCanchaPK.canchaId = :canchaId"),
    @NamedQuery(name = "EquipoCancha.findByStatus", query = "SELECT e FROM EquipoCancha e WHERE e.status = :status")})
public class EquipoCancha implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipoCanchaPK equipoCanchaPK;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;
    @JoinColumn(name = "cancha_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cancha cancha;

    public EquipoCancha() {
    }

    public EquipoCancha(EquipoCanchaPK equipoCanchaPK) {
        this.equipoCanchaPK = equipoCanchaPK;
    }

    public EquipoCancha(int id, int equipoId, int canchaId) {
        this.equipoCanchaPK = new EquipoCanchaPK(id, equipoId, canchaId);
    }

    public EquipoCanchaPK getEquipoCanchaPK() {
        return equipoCanchaPK;
    }

    public void setEquipoCanchaPK(EquipoCanchaPK equipoCanchaPK) {
        this.equipoCanchaPK = equipoCanchaPK;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoCanchaPK != null ? equipoCanchaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoCancha)) {
            return false;
        }
        EquipoCancha other = (EquipoCancha) object;
        if ((this.equipoCanchaPK == null && other.equipoCanchaPK != null) || (this.equipoCanchaPK != null && !this.equipoCanchaPK.equals(other.equipoCanchaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.EquipoCancha[ equipoCanchaPK=" + equipoCanchaPK + " ]";
    }
    
}
