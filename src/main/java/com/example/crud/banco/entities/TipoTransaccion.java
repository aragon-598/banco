package com.example.crud.banco.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TipoTransaccion
 */
@Entity
@Table(name = "tipo_transaccion", schema = "prueba_consiti_db")
@XmlRootElement
public class TipoTransaccion implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_transaccion", nullable = false)
    private Integer idTipoTransaccion;
    @Column(name = "nombre_transaccion", nullable = false, length = 100)
    private String nombreTransaccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoTransaccion")
    @JsonIgnore
    private List<Transaccion> transaccionList;


    public TipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }


    public TipoTransaccion(Integer idTipoTransaccion, String nombreTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
        this.nombreTransaccion = nombreTransaccion;
    }


    public TipoTransaccion() {
    }


    public Integer getIdTipoTransaccion() {
        return idTipoTransaccion;
    }


    public void setIdTipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }


    public String getNombreTransaccion() {
        return nombreTransaccion;
    }


    public void setNombreTransaccion(String nombreTransaccion) {
        this.nombreTransaccion = nombreTransaccion;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }


    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }
    
}