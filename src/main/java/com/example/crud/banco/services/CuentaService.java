package com.example.crud.banco.services;

import java.util.List;

import com.example.crud.banco.entities.Cuenta;
import com.example.crud.banco.repository.CuentaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CuentaService {
    
    public final static Logger logger = LoggerFactory.getLogger(CuentaService.class);

    @Autowired
    CuentaRepository repository;

    public List<Cuenta> getAllCuentasByCliente(int codCliente) {
        
        try {
            List<Cuenta> cuentasByCliente = repository.findCuentasByIdCliente(codCliente);

            if(!cuentasByCliente.isEmpty())
                return cuentasByCliente;
        } catch (Exception e) {
            logger.error("Error en obtener las cuentas del cliente", e);
        }
        
        return null;
    }

    public Cuenta getCuentaById(String numeroCuenta) {
        
        boolean existeCuenta = false;

        try {
            existeCuenta = repository.existsById(numeroCuenta);
            Cuenta cuenta = repository.findById(numeroCuenta).get();

            if(existeCuenta)
                return cuenta;

        } catch (Exception e) {
            logger.error("Error al obtener una cuenta por el numero de cuenta", e);
        }

        return null;
    }

    public void guardarCuenta(Cuenta cuenta) {
        boolean existeCuenta = false;
        try {
            existeCuenta = repository.existsById(cuenta.getNumeroCuenta());

            if(!existeCuenta)
                repository.save(cuenta);

        } catch (Exception e) {
            logger.error("Error en guardar cuenta", e);
        }
    }

    public void eliminar(String numeroCuenta) {
        
        try {
            
            if(repository.existsById(numeroCuenta))
                repository.deleteById(numeroCuenta);

        } catch (Exception e) {
            logger.error("Error al elimnar una cuenta", e);
        }
    }

}
