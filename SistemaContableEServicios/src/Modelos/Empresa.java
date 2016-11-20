/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.List;
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
 * @author Merii
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdempresa", query = "SELECT e FROM Empresa e WHERE e.idempresa = :idempresa"),
    @NamedQuery(name = "Empresa.findByNomempresa", query = "SELECT e FROM Empresa e WHERE e.nomempresa = :nomempresa"),
    @NamedQuery(name = "Empresa.findByNitempresa", query = "SELECT e FROM Empresa e WHERE e.nitempresa = :nitempresa"),
    @NamedQuery(name = "Empresa.findByDirempresa", query = "SELECT e FROM Empresa e WHERE e.dirempresa = :dirempresa")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresa")
    private Integer idempresa;
    @Basic(optional = false)
    @Column(name = "nomempresa")
    private String nomempresa;
    @Basic(optional = false)
    @Column(name = "nitempresa")
    private String nitempresa;
    @Basic(optional = false)
    @Column(name = "dirempresa")
    private String dirempresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempresa")
    private List<Area> areaList;
    @OneToMany(mappedBy = "idempresa")
    private List<Catalogocuenta> catalogocuentaList;
    @JoinColumn(name = "idcatalogo", referencedColumnName = "idcatalogo")
    @ManyToOne
    private Catalogocuenta idcatalogo;

    public Empresa() {
    }

    public Empresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Empresa(Integer idempresa, String nomempresa, String nitempresa, String dirempresa) {
        this.idempresa = idempresa;
        this.nomempresa = nomempresa;
        this.nitempresa = nitempresa;
        this.dirempresa = dirempresa;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNomempresa() {
        return nomempresa;
    }

    public void setNomempresa(String nomempresa) {
        this.nomempresa = nomempresa;
    }

    public String getNitempresa() {
        return nitempresa;
    }

    public void setNitempresa(String nitempresa) {
        this.nitempresa = nitempresa;
    }

    public String getDirempresa() {
        return dirempresa;
    }

    public void setDirempresa(String dirempresa) {
        this.dirempresa = dirempresa;
    }

    @XmlTransient
    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    @XmlTransient
    public List<Catalogocuenta> getCatalogocuentaList() {
        return catalogocuentaList;
    }

    public void setCatalogocuentaList(List<Catalogocuenta> catalogocuentaList) {
        this.catalogocuentaList = catalogocuentaList;
    }

    public Catalogocuenta getIdcatalogo() {
        return idcatalogo;
    }

    public void setIdcatalogo(Catalogocuenta idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempresa != null ? idempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idempresa == null && other.idempresa != null) || (this.idempresa != null && !this.idempresa.equals(other.idempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Empresa[ idempresa=" + idempresa + " ]";
    }
    
}
