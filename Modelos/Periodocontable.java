/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Merii
 */
@Entity
@Table(name = "periodocontable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodocontable.findAll", query = "SELECT p FROM Periodocontable p"),
    @NamedQuery(name = "Periodocontable.findByIdperiodo", query = "SELECT p FROM Periodocontable p WHERE p.idperiodo = :idperiodo"),
    @NamedQuery(name = "Periodocontable.findByFechainicio", query = "SELECT p FROM Periodocontable p WHERE p.fechainicio = :fechainicio"),
    @NamedQuery(name = "Periodocontable.findByFechafin", query = "SELECT p FROM Periodocontable p WHERE p.fechafin = :fechafin")})
public class Periodocontable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperiodo")
    private Integer idperiodo;
    @Basic(optional = false)
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperiodo")
    private List<Diario> diarioList;

    public Periodocontable() {
    }

    public Periodocontable(Integer idperiodo) {
        this.idperiodo = idperiodo;
    }

    public Periodocontable(Integer idperiodo, Date fechainicio, Date fechafin) {
        this.idperiodo = idperiodo;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
    }

    public Integer getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Integer idperiodo) {
        this.idperiodo = idperiodo;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    @XmlTransient
    public List<Diario> getDiarioList() {
        return diarioList;
    }

    public void setDiarioList(List<Diario> diarioList) {
        this.diarioList = diarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiodo != null ? idperiodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodocontable)) {
            return false;
        }
        Periodocontable other = (Periodocontable) object;
        if ((this.idperiodo == null && other.idperiodo != null) || (this.idperiodo != null && !this.idperiodo.equals(other.idperiodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Periodocontable[ idperiodo=" + idperiodo + " ]";
    }
    
}
