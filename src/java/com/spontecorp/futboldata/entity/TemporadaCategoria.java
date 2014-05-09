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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "temporada_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TemporadaCategoria.findAll", query = "SELECT t FROM TemporadaCategoria t"),
    @NamedQuery(name = "TemporadaCategoria.findById", query = "SELECT t FROM TemporadaCategoria t WHERE t.temporadaCategoriaPK.id = :id"),
    @NamedQuery(name = "TemporadaCategoria.findByTemporadaId", query = "SELECT t FROM TemporadaCategoria t WHERE t.temporadaCategoriaPK.temporadaId = :temporadaId"),
    @NamedQuery(name = "TemporadaCategoria.findByCategoriaId", query = "SELECT t FROM TemporadaCategoria t WHERE t.temporadaCategoriaPK.categoriaId = :categoriaId"),
    @NamedQuery(name = "TemporadaCategoria.findByAlias", query = "SELECT t FROM TemporadaCategoria t WHERE t.alias = :alias"),
    @NamedQuery(name = "TemporadaCategoria.findByStatus", query = "SELECT t FROM TemporadaCategoria t WHERE t.status = :status")})
public class TemporadaCategoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TemporadaCategoriaPK temporadaCategoriaPK;
    @Size(max = 45)
    @Column(name = "alias")
    private String alias;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "temporada_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Temporada temporada;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categoria categoria;

    public TemporadaCategoria() {
    }

    public TemporadaCategoria(TemporadaCategoriaPK temporadaCategoriaPK) {
        this.temporadaCategoriaPK = temporadaCategoriaPK;
    }

    public TemporadaCategoria(int id, int temporadaId, int categoriaId) {
        this.temporadaCategoriaPK = new TemporadaCategoriaPK(id, temporadaId, categoriaId);
    }

    public TemporadaCategoriaPK getTemporadaCategoriaPK() {
        return temporadaCategoriaPK;
    }

    public void setTemporadaCategoriaPK(TemporadaCategoriaPK temporadaCategoriaPK) {
        this.temporadaCategoriaPK = temporadaCategoriaPK;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temporadaCategoriaPK != null ? temporadaCategoriaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemporadaCategoria)) {
            return false;
        }
        TemporadaCategoria other = (TemporadaCategoria) object;
        if ((this.temporadaCategoriaPK == null && other.temporadaCategoriaPK != null) || (this.temporadaCategoriaPK != null && !this.temporadaCategoriaPK.equals(other.temporadaCategoriaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.TemporadaCategoria[ temporadaCategoriaPK=" + temporadaCategoriaPK + " ]";
    }
    
}
