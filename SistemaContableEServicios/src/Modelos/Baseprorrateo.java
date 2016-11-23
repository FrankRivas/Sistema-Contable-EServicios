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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Merii
 */
@Entity
@Table(name = "baseprorrateo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baseprorrateo.findAll", query = "SELECT b FROM Baseprorrateo b"),
    @NamedQuery(name = "Baseprorrateo.findByIdbase", query = "SELECT b FROM Baseprorrateo b WHERE b.idbase = :idbase"),
    @NamedQuery(name = "Baseprorrateo.findByNombase", query = "SELECT b FROM Baseprorrateo b WHERE b.nombase = :nombase"),
    @NamedQuery(name = "Baseprorrateo.findByTotalbase", query = "SELECT b FROM Baseprorrateo b WHERE b.totalbase = :totalbase")})
public class Baseprorrateo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="baseprorrateo_idbase_seq",
                       sequenceName="baseprorrateo_idbase_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator="baseprorrateo_idbase_seq")
    @Basic(optional = false)
    @Column(name = "idbase")
    private Integer idbase;
    @Basic(optional = false)
    @Column(name = "nombase")
    private String nombase;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "totalbase")
    private BigDecimal totalbase;
    @JoinTable(name = "prorrateo", joinColumns = {
        @JoinColumn(name = "idbase", referencedColumnName = "idbase")}, inverseJoinColumns = {
        @JoinColumn(name = "idcentro", referencedColumnName = "idcentro")})
    @ManyToMany
    private List<Centrodecosto> centrodecostoList;
    @OneToMany(mappedBy = "idbase")
    private List<Cuenta> cuentaList;

    public Baseprorrateo() {
    }

    public Baseprorrateo(Integer idbase) {
        this.idbase = idbase;
    }

    public Baseprorrateo(String nombase, BigDecimal totalbase) {
        this.nombase = nombase;
        this.totalbase = totalbase;
    }
    
    public Baseprorrateo(Integer idbase, String nombase, BigDecimal totalbase) {
        this.idbase = idbase;
        this.nombase = nombase;
        this.totalbase = totalbase;
    }

    public Integer getIdbase() {
        return idbase;
    }

    public void setIdbase(Integer idbase) {
        this.idbase = idbase;
    }

    public String getNombase() {
        return nombase;
    }

    public void setNombase(String nombase) {
        this.nombase = nombase;
    }

    public BigDecimal getTotalbase() {
        return totalbase;
    }

    public void setTotalbase(BigDecimal totalbase) {
        this.totalbase = totalbase;
    }

    @XmlTransient
    public List<Centrodecosto> getCentrodecostoList() {
        return centrodecostoList;
    }

    public void setCentrodecostoList(List<Centrodecosto> centrodecostoList) {
        this.centrodecostoList = centrodecostoList;
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
        hash += (idbase != null ? idbase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baseprorrateo)) {
            return false;
        }
        Baseprorrateo other = (Baseprorrateo) object;
        if ((this.idbase == null && other.idbase != null) || (this.idbase != null && !this.idbase.equals(other.idbase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Baseprorrateo[ idbase=" + idbase + " ]";
    }
    
}
