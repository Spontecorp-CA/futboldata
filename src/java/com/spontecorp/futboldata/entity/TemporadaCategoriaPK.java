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
public class TemporadaCategoriaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temporada_id")
    private int temporadaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "categoria_id")
    private int categoriaId;

    public TemporadaCategoriaPK() {
    }

    public TemporadaCategoriaPK(int id, int temporadaId, int categoriaId) {
        this.id = id;
        this.temporadaId = temporadaId;
        this.categoriaId = categoriaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(int temporadaId) {
        this.temporadaId = temporadaId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) temporadaId;
        hash += (int) categoriaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemporadaCategoriaPK)) {
            return false;
        }
        TemporadaCategoriaPK other = (TemporadaCategoriaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.temporadaId != other.temporadaId) {
            return false;
        }
        if (this.categoriaId != other.categoriaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.TemporadaCategoriaPK[ id=" + id + ", temporadaId=" + temporadaId + ", categoriaId=" + categoriaId + " ]";
    }
    
}
