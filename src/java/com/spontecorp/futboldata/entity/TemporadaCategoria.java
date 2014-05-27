/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
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
    @NamedQuery(name = "TemporadaCategoria.findById", query = "SELECT t FROM TemporadaCategoria t WHERE t.id = :id"),
    @NamedQuery(name = "TemporadaCategoria.findByAlias", query = "SELECT t FROM TemporadaCategoria t WHERE t.alias = :alias"),
    @NamedQuery(name = "TemporadaCategoria.findByStatus", query = "SELECT t FROM TemporadaCategoria t WHERE t.status = :status")})
public class TemporadaCategoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "alias")
    private String alias;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria categoriaId;
    @JoinColumn(name = "temporada_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Temporada temporadaId;

    public TemporadaCategoria() {
    }

    public TemporadaCategoria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Temporada getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(Temporada temporadaId) {
        this.temporadaId = temporadaId;
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
        if (!(object instanceof TemporadaCategoria)) {
            return false;
        }
        TemporadaCategoria other = (TemporadaCategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return categoriaId.getNombre();
    }
    
}
