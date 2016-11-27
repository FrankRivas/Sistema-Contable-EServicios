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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Merii
 */
@Entity
@Table(name = "centrodecosto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centrodecosto.findAll", query = "SELECT c FROM Centrodecosto c"),
    @NamedQuery(name = "Centrodecosto.findByIdcentro", query = "SELECT c FROM Centrodecosto c WHERE c.idcentro = :idcentro"),
    @NamedQuery(name = "Centrodecosto.findByNomcentro", query = "SELECT c FROM Centrodecosto c WHERE c.nomcentro = :nomcentro"),
    @NamedQuery(name = "Centrodecosto.findByTotalcosto", query = "SELECT c FROM Centrodecosto c WHERE c.totalcosto = :totalcosto")})
public class Centrodecosto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="centrodecosto_idcentro_seq",
                       sequenceName="centrodecosto_idcentro_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator="centrodecosto_idcentro_seq")
    @Basic(optional = false)
    @Column(name = "idcentro")
    private Integer idcentro;
    @Basic(optional = false)
    @Column(name = "nomcentro")
    private String nomcentro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "totalcosto")
    private BigDecimal totalcosto;
    @ManyToMany(mappedBy = "centrodecostoList")
    private List<Baseprorrateo> baseprorrateoList;

    public Centrodecosto() {
    }

    public Centrodecosto(Integer idcentro) {
        this.idcentro = idcentro;
    }
    
    public Centrodecosto(String nomcentro, BigDecimal totalcosto) {
        this.nomcentro = nomcentro;
        this.totalcosto = totalcosto;
    }

    public Centrodecosto(Integer idcentro, String nomcentro, BigDecimal totalcosto) {
        this.idcentro = idcentro;
        this.nomcentro = nomcentro;
        this.totalcosto = totalcosto;
    }

    public Integer getIdcentro() {
        return idcentro;
    }

    public void setIdcentro(Integer idcentro) {
        this.idcentro = idcentro;
    }

    public String getNomcentro() {
        return nomcentro;
    }

    public void setNomcentro(String nomcentro) {
        this.nomcentro = nomcentro;
    }

    public BigDecimal getTotalcosto() {
        return totalcosto;
    }

    public void setTotalcosto(BigDecimal totalcosto) {
        this.totalcosto = totalcosto;
    }

    @XmlTransient
    public List<Baseprorrateo> getBaseprorrateoList() {
        return baseprorrateoList;
    }

    public void setBaseprorrateoList(List<Baseprorrateo> baseprorrateoList) {
        this.baseprorrateoList = baseprorrateoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcentro != null ? idcentro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centrodecosto)) {
            return false;
        }
        Centrodecosto other = (Centrodecosto) object;
        if ((this.idcentro == null && other.idcentro != null) || (this.idcentro != null && !this.idcentro.equals(other.idcentro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Centrodecosto[ idcentro=" + idcentro + " ]";
    }
    
}
