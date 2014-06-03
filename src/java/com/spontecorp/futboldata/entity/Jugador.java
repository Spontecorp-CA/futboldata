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
@Table(name = "jugador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findById", query = "SELECT j FROM Jugador j WHERE j.id = :id"),
    @NamedQuery(name = "Jugador.findByCamiseta", query = "SELECT j FROM Jugador j WHERE j.camiseta = :camiseta"),
    @NamedQuery(name = "Jugador.findByZurdo", query = "SELECT j FROM Jugador j WHERE j.zurdo = :zurdo"),
    @NamedQuery(name = "Jugador.findByAltura", query = "SELECT j FROM Jugador j WHERE j.altura = :altura"),
    @NamedQuery(name = "Jugador.findByPeso", query = "SELECT j FROM Jugador j WHERE j.peso = :peso"),
    @NamedQuery(name = "Jugador.findByStatus", query = "SELECT j FROM Jugador j WHERE j.status = :status")})
public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "camiseta")
    private Integer camiseta;
    @Column(name = "zurdo")
    private Integer zurdo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "altura")
    private Double altura;
    @Column(name = "peso")
    private Double peso;
    @Column(name = "status")
    private Integer status;
    @JoinTable(name = "jugador_titulo", joinColumns = {
        @JoinColumn(name = "jugador_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "titulo_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Titulo> tituloCollection;
    @JoinTable(name = "jugador_premio", joinColumns = {
        @JoinColumn(name = "jugador_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "premio_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Premio> premioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jugadorId")
    private Collection<Convocado> convocadoCollection;
    @JoinColumn(name = "agente_id", referencedColumnName = "id")
    @ManyToOne
    private Agente agenteId;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Persona personaId;
    @JoinColumn(name = "posicion_id", referencedColumnName = "id")
    @ManyToOne
    private Posicion posicionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jugadorId")
    private Collection<Transferencia> transferenciaCollection;
    @OneToMany(mappedBy = "jugadorId")
    private Collection<Contrato> contratoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jugadorId")
    private Collection<EquipoHasJugador> equipoHasJugadorCollection;
    @OneToMany(mappedBy = "jugador1Id")
    private Collection<PartidoEvento> partidoEventoCollection;
    @OneToMany(mappedBy = "jugador2Id")
    private Collection<PartidoEvento> partidoEventoCollection1;

    public Jugador() {
    }

    public Jugador(Integer id) {
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

    public Integer getZurdo() {
        return zurdo;
    }

    public void setZurdo(Integer zurdo) {
        this.zurdo = zurdo;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Titulo> getTituloCollection() {
        return tituloCollection;
    }

    public void setTituloCollection(Collection<Titulo> tituloCollection) {
        this.tituloCollection = tituloCollection;
    }

    @XmlTransient
    public Collection<Premio> getPremioCollection() {
        return premioCollection;
    }

    public void setPremioCollection(Collection<Premio> premioCollection) {
        this.premioCollection = premioCollection;
    }

    @XmlTransient
    public Collection<Convocado> getConvocadoCollection() {
        return convocadoCollection;
    }

    public void setConvocadoCollection(Collection<Convocado> convocadoCollection) {
        this.convocadoCollection = convocadoCollection;
    }

    public Agente getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(Agente agenteId) {
        this.agenteId = agenteId;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Posicion getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(Posicion posicionId) {
        this.posicionId = posicionId;
    }

    @XmlTransient
    public Collection<Transferencia> getTransferenciaCollection() {
        return transferenciaCollection;
    }

    public void setTransferenciaCollection(Collection<Transferencia> transferenciaCollection) {
        this.transferenciaCollection = transferenciaCollection;
    }

    @XmlTransient
    public Collection<Contrato> getContratoCollection() {
        return contratoCollection;
    }

    public void setContratoCollection(Collection<Contrato> contratoCollection) {
        this.contratoCollection = contratoCollection;
    }

    @XmlTransient
    public Collection<EquipoHasJugador> getEquipoHasJugadorCollection() {
        return equipoHasJugadorCollection;
    }

    public void setEquipoHasJugadorCollection(Collection<EquipoHasJugador> equipoHasJugadorCollection) {
        this.equipoHasJugadorCollection = equipoHasJugadorCollection;
    }

    @XmlTransient
    public Collection<PartidoEvento> getPartidoEventoCollection() {
        return partidoEventoCollection;
    }

    public void setPartidoEventoCollection(Collection<PartidoEvento> partidoEventoCollection) {
        this.partidoEventoCollection = partidoEventoCollection;
    }

    @XmlTransient
    public Collection<PartidoEvento> getPartidoEventoCollection1() {
        return partidoEventoCollection1;
    }

    public void setPartidoEventoCollection1(Collection<PartidoEvento> partidoEventoCollection1) {
        this.partidoEventoCollection1 = partidoEventoCollection1;
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
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Jugador[ id=" + id + " ]";
    }
    
}
