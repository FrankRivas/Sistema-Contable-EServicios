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
@Table(name = "tipocuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocuenta.findAll", query = "SELECT t FROM Tipocuenta t"),
    @NamedQuery(name = "Tipocuenta.findByIdtipocuenta", query = "SELECT t FROM Tipocuenta t WHERE t.idtipocuenta = :idtipocuenta"),
    @NamedQuery(name = "Tipocuenta.findByNomtipocuenta", query = "SELECT t FROM Tipocuenta t WHERE t.nomtipocuenta = :nomtipocuenta")})
public class Tipocuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipocuenta")
    private String idtipocuenta;
    @Basic(optional = false)
    @Column(name = "nomtipocuenta")
    private String nomtipocuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipocuenta")
    private List<Cuenta> cuentaList;

    public Tipocuenta() {
    }

    public Tipocuenta(String idtipocuenta) {
        this.idtipocuenta = idtipocuenta;
    }

    public Tipocuenta(String idtipocuenta, String nomtipocuenta) {
        this.idtipocuenta = idtipocuenta;
        this.nomtipocuenta = nomtipocuenta;
    }

    public String getIdtipocuenta() {
        return idtipocuenta;
    }

    public void setIdtipocuenta(String idtipocuenta) {
        this.idtipocuenta = idtipocuenta;
    }

    public String getNomtipocuenta() {
        return nomtipocuenta;
    }

    public void setNomtipocuenta(String nomtipocuenta) {
        this.nomtipocuenta = nomtipocuenta;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocuenta != null ? idtipocuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocuenta)) {
            return false;
        }
        Tipocuenta other = (Tipocuenta) object;
        if ((this.idtipocuenta == null && other.idtipocuenta != null) || (this.idtipocuenta != null && !this.idtipocuenta.equals(other.idtipocuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Tipocuenta[ idtipocuenta=" + idtipocuenta + " ]";
    }
    
}
