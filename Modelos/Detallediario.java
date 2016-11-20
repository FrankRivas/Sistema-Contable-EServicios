/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Merii
 */
@Entity
@Table(name = "detallediario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallediario.findAll", query = "SELECT d FROM Detallediario d"),
    @NamedQuery(name = "Detallediario.findByIddetalle", query = "SELECT d FROM Detallediario d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallediario.findByDebe", query = "SELECT d FROM Detallediario d WHERE d.debe = :debe"),
    @NamedQuery(name = "Detallediario.findByHaber", query = "SELECT d FROM Detallediario d WHERE d.haber = :haber")})
public class Detallediario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private Integer iddetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debe")
    private BigDecimal debe;
    @Column(name = "haber")
    private BigDecimal haber;
    @JoinColumn(name = "codcuenta", referencedColumnName = "codcuenta")
    @ManyToOne(optional = false)
    private Cuenta codcuenta;
    @JoinColumn(name = "idregistro", referencedColumnName = "idregistro")
    @ManyToOne(optional = false)
    private Diario idregistro;

    public Detallediario() {
    }

    public Detallediario(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public Cuenta getCodcuenta() {
        return codcuenta;
    }

    public void setCodcuenta(Cuenta codcuenta) {
        this.codcuenta = codcuenta;
    }

    public Diario getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Diario idregistro) {
        this.idregistro = idregistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallediario)) {
            return false;
        }
        Detallediario other = (Detallediario) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Detallediario[ iddetalle=" + iddetalle + " ]";
    }
    
}
