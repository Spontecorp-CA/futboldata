/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "partido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partido.findAll", query = "SELECT p FROM Partido p"),
    @NamedQuery(name = "Partido.findById", query = "SELECT p FROM Partido p WHERE p.id = :id"),
    @NamedQuery(name = "Partido.findByFecha", query = "SELECT p FROM Partido p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Partido.findByHoraInicio", query = "SELECT p FROM Partido p WHERE p.horaInicio = :horaInicio"),
    @NamedQuery(name = "Partido.findByHoraFin", query = "SELECT p FROM Partido p WHERE p.horaFin = :horaFin"),
    @NamedQuery(name = "Partido.findByEquipoLocalText", query = "SELECT p FROM Partido p WHERE p.equipoLocalText = :equipoLocalText"),
    @NamedQuery(name = "Partido.findByEquipoVisitanteText", query = "SELECT p FROM Partido p WHERE p.equipoVisitanteText = :equipoVisitanteText"),
    @NamedQuery(name = "Partido.findByTxTv", query = "SELECT p FROM Partido p WHERE p.txTv = :txTv"),
    @NamedQuery(name = "Partido.findByTxRadio", query = "SELECT p FROM Partido p WHERE p.txRadio = :txRadio"),
    @NamedQuery(name = "Partido.findByAsistencia", query = "SELECT p FROM Partido p WHERE p.asistencia = :asistencia"),
    @NamedQuery(name = "Partido.findByGolesEquipoLocal", query = "SELECT p FROM Partido p WHERE p.golesEquipoLocal = :golesEquipoLocal"),
    @NamedQuery(name = "Partido.findByGolesEquipoVisitante", query = "SELECT p FROM Partido p WHERE p.golesEquipoVisitante = :golesEquipoVisitante"),
    @NamedQuery(name = "Partido.findByGolesLocalProrroga", query = "SELECT p FROM Partido p WHERE p.golesLocalProrroga = :golesLocalProrroga"),
    @NamedQuery(name = "Partido.findByGolesVisitanteProrroga", query = "SELECT p FROM Partido p WHERE p.golesVisitanteProrroga = :golesVisitanteProrroga"),
    @NamedQuery(name = "Partido.findByGolesLocalPenalties", query = "SELECT p FROM Partido p WHERE p.golesLocalPenalties = :golesLocalPenalties"),
    @NamedQuery(name = "Partido.findByGolesVisitantePenalties", query = "SELECT p FROM Partido p WHERE p.golesVisitantePenalties = :golesVisitantePenalties")})
