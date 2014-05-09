/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "equipo_has_jugador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoHasJugador.findAll", query = "SELECT e FROM EquipoHasJugador e"),
    @NamedQuery(name = "EquipoHasJugador.findById", query = "SELECT e FROM EquipoHasJugador e WHERE e.id = :id"),
    @NamedQuery(name = "EquipoHasJugador.findByFechaEntrada", query = "SELECT e FROM EquipoHasJugador e WHERE e.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "EquipoHasJugador.findByFechaSalida", query = "SELECT e FROM EquipoHasJugador e WHERE e.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "EquipoHasJugador.findByStatus", query = "SELECT e FROM EquipoHasJugador e WHERE e.status = :status")})
public class EquipoHasJugador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jugador jugadorId;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoId;

    public EquipoHasJugador() {
    }

    public EquipoHasJugador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Jugador getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Jugador jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
 * @author sponte07
 */
@Entity
@Table(name = "equipo_has_jugador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoHasJugador.findAll", query = "SELECT e FROM EquipoHasJugador e"),
    @NamedQuery(name = "EquipoHasJugador.findById", query = "SELECT e FROM EquipoHasJugador e WHERE e.id = :id"),
    @NamedQuery(name = "EquipoHasJugador.findByFechaEntrada", query = "SELECT e FROM EquipoHasJugador e WHERE e.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "EquipoHasJugador.findByFechaSalida", query = "SELECT e FROM EquipoHasJugador e WHERE e.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "EquipoHasJugador.findByStatus", query = "SELECT e FROM EquipoHasJugador e WHERE e.status = :status")})
public class EquipoHasJugador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoId;
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jugador jugadorId;

    public EquipoHasJugador() {
    }

    public EquipoHasJugador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
    }

    public Jugador getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Jugador jugadorId) {
        this.jugadorId = jugadorId;
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
        if (!(object instanceof EquipoHasJugador)) {
            return false;
        }
        EquipoHasJugador other = (EquipoHasJugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.EquipoHasJugador[ id=" + id + " ]";
    }
    
}
