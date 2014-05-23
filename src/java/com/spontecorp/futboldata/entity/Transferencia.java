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
@Table(name = "transferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t"),
    @NamedQuery(name = "Transferencia.findById", query = "SELECT t FROM Transferencia t WHERE t.id = :id"),
    @NamedQuery(name = "Transferencia.findByFechaIngreso", query = "SELECT t FROM Transferencia t WHERE t.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Transferencia.findByFechaEgreso", query = "SELECT t FROM Transferencia t WHERE t.fechaEgreso = :fechaEgreso"),
    @NamedQuery(name = "Transferencia.findByAltaBaja", query = "SELECT t FROM Transferencia t WHERE t.altaBaja = :altaBaja")})
public class Transferencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.DATE)
    private Date fechaEgreso;
    @Column(name = "alta_baja")
    private Integer altaBaja;
    @JoinColumn(name = "equipo_provedor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoProvedorId;
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jugador jugadorId;
    @JoinColumn(name = "equipo_receptor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoReceptorId;

    public Transferencia() {
    }

    public Transferencia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Integer getAltaBaja() {
        return altaBaja;
    }

    public void setAltaBaja(Integer altaBaja) {
        this.altaBaja = altaBaja;
    }

    public Equipo getEquipoProvedorId() {
        return equipoProvedorId;
    }

    public void setEquipoProvedorId(Equipo equipoProvedorId) {
        this.equipoProvedorId = equipoProvedorId;
    }

    public Jugador getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Jugador jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Equipo getEquipoReceptorId() {
        return equipoReceptorId;
    }

    public void setEquipoReceptorId(Equipo equipoReceptorId) {
        this.equipoReceptorId = equipoReceptorId;
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
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Transferencia[ id=" + id + " ]";
    }
    
}