public class Partido implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partidoId")
    private Collection<PartidoEventoEquipo> partidoEventoEquipoCollection;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne
    private Categoria categoriaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partidoId")
    private Collection<Clasificacion> clasificacionCollection;
    @Column(name = "numero")
    private Integer numero;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Lob
    @Size(max = 65535)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 45)
    @Column(name = "equipo_local_text")
    private String equipoLocalText;
    @Size(max = 45)
    @Column(name = "equipo_visitante_text")
    private String equipoVisitanteText;
    @Column(name = "tx_tv")
    private Integer txTv;
    @Column(name = "tx_radio")
    private Integer txRadio;
    @Column(name = "asistencia")
    private Integer asistencia;
    @Column(name = "goles_equipo_local")
    private Integer golesEquipoLocal;
    @Column(name = "goles_equipo_visitante")
    private Integer golesEquipoVisitante;
    @Column(name = "goles_local_prorroga")
    private Integer golesLocalProrroga;
    @Column(name = "goles_visitante_prorroga")
    private Integer golesVisitanteProrroga;
    @Column(name = "goles_local_penalties")
    private Integer golesLocalPenalties;
    @Column(name = "goles_visitante_penalties")
    private Integer golesVisitantePenalties;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partidoId")
    private Collection<Convocatoria> convocatoriaCollection;
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    @ManyToOne
    private Cancha canchaId;
    @JoinColumn(name = "equipo_local_id", referencedColumnName = "id")
    @ManyToOne
    private Equipo equipoLocalId;
    @JoinColumn(name = "equipo_visitante_id", referencedColumnName = "id")
    @ManyToOne
    private Equipo equipoVisitanteId;
    @JoinColumn(name = "jornada_id", referencedColumnName = "id")
    @ManyToOne
    private Jornada jornadaId;
    @JoinColumn(name = "llave_id", referencedColumnName = "id")
    @ManyToOne
    private Llave llaveId;
    @JoinColumn(name = "status_partido_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StatusPartido statusPartidoId;
    @OneToMany(mappedBy = "partidoId")
    private Collection<Staff> staffCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partidoId")
    private Collection<PartidoEvento> partidoEventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partidoId")
    private Collection<PartidoArbitro> partidoArbitroCollection;

    public Partido() {
    }

    public Partido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEquipoLocalText() {
        return equipoLocalText;
    }

    public void setEquipoLocalText(String equipoLocalText) {
        this.equipoLocalText = equipoLocalText;
    }

    public String getEquipoVisitanteText() {
        return equipoVisitanteText;
    }

    public void setEquipoVisitanteText(String equipoVisitanteText) {
        this.equipoVisitanteText = equipoVisitanteText;
    }

    public Integer getTxTv() {
        return txTv;
    }

    public void setTxTv(Integer txTv) {
        this.txTv = txTv;
    }

    public Integer getTxRadio() {
        return txRadio;
    }

    public void setTxRadio(Integer txRadio) {
        this.txRadio = txRadio;
    }

    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }

    public Integer getGolesEquipoLocal() {
        return golesEquipoLocal;
    }

    public void setGolesEquipoLocal(Integer golesEquipoLocal) {
        this.golesEquipoLocal = golesEquipoLocal;
    }

    public Integer getGolesEquipoVisitante() {
        return golesEquipoVisitante;
    }

    public void setGolesEquipoVisitante(Integer golesEquipoVisitante) {
        this.golesEquipoVisitante = golesEquipoVisitante;
    }

    public Integer getGolesLocalProrroga() {
        return golesLocalProrroga;
    }

    public void setGolesLocalProrroga(Integer golesLocalProrroga) {
        this.golesLocalProrroga = golesLocalProrroga;
    }

    public Integer getGolesVisitanteProrroga() {
        return golesVisitanteProrroga;
    }

    public void setGolesVisitanteProrroga(Integer golesVisitanteProrroga) {
        this.golesVisitanteProrroga = golesVisitanteProrroga;
    }

    public Integer getGolesLocalPenalties() {
        return golesLocalPenalties;
    }

    public void setGolesLocalPenalties(Integer golesLocalPenalties) {
        this.golesLocalPenalties = golesLocalPenalties;
    }

    public Integer getGolesVisitantePenalties() {
        return golesVisitantePenalties;
    }

    public void setGolesVisitantePenalties(Integer golesVisitantePenalties) {
        this.golesVisitantePenalties = golesVisitantePenalties;
    }

    @XmlTransient
    public Collection<Convocatoria> getConvocatoriaCollection() {
        return convocatoriaCollection;
    }

    public void setConvocatoriaCollection(Collection<Convocatoria> convocatoriaCollection) {
        this.convocatoriaCollection = convocatoriaCollection;
    }

    public Cancha getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(Cancha canchaId) {
        this.canchaId = canchaId;
    }

    public Equipo getEquipoLocalId() {
        return equipoLocalId;
    }

    public void setEquipoLocalId(Equipo equipoLocalId) {
        this.equipoLocalId = equipoLocalId;
    }

    public Equipo getEquipoVisitanteId() {
        return equipoVisitanteId;
    }

    public void setEquipoVisitanteId(Equipo equipoVisitanteId) {
        this.equipoVisitanteId = equipoVisitanteId;
    }

    public Jornada getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(Jornada jornadaId) {
        this.jornadaId = jornadaId;
    }

    public Llave getLlaveId() {
        return llaveId;
    }

    public void setLlaveId(Llave llaveId) {
        this.llaveId = llaveId;
    }

    public StatusPartido getStatusPartidoId() {
        return statusPartidoId;
    }

    public void setStatusPartidoId(StatusPartido statusPartidoId) {
        this.statusPartidoId = statusPartidoId;
    }

    @XmlTransient
    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
    }

    @XmlTransient
    public Collection<PartidoEvento> getPartidoEventoCollection() {
        return partidoEventoCollection;
    }

    public void setPartidoEventoCollection(Collection<PartidoEvento> partidoEventoCollection) {
        this.partidoEventoCollection = partidoEventoCollection;
    }

    @XmlTransient
    public Collection<PartidoArbitro> getPartidoArbitroCollection() {
        return partidoArbitroCollection;
    }

    public void setPartidoArbitroCollection(Collection<PartidoArbitro> partidoArbitroCollection) {
        this.partidoArbitroCollection = partidoArbitroCollection;
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
        if (!(object instanceof Partido)) {
            return false;
        }
        Partido other = (Partido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Partido[ id=" + id + " ]";
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    @XmlTransient
    public Collection<Clasificacion> getClasificacionCollection() {
        return clasificacionCollection;
    }

    public void setClasificacionCollection(Collection<Clasificacion> clasificacionCollection) {
        this.clasificacionCollection = clasificacionCollection;
    }

    @XmlTransient
    public Collection<PartidoEventoEquipo> getPartidoEventoEquipoCollection() {
        return partidoEventoEquipoCollection;
    }

    public void setPartidoEventoEquipoCollection(Collection<PartidoEventoEquipo> partidoEventoEquipoCollection) {
        this.partidoEventoEquipoCollection = partidoEventoEquipoCollection;
    }
    
}
