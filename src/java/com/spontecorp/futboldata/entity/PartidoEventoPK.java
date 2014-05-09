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
 * @author jgcastillo
 */
@Embeddable
public class PartidoEventoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "partido_id")
    private int partidoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "evento_id")
    private int eventoId;

    public PartidoEventoPK() {
    }

    public PartidoEventoPK(int id, int partidoId, int eventoId) {
        this.id = id;
        this.partidoId = partidoId;
        this.eventoId = eventoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(int partidoId) {
        this.partidoId = partidoId;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) partidoId;
        hash += (int) eventoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidoEventoPK)) {
            return false;
        }
        PartidoEventoPK other = (PartidoEventoPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.partidoId != other.partidoId) {
            return false;
        }
        if (this.eventoId != other.eventoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.PartidoEventoPK[ id=" + id + ", partidoId=" + partidoId + ", eventoId=" + eventoId + " ]";
    }
    
}
