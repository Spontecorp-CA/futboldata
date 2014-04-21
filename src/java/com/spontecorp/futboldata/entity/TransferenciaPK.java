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
public class TransferenciaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jugador_id")
    private int jugadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipo_provedor_id")
    private int equipoProvedorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "equipo_receptor_id")
    private int equipoReceptorId;

    public TransferenciaPK() {
    }

    public TransferenciaPK(int id, int jugadorId, int equipoProvedorId, int equipoReceptorId) {
        this.id = id;
        this.jugadorId = jugadorId;
        this.equipoProvedorId = equipoProvedorId;
        this.equipoReceptorId = equipoReceptorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public int getEquipoProvedorId() {
        return equipoProvedorId;
    }

    public void setEquipoProvedorId(int equipoProvedorId) {
        this.equipoProvedorId = equipoProvedorId;
    }

    public int getEquipoReceptorId() {
        return equipoReceptorId;
    }

    public void setEquipoReceptorId(int equipoReceptorId) {
        this.equipoReceptorId = equipoReceptorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) jugadorId;
        hash += (int) equipoProvedorId;
        hash += (int) equipoReceptorId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferenciaPK)) {
            return false;
        }
        TransferenciaPK other = (TransferenciaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.jugadorId != other.jugadorId) {
            return false;
        }
        if (this.equipoProvedorId != other.equipoProvedorId) {
            return false;
        }
        if (this.equipoReceptorId != other.equipoReceptorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.TransferenciaPK[ id=" + id + ", jugadorId=" + jugadorId + ", equipoProvedorId=" + equipoProvedorId + ", equipoReceptorId=" + equipoReceptorId + " ]";
    }
    
}
