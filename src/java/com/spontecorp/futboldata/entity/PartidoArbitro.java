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
@Table(name = "partido_arbitro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartidoArbitro.findAll", query = "SELECT p FROM PartidoArbitro p"),
    @NamedQuery(name = "PartidoArbitro.findById", query = "SELECT p FROM PartidoArbitro p WHERE p.id = :id")})
public class PartidoArbitro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "arbitro_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Arbitro arbitroId;
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partido partidoId;
    @JoinColumn(name = "tipo_arbitro_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoArbitro tipoArbitroId;

    public PartidoArbitro() {
    }

    public PartidoArbitro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Arbitro getArbitroId() {
        return arbitroId;
    }

    public void setArbitroId(Arbitro arbitroId) {
        this.arbitroId = arbitroId;
    }

    public Partido getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Partido partidoId) {
        this.partidoId = partidoId;
    }

    public TipoArbitro getTipoArbitroId() {
        return tipoArbitroId;
    }

    public void setTipoArbitroId(TipoArbitro tipoArbitroId) {
        this.tipoArbitroId = tipoArbitroId;
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
        if (!(object instanceof PartidoArbitro)) {
            return false;
        }
        PartidoArbitro other = (PartidoArbitro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.PartidoArbitro[ id=" + id + " ]";
    }
    
}
