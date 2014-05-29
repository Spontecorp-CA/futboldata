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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "imagen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagen.findAll", query = "SELECT i FROM Imagen i"),
    @NamedQuery(name = "Imagen.findById", query = "SELECT i FROM Imagen i WHERE i.id = :id"),
    @NamedQuery(name = "Imagen.findByTitulo", query = "SELECT i FROM Imagen i WHERE i.titulo = :titulo"),
    @NamedQuery(name = "Imagen.findByNombre", query = "SELECT i FROM Imagen i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Imagen.findByIsLogo", query = "SELECT i FROM Imagen i WHERE i.isLogo = :isLogo"),
    @NamedQuery(name = "Imagen.findByStatus", query = "SELECT i FROM Imagen i WHERE i.status = :status")})
public class Imagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "is_logo")
    private Integer isLogo;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "asociacion_id", referencedColumnName = "id")
    @ManyToOne
    private Asociacion asociacionId;
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    @ManyToOne
    private Cancha canchaId;
    @JoinColumn(name = "patrocinante_id", referencedColumnName = "id")
    @ManyToOne
    private Patrocinante patrocinanteId;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne
    private Equipo equipoId;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne
    private Persona personaId;
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @ManyToOne
    private Club clubId;
    @JoinColumn(name = "competicion_id", referencedColumnName = "id")
    @ManyToOne
    private Competicion competicionId;

    public Imagen() {
    }

    public Imagen(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIsLogo() {
        return isLogo;
    }

    public void setIsLogo(Integer isLogo) {
        this.isLogo = isLogo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Asociacion getAsociacionId() {
        return asociacionId;
    }

    public void setAsociacionId(Asociacion asociacionId) {
        this.asociacionId = asociacionId;
    }

    public Cancha getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(Cancha canchaId) {
        this.canchaId = canchaId;
    }

    public Patrocinante getPatrocinanteId() {
        return patrocinanteId;
    }

    public void setPatrocinanteId(Patrocinante patrocinanteId) {
        this.patrocinanteId = patrocinanteId;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titulo;
    }
    
}
