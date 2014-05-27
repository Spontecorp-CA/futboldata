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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "club")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Club.findAll", query = "SELECT c FROM Club c"),
    @NamedQuery(name = "Club.findById", query = "SELECT c FROM Club c WHERE c.id = :id"),
    @NamedQuery(name = "Club.findByNombre", query = "SELECT c FROM Club c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Club.findByYearFundacion", query = "SELECT c FROM Club c WHERE c.yearFundacion = :yearFundacion"),
    @NamedQuery(name = "Club.findByStatus", query = "SELECT c FROM Club c WHERE c.status = :status")})
public class Club implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 4)
    @Column(name = "year_fundacion")
    private String yearFundacion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "historia")
    private String historia;
    @Column(name = "status")
    private Integer status;
    @Lob
    @Size(max = 65535)
    @Column(name = "logo")
    private String logo;
    @JoinTable(name = "club_premio", joinColumns = {
        @JoinColumn(name = "club_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "premio_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Premio> premioCollection;
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    @ManyToOne
    private Direccion direccionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clubId")
    private Collection<ClubCancha> clubCanchaCollection;
    @OneToMany(mappedBy = "clubId")
    private Collection<Contrato> contratoCollection;
    @OneToMany(mappedBy = "clubId")
    private Collection<Imagen> imagenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clubId")
    private Collection<Audio> audioCollection;
    @OneToMany(mappedBy = "clubId")
    private Collection<Staff> staffCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clubId")
    private Collection<Equipo> equipoCollection;
    @OneToMany(mappedBy = "clubId")
    private Collection<RedSocial> redSocialCollection;

    public Club() {
    }

    public Club(Integer id) {
        this.id = id;
    }

    public Club(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getYearFundacion() {
        return yearFundacion;
    }

    public void setYearFundacion(String yearFundacion) {
        this.yearFundacion = yearFundacion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Direccion getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Direccion direccionId) {
        this.direccionId = direccionId;
    }

    @XmlTransient
    public Collection<ClubCancha> getClubCanchaCollection() {
        return clubCanchaCollection;
    }

    public void setClubCanchaCollection(Collection<ClubCancha> clubCanchaCollection) {
        this.clubCanchaCollection = clubCanchaCollection;
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
    public Collection<Audio> getAudioCollection() {
        return audioCollection;
    }

    public void setAudioCollection(Collection<Audio> audioCollection) {
        this.audioCollection = audioCollection;
    }

    @XmlTransient
    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
    }

    @XmlTransient
    public Collection<Equipo> getEquipoCollection() {
        return equipoCollection;
    }

    public void setEquipoCollection(Collection<Equipo> equipoCollection) {
        this.equipoCollection = equipoCollection;
    }

    @XmlTransient
    public Collection<RedSocial> getRedSocialCollection() {
        return redSocialCollection;
    }

    public void setRedSocialCollection(Collection<RedSocial> redSocialCollection) {
        this.redSocialCollection = redSocialCollection;
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
        if (!(object instanceof Club)) {
            return false;
        }
        Club other = (Club) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Club[ id=" + id + " ]";
    }
    
}
