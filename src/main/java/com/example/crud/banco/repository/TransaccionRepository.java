package com.example.crud.banco.repository;

import java.util.List;

import com.example.crud.banco.entities.Transaccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{
    
    @Query("SELECT t FROM Transaccion t WHERE t.numeroCuenta.numeroCuenta = :numeroCuenta")
    public List<Transaccion> getTransaccionesByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);
}
