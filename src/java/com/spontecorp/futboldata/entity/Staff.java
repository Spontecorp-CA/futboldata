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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findById", query = "SELECT s FROM Staff s WHERE s.id = :id"),
    @NamedQuery(name = "Staff.findByStatus", query = "SELECT s FROM Staff s WHERE s.status = :status"),
    @NamedQuery(name = "Staff.findByFechaDesde", query = "SELECT s FROM Staff s WHERE s.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Staff.findByFechaHasta", query = "SELECT s FROM Staff s WHERE s.fechaHasta = :fechaHasta")})
public class Staff implements Serializable {

    @Column(name = "organizacion_id")
    private Integer organizacionId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private Integer status;
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @ManyToMany(mappedBy = "staffCollection")
    private Collection<Premio> premioCollection;
    @JoinTable(name = "staff_has_titulo", joinColumns = {
        @JoinColumn(name = "staff_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "titulo_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Titulo> tituloCollection;
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    @ManyToOne
    private Cargo cargoId;
    @JoinColumn(name = "agente_id", referencedColumnName = "id")
    @ManyToOne
    private Agente agenteId;
    @JoinColumn(name = "asociacion_id", referencedColumnName = "id")
    @ManyToOne
    private Asociacion asociacionId;
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @ManyToOne
    private Club clubId;
    @JoinColumn(name = "competicion_id", referencedColumnName = "id")
    @ManyToOne
    private Competicion competicionId;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne
    private Equipo equipoId;
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    @ManyToOne
    private Partido partidoId;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Persona personaId;
    @OneToMany(mappedBy = "staffId")
    private Collection<PartidoEvento> partidoEventoCollection;

    public Staff() {
    }

    public Staff(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    @XmlTransient
    public Collection<Premio> getPremioCollection() {
        return premioCollection;
    }

    public void setPremioCollection(Collection<Premio> premioCollection) {
        this.premioCollection = premioCollection;
    }

    @XmlTransient
    public Collection<Titulo> getTituloCollection() {
        return tituloCollection;
    }

    public void setTituloCollection(Collection<Titulo> tituloCollection) {
        this.tituloCollection = tituloCollection;
    }

    public Cargo getCargoId() {
        return cargoId;
    }

    public void setCargoId(Cargo cargoId) {
        this.cargoId = cargoId;
    }

    public Agente getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(Agente agenteId) {
        this.agenteId = agenteId;
    }

    public Asociacion getAsociacionId() {
        return asociacionId;
    }

    public void setAsociacionId(Asociacion asociacionId) {
        this.asociacionId = asociacionId;
    }

    public Club getClubId() {
        return clubId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
    }

    public Competicion getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(Competicion competicionId) {
        this.competicionId = competicionId;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
    }

    public Partido getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Partido partidoId) {
        this.partidoId = partidoId;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    @XmlTransient
    public Collection<PartidoEvento> getPartidoEventoCollection() {
        return partidoEventoCollection;
    }

    public void setPartidoEventoCollection(Collection<PartidoEvento> partidoEventoCollection) {
        this.partidoEventoCollection = partidoEventoCollection;
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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return personaId.getNombre() + " " + personaId.getApellido();
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }
}
