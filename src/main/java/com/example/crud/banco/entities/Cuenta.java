package com.example.crud.banco.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cuenta", schema = "prueba_consiti_db")
@XmlRootElement
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_cuenta", nullable = false, length = 10)
    private String numeroCuenta;
    @Basic(optional = false)
    @Column(name = "nombre_cuenta", nullable = false, length = 50)
    private String nombreCuenta;
    @Column(name = "monto_apertura", precision = 2, scale = 10)
    private Double montoApertura;
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApertura;
    @Column(name = "saldo", precision = 2, scale = 10)
    private Double saldo;
    @Column(name = "estado_cuenta", nullable = false, length = 1)
    private char estadoCuenta;
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", nullable = false)
    @ManyToOne(optional = false)
    private Cliente codCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroCuenta")
    @JsonIgnore
    private List<Transaccion> transaccionList;

    public Cuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Cuenta(String numeroCuenta, String nombreCuenta, Double montoApertura, Date fechaApertura, Double saldo,
            char estadoCuenta, Cliente codCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCuenta = nombreCuenta;
        this.montoApertura = montoApertura;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.estadoCuenta = estadoCuenta;
    }

    public Cuenta() {
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public Double getMontoApertura() {
        return montoApertura;
    }

    public void setMontoApertura(Double montoApertura) {
        this.montoApertura = montoApertura;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public char getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(char estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

}