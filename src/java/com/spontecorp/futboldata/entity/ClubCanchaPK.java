/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sponte03
 */
@Embeddable
public class ClubCanchaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "club_id")
    private int clubId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancha_id")
    private int canchaId;

    public ClubCanchaPK() {
    }

    public ClubCanchaPK(int id, int clubId, int canchaId) {
        this.id = id;
        this.clubId = clubId;
        this.canchaId = canchaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(int canchaId) {
        this.canchaId = canchaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) clubId;
        hash += (int) canchaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClubCanchaPK)) {
            return false;
        }
        ClubCanchaPK other = (ClubCanchaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.clubId != other.clubId) {
            return false;
        }
        if (this.canchaId != other.canchaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.ClubCanchaPK[ id=" + id + ", clubId=" + clubId + ", canchaId=" + canchaId + " ]";
    }
    
}
