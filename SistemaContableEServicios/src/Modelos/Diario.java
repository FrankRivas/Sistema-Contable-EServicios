/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Merii
 */
@Entity
@Table(name = "diario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diario.findAll", query = "SELECT d FROM Diario d"),
    @NamedQuery(name = "Diario.findByIdregistro", query = "SELECT d FROM Diario d WHERE d.idregistro = :idregistro"),
    @NamedQuery(name = "Diario.findByConcepto", query = "SELECT d FROM Diario d WHERE d.concepto = :concepto"),
    @NamedQuery(name = "Diario.findByFecha", query = "SELECT d FROM Diario d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "Diario.findByEstadodiario", query = "SELECT d FROM Diario d WHERE d.estadodiario = :estadodiario")})
public class Diario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregistro")
    private Integer idregistro;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "estadodiario")
    private Character estadodiario;
    @JoinColumn(name = "idperiodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodocontable idperiodo;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idregistro")
    private List<Detallediario> detallediarioList;

    public Diario() {
    }

    public Diario(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public Diario(Integer idregistro, String concepto, Date fecha, Character estadodiario) {
        this.idregistro = idregistro;
        this.concepto = concepto;
        this.fecha = fecha;
        this.estadodiario = estadodiario;
    }

    public Integer getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getEstadodiario() {
        return estadodiario;
    }

    public void setEstadodiario(Character estadodiario) {
        this.estadodiario = estadodiario;
    }

    public Periodocontable getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Periodocontable idperiodo) {
        this.idperiodo = idperiodo;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public List<Detallediario> getDetallediarioList() {
        return detallediarioList;
    }

    public void setDetallediarioList(List<Detallediario> detallediarioList) {
        this.detallediarioList = detallediarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregistro != null ? idregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diario)) {
            return false;
        }
        Diario other = (Diario) object;
        if ((this.idregistro == null && other.idregistro != null) || (this.idregistro != null && !this.idregistro.equals(other.idregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Diario[ idregistro=" + idregistro + " ]";
    }
    
}
