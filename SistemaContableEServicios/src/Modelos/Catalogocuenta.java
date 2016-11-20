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
@Table(name = "catalogocuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catalogocuenta.findAll", query = "SELECT c FROM Catalogocuenta c"),
    @NamedQuery(name = "Catalogocuenta.findByIdcatalogo", query = "SELECT c FROM Catalogocuenta c WHERE c.idcatalogo = :idcatalogo"),
    @NamedQuery(name = "Catalogocuenta.findByNomcatalogo", query = "SELECT c FROM Catalogocuenta c WHERE c.nomcatalogo = :nomcatalogo")})
public class Catalogocuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcatalogo")
    private Integer idcatalogo;
    @Basic(optional = false)
    @Column(name = "nomcatalogo")
    private String nomcatalogo;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private Empresa idempresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcatalogo")
    private List<Cuenta> cuentaList;
    @OneToMany(mappedBy = "idcatalogo")
    private List<Empresa> empresaList;

    public Catalogocuenta() {
    }

    public Catalogocuenta(Integer idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    public Catalogocuenta(Integer idcatalogo, String nomcatalogo) {
        this.idcatalogo = idcatalogo;
        this.nomcatalogo = nomcatalogo;
    }

    public Integer getIdcatalogo() {
        return idcatalogo;
    }

    public void setIdcatalogo(Integer idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    public String getNomcatalogo() {
        return nomcatalogo;
    }

    public void setNomcatalogo(String nomcatalogo) {
        this.nomcatalogo = nomcatalogo;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcatalogo != null ? idcatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogocuenta)) {
            return false;
        }
        Catalogocuenta other = (Catalogocuenta) object;
        if ((this.idcatalogo == null && other.idcatalogo != null) || (this.idcatalogo != null && !this.idcatalogo.equals(other.idcatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Catalogocuenta[ idcatalogo=" + idcatalogo + " ]";
    }
    
}
