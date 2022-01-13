package com.example.crud.banco.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cliente", schema = "prueba_consiti_db")
@XmlRootElement
public class Cliente implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_cliente", nullable = false)
    private Integer codCliente;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    @Column(name = "dui", nullable = false, length = 45)
    private String dui;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCliente")
    @JsonIgnore
    private List<Cuenta> cuentaList;

    public Cliente(Integer codCliente, String nombre, String apellidos, String dui, Date fechaRegistro) {
        this.codCliente = codCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dui = dui;
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Cliente() {
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }


    
}