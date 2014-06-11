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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "arbitro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arbitro.findAll", query = "SELECT a FROM Arbitro a"),
    @NamedQuery(name = "Arbitro.findById", query = "SELECT a FROM Arbitro a WHERE a.id = :id"),
    @NamedQuery(name = "Arbitro.findByStatus", query = "SELECT a FROM Arbitro a WHERE a.status = :status")})
public class Arbitro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private Integer status;
    @JoinTable(name = "arbitro_premio", joinColumns = {
        @JoinColumn(name = "arbitro_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "premio_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Premio> premioCollection;
    @JoinTable(name = "competicion_arbitro", joinColumns = {
        @JoinColumn(name = "arbitro_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "competicion_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Competicion> competicionCollection;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Persona personaId;
    @JoinColumn(name = "asociacion_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Asociacion asociacionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arbitroId")
    private Collection<PartidoArbitro> partidoArbitroCollection;

    public Arbitro() {
    }

    public Arbitro(Integer id) {
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

    @XmlTransient
    public Collection<Premio> getPremioCollection() {
        return premioCollection;
    }

    public void setPremioCollection(Collection<Premio> premioCollection) {
        this.premioCollection = premioCollection;
    }

    @XmlTransient
    public Collection<Competicion> getCompeticionCollection() {
        return competicionCollection;
    }

    public void setCompeticionCollection(Collection<Competicion> competicionCollection) {
        this.competicionCollection = competicionCollection;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Asociacion getAsociacionId() {
        return asociacionId;
    }

    public void setAsociacionId(Asociacion asociacionId) {
        this.asociacionId = asociacionId;
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
        if (!(object instanceof Arbitro)) {
            return false;
        }
        Arbitro other = (Arbitro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return personaId.getApellido() + " " +personaId.getNombre();
    }
    
}
