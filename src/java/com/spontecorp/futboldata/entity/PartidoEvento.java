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
import javax.persistence.Lob;
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
@Table(name = "partido_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartidoEvento.findAll", query = "SELECT p FROM PartidoEvento p"),
    @NamedQuery(name = "PartidoEvento.findById", query = "SELECT p FROM PartidoEvento p WHERE p.id = :id"),
    @NamedQuery(name = "PartidoEvento.findByMinuto", query = "SELECT p FROM PartidoEvento p WHERE p.minuto = :minuto")})
public class PartidoEvento implements Serializable {
    @JoinColumn(name = "convocado_id", referencedColumnName = "id")
    @ManyToOne
    private Convocado convocadoId;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "minuto")
    private Integer minuto;
    @JoinColumn(name = "jugador1_id", referencedColumnName = "id")
    @ManyToOne
    private Jugador jugador1Id;
    @JoinColumn(name = "jugador2_id", referencedColumnName = "id")
    @ManyToOne
    private Jugador jugador2Id;
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @ManyToOne
    private Staff staffId;
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Evento eventoId;
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partido partidoId;

    public PartidoEvento() {
    }

    public PartidoEvento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Jugador getJugador1Id() {
        return jugador1Id;
    }

    public void setJugador1Id(Jugador jugador1Id) {
        this.jugador1Id = jugador1Id;
    }

    public Jugador getJugador2Id() {
        return jugador2Id;
    }

    public void setJugador2Id(Jugador jugador2Id) {
        this.jugador2Id = jugador2Id;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
    }

    public Partido getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Partido partidoId) {
        this.partidoId = partidoId;
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
        if (!(object instanceof PartidoEvento)) {
            return false;
        }
        PartidoEvento other = (PartidoEvento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.PartidoEvento[ id=" + id + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Convocado getConvocadoId() {
        return convocadoId;
    }

    public void setConvocadoId(Convocado convocadoId) {
        this.convocadoId = convocadoId;
    }
    
}
