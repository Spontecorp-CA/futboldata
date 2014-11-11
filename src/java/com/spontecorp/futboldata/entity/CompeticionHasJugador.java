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
@Table(name = "competicion_has_jugador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompeticionHasJugador.findAll", query = "SELECT c FROM CompeticionHasJugador c"),
    @NamedQuery(name = "CompeticionHasJugador.findById", query = "SELECT c FROM CompeticionHasJugador c WHERE c.id = :id"),
    @NamedQuery(name = "CompeticionHasJugador.findByFicha", query = "SELECT c FROM CompeticionHasJugador c WHERE c.ficha = :ficha")})
public class CompeticionHasJugador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ficha")
    private String ficha;
    @JoinColumn(name = "competicion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Competicion competicionId;
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jugador jugadorId;

    public CompeticionHasJugador() {
    }

    public CompeticionHasJugador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public Competicion getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(Competicion competicionId) {
        this.competicionId = competicionId;
    }

    public Jugador getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Jugador jugadorId) {
        this.jugadorId = jugadorId;
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
        if (!(object instanceof CompeticionHasJugador)) {
            return false;
        }
        CompeticionHasJugador other = (CompeticionHasJugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.CompeticionHasJugador[ id=" + id + " ]";
    }

}
