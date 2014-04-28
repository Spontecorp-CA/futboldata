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
public class PartidoArbitroPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "partido_id")
    private int partidoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arbitro_id")
    private int arbitroId;

    public PartidoArbitroPK() {
    }

    public PartidoArbitroPK(int id, int partidoId, int arbitroId) {
        this.id = id;
        this.partidoId = partidoId;
        this.arbitroId = arbitroId;
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

    public int getArbitroId() {
        return arbitroId;
    }

    public void setArbitroId(int arbitroId) {
        this.arbitroId = arbitroId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) partidoId;
        hash += (int) arbitroId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidoArbitroPK)) {
            return false;
        }
        PartidoArbitroPK other = (PartidoArbitroPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.partidoId != other.partidoId) {
            return false;
        }
        if (this.arbitroId != other.arbitroId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.PartidoArbitroPK[ id=" + id + ", partidoId=" + partidoId + ", arbitroId=" + arbitroId + " ]";
    }
    
}
