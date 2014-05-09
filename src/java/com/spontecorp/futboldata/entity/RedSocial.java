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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "red_social")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedSocial.findAll", query = "SELECT r FROM RedSocial r"),
    @NamedQuery(name = "RedSocial.findById", query = "SELECT r FROM RedSocial r WHERE r.id = :id"),
    @NamedQuery(name = "RedSocial.findByUsuario", query = "SELECT r FROM RedSocial r WHERE r.usuario = :usuario")})
public class RedSocial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 80)
    @Column(name = "usuario")
    private String usuario;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne
    private Persona personaId;
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
    @JoinColumn(name = "tipo_red_social_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoRedSocial tipoRedSocialId;

    public RedSocial() {
    }

    public RedSocial(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public TipoRedSocial getTipoRedSocialId() {
        return tipoRedSocialId;
    }

    public void setTipoRedSocialId(TipoRedSocial tipoRedSocialId) {
        this.tipoRedSocialId = tipoRedSocialId;
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
        if (!(object instanceof RedSocial)) {
            return false;
        }
        RedSocial other = (RedSocial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.RedSocial[ id=" + id + " ]";
    }
    
}
