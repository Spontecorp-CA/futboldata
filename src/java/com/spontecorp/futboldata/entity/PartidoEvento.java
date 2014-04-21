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
@Table(name = "partido_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartidoEvento.findAll", query = "SELECT p FROM PartidoEvento p"),
    @NamedQuery(name = "PartidoEvento.findById", query = "SELECT p FROM PartidoEvento p WHERE p.partidoEventoPK.id = :id"),
    @NamedQuery(name = "PartidoEvento.findByPartidoId", query = "SELECT p FROM PartidoEvento p WHERE p.partidoEventoPK.partidoId = :partidoId"),
    @NamedQuery(name = "PartidoEvento.findByEventoId", query = "SELECT p FROM PartidoEvento p WHERE p.partidoEventoPK.eventoId = :eventoId"),
    @NamedQuery(name = "PartidoEvento.findByMinuto", query = "SELECT p FROM PartidoEvento p WHERE p.minuto = :minuto")})
public class PartidoEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PartidoEventoPK partidoEventoPK;
    @Column(name = "minuto")
    private Integer minuto;
    @JoinColumn(name = "partido_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partido partido;
    @JoinColumn(name = "evento_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento;
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @ManyToOne
    private Staff staffId;
    @JoinColumn(name = "jugador2_id", referencedColumnName = "id")
    @ManyToOne
    private Jugador jugador2Id;
    @JoinColumn(name = "jugador1_id", referencedColumnName = "id")
    @ManyToOne
    private Jugador jugador1Id;

    public PartidoEvento() {
    }

    public PartidoEvento(PartidoEventoPK partidoEventoPK) {
        this.partidoEventoPK = partidoEventoPK;
    }

    public PartidoEvento(int id, int partidoId, int eventoId) {
        this.partidoEventoPK = new PartidoEventoPK(id, partidoId, eventoId);
    }

    public PartidoEventoPK getPartidoEventoPK() {
        return partidoEventoPK;
    }

    public void setPartidoEventoPK(PartidoEventoPK partidoEventoPK) {
        this.partidoEventoPK = partidoEventoPK;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    public Jugador getJugador2Id() {
        return jugador2Id;
    }

    public void setJugador2Id(Jugador jugador2Id) {
        this.jugador2Id = jugador2Id;
    }

    public Jugador getJugador1Id() {
        return jugador1Id;
    }

    public void setJugador1Id(Jugador jugador1Id) {
        this.jugador1Id = jugador1Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partidoEventoPK != null ? partidoEventoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidoEvento)) {
            return false;
        }
        PartidoEvento other = (PartidoEvento) object;
        if ((this.partidoEventoPK == null && other.partidoEventoPK != null) || (this.partidoEventoPK != null && !this.partidoEventoPK.equals(other.partidoEventoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.PartidoEvento[ partidoEventoPK=" + partidoEventoPK + " ]";
    }
    
}
