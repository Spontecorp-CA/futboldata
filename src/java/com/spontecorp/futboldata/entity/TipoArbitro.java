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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jgcastillo
 */
@Entity
@Table(name = "tipo_arbitro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoArbitro.findAll", query = "SELECT t FROM TipoArbitro t"),
    @NamedQuery(name = "TipoArbitro.findById", query = "SELECT t FROM TipoArbitro t WHERE t.id = :id"),
    @NamedQuery(name = "TipoArbitro.findByNombre", query = "SELECT t FROM TipoArbitro t WHERE t.nombre = :nombre")})
public class TipoArbitro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoArbitroId")
    private Collection<PartidoArbitro> partidoArbitroCollection;

    public TipoArbitro() {
    }

    public TipoArbitro(Integer id) {
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

    @XmlTransient
    public Collection<PartidoArbitro> getPartidoArbitroCollection() {
        return partidoArbitroCollection;
    }

    public void setPartidoArbitroCollection(Collection<PartidoArbitro> partidoArbitroCollection) {
        this.partidoArbitroCollection = partidoArbitroCollection;
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
        if (!(object instanceof TipoArbitro)) {
            return false;
        }
        TipoArbitro other = (TipoArbitro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spontecorp.futboldata.entity.TipoArbitro[ id=" + id + " ]";
    }
    
}
