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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findById", query = "SELECT e FROM Equipo e WHERE e.id = :id"),
    @NamedQuery(name = "Equipo.findByNombre", query = "SELECT e FROM Equipo e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Equipo.findByNombreAlterno", query = "SELECT e FROM Equipo e WHERE e.nombreAlterno = :nombreAlterno"),
    @NamedQuery(name = "Equipo.findByStatus", query = "SELECT e FROM Equipo e WHERE e.status = :status"),
    @NamedQuery(name = "Equipo.findByAbreviacion", query = "SELECT e FROM Equipo e WHERE e.abreviacion = :abreviacion")})
public class Equipo implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<PartidoEventoEquipo> partidoEventoEquipoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<ClasificacionGrupo> clasificacionGrupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<EquipoEnGrupo> equipoEnGrupoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "nombre_alterno")
    private String nombreAlterno;
    @Column(name = "status")
    private Integer status;
    @Size(max = 10)
    @Column(name = "abreviacion")
    private String abreviacion;
    @Lob
    @Size(max = 65535)
    @Column(name = "logo")
    private String logo;
    @JoinTable(name = "equipo_premio", joinColumns = {
        @JoinColumn(name = "equipo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "premio_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Premio> premioCollection;
    @JoinTable(name = "equipo_titulo", joinColumns = {
        @JoinColumn(name = "equipo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "titulo_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Titulo> tituloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoProvedorId")
    private Collection<Transferencia> transferenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoReceptorId")
    private Collection<Transferencia> transferenciaCollection1;
    @OneToMany(mappedBy = "equipoId")
    private Collection<Contrato> contratoCollection;
    @OneToMany(mappedBy = "equipoId")
    private Collection<Imagen> imagenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<EquipoInLiga> equipoInLigaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<Convocatoria> convocatoriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<EquipoHasJugador> equipoHasJugadorCollection;
    @OneToMany(mappedBy = "equipoLocalId")
    private Collection<Partido> partidoCollection;
    @OneToMany(mappedBy = "equipoVisitanteId")
    private Collection<Partido> partidoCollection1;
    @OneToMany(mappedBy = "equipoId")
    private Collection<Staff> staffCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<EquipoCancha> equipoCanchaCollection;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria categoriaId;
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Club clubId;
    @OneToMany(mappedBy = "equipoId")
    private Collection<RedSocial> redSocialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoId")
    private Collection<Clasificacion> clasificacionCollection;

    public Equipo() {
    }

    public Equipo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreAlterno() {
        return nombreAlterno;
    }

    public void setNombreAlterno(String nombreAlterno) {
        this.nombreAlterno = nombreAlterno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    @XmlTransient
    public Collection<Transferencia> getTransferenciaCollection() {
        return transferenciaCollection;
    }

    public void setTransferenciaCollection(Collection<Transferencia> transferenciaCollection) {
        this.transferenciaCollection = transferenciaCollection;
    }

    @XmlTransient
    public Collection<Transferencia> getTransferenciaCollection1() {
        return transferenciaCollection1;
    }

    public void setTransferenciaCollection1(Collection<Transferencia> transferenciaCollection1) {
        this.transferenciaCollection1 = transferenciaCollection1;
    }

    @XmlTransient
    public Collection<Contrato> getContratoCollection() {
        return contratoCollection;
    }

    public void setContratoCollection(Collection<Contrato> contratoCollection) {
        this.contratoCollection = contratoCollection;
    }

    @XmlTransient
    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
    }

    @XmlTransient
    public Collection<EquipoInLiga> getEquipoInLigaCollection() {
        return equipoInLigaCollection;
    }

    public void setEquipoInLigaCollection(Collection<EquipoInLiga> equipoInLigaCollection) {
        this.equipoInLigaCollection = equipoInLigaCollection;
    }

    @XmlTransient
    public Collection<Convocatoria> getConvocatoriaCollection() {
        return convocatoriaCollection;
    }

    public void setConvocatoriaCollection(Collection<Convocatoria> convocatoriaCollection) {
        this.convocatoriaCollection = convocatoriaCollection;
    }

    @XmlTransient
    public Collection<EquipoHasJugador> getEquipoHasJugadorCollection() {
        return equipoHasJugadorCollection;
    }

    public void setEquipoHasJugadorCollection(Collection<EquipoHasJugador> equipoHasJugadorCollection) {
        this.equipoHasJugadorCollection = equipoHasJugadorCollection;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection1() {
        return partidoCollection1;
    }

    public void setPartidoCollection1(Collection<Partido> partidoCollection1) {
        this.partidoCollection1 = partidoCollection1;
    }

    @XmlTransient
    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
    }

    @XmlTransient
    public Collection<EquipoCancha> getEquipoCanchaCollection() {
        return equipoCanchaCollection;
    }

    public void setEquipoCanchaCollection(Collection<EquipoCancha> equipoCanchaCollection) {
        this.equipoCanchaCollection = equipoCanchaCollection;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Club getClubId() {
        return clubId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
    }

    @XmlTransient
    public Collection<RedSocial> getRedSocialCollection() {
        return redSocialCollection;
    }

    public void setRedSocialCollection(Collection<RedSocial> redSocialCollection) {
        this.redSocialCollection = redSocialCollection;
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
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @XmlTransient
    public Collection<EquipoEnGrupo> getEquipoEnGrupoCollection() {
        return equipoEnGrupoCollection;
    }

    public void setEquipoEnGrupoCollection(Collection<EquipoEnGrupo> equipoEnGrupoCollection) {
        this.equipoEnGrupoCollection = equipoEnGrupoCollection;
    }

    @XmlTransient
    public Collection<ClasificacionGrupo> getClasificacionGrupoCollection() {
        return clasificacionGrupoCollection;
    }

    public void setClasificacionGrupoCollection(Collection<ClasificacionGrupo> clasificacionGrupoCollection) {
        this.clasificacionGrupoCollection = clasificacionGrupoCollection;
    }

    @XmlTransient
    public Collection<PartidoEventoEquipo> getPartidoEventoEquipoCollection() {
        return partidoEventoEquipoCollection;
    }

    public void setPartidoEventoEquipoCollection(Collection<PartidoEventoEquipo> partidoEventoEquipoCollection) {
        this.partidoEventoEquipoCollection = partidoEventoEquipoCollection;
    }
    
}
