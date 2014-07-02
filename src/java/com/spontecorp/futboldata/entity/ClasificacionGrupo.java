/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "clasificacion_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionGrupo.findAll", query = "SELECT c FROM ClasificacionGrupo c"),
    @NamedQuery(name = "ClasificacionGrupo.findById", query = "SELECT c FROM ClasificacionGrupo c WHERE c.id = :id"),
    @NamedQuery(name = "ClasificacionGrupo.findByJJugados", query = "SELECT c FROM ClasificacionGrupo c WHERE c.jJugados = :jJugados"),
    @NamedQuery(name = "ClasificacionGrupo.findByJGanados", query = "SELECT c FROM ClasificacionGrupo c WHERE c.jGanados = :jGanados"),
    @NamedQuery(name = "ClasificacionGrupo.findByJEmpatados", query = "SELECT c FROM ClasificacionGrupo c WHERE c.jEmpatados = :jEmpatados"),
    @NamedQuery(name = "ClasificacionGrupo.findByJPerdidos", query = "SELECT c FROM ClasificacionGrupo c WHERE c.jPerdidos = :jPerdidos"),
    @NamedQuery(name = "ClasificacionGrupo.findByGolesFavor", query = "SELECT c FROM ClasificacionGrupo c WHERE c.golesFavor = :golesFavor"),
    @NamedQuery(name = "ClasificacionGrupo.findByGolesContra", query = "SELECT c FROM ClasificacionGrupo c WHERE c.golesContra = :golesContra"),
    @NamedQuery(name = "ClasificacionGrupo.findByDiferencia", query = "SELECT c FROM ClasificacionGrupo c WHERE c.diferencia = :diferencia"),
    @NamedQuery(name = "ClasificacionGrupo.findByPuntos", query = "SELECT c FROM ClasificacionGrupo c WHERE c.puntos = :puntos"),
    @NamedQuery(name = "ClasificacionGrupo.findByIsLocal", query = "SELECT c FROM ClasificacionGrupo c WHERE c.isLocal = :isLocal"),
    @NamedQuery(name = "ClasificacionGrupo.findByStatus", query = "SELECT c FROM ClasificacionGrupo c WHERE c.status = :status")})
public class ClasificacionGrupo implements Serializable {
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
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoId;
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grupo grupoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clasificacionGrupoId")
    private Collection<Clasificacion> clasificacionCollection;

    public ClasificacionGrupo() {
    }

    public ClasificacionGrupo(Integer id) {
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

    public Grupo getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Grupo grupoId) {
        this.grupoId = grupoId;
    }

    @XmlTransient
    public Collection<Clasificacion> getClasificacionCollection() {
        return clasificacionCollection;
    }

    public void setClasificacionCollection(Collection<Clasificacion> clasificacionCollection) {
        this.clasificacionCollection = clasificacionCollection;
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
        if (!(object instanceof ClasificacionGrupo)) {
            return false;
        }
        ClasificacionGrupo other = (ClasificacionGrupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.ClasificacionGrupo[ id=" + id + " ]";
    }
    
}
