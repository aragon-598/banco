package com.example.crud.banco.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "transaccion", schema = "prueba_consiti_db")
@XmlRootElement
public class Transaccion implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transaccion", nullable = false)
    private Integer idTransaccion;
    @Column(name = "valor_monetario", precision = 2, scale = 10)
    private Double valorMonetario;
    @Column(name = "fecha_transaccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransaccion;
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "numero_cuenta", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta numeroCuenta;
    @JoinColumn(name = "id_tipo_transaccion", referencedColumnName = "id_tipo_transaccion", nullable = false)
    @ManyToOne(optional = false)
    private TipoTransaccion idTipoTransaccion;

    public Transaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Transaccion(Integer idTransaccion, Double valorMonetario, Date fechaTransaccion) {
        this.idTransaccion = idTransaccion;
        this.valorMonetario = valorMonetario;
        this.fechaTransaccion = fechaTransaccion;
    }

    public Transaccion() {
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Double getValorMonetario() {
        return valorMonetario;
    }

    public void setValorMonetario(Double valorMonetario) {
        this.valorMonetario = valorMonetario;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Cuenta getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Cuenta numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoTransaccion getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(TipoTransaccion idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }


}