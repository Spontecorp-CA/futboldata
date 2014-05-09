/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
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
@Table(name = "partido_arbitro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartidoArbitro.findAll", query = "SELECT p FROM PartidoArbitro p"),
    @NamedQuery(name = "PartidoArbitro.findById", query = "SELECT p FROM PartidoArbitro p WHERE p.partidoArbitroPK.id = :id"),
    @NamedQuery(name = "PartidoArbitro.findByPartidoId", query = "SELECT p FROM PartidoArbitro p WHERE p.partidoArbitroPK.partidoId = :partidoId"),
    @NamedQuery(name = "PartidoArbitro.findByArbitroId", query = "SELECT p FROM PartidoArbitro p WHERE p.partidoArbitroPK.arbitroId = :arbitroId")})
public class PartidoArbitro implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PartidoArbitroPK partidoArbitroPK;
    @JoinColumn(name = "partido_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partido partido;
    @JoinColumn(name = "arbitro_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Arbitro arbitro;
    @JoinColumn(name = "tipo_arbitro_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoArbitro tipoArbitroId;

    public PartidoArbitro() {
    }

    public PartidoArbitro(PartidoArbitroPK partidoArbitroPK) {
        this.partidoArbitroPK = partidoArbitroPK;
    }

    public PartidoArbitro(int id, int partidoId, int arbitroId) {
        this.partidoArbitroPK = new PartidoArbitroPK(id, partidoId, arbitroId);
    }

    public PartidoArbitroPK getPartidoArbitroPK() {
        return partidoArbitroPK;
    }

    public void setPartidoArbitroPK(PartidoArbitroPK partidoArbitroPK) {
        this.partidoArbitroPK = partidoArbitroPK;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
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
        hash += (partidoArbitroPK != null ? partidoArbitroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidoArbitro)) {
            return false;
        }
        PartidoArbitro other = (PartidoArbitro) object;
        if ((this.partidoArbitroPK == null && other.partidoArbitroPK != null) || (this.partidoArbitroPK != null && !this.partidoArbitroPK.equals(other.partidoArbitroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.PartidoArbitro[ partidoArbitroPK=" + partidoArbitroPK + " ]";
    }
    
}
