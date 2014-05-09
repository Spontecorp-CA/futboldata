/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author jgcastillo
 */
@Entity
@Table(name = "transferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t"),
    @NamedQuery(name = "Transferencia.findById", query = "SELECT t FROM Transferencia t WHERE t.transferenciaPK.id = :id"),
    @NamedQuery(name = "Transferencia.findByJugadorId", query = "SELECT t FROM Transferencia t WHERE t.transferenciaPK.jugadorId = :jugadorId"),
    @NamedQuery(name = "Transferencia.findByEquipoProvedorId", query = "SELECT t FROM Transferencia t WHERE t.transferenciaPK.equipoProvedorId = :equipoProvedorId"),
    @NamedQuery(name = "Transferencia.findByEquipoReceptorId", query = "SELECT t FROM Transferencia t WHERE t.transferenciaPK.equipoReceptorId = :equipoReceptorId"),
    @NamedQuery(name = "Transferencia.findByFechaIngreso", query = "SELECT t FROM Transferencia t WHERE t.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Transferencia.findByFechaEgreso", query = "SELECT t FROM Transferencia t WHERE t.fechaEgreso = :fechaEgreso"),
    @NamedQuery(name = "Transferencia.findByAltaBaja", query = "SELECT t FROM Transferencia t WHERE t.altaBaja = :altaBaja")})
public class Transferencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransferenciaPK transferenciaPK;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.DATE)
    private Date fechaEgreso;
    @Column(name = "alta_baja")
    private Integer altaBaja;
    @JoinColumn(name = "jugador_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Jugador jugador;
    @JoinColumn(name = "equipo_provedor_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;
    @JoinColumn(name = "equipo_receptor_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipo equipo1;

    public Transferencia() {
    }

    public Transferencia(TransferenciaPK transferenciaPK) {
        this.transferenciaPK = transferenciaPK;
    }

    public Transferencia(int id, int jugadorId, int equipoProvedorId, int equipoReceptorId) {
        this.transferenciaPK = new TransferenciaPK(id, jugadorId, equipoProvedorId, equipoReceptorId);
    }

    public TransferenciaPK getTransferenciaPK() {
        return transferenciaPK;
    }

    public void setTransferenciaPK(TransferenciaPK transferenciaPK) {
        this.transferenciaPK = transferenciaPK;
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

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transferenciaPK != null ? transferenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        if ((this.transferenciaPK == null && other.transferenciaPK != null) || (this.transferenciaPK != null && !this.transferenciaPK.equals(other.transferenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Transferencia[ transferenciaPK=" + transferenciaPK + " ]";
    }
    
}
