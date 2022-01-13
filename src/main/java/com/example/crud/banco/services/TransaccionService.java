package com.example.crud.banco.services;

import java.util.List;

import com.example.crud.banco.entities.Transaccion;
import com.example.crud.banco.repository.TransaccionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransaccionService {
    
    private final static Logger logger = LoggerFactory.getLogger(TransaccionService.class);

    @Autowired
    TransaccionRepository repository;

    public List<Transaccion> getTransaccionesByNumeroCuenta(String numeroCuenta) {
        
        try {
            
            List<Transaccion> transaccionsByNumeroCuenta = repository.getTransaccionesByNumeroCuenta(numeroCuenta);

            if(!transaccionsByNumeroCuenta.isEmpty())
                return transaccionsByNumeroCuenta;

        } catch (Exception e) {
            logger.error("Error en obtener las transacciones de una cuenta", e);
        }

        return null;

    }

    public Transaccion getTransaccionById(int idTransaccion) {
        
        boolean existeTransaccion = false;

        try {
            
            existeTransaccion = repository.existsById(idTransaccion);

            if(existeTransaccion)
                return repository.getById(idTransaccion);

        } catch (Exception e) {
            logger.error("Error en obtener transaccion por IF", e);
        }

        return null;

    }

    public void guardarTransaccion(Transaccion transaccion) {
        
        try {
        
            repository.save(transaccion);

        } catch (Exception e) {
            logger.error("Error en guardar transaccion", e);
        }
        
    }
}
