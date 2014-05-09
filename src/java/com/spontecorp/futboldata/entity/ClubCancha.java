/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "ClubCancha.findById", query = "SELECT c FROM ClubCancha c WHERE c.clubCanchaPK.id = :id"),
    @NamedQuery(name = "ClubCancha.findByClubId", query = "SELECT c FROM ClubCancha c WHERE c.clubCanchaPK.clubId = :clubId"),
    @NamedQuery(name = "ClubCancha.findByCanchaId", query = "SELECT c FROM ClubCancha c WHERE c.clubCanchaPK.canchaId = :canchaId"),
    @NamedQuery(name = "ClubCancha.findByStatus", query = "SELECT c FROM ClubCancha c WHERE c.status = :status")})
public class ClubCancha implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClubCanchaPK clubCanchaPK;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "club_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Club club;
    @JoinColumn(name = "cancha_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cancha cancha;

    public ClubCancha() {
    }

    public ClubCancha(ClubCanchaPK clubCanchaPK) {
        this.clubCanchaPK = clubCanchaPK;
    }

    public ClubCancha(int id, int clubId, int canchaId) {
        this.clubCanchaPK = new ClubCanchaPK(id, clubId, canchaId);
    }

    public ClubCanchaPK getClubCanchaPK() {
        return clubCanchaPK;
    }

    public void setClubCanchaPK(ClubCanchaPK clubCanchaPK) {
        this.clubCanchaPK = clubCanchaPK;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clubCanchaPK != null ? clubCanchaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClubCancha)) {
            return false;
        }
        ClubCancha other = (ClubCancha) object;
        if ((this.clubCanchaPK == null && other.clubCanchaPK != null) || (this.clubCanchaPK != null && !this.clubCanchaPK.equals(other.clubCanchaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.ClubCancha[ clubCanchaPK=" + clubCanchaPK + " ]";
    }
    
}
