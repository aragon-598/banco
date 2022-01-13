package com.example.crud.banco.repository;

import java.util.List;

import com.example.crud.banco.entities.Cuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,String>{
    
    public boolean existsByNumeroCuenta(String numeroCuenta);

    @Query("SELECT c FROM Cuenta c WHERE c.codCliente.codCliente = :codCliente")
    public List<Cuenta> findCuentasByIdCliente(@Param("codCliente") int codCliente);
}
