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
@Table(name = "techo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Techo.findAll", query = "SELECT t FROM Techo t"),
    @NamedQuery(name = "Techo.findByIdtecho", query = "SELECT t FROM Techo t WHERE t.idtecho = :idtecho"),
    @NamedQuery(name = "Techo.findByDesde", query = "SELECT t FROM Techo t WHERE t.desde = :desde"),
    @NamedQuery(name = "Techo.findByHasta", query = "SELECT t FROM Techo t WHERE t.hasta = :hasta"),
    @NamedQuery(name = "Techo.findByPorcenaplicar", query = "SELECT t FROM Techo t WHERE t.porcenaplicar = :porcenaplicar"),
    @NamedQuery(name = "Techo.findBySobreexceso", query = "SELECT t FROM Techo t WHERE t.sobreexceso = :sobreexceso"),
    @NamedQuery(name = "Techo.findByCuotafija", query = "SELECT t FROM Techo t WHERE t.cuotafija = :cuotafija")})
public class Techo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtecho")
    private Integer idtecho;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desde")
    private BigDecimal desde;
    @Column(name = "hasta")
    private BigDecimal hasta;
    @Column(name = "porcenaplicar")
    private BigDecimal porcenaplicar;
    @Column(name = "sobreexceso")
    private BigDecimal sobreexceso;
    @Column(name = "cuotafija")
    private BigDecimal cuotafija;
    @JoinColumn(name = "idretimp", referencedColumnName = "idretimp")
    @ManyToOne
    private Retencionimpuesto idretimp;

    public Techo() {
    }

    public Techo(Integer idtecho) {
        this.idtecho = idtecho;
    }

    public Integer getIdtecho() {
        return idtecho;
    }

    public void setIdtecho(Integer idtecho) {
        this.idtecho = idtecho;
    }

    public BigDecimal getDesde() {
        return desde;
    }

    public void setDesde(BigDecimal desde) {
        this.desde = desde;
    }

    public BigDecimal getHasta() {
        return hasta;
    }

    public void setHasta(BigDecimal hasta) {
        this.hasta = hasta;
    }

    public BigDecimal getPorcenaplicar() {
        return porcenaplicar;
    }

    public void setPorcenaplicar(BigDecimal porcenaplicar) {
        this.porcenaplicar = porcenaplicar;
    }

    public BigDecimal getSobreexceso() {
        return sobreexceso;
    }

    public void setSobreexceso(BigDecimal sobreexceso) {
        this.sobreexceso = sobreexceso;
    }

    public BigDecimal getCuotafija() {
        return cuotafija;
    }

    public void setCuotafija(BigDecimal cuotafija) {
        this.cuotafija = cuotafija;
    }

    public Retencionimpuesto getIdretimp() {
        return idretimp;
    }

    public void setIdretimp(Retencionimpuesto idretimp) {
        this.idretimp = idretimp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtecho != null ? idtecho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Techo)) {
            return false;
        }
        Techo other = (Techo) object;
        if ((this.idtecho == null && other.idtecho != null) || (this.idtecho != null && !this.idtecho.equals(other.idtecho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Techo[ idtecho=" + idtecho + " ]";
    }
    
}
