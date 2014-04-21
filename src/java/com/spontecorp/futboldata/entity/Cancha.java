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
import javax.persistence.Lob;
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
@Table(name = "cancha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancha.findAll", query = "SELECT c FROM Cancha c"),
    @NamedQuery(name = "Cancha.findById", query = "SELECT c FROM Cancha c WHERE c.id = :id"),
    @NamedQuery(name = "Cancha.findByNombre", query = "SELECT c FROM Cancha c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cancha.findByCapacidad", query = "SELECT c FROM Cancha c WHERE c.capacidad = :capacidad"),
    @NamedQuery(name = "Cancha.findBySuperficie", query = "SELECT c FROM Cancha c WHERE c.superficie = :superficie"),
    @NamedQuery(name = "Cancha.findByMedidas", query = "SELECT c FROM Cancha c WHERE c.medidas = :medidas"),
    @NamedQuery(name = "Cancha.findByStatus", query = "SELECT c FROM Cancha c WHERE c.status = :status"),
    @NamedQuery(name = "Cancha.findByCoordenadaLong", query = "SELECT c FROM Cancha c WHERE c.coordenadaLong = :coordenadaLong"),
    @NamedQuery(name = "Cancha.findByCoordenadaLat", query = "SELECT c FROM Cancha c WHERE c.coordenadaLat = :coordenadaLat")})
public class Cancha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "capacidad")
    private Integer capacidad;
    @Size(max = 45)
    @Column(name = "superficie")
    private String superficie;
    @Size(max = 45)
    @Column(name = "medidas")
    private String medidas;
    @Lob
    @Size(max = 65535)
    @Column(name = "foto")
    private String foto;
    @Column(name = "status")
    private Integer status;
    @Column(name = "coordenada_long")
    private Integer coordenadaLong;
    @Column(name = "coordenada_lat")
    private Integer coordenadaLat;
    @OneToMany(mappedBy = "canchaId")
    private Collection<Partido> partidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancha")
    private Collection<ClubCancha> clubCanchaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancha")
    private Collection<EquipoCancha> equipoCanchaCollection;
    @OneToMany(mappedBy = "canchaId")
    private Collection<Imagen> imagenCollection;
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    @ManyToOne
    private Direccion direccionId;

    public Cancha() {
    }

    public Cancha(Integer id) {
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

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCoordenadaLong() {
        return coordenadaLong;
    }

    public void setCoordenadaLong(Integer coordenadaLong) {
        this.coordenadaLong = coordenadaLong;
    }

    public Integer getCoordenadaLat() {
        return coordenadaLat;
    }

    public void setCoordenadaLat(Integer coordenadaLat) {
        this.coordenadaLat = coordenadaLat;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }

    @XmlTransient
    public Collection<ClubCancha> getClubCanchaCollection() {
        return clubCanchaCollection;
    }

    public void setClubCanchaCollection(Collection<ClubCancha> clubCanchaCollection) {
        this.clubCanchaCollection = clubCanchaCollection;
    }

    @XmlTransient
    public Collection<EquipoCancha> getEquipoCanchaCollection() {
        return equipoCanchaCollection;
    }

    public void setEquipoCanchaCollection(Collection<EquipoCancha> equipoCanchaCollection) {
        this.equipoCanchaCollection = equipoCanchaCollection;
    }

    @XmlTransient
    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
    }

    public Direccion getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Direccion direccionId) {
        this.direccionId = direccionId;
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
        if (!(object instanceof Cancha)) {
            return false;
        }
        Cancha other = (Cancha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Cancha[ id=" + id + " ]";
    }
    
}
