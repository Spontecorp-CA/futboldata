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
@Table(name = "convocatoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convocatoria.findAll", query = "SELECT c FROM Convocatoria c"),
    @NamedQuery(name = "Convocatoria.findById", query = "SELECT c FROM Convocatoria c WHERE c.id = :id")})
public class Convocatoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partido partidoId;
    @JoinColumn(name = "formacion_id", referencedColumnName = "id")
    @ManyToOne
    private Formacion formacionId;
    @JoinColumn(name = "equipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipo equipoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "convocatoriaId")
    private Collection<Convocado> convocadoCollection;

    public Convocatoria() {
    }

    public Convocatoria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Partido getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Partido partidoId) {
        this.partidoId = partidoId;
    }

    public Formacion getFormacionId() {
        return formacionId;
    }

    public void setFormacionId(Formacion formacionId) {
        this.formacionId = formacionId;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
    }

    @XmlTransient
    public Collection<Convocado> getConvocadoCollection() {
        return convocadoCollection;
    }

    public void setConvocadoCollection(Collection<Convocado> convocadoCollection) {
        this.convocadoCollection = convocadoCollection;
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
        if (!(object instanceof Convocatoria)) {
            return false;
        }
        Convocatoria other = (Convocatoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.Convocatoria[ id=" + id + " ]";
    }
    
}
