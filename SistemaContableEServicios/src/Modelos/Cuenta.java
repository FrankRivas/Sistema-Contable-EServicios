/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByCodcuenta", query = "SELECT c FROM Cuenta c WHERE c.codcuenta = :codcuenta"),
    @NamedQuery(name = "Cuenta.findByNomcuenta", query = "SELECT c FROM Cuenta c WHERE c.nomcuenta = :nomcuenta"),
    @NamedQuery(name = "Cuenta.findByEstadocuenta", query = "SELECT c FROM Cuenta c WHERE c.estadocuenta = :estadocuenta"),
    @NamedQuery(name = "Cuenta.findBySaldocuenta", query = "SELECT c FROM Cuenta c WHERE c.saldocuenta = :saldocuenta")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codcuenta")
    private String codcuenta;
    @Basic(optional = false)
    @Column(name = "nomcuenta")
    private String nomcuenta;
    @Basic(optional = false)
    @Column(name = "estadocuenta")
    private Character estadocuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "saldocuenta")
    private BigDecimal saldocuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcuenta")
    private List<Detallediario> detallediarioList;
    @JoinColumn(name = "idbase", referencedColumnName = "idbase")
    @ManyToOne
    private Baseprorrateo idbase;
    @JoinColumn(name = "idcatalogo", referencedColumnName = "idcatalogo")
    @ManyToOne(optional = false)
    private Catalogocuenta idcatalogo;
    @OneToMany(mappedBy = "cueCodcuenta")
    private List<Cuenta> cuentaList;
    @JoinColumn(name = "cue_codcuenta", referencedColumnName = "codcuenta")
    @ManyToOne
    private Cuenta cueCodcuenta;
    @JoinColumn(name = "idtipocuenta", referencedColumnName = "idtipocuenta")
    @ManyToOne(optional = false)
    private Tipocuenta idtipocuenta;

    public Cuenta() {
    }

    public Cuenta(String codcuenta) {
        this.codcuenta = codcuenta;
    }

    public Cuenta(String codcuenta, String nomcuenta, Character estadocuenta, BigDecimal saldocuenta) {
        this.codcuenta = codcuenta;
        this.nomcuenta = nomcuenta;
        this.estadocuenta = estadocuenta;
        this.saldocuenta = saldocuenta;
    }

    public String getCodcuenta() {
        return codcuenta;
    }

    public void setCodcuenta(String codcuenta) {
        this.codcuenta = codcuenta;
    }

    public String getNomcuenta() {
        return nomcuenta;
    }

    public void setNomcuenta(String nomcuenta) {
        this.nomcuenta = nomcuenta;
    }

    public Character getEstadocuenta() {
        return estadocuenta;
    }

    public void setEstadocuenta(Character estadocuenta) {
        this.estadocuenta = estadocuenta;
    }

    public BigDecimal getSaldocuenta() {
        return saldocuenta;
    }

    public void setSaldocuenta(BigDecimal saldocuenta) {
        this.saldocuenta = saldocuenta;
    }

    @XmlTransient
    public List<Detallediario> getDetallediarioList() {
        return detallediarioList;
    }

    public void setDetallediarioList(List<Detallediario> detallediarioList) {
        this.detallediarioList = detallediarioList;
    }

    public Baseprorrateo getIdbase() {
        return idbase;
    }

    public void setIdbase(Baseprorrateo idbase) {
        this.idbase = idbase;
    }

    public Catalogocuenta getIdcatalogo() {
        return idcatalogo;
    }

    public void setIdcatalogo(Catalogocuenta idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    public Cuenta getCueCodcuenta() {
        return cueCodcuenta;
    }

    public void setCueCodcuenta(Cuenta cueCodcuenta) {
        this.cueCodcuenta = cueCodcuenta;
    }

    public Tipocuenta getIdtipocuenta() {
        return idtipocuenta;
    }

    public void setIdtipocuenta(Tipocuenta idtipocuenta) {
        this.idtipocuenta = idtipocuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcuenta != null ? codcuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.codcuenta == null && other.codcuenta != null) || (this.codcuenta != null && !this.codcuenta.equals(other.codcuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Cuenta[ codcuenta=" + codcuenta + " ]";
    }
    
}
