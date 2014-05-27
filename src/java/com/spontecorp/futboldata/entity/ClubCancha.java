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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sponte03
 */
@Entity
@Table(name = "club_cancha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClubCancha.findAll", query = "SELECT c FROM ClubCancha c"),
    @NamedQuery(name = "ClubCancha.findById", query = "SELECT c FROM ClubCancha c WHERE c.id = :id"),
    @NamedQuery(name = "ClubCancha.findByStatus", query = "SELECT c FROM ClubCancha c WHERE c.status = :status")})
public class ClubCancha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cancha canchaId;
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Club clubId;

    public ClubCancha() {
    }

    public ClubCancha(Integer id) {
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

    public Cancha getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(Cancha canchaId) {
        this.canchaId = canchaId;
    }

    public Club getClubId() {
        return clubId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
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
        if (!(object instanceof ClubCancha)) {
            return false;
        }
        ClubCancha other = (ClubCancha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.ClubCancha[ id=" + id + " ]";
    }
    
}
