package com.example.crud.banco.repository;

import com.example.crud.banco.entities.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Clienterepository extends JpaRepository<Cliente, Integer>{
    
    public boolean existsByDui(String dui);
}
