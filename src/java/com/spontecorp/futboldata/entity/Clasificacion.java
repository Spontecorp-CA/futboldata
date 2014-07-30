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
@Table(name = "clasificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificacion.findAll", query = "SELECT c FROM Clasificacion c"),
    @NamedQuery(name = "Clasificacion.findById", query = "SELECT c FROM Clasificacion c WHERE c.id = :id"),
    @NamedQuery(name = "Clasificacion.findByJJugados", query = "SELECT c FROM Clasificacion c WHERE c.jJugados = :jJugados"),
    @NamedQuery(name = "Clasificacion.findByJGanados", query = "SELECT c FROM Clasificacion c WHERE c.jGanados = :jGanados"),
    @NamedQuery(name = "Clasificacion.findByJEmpatados", query = "SELECT c FROM Clasificacion c WHERE c.jEmpatados = :jEmpatados"),
    @NamedQuery(name = "Clasificacion.findByJPerdidos", query = "SELECT c FROM Clasificacion c WHERE c.jPerdidos = :jPerdidos"),
    @NamedQuery(name = "Clasificacion.findByGolesFavor", query = "SELECT c FROM Clasificacion c WHERE c.golesFavor = :golesFavor"),
    @NamedQuery(name = "Clasificacion.findByGolesContra", query = "SELECT c FROM Clasificacion c WHERE c.golesContra = :golesContra"),
    @NamedQuery(name = "Clasificacion.findByDiferencia", query = "SELECT c FROM Clasificacion c WHERE c.diferencia = :diferencia"),
    @NamedQuery(name = "Clasificacion.findByPuntos", query = "SELECT c FROM Clasificacion c WHERE c.puntos = :puntos"),
    @NamedQuery(name = "Clasificacion.findByIsLocal", query = "SELECT c FROM Clasificacion c WHERE c.isLocal = :isLocal")})
public class Clasificacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "j_jugados")
    private Integer jJugados;
    @Column(name = "j_ganados")
    private Integer jGanados;
    @Column(name = "j_empatados")
    private Integer jEmpatados;
    @Column(name = "j_perdidos")
    private Integer jPerdidos;
    @Column(name = "goles_favor")
    private Integer golesFavor;
    @Column(name = "goles_contra")
    private Integer golesContra;
    @Column(name = "diferencia")
    private Integer diferencia;
    @Column(name = "puntos")
    private Integer puntos;
    @Column(name = "is_local")
    private Integer isLocal;
    @JoinColumn(name = "jornada_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jornada jornadaId;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoId;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partido partidoId;

    public Clasificacion() {
    }

    public Clasificacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJJugados() {
        return jJugados;
    }

    public void setJJugados(Integer jJugados) {
        this.jJugados = jJugados;
    }

    public Integer getJGanados() {
        return jGanados;
    }

    public void setJGanados(Integer jGanados) {
        this.jGanados = jGanados;
    }

    public Integer getJEmpatados() {
        return jEmpatados;
    }

    public void setJEmpatados(Integer jEmpatados) {
        this.jEmpatados = jEmpatados;
    }

    public Integer getJPerdidos() {
        return jPerdidos;
    }

    public void setJPerdidos(Integer jPerdidos) {
        this.jPerdidos = jPerdidos;
    }

    public Integer getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(Integer golesFavor) {
        this.golesFavor = golesFavor;
    }

    public Integer getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(Integer golesContra) {
        this.golesContra = golesContra;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Integer diferencia) {
        this.diferencia = diferencia;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Integer isLocal) {
        this.isLocal = isLocal;
    }

    public Jornada getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(Jornada jornadaId) {
        this.jornadaId = jornadaId;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
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
        if (!(object instanceof Clasificacion)) {
            return false;
        }
        Clasificacion other = (Clasificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Clasificacion[ id=" + id + " ]";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Partido getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Partido partidoId) {
        this.partidoId = partidoId;
    }

}
