package com.example.crud.banco.repository;

import com.example.crud.banco.entities.TipoTransaccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion, Integer>{
    
}
