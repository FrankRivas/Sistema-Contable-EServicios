/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Merii
 */
@Entity
@Table(name = "retencionimpuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencionimpuesto.findAll", query = "SELECT r FROM Retencionimpuesto r"),
    @NamedQuery(name = "Retencionimpuesto.findByIdretimp", query = "SELECT r FROM Retencionimpuesto r WHERE r.idretimp = :idretimp"),
    @NamedQuery(name = "Retencionimpuesto.findByNomretimpt", query = "SELECT r FROM Retencionimpuesto r WHERE r.nomretimpt = :nomretimpt")})
public class Retencionimpuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idretimp")
    private Integer idretimp;
    @Basic(optional = false)
    @Column(name = "nomretimpt")
    private String nomretimpt;
    @OneToMany(mappedBy = "idretimp")
    private List<Techo> techoList;

    public Retencionimpuesto() {
    }

    public Retencionimpuesto(Integer idretimp) {
        this.idretimp = idretimp;
    }

    public Retencionimpuesto(Integer idretimp, String nomretimpt) {
        this.idretimp = idretimp;
        this.nomretimpt = nomretimpt;
    }

    public Integer getIdretimp() {
        return idretimp;
    }

    public void setIdretimp(Integer idretimp) {
        this.idretimp = idretimp;
    }

    public String getNomretimpt() {
        return nomretimpt;
    }

    public void setNomretimpt(String nomretimpt) {
        this.nomretimpt = nomretimpt;
    }

    @XmlTransient
    public List<Techo> getTechoList() {
        return techoList;
    }

    public void setTechoList(List<Techo> techoList) {
        this.techoList = techoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idretimp != null ? idretimp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retencionimpuesto)) {
            return false;
        }
        Retencionimpuesto other = (Retencionimpuesto) object;
        if ((this.idretimp == null && other.idretimp != null) || (this.idretimp != null && !this.idretimp.equals(other.idretimp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Retencionimpuesto[ idretimp=" + idretimp + " ]";
    }
    
}
