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
@Table(name = "convocado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convocado.findAll", query = "SELECT c FROM Convocado c"),
    @NamedQuery(name = "Convocado.findById", query = "SELECT c FROM Convocado c WHERE c.id = :id"),
    @NamedQuery(name = "Convocado.findByCamiseta", query = "SELECT c FROM Convocado c WHERE c.camiseta = :camiseta"),
    @NamedQuery(name = "Convocado.findByTitular", query = "SELECT c FROM Convocado c WHERE c.titular = :titular"),
    @NamedQuery(name = "Convocado.findByTiempoJugado", query = "SELECT c FROM Convocado c WHERE c.tiempoJugado = :tiempoJugado")})
public class Convocado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "camiseta")
    private Integer camiseta;
    @Column(name = "titular")
    private Integer titular;
    @Column(name = "tiempo_jugado")
    @Temporal(TemporalType.TIME)
    private Date tiempoJugado;
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jugador jugadorId;
    @JoinColumn(name = "posicion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Posicion posicionId;
    @JoinColumn(name = "convocatoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Convocatoria convocatoriaId;

    public Convocado() {
    }

    public Convocado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCamiseta() {
        return camiseta;
    }

    public void setCamiseta(Integer camiseta) {
        this.camiseta = camiseta;
    }

    public Integer getTitular() {
        return titular;
    }

    public void setTitular(Integer titular) {
        this.titular = titular;
    }

    public Date getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(Date tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    public Jugador getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Jugador jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Posicion getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(Posicion posicionId) {
        this.posicionId = posicionId;
    }

    public Convocatoria getConvocatoriaId() {
        return convocatoriaId;
    }

    public void setConvocatoriaId(Convocatoria convocatoriaId) {
        this.convocatoriaId = convocatoriaId;
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
        if (!(object instanceof Convocado)) {
            return false;
        }
        Convocado other = (Convocado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(jugadorId == null){
                   return "com.spontecorp.futboldata.entity.Convocado[ id=" + id + " ]"; 
        }else {
            return jugadorId.getPersonaId().toString();
        }

    }
    
}
