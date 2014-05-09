/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sponte03
 */
@Embeddable
public class EquipoCanchaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipo_id")
    private int equipoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancha_id")
    private int canchaId;

    public EquipoCanchaPK() {
    }

    public EquipoCanchaPK(int id, int equipoId, int canchaId) {
        this.id = id;
        this.equipoId = equipoId;
        this.canchaId = canchaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public int getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(int canchaId) {
        this.canchaId = canchaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) equipoId;
        hash += (int) canchaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoCanchaPK)) {
            return false;
        }
        EquipoCanchaPK other = (EquipoCanchaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.equipoId != other.equipoId) {
            return false;
        }
        if (this.canchaId != other.canchaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.EquipoCanchaPK[ id=" + id + ", equipoId=" + equipoId + ", canchaId=" + canchaId + " ]";
    }
    
}
