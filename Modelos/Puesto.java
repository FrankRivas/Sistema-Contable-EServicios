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
@Table(name = "puesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puesto.findAll", query = "SELECT p FROM Puesto p"),
    @NamedQuery(name = "Puesto.findByIdpuesto", query = "SELECT p FROM Puesto p WHERE p.idpuesto = :idpuesto"),
    @NamedQuery(name = "Puesto.findByNompuesto", query = "SELECT p FROM Puesto p WHERE p.nompuesto = :nompuesto"),
    @NamedQuery(name = "Puesto.findByDescripcionpuesto", query = "SELECT p FROM Puesto p WHERE p.descripcionpuesto = :descripcionpuesto"),
    @NamedQuery(name = "Puesto.findBySalario", query = "SELECT p FROM Puesto p WHERE p.salario = :salario")})
public class Puesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpuesto")
    private Integer idpuesto;
    @Basic(optional = false)
    @Column(name = "nompuesto")
    private String nompuesto;
    @Column(name = "descripcionpuesto")
    private String descripcionpuesto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "salario")
    private BigDecimal salario;
    @JoinColumn(name = "idarea", referencedColumnName = "idarea")
    @ManyToOne(optional = false)
    private Area idarea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpuesto")
    private List<Empleado> empleadoList;

    public Puesto() {
    }

    public Puesto(Integer idpuesto) {
        this.idpuesto = idpuesto;
    }

    public Puesto(Integer idpuesto, String nompuesto, BigDecimal salario) {
        this.idpuesto = idpuesto;
        this.nompuesto = nompuesto;
        this.salario = salario;
    }

    public Integer getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(Integer idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getNompuesto() {
        return nompuesto;
    }

    public void setNompuesto(String nompuesto) {
        this.nompuesto = nompuesto;
    }

    public String getDescripcionpuesto() {
        return descripcionpuesto;
    }

    public void setDescripcionpuesto(String descripcionpuesto) {
        this.descripcionpuesto = descripcionpuesto;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Area getIdarea() {
        return idarea;
    }

    public void setIdarea(Area idarea) {
        this.idarea = idarea;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpuesto != null ? idpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puesto)) {
            return false;
        }
        Puesto other = (Puesto) object;
        if ((this.idpuesto == null && other.idpuesto != null) || (this.idpuesto != null && !this.idpuesto.equals(other.idpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Puesto[ idpuesto=" + idpuesto + " ]";
    }
    
}
