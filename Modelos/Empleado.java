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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByCodempleado", query = "SELECT e FROM Empleado e WHERE e.codempleado = :codempleado"),
    @NamedQuery(name = "Empleado.findByNomempleado", query = "SELECT e FROM Empleado e WHERE e.nomempleado = :nomempleado"),
    @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido"),
    @NamedQuery(name = "Empleado.findByEmail", query = "SELECT e FROM Empleado e WHERE e.email = :email"),
    @NamedQuery(name = "Empleado.findByIsss", query = "SELECT e FROM Empleado e WHERE e.isss = :isss"),
    @NamedQuery(name = "Empleado.findByAfp", query = "SELECT e FROM Empleado e WHERE e.afp = :afp"),
    @NamedQuery(name = "Empleado.findBySexo", query = "SELECT e FROM Empleado e WHERE e.sexo = :sexo"),
    @NamedQuery(name = "Empleado.findByDirempleado", query = "SELECT e FROM Empleado e WHERE e.dirempleado = :dirempleado"),
    @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleado.findByDui", query = "SELECT e FROM Empleado e WHERE e.dui = :dui"),
    @NamedQuery(name = "Empleado.findByNitempleado", query = "SELECT e FROM Empleado e WHERE e.nitempleado = :nitempleado"),
    @NamedQuery(name = "Empleado.findByEdad", query = "SELECT e FROM Empleado e WHERE e.edad = :edad"),
    @NamedQuery(name = "Empleado.findByFechan", query = "SELECT e FROM Empleado e WHERE e.fechan = :fechan"),
    @NamedQuery(name = "Empleado.findByEstadocivil", query = "SELECT e FROM Empleado e WHERE e.estadocivil = :estadocivil")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codempleado")
    private Integer codempleado;
    @Basic(optional = false)
    @Column(name = "nomempleado")
    private String nomempleado;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "isss")
    private String isss;
    @Column(name = "afp")
    private String afp;
    @Basic(optional = false)
    @Column(name = "sexo")
    private Character sexo;
    @Column(name = "dirempleado")
    private String dirempleado;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "dui")
    private String dui;
    @Column(name = "nitempleado")
    private String nitempleado;
    @Column(name = "edad")
    private Short edad;
    @Column(name = "fechan")
    @Temporal(TemporalType.DATE)
    private Date fechan;
    @Column(name = "estadocivil")
    private Character estadocivil;
    @JoinColumn(name = "idpuesto", referencedColumnName = "idpuesto")
    @ManyToOne(optional = false)
    private Puesto idpuesto;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codempleado")
    private List<Usuario> usuarioList;

    public Empleado() {
    }

    public Empleado(Integer codempleado) {
        this.codempleado = codempleado;
    }

    public Empleado(Integer codempleado, String nomempleado, String apellido, Character sexo) {
        this.codempleado = codempleado;
        this.nomempleado = nomempleado;
        this.apellido = apellido;
        this.sexo = sexo;
    }

    public Integer getCodempleado() {
        return codempleado;
    }

    public void setCodempleado(Integer codempleado) {
        this.codempleado = codempleado;
    }

    public String getNomempleado() {
        return nomempleado;
    }

    public void setNomempleado(String nomempleado) {
        this.nomempleado = nomempleado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsss() {
        return isss;
    }

    public void setIsss(String isss) {
        this.isss = isss;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getDirempleado() {
        return dirempleado;
    }

    public void setDirempleado(String dirempleado) {
        this.dirempleado = dirempleado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNitempleado() {
        return nitempleado;
    }

    public void setNitempleado(String nitempleado) {
        this.nitempleado = nitempleado;
    }

    public Short getEdad() {
        return edad;
    }

    public void setEdad(Short edad) {
        this.edad = edad;
    }

    public Date getFechan() {
        return fechan;
    }

    public void setFechan(Date fechan) {
        this.fechan = fechan;
    }

    public Character getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Character estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Puesto getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(Puesto idpuesto) {
        this.idpuesto = idpuesto;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codempleado != null ? codempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.codempleado == null && other.codempleado != null) || (this.codempleado != null && !this.codempleado.equals(other.codempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Empleado[ codempleado=" + codempleado + " ]";
    }
    
}
